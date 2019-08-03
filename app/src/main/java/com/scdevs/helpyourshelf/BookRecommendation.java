package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import me.relex.photodraweeview.PhotoDraweeView;

public class BookRecommendation extends AppCompatActivity {

    //URL from Samuel (summer reading book)
    private String tile1URL = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\n";
    private String tile2URL = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\n";
    private String tile3URL = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\n";
    private String tile4URL = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api\n";


    private ImageView tile1;
    private ImageView tile2;
    private ImageView tile3;
    private ImageView tile4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_recommendation);

        PhotoDraweeView drawerView1 = (PhotoDraweeView) findViewById(R.id.tile1);
        drawerView1.setPhotoUri(Uri.parse(tile1URL));

        PhotoDraweeView drawerView2 = (PhotoDraweeView) findViewById(R.id.tile2);
        drawerView2.setPhotoUri(Uri.parse(tile2URL));

        PhotoDraweeView drawerView3 = (PhotoDraweeView) findViewById(R.id.tile3);
        drawerView3.setPhotoUri(Uri.parse(tile3URL));

        PhotoDraweeView drawerView4 = (PhotoDraweeView) findViewById(R.id.tile4);
        drawerView4.setPhotoUri(Uri.parse(tile4URL));

    }
}
