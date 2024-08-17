package com.moudjane.spotifystatback.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class SpotifyController {

    @GetMapping("/top-tracks")
    public Map<String, Object> getTopTracks(@AuthenticationPrincipal OAuth2User principal) {
        String accessToken = principal.getAttribute("access_token");

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.spotify.com/v1/me/top/tracks";

        // Créer l'en-tête avec le token d'accès
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);

        return response.getBody();  // Retourne les données des pistes
    }
}