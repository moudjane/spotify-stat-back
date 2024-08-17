package com.moudjane.spotifystatback.service;

import com.moudjane.spotifystatback.config.SpotifyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SpotifyService {

    @Autowired
    private SpotifyConfig spotifyConfig;

    public String exchangeCodeForAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://accounts.spotify.com/api/token";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("grant_type", "authorization_code");
        requestBody.put("code", code);
        requestBody.put("redirect_uri", spotifyConfig.getRedirectUri());
        requestBody.put("client_id", spotifyConfig.getClientId());
        requestBody.put("client_secret", spotifyConfig.getClientSecret());

        Map<String, String> response = restTemplate.postForObject(url, requestBody, Map.class);

        return response.get("access_token");
    }
}