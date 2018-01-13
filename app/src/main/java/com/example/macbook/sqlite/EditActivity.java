package com.example.macbook.sqlite;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macbook.sqlite.data.DBHandler;
import com.example.macbook.sqlite.model.Books;

import java.util.Calendar;

/**
 * Created by MacBook on 1/8/18.
 */

public class EditActivity extends AppCompatActivity {

    private EditText titleBook;
    private EditText authorBook;
    private EditText categoriesBook;
    private EditText releaseBook;
    private DBHandler handler;
    private Books books;
    private Calendar calendar;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        intent = getIntent();
        toolbar();
        initView();

        if (savedInstanceState != null){
            titleBook.setText(savedInstanceState.getString("title"));
            authorBook.setText(savedInstanceState.getString("author"));
            categoriesBook.setText(savedInstanceState.getString("categories"));
            releaseBook.setText(savedInstanceState.getString("release"));
        }
    }

    private void initView() {
        titleBook = findViewById(R.id.edt_titleBook);
        authorBook = findViewById(R.id.edt_authorBook);
        categoriesBook = findViewById(R.id.edt_categoriesBook);
        releaseBook = findViewById(R.id.edt_releaseBook);
        calendar = Calendar.getInstance();

        titleBook.setText(intent.getStringExtra("title"));
        authorBook.setText(intent.getStringExtra("author"));
        categoriesBook.setText(intent.getStringExtra("categories"));
        releaseBook.setText(intent.getStringExtra("release"));
    }

    private void updateBook() {
        int id  = intent.getIntExtra("id", 0);
        handler = new DBHandler(this);
        books = new Books(titleBook.getText().toString(), authorBook.getText().toString(),
                categoriesBook.getText().toString(), releaseBook.getText().toString());
        books.setId(id);
        handler.updateBook(books);
    }

    @SuppressLint("RestrictedApi")
    private void toolbar() {
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setTitle(null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title",titleBook.getText().toString());
        outState.putString("author",authorBook.getText().toString());
        outState.putString("categories",categoriesBook.getText().toString());
        outState.putString("release",releaseBook.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                updateBook();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDatePicker(View view) {
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                prosesDatePickerResult(year, month,day);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void prosesDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        String dateMessage = (day_string + "/" + month_string + "/" + year_string);
        releaseBook.setText(dateMessage);
    }


}
