package com.example.tv_show.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tv_show.R;
import com.example.tv_show.adapters.ImagesliderAdapter;
import com.example.tv_show.databinding.ActivityMainBinding;
import com.example.tv_show.databinding.ActivityTVShowDetailsBinding;
import com.example.tv_show.viewModels.TvShowDetailsViewModel;

import java.util.Locale;

public class TVShowDetailsActivity extends AppCompatActivity {

    private ActivityTVShowDetailsBinding activityTVShowDetailsBinding;
    private TvShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTVShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_t_v_show_details);
        doInitialization();
    }

    private void doInitialization() {
        tvShowDetailsViewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
        activityTVShowDetailsBinding.imageBack.setOnClickListener(view -> {
            onBackPressed();
        });
        getTvShowDetails();
    }

    private void getTvShowDetails() {
        activityTVShowDetailsBinding.setIsLoading(true);
        String tvShowId = String.valueOf(getIntent().getIntExtra("id", -1));
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(this, tvShowDetailsResponse -> {
            activityTVShowDetailsBinding.setIsLoading(false);
            if (tvShowDetailsResponse.getTvShowsDetails() != null) {
                if (tvShowDetailsResponse.getTvShowsDetails().getPictures() != null) {
                    loadImageSlider(tvShowDetailsResponse.getTvShowsDetails().getPictures());
                }
                activityTVShowDetailsBinding.setTvShowImagUrl(
                        tvShowDetailsResponse.getTvShowsDetails().getImagePath()
                );
                activityTVShowDetailsBinding.imageTVShow.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.setDescription(
                        String.valueOf(
                                HtmlCompat.fromHtml(
                                        tvShowDetailsResponse.getTvShowsDetails().getDescription(),
                                        HtmlCompat.FROM_HTML_MODE_LEGACY
                                )
                        )
                );
                activityTVShowDetailsBinding.textDescreption.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.textReadMore.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.textReadMore.setOnClickListener(view -> {
                    if (activityTVShowDetailsBinding.textReadMore.getText().equals("Read More")){
                        activityTVShowDetailsBinding.textDescreption.setMaxLines(Integer.MAX_VALUE);
                        activityTVShowDetailsBinding.textDescreption.setEllipsize(null);
                        activityTVShowDetailsBinding.textReadMore.setText(R.string.readLess);
                    }else{
                        activityTVShowDetailsBinding.textDescreption.setMaxLines(4);
                        activityTVShowDetailsBinding.textDescreption.setEllipsize(TextUtils.TruncateAt.END);
                        activityTVShowDetailsBinding.textReadMore.setText(R.string.read_more);
                    }
                });

                activityTVShowDetailsBinding.setRating(
                        String.format(
                                Locale.getDefault(),
                                "%.2f",
                                Double.parseDouble(tvShowDetailsResponse.getTvShowsDetails().getRating())
                        )
                );

                if (tvShowDetailsResponse.getTvShowsDetails().getGenres() != null){
                    activityTVShowDetailsBinding.setGenre(tvShowDetailsResponse.getTvShowsDetails().getGenres()[0]);
                }else{
                    activityTVShowDetailsBinding.setGenre("N/A");
                }
                activityTVShowDetailsBinding.setRuntime(tvShowDetailsResponse.getTvShowsDetails().getRuntime()+ " min");
                activityTVShowDetailsBinding.divider1.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.divider2.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.layoutMisc.setVisibility(View.VISIBLE);

                activityTVShowDetailsBinding.buttonWebSite.setOnClickListener(view -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(tvShowDetailsResponse.getTvShowsDetails().getUrl()));
                     startActivity(intent);
                });

                activityTVShowDetailsBinding.buttonEpisodes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                activityTVShowDetailsBinding.buttonWebSite.setVisibility(View.VISIBLE);
                activityTVShowDetailsBinding.buttonEpisodes.setVisibility(View.VISIBLE);



                loadBaicktvDetails();
            }
        });

    }

    private void loadImageSlider(String[] sliderImage) {
        activityTVShowDetailsBinding.sliderImager.setOffscreenPageLimit(1);
        activityTVShowDetailsBinding.sliderImager.setAdapter(new ImagesliderAdapter(sliderImage));
        activityTVShowDetailsBinding.sliderImager.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.viewBindingEdge.setVisibility(View.VISIBLE);

        setupSliderIndicator(sliderImage.length);
        activityTVShowDetailsBinding.sliderImager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                 setCurrentSliderIndicator(position);
            }

        });
    }

    private void setupSliderIndicator(int count){
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(8,0,8,0);
        for (int i= 0; i<indicators.length;i++){
            indicators[i]= new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.slider_indicator_intractctive
            ));
            indicators[i].setLayoutParams(layoutParams);
            activityTVShowDetailsBinding.layoutSliderIndicators.addView(indicators[i]);
        }
        activityTVShowDetailsBinding.layoutSliderIndicators.setVisibility(View.VISIBLE);

        setCurrentSliderIndicator(0);

    }

    private void setCurrentSliderIndicator(int position ){
        int cjildCount =activityTVShowDetailsBinding.layoutSliderIndicators.getChildCount();
        for (int i =0; i<cjildCount; i++){

            ImageView imageView= (ImageView) activityTVShowDetailsBinding.layoutSliderIndicators.getChildAt(i);
            if (i==position){
               imageView.setImageDrawable(
                       ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_indicator)
               ) ;

            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.slider_indicator_intractctive)
                );
            }
        }
    }


private void loadBaicktvDetails(){

        activityTVShowDetailsBinding.setTvName(getIntent().getStringExtra("name"));
        activityTVShowDetailsBinding.setNetworkCountryState(getIntent().getStringExtra("network")+"("+ getIntent().getStringExtra("country")+")");
        activityTVShowDetailsBinding.setStatus(getIntent().getStringExtra("status"));
        activityTVShowDetailsBinding.setStartDate(getIntent().getStringExtra("startDate"));
        activityTVShowDetailsBinding.textName.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.textDate.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.textNetworkCountry.setVisibility(View.VISIBLE);
        activityTVShowDetailsBinding.textStatus.setVisibility(View.VISIBLE);
}



}