package com.example.MovieWatchlistApplication.Service;

import com.example.MovieWatchlistApplication.Entity.Movie;
import com.example.MovieWatchlistApplication.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class DatabaseService {
    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private RatingService ratingService;

    public void create(Movie movie){
        String rating = ratingService.getMovieRating(movie.getTitle());
        if(!ObjectUtils.isEmpty(rating)){
            movie.setRating(Float.parseFloat(rating));
        }
        movieRepo.save(movie);
    }

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public Movie getMovieById(Integer id){
        return movieRepo.findById(id).get();
    }

    public void update(Movie movie, Integer id) {

        Movie movieToBeUpdated = getMovieById(id);
        movieToBeUpdated.setTitle(movie.getTitle());
        movieToBeUpdated.setRating(movie.getRating());
        movieToBeUpdated.setPriority(movie.getPriority());
        movieToBeUpdated.setComment(movie.getComment());

        movieRepo.save(movieToBeUpdated);
    }
}
