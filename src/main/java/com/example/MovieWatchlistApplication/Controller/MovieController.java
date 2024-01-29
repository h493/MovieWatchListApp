package com.example.MovieWatchlistApplication.Controller;

import com.example.MovieWatchlistApplication.Entity.Movie;
import com.example.MovieWatchlistApplication.Service.DatabaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private DatabaseService databaseService;

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id){
            String viewName = "watchlistItemForm";

            Map<String, Object> model = new HashMap<>();


            if(id == null){
                model.put("watchlistItem", new Movie());
            }else{
                model.put("watchlistItem", databaseService.getMovieById(id));
            }
//            Movie movie = new Movie();
//            movie.setComment("Comment krne layak nhi h");
//            movie.setPriority("LOW");
//            movie.setRating(0);
//            movie.setTitle("Suryavansham");
//             model.put("watchlistItem", movie);



            return new ModelAndView(viewName, model);
    }


    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            // if error is there, redisplay the form and let user enter again

            return new ModelAndView("watchlistItemForm");
        }

        Integer id = movie.getId();
        if(id == null){
            databaseService.create(movie);
        }else{
            databaseService.update(movie, id);
        }


        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(redirectView);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){
        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<>();
        List<Movie> movieList = databaseService.getAllMovies();
        model.put("watchlistrows", movieList);
        model.put("noofmovies", movieList.size());
        return new ModelAndView(viewName, model);
    }
}
