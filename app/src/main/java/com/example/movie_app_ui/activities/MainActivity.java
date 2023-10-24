package com.example.movie_app_ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.example.movie_app_ui.R;
import com.example.movie_app_ui.adapters.SliderAdapter;
import com.example.movie_app_ui.domain.SliderItems;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter bestMoviesAdapter, upcomingMoviesAdapter, categoryAdapter;
    private RecyclerView bestMoviesRecyclerView, upcomingMoviesRecyclerView, categoryRecyclerView;
    private RequestQueue requestQueue;
    private StringRequest stringBestRequest, stringCategoryRequest, stringUpcomingRequest;
    private ProgressBar bestLoading, categoryLoading, upcomingLoading;
    private ViewPager2 sliderView;
    private Handler slideHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        banners();
    }

    private void initView() {
        sliderView = findViewById(R.id.sliderViewPage);
        bestMoviesRecyclerView = findViewById(R.id.bestView);
        bestMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryRecyclerView = findViewById(R.id.categoryView);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        upcomingMoviesRecyclerView = findViewById(R.id.upcomingView);
        upcomingMoviesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bestLoading = findViewById(R.id.bestProgressBar);
        categoryLoading = findViewById(R.id.categoryProgressBar);
        upcomingLoading = findViewById(R.id.upcomingProgressBar);
    }

    void banners() {
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.wide));
        sliderItems.add(new SliderItems(R.drawable.wide1));
        sliderItems.add(new SliderItems(R.drawable.wide3));

        sliderView.setAdapter(new SliderAdapter(sliderItems, sliderView));
        sliderView.setClipToPadding(false);
        sliderView.setClipChildren(false);
        sliderView.setOffscreenPageLimit(3);
        sliderView.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        sliderView.setPageTransformer(compositePageTransformer);
        sliderView.setCurrentItem(1);
        sliderView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            sliderView.setCurrentItem(sliderView.getCurrentItem() + 1);

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable, 2000);
    }
}