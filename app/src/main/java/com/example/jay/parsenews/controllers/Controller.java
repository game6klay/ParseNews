package com.example.jay.parsenews.controllers;

import android.util.Log;

import com.example.jay.parsenews.models.api.RestApiManager;
import com.example.jay.parsenews.models.pojo.FeedResponse;
import com.example.jay.parsenews.models.pojo.Items;
import com.example.jay.parsenews.models.pojo.Result;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jay on 5/7/17.
 */

public class Controller {
    private static final String TAG = Controller.class.getSimpleName();
    private FeedCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(FeedCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {

        mApiManager.getFeedApi().getItems(new Callback<FeedResponse>() {
            @Override
            public void success(FeedResponse feedResponse, Response response) {
                Log.i(TAG,response.toString());
                Items items =feedResponse.getItems();
                mListener.onFetchProgress(items);
                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }


    public interface FeedCallbackListener {
        void onFetchStart();
        void onFetchProgress(Result result);
        void onFetchProgress(Items items);
        void onFetchComplete();
        void onFetchFailed();
        void onItemClick(int p);
    }

}
