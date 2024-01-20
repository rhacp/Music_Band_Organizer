package com.anghel.music_band_organizer.services.open_ai;

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
        try {
            URI obj = URI.create(url);
            BufferedReader br = getBufferedReader(prompt, obj);
            String line;

            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader getBufferedReader(String prompt, URI obj) throws IOException {
        //Create the connection passing the url, apiKey. You prepare the call.
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
    }

    private String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;
        int end = response.indexOf("\"", start);

        return response.substring(start, end);
    }
}
