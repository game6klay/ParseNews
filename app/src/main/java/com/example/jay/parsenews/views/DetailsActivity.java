package com.example.jay.parsenews.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.parsenews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 5/8/17.
 */

public class DetailsActivity extends AppCompatActivity {

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    private static final String EXTRA_LINK = "EXTRA_LINK";
    private static final String EXTRA_PUBLISH_AT = "EXTRA_PUBLISH_AT";
    private static final String EXTRA_SUMMARY = "EXTRA_SUMMARY";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @BindView(R.id.detail_title)
    TextView tvTitle;

    @BindView(R.id.detail_type)
    TextView tvType;

    @BindView(R.id.detail_link)
    TextView tvLink;

    @BindView(R.id.detail_publish_at)
    TextView tvPublishAt;

    @BindView(R.id.detail_summary)
    TextView tvSummary;

    @BindView(R.id.detail_image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ButterKnife.bind(this);

        tvTitle.setText(extras.getString(EXTRA_TITLE));
        tvType.setText(extras.getString(EXTRA_TYPE));
        tvLink.setText(extras.getString(EXTRA_LINK));
        tvPublishAt.setText(extras.getString(EXTRA_PUBLISH_AT));
        tvSummary.setText(extras.getString(EXTRA_SUMMARY));

        Picasso.with(this)
                .load(extras.getString(EXTRA_IMAGE_RES_ID))
                .centerCrop()
                .fit()
                .into(imageView);

    }
}
