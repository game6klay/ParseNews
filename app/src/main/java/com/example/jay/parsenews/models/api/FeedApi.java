package com.example.jay.parsenews.models.api;

import com.example.jay.parsenews.models.pojo.FeedResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jay on 5/7/17.
 */

public interface FeedApi {
    @GET("")
    void getItems(Callback<FeedResponse> responseCallback);
}
