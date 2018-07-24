package cl.vero.moviestowatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cl.vero.moviestowatch.models.Movie;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    public static final String MOVIE_ID = "cl.vero.moviestowatch.KEY.MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.nameMovieEt);
        Button saveBtn = findViewById(R.id.saveBtn);
        Button button = findViewById(R.id.secondBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String movieNameUser = editText.getText().toString();
                if (movieNameUser.trim().length()>0){
                    Movie movie = new Movie(movieNameUser,false);
                    movie.save();
                    Log.d("TAG", movie.getName());
                    movies = getMovies();
                    editText.setText(" ");
                    Toast.makeText(MainActivity.this, "Película guardada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Escribe el nombre de una película", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sizeList = movies.size();
                if (sizeList > 0){
                    Movie lastMovie = movies.get(sizeList-1);
                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    intent.putExtra(MOVIE_ID,lastMovie.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "No hay películas", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }



    private List<Movie> getMovies(){
        return Movie.find(Movie.class, "watched = 0");
    }


    @Override
    protected void onResume() {
        super.onResume();
        movies = getMovies();
    }
}
