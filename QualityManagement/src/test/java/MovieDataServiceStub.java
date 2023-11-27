import at.ac.tuwien.qs.movierental.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MovieDataServiceStub implements MovieDataService {

    private boolean serviceAvailable;

    public MovieDataServiceStub() {
        this.serviceAvailable = true;
    }

    public void setServiceAvailable(boolean serviceAvailable) {
        this.serviceAvailable = serviceAvailable;
    }

    @Override
    public List<Movie> searchMovies(Movie movie) throws ServiceNotAvailableException {
        if (!serviceAvailable) {
            throw new ServiceNotAvailableException("Der Service ist derzeit nicht verfügbar.");
        }

        if (movie.getTitle().equals("Bambi")) {

            Movie movieBambi = new Movie();
            movieBambi.setTitle("Bambi");
            movieBambi.setRating(3.5f);

            if (movie.getYearPublished() != null) {
                return List.of(movieBambi);
            } else {
                return List.of(movieBambi , movieBambi);
            }

        } else {
            return new ArrayList<>();
        }

    }
}
