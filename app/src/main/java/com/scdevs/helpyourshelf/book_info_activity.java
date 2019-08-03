package com.scdevs.helpyourshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class book_info_activity extends AppCompatActivity {

    private ImageView imageView;
    private Button toShelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_activity);

        imageView = findViewById(R.id.book_image);

        String url = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        Picasso.with(this).load(url).into(imageView);
    }
}
