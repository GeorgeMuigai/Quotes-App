package com.gdev.quotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuotesAPI {
    @GET("quotes")
    Call<List<ResultsModal>> getQuotes();
}
