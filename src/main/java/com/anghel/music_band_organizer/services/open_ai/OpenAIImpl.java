package com.anghel.music_band_organizer.services.open_ai;

import com.anghel.music_band_organizer.exceptions.open_ai.OpenAIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;

@Service
public class OpenAIImpl implements OpenAI {

    @Value("${api.url}")
    private String url;

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.model}")
    private String model;

    public String chatGPT(String prompt) {
        //Make the call.
        BufferedReader br = getBufferedReader(prompt);

        //Get the entire response as StringBuilder.
        StringBuilder response = getJSONResponseFromBuffer(br);

        //Extract the needed part.
        return extractMessageFromJSONResponse(response.toString());
        }

    public StringBuilder getJSONResponseFromBuffer(BufferedReader br) {
        try {
            String line;

            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            return response;
        } catch (IOException e) {
            throw new OpenAIException("OpenAI Error: not able convert response from the buffer to JSON string.");
        }
    }

    public BufferedReader getBufferedReader(String prompt) {
        try {
            //Create the connection passing the url, apiKey. You prepare the call.
            URI obj = URI.create(url);
            HttpURLConnection connection = (HttpURLConnection) obj.toURL().openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body. Here you pass the model.
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            return new BufferedReader(new InputStreamReader(connection.getInputStream()));

        } catch (IOException e) {
            throw new OpenAIException("OpenAI Error: not able to get the buffered response (make the call).");
        }
    }

    public String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("logprobs", start);

        return response.substring(start, end - 17).replace("\\", "");
    }
}
