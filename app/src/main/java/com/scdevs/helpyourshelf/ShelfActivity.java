package com.scdevs.helpyourshelf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scdevs.helpyourshelf.DBModels.Book;

import java.util.ArrayList;

public class ShelfActivity extends AppCompatActivity implements BooksRecyclerView.ItemClickListener{

    BooksRecyclerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);

        ArrayList<String> books = new ArrayList<String>();
        Intent i = getIntent();
        books.add(i.getStringExtra("name"));

        RecyclerView recyclerView = findViewById(R.id.booksrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BooksRecyclerView(this, books);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, book_info_activity.class);
        i.putExtra("name", adapter.getItem(position));
        startActivity(i);
    }
}
