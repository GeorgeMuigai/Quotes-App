package com.gdev.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_quotes;
    List<QuotesModal> quotesList = new ArrayList<>();
    QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_quotes = findViewById(R.id.rv_quotes);
        rv_quotes.setHasFixedSize(true);
        rv_quotes.setLayoutManager(new LinearLayoutManager(this));

        // adding the adapter
        adapter = new QuotesAdapter(quotesList);
        rv_quotes.setAdapter(adapter);

        // Retrofit CLient
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quotable.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuotesAPI quotesAPI = retrofit.create(QuotesAPI.class);

        Call<List<QuotesModal>> quotes = quotesAPI.getQuotes();

        quotes.enqueue(new Callback<List<QuotesModal>>() {
            @Override
            public void onResponse(Call<List<QuotesModal>> call, Response<List<QuotesModal>> response) {
                if(!response.isSuccessful() && response.body() != null)
                {
                    Toast.makeText(MainActivity.this, "We encountered an Error when loading data", Toast.LENGTH_SHORT).show();
                    return;
                }
                quotesList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<QuotesModal>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}