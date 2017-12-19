package com.lambda.gradle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import static java.lang.Math.toIntExact;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Test extends IMC implements RequestStreamHandler {
    JSONParser parser = new JSONParser();

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        double weight;
        double height;
        String estadoICM;
        String responseCode = "200";

        try {
            JSONObject event = (JSONObject)parser.parse(reader);

            if (event.get("body") != null) {
                JSONObject body = (JSONObject)parser.parse((String)event.get("body"));
                if ( body.get("weight") != null) weight = (double) body.get("weight");
                if ( body.get("height") != null) height = ((Number) body.get("height")).doubleValue();
            }

            IMC imcObject = new IMC();
            double imc = imcObject.getIMC(80,1.70);

            if (imc < 16.00){
                estadoICM = "Infrapeso: Delgadez Severa";
            }else if (imc > 16.00 && imc < 16.99){
                estadoICM = "Infrapeso: Delgadez moderada";
            }else if (imc > 17.00 && imc < 18.49){
                estadoICM = "Infrapeso: Delgadez aceptable";
            }else if (imc > 18.50 && imc < 24.99){
                estadoICM = "Peso Normal";
            }else if (imc > 25.00 && imc < 29.99){
                estadoICM = "Sobrepeso";
            }else if (imc > 30.00 && imc < 34.99){
                estadoICM = "Obeso: Tipo I";
            }else if (imc > 35.00 && imc < 40.00){
                estadoICM = "Obeso: Tipo II";
            }else {
                estadoICM = "Obeso: Tipo III";
            }


            JSONObject responseBody = new JSONObject();
            responseBody.put("input", event.toJSONString());
            responseBody.put("message", estadoICM);

            JSONObject headerJson = new JSONObject();
            headerJson.put("x-custom-response-header", "my custom response header value");

            responseJson.put("statusCode", responseCode);
            responseJson.put("headers", headerJson);
            responseJson.put("body", responseBody.toString());

        } catch(ParseException pex) {
            responseJson.put("statusCode", "400");
            responseJson.put("exception", pex);
        }

        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
        writer.write(responseJson.toJSONString());
        writer.close();
    }

}

