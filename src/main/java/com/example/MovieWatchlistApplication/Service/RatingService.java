package com.example.MovieWatchlistApplication.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingService {

    String apiUrl = "https://www.omdbapi.com/?apikey=a8431cee&t=";

    public String getMovieRating(String title){
         try{
            // try to fetch the rating by calling omdb api
             RestTemplate restTemplate = new RestTemplate();
             //apiUrl + title

             ResponseEntity<ObjectNode> response = restTemplate.getForEntity(apiUrl + title, ObjectNode.class);

             ObjectNode jsonBody = response.getBody();

             return jsonBody.path("imdbRating").asText();
         }catch (Exception ex){
            // to user entered rating will be taken
             System.out.println("Movie name not available or api is down" + ex.getMessage());
             return null;
         }
    }
}
