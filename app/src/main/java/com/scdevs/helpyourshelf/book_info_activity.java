package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class book_info_activity extends AppCompatActivity {

    private ImageView book_image;
    private Button toShelf;

    private String url = "http://books.google.com/books/content?id=EOVjmwEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_activity);

        book_image = findViewById(R.id.book_image);
        Picasso.get().load(url).resize(600,600).into(book_image);

    }
}
