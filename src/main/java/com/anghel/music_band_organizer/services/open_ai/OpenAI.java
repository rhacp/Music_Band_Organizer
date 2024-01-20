package com.anghel.music_band_organizer.services.open_ai;

import java.io.BufferedReader;

public interface OpenAI {

    String chatGPT(String prompt);

    StringBuilder getJSONResponseFromBuffer(BufferedReader br);

    BufferedReader getBufferedReader(String prompt);

    String extractMessageFromJSONResponse(String response);
}
