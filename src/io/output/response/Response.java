package io.output.response;

import app.App;
import components.movie.Movie;
import components.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Response {
    @Setter @Getter private String error;
    @Setter @Getter private List<Movie> currentMoviesList;
    @Setter @Getter private User currentUser;
//    public static final String SUCCESS = "success";
//    public static final String FAIL = "fail";

    public static class Builder {
        private String error = null;
        private List<Movie> currentMoviesList = new ArrayList<>();
        private User currentUser = null;

        public Builder fail() {
            this.error = "Error";
            return this;
        }

        public Builder user() {
            this.currentUser = App.getInstance().getCurrentUser();
            return this;
        }

        public Builder movies() {
            App app = App.getInstance();
            if (this.currentUser != null) {
                this.currentMoviesList = app.getCurrentMovieList();
            }
            return this;
        }

        public Builder movies(List<Movie> currentMoviesList) {
            this.currentMoviesList = currentMoviesList;
            return this;
        }

        public Response build() {
            return new Response(this);
        }

    }

    private Response(Builder builder) {
        this.error = builder.error;
        this.currentUser = builder.currentUser;
        this.currentMoviesList = builder.currentMoviesList;
    }
}
