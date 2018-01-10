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
        int edad = 0;
        char genero = 'f';
        double weight =0.0;
        double height = 0.0;
        double normalWeight = 0.0;
        String responseCode = "200";
        final String paramWeight = "weight";
        final String paramHeight = "height";

        try {
            JSONObject event = (JSONObject)parser.parse(reader);

            if (event.get("body") != null) {
                JSONObject body = (JSONObject)parser.parse((String)event.get("body"));
                if ( body.get(paramWeight) != null) weight = ((Number) body.get(paramWeight)).doubleValue();
                if ( body.get(paramHeight) != null) height = ((Number) body.get(paramHeight)).doubleValue();
                if ( body.get(edad) != null) edad = ((Integer) body.get(edad));
                if ( body.get(genero) != null) genero = ((Character) body.get(genero));
            }

            IMC imcObject = new IMC();
            double imc = imcObject.getIMC(weight,height);

            StateFactory stateFactory = new StateFactory();
            State stateIMC = stateFactory.getState(imc);
            String state = stateIMC.get();
            normalWeight = getNormalWeight(weight,height);

            IGCFactory igcFactory = new IGCFactory();
            double igc = igcFactory.getIGC(imc, genero, edad);

            JSONObject responseBody = new JSONObject();
            responseBody.put("input", event.toJSONString());
            responseBody.put("state", state);
            responseBody.put(paramWeight, weight);
            responseBody.put(paramHeight, height);
            responseBody.put("pesoMaximo",normalWeight);
            responseBody.put("imc",imc);
            responseBody.put("igc",igc);

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

