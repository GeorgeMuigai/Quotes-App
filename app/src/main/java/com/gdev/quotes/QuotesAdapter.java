package com.gdev.quotes;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesViewHolder> {

    List<ResultsModal> quotesList;
    Context context;

    public QuotesAdapter(List<ResultsModal> quotesList, Context context) {
        this.quotesList = quotesList;
        this.context = context;
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

        // copy the quote to clipboard
        holder.btn_copy.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("Quote", quotesList.get(position).getContent());
            clipboardManager.setPrimaryClip(clipData);

            Toast.makeText(context, "Copied Successfully", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    class QuotesViewHolder extends RecyclerView.ViewHolder{

        TextView author, content;
        Button btn_copy;

        public QuotesViewHolder(@NonNull View itemView) {
            super(itemView);

            author = itemView.findViewById(R.id.author);
            content = itemView.findViewById(R.id.content);
            btn_copy = itemView.findViewById(R.id.btn_copy);
        }
    }

}
