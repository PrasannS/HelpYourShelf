package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.relex.photodraweeview.PhotoDraweeView;

public class book_info_activity extends AppCompatActivity {

    private ImageView imageView;
    private Button toShelf;

    private String url = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_activity);

        PhotoDraweeView drawerView = (PhotoDraweeView) findViewById(R.id.book_image);
        drawerView.setPhotoUri(Uri.parse(url));

        /*
        picaso not working
        String url = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
        Picasso.with(this).load(url).into(imageView); */
    }
}
