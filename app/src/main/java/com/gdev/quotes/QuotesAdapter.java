package com.gdev.quotes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {

    List<ResultsModal> quotesList;

    public QuotesAdapter(List<ResultsModal> quotesList) {
        this.quotesList = quotesList;
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        holder.content.setText(quotesList.get(position).getContent());
        holder.author.setText(quotesList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    class QuotesViewHolder extends RecyclerView.ViewHolder{

        TextView author, content;

        public QuotesViewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.author);
            content = itemView.findViewById(R.id.content);
        }
    }

}
