package com.gdev.quotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_quotes;
    List<QuotesModal> quotesList;
    QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_quotes = findViewById(R.id.rv_quotes);
        rv_quotes.setHasFixedSize(true);
        rv_quotes.setLayoutManager(new LinearLayoutManager(this));

        // adding the adapter
    }
}