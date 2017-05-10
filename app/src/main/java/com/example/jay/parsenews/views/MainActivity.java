package com.example.jay.parsenews.views;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.jay.parsenews.R;
import com.example.jay.parsenews.controllers.Controller;
import com.example.jay.parsenews.models.adapters.FeedAdapter;
import com.example.jay.parsenews.models.pojo.Items;
import com.example.jay.parsenews.models.pojo.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Controller.FeedCallbackListener{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Items items = new Items();
    List<Result> list = items.getResult();
    private FeedAdapter mFeedAdapter;
    private Controller mController;

    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_TITLE = "EXTRA_TITLE";
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    private static final String EXTRA_LINK = "EXTRA_LINK";
    private static final String EXTRA_PUBLISH_AT = "EXTRA_PUBLISH_AT";
    private static final String EXTRA_SUMMARY = "EXTRA_SUMMARY";
    private static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configToolbar();
        mController = new Controller(MainActivity.this);
        configViews();
        mController.startFetching();
    }

    private void configToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configViews() {
        ButterKnife.bind(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());


        mFeedAdapter = new FeedAdapter(this, list);
        mRecyclerView.setAdapter(mFeedAdapter);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark));

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mController.startFetching();
            }
        });
    }


    @Override
    public void onFetchStart() {

    }

    @Override
    public void onFetchProgress(Result result) {
        mFeedAdapter.addItem(result);
    }

    @Override
    public void onFetchProgress(Items items) {
        List<Result> mResults = items.getResult();
        mFeedAdapter.addItems(mResults);
    }

    @Override
    public void onFetchComplete() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFetchFailed() {

    }

    @Override
    public void onItemClick(int p) {

        String name = "NAME";
        Result item = (Result) items.getResult().get(p);
        Intent i = new Intent(this, DetailsActivity.class);
        Log.i(name , item.getTitle());
        Bundle extras = new Bundle();
        extras.putString(EXTRA_TITLE, item.getTitle());
        extras.putString(EXTRA_TYPE, item.getType());
        extras.putString(EXTRA_LINK,  item.getLink());
        extras.putString(EXTRA_PUBLISH_AT, ""+ item.getPublished_at());
        extras.putString(EXTRA_SUMMARY, item.getSummary());
        extras.putString(EXTRA_IMAGE_RES_ID, item.getMain_image().getOriginal_url());
        i.putExtra(BUNDLE_EXTRAS, extras);

        startActivity(i);
    }
}
