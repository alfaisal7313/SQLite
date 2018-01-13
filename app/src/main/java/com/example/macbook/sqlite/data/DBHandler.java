package com.example.macbook.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.transition.Slide;

import com.example.macbook.sqlite.model.Books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MacBook on 1/9/18.
 */

public class DBHandler extends BookDbHelper {

    public DBHandler(Context context) {
        super(context);
    }

    public void insertBook(Books book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLOUMN_TITLE, book.getTitle());
        values.put(BookContract.BookEntry.COLOUMN_AUTHOR, book.getAuthor());
        values.put(BookContract.BookEntry.COLOUMN_CATEGORIES, book.getCategories());
        values.put(BookContract.BookEntry.COLOUMN_RELEASE, book.getRelease());
        db.insert(BookContract.BookEntry.TABLE_NAME, null, values);
        db.close();
    }

    public Books readBook(int id_book){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                BookContract.BookEntry.COLOUMN_TITLE,
                BookContract.BookEntry.COLOUMN_AUTHOR,
                BookContract.BookEntry.COLOUMN_CATEGORIES,
                BookContract.BookEntry.COLOUMN_RELEASE,
        };

        String selection = BookContract.BookEntry._ID + " =?";
        String[] selectionArgs =  {String.valueOf(id_book)};

        Cursor cursor = db.query(
                BookContract.BookEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Books books = new Books(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return books;
    }

    public List<Books> readAllBook(){
        List<Books> booksList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " +BookContract.BookEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Books books = new Books(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                booksList.add(books);
            }
            while (cursor.moveToNext());
        }
        return booksList;
    }

    public int updateBook(Books book){
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLOUMN_TITLE, book.getTitle());
        values.put(BookContract.BookEntry.COLOUMN_AUTHOR, book.getAuthor());
        values.put(BookContract.BookEntry.COLOUMN_CATEGORIES, book.getCategories());
        values.put(BookContract.BookEntry.COLOUMN_RELEASE, book.getRelease());

        String selection = BookContract.BookEntry._ID + " =?" ;
        String[] selectionArgs =  {String.valueOf(book.getId())};

        return db.update(BookContract.BookEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteBook(Books book){
        SQLiteDatabase db = this.getWritableDatabase();
        String selecton = BookContract.BookEntry._ID + " =?";
        String[] selectionArgs =  {String.valueOf(book.getId())};
        db.delete(BookContract.BookEntry.TABLE_NAME, selecton, selectionArgs);

    }

//    public void deleteAllBook(){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        String deleteQuery = "DELETE FROM " +BookContract.BookEntry.TABLE_NAME;
//        db.execSQL(deleteQuery);
//    }
}
