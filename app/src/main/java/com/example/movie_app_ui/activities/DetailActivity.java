package com.example.movie_app_ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movie_app_ui.R;
import com.example.movie_app_ui.adapters.ActorListAdapter;
import com.example.movie_app_ui.adapters.DetailMovieCategoryAdapter;
import com.example.movie_app_ui.models.MovieItem;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressBar detailLoading;
    private TextView titleTxt, movieRateTxt, movieLengthTxt, movieSummaryTxt, movieActorTxt;
    private int movieId;
    private ImageView movieImg, backImg, favImg;
    private RecyclerView categoryRV, actorRV;

    private RecyclerView.Adapter actorListAdapter, categoryAdapter;
    private NestedScrollView scrollView;

    private String movieDetailAPI = "https://moviesapi.ir/api/v1/movies/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        movieId = getIntent().getIntExtra("id", 0);
        initView();
        sendRequest();
    }

    private void initView() {
        titleTxt = findViewById(R.id.movieNameTxt);
        detailLoading = findViewById(R.id.movieDetailLoading);
        scrollView = findViewById(R.id.movieDetailScrollView);
        movieImg = findViewById(R.id.movieDetailImage);
        movieRateTxt = findViewById(R.id.evaluateStarTxt);
        movieLengthTxt = findViewById(R.id.movieLengthTxt);
        movieSummaryTxt = findViewById(R.id.movieSummary);
        movieActorTxt = findViewById(R.id.actorTxt);
        backImg = findViewById(R.id.backImg);
        favImg = findViewById(R.id.favImg);
        categoryRV = findViewById(R.id.categoryRV);
        categoryRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        actorRV = findViewById(R.id.actorRV);
        actorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        backImg.setOnClickListener(v -> finish());
    }

    private void sendRequest() {
        requestQueue = Volley.newRequestQueue(this);
        detailLoading.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        stringRequest = new StringRequest(Request.Method.GET, movieDetailAPI + movieId, response -> {
            Gson gson = new Gson();
            detailLoading.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);

            MovieItem item = gson.fromJson(response, MovieItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(movieImg);
            titleTxt.setText(item.getTitle());
            movieRateTxt.setText(item.getImdbRating());
            movieLengthTxt.setText(item.getRuntime());
            movieSummaryTxt.setText(item.getPlot());
            movieActorTxt.setText(item.getActors());
            if (item.getImages() != null) {
                actorListAdapter = new ActorListAdapter(item.getImages());
                actorRV.setAdapter(actorListAdapter);
            }
            if (item.getGenres() != null) {
                categoryAdapter = new DetailMovieCategoryAdapter(item.getGenres());
                categoryRV.setAdapter(categoryAdapter);
            }
        }, error -> {
            detailLoading.setVisibility(View.GONE);
        });
        requestQueue.add(stringRequest);
    }
}