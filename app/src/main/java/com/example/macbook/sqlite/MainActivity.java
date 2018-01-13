package com.example.macbook.sqlite;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.macbook.sqlite.adapter.BookAdapter;
import com.example.macbook.sqlite.data.DBHandler;
import com.example.macbook.sqlite.model.Books;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView tvStatuts, txtCount;
    private FloatingActionButton btn_add;
    private RecyclerView rv_main;
    private BookAdapter adapterBooks;
    private List<Books> booksList = new ArrayList<>();
    private DBHandler handler;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        toolbar();

    }

    private void initView() {
        tvStatuts = findViewById(R.id.statusData);
        rv_main = findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(this));
        rv_main.setHasFixedSize(true);

        handler = new DBHandler(this);
        booksList = handler.readAllBook();
        adapterBooks = new BookAdapter(booksList);
        rv_main.setAdapter(adapterBooks);

        if (adapterBooks.getItemCount() <= 0 ) {
            tvStatuts.setVisibility(View.VISIBLE);
        }

        btn_add = findViewById(R.id.fab);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });
    }

    private void toolbar() {

        toolBar = findViewById(R.id.toolbar);
        txtCount = findViewById(R.id.txt_toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(R.string.app_name);
        String count = String.valueOf(adapterBooks.getItemCount()) ;
        txtCount.setText(count);
    }
}
