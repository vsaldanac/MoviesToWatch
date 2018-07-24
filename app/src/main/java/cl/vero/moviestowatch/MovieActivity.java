package cl.vero.moviestowatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import cl.vero.moviestowatch.models.Movie;

public class MovieActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        long id = getIntent().getLongExtra(MainActivity.MOVIE_ID,0);
        movie = Movie.findById(Movie.class,id);


        TextView textView = findViewById(R.id.movieTv);
        checkBox = findViewById(R.id.watchedCb);

        getSupportActionBar().setTitle(movie.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        movie.setWatched(checkBox.isChecked());
        movie.save();
    }
}
