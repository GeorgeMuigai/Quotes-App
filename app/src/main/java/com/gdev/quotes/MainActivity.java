package com.gdev.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_quotes;
    List<ResultsModal> quotesList;
    QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quotesList = new ArrayList<>();

        rv_quotes = findViewById(R.id.rv_quotes);
        rv_quotes.setHasFixedSize(true);
        rv_quotes.setLayoutManager(new LinearLayoutManager(this));

        // adding the adapter
        adapter = new QuotesAdapter(quotesList);
        rv_quotes.setAdapter(adapter);

        // Retrofit Client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://type.fit/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuotesAPI quotesAPI = retrofit.create(QuotesAPI.class);

        Call<List<ResultsModal>> quotes = quotesAPI.getQuotes();

        quotes.enqueue(new Callback<List<ResultsModal>>() {
            @Override
            public void onResponse(Call<List<ResultsModal>> call, Response<List<ResultsModal>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Error code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                quotesList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ResultsModal>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}