package com.lambda.gradle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;

import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Test implements RequestStreamHandler {
    JSONParser parser = new JSONParser();

    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {

        LambdaLogger logger = context.getLogger();
        logger.log("Loading Java Lambda handler of ProxyWithStream");


        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject responseJson = new JSONObject();
        String name = "you";
        Long age = 0L;
        String greeting;
        String responseCode = "200";

        try {
            JSONObject event = (JSONObject)parser.parse(reader);

            if (event.get("body") != null) {
                JSONObject body = (JSONObject)parser.parse((String)event.get("body"));
                if ( body.get("name") != null) {
                    name = (String)body.get("name");
                }
                if ( body.get("age") != null) {
                    age = (Long) body.get("age");
                }
            }

            if (age >= 18){
                greeting = "Hello, " + name + "! " + "Your are adult";
            } else {
                greeting = "Hello, " + name + "! " + "Your are younger";
            }


            JSONObject responseBody = new JSONObject();
            responseBody.put("input", event.toJSONString());
            responseBody.put("message", greeting);

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

