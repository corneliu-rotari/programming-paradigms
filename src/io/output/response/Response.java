package io.output.response;

import app.App;
import components.movie.Movie;
import components.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public final class Response {
    private String error;
    private List<Movie> currentMoviesList;
    private User currentUser;

    /**
     * Response Builder
     * Every propriety is optional
     */
    public static final class Builder {
        private String error = null;
        private List<Movie> currentMoviesList = new ArrayList<>();
        private User currentUser = null;

        /**
         * Places "Error" in the error field
         * @return builder
         */
        public Builder fail() {
            this.error = "Error";
            return this;
        }

        /**
         * Places the current user in Response
         * @return builder object
         */
        public Builder user() {
            this.currentUser = App.getInstance().getCurrentUser();
            return this;
        }

        /**
         * Places the current movie list in the response
         * @return builder object
         */
        public Builder movies() {
            this.currentMoviesList = App.getInstance().getCurrentMovieList();
            return this;
        }

        /**
         * Creates the Response object
         * @return Response object
         */
        public Response build() {
            return new Response(this);
        }

    }

    private Response(final Builder builder) {
        this.error = builder.error;
        this.currentUser = builder.currentUser;
        this.currentMoviesList = builder.currentMoviesList;
    }
}
