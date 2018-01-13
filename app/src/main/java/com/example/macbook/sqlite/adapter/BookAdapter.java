package com.example.macbook.sqlite.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbook.sqlite.EditActivity;
import com.example.macbook.sqlite.R;
import com.example.macbook.sqlite.data.DBHandler;
import com.example.macbook.sqlite.model.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook on 1/8/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private List<Books> dataBooks = new ArrayList<>();
    private DBHandler handler;

    public BookAdapter(List<Books> dataBooks) {
        this.dataBooks = dataBooks;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false));
    }

    @Override
    public void onBindViewHolder(final BookHolder holder, final int position) {
            final Books book = dataBooks.get(position);
            holder.title.setText(book.getTitle());
            holder.author.setText(book.getAuthor());
            holder.kategories.setText(book.getCategories());
            holder.release.setText(book.getRelease());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(holder.itemView.getContext(), book);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBooks.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder{
        TextView title, author, kategories, release;
        ImageView imgBook;

        public BookHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleBook);
            author = itemView.findViewById(R.id.author);
            kategories = itemView.findViewById(R.id.categoriesBook);
            release = itemView.findViewById(R.id.releaseBook);
        }
    }

    private void alertDialog(final Context context, final Books books) {
        String[] option = {"View", "Edit", "Detele"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Option");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        Toast.makeText(context, "View", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent edtIntent = new Intent(context, EditActivity.class);
                        edtIntent.putExtra("id", books.getId());
                        edtIntent.putExtra("title", books.getTitle());
                        edtIntent.putExtra("author", books.getAuthor());
                        edtIntent.putExtra("categories", books.getCategories());
                        edtIntent.putExtra("release", books.getRelease());
                        context.startActivity(edtIntent);

                        break;
                    case 2:
                        AlertDialog.Builder dialog= new AlertDialog.Builder(context)
                                .setMessage("Are you sure delete data?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                handler = new DBHandler(context);
                                handler.deleteBook(books);
                                ((Activity)context).finish();
                                context.startActivity(((Activity) context).getIntent());
                            }
                        });
                        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        dialog.show();
                        break;
                }
            }
        });
        builder.show();

    }
}
