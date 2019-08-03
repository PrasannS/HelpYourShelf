package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


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

        tile1.findViewById(R.id.tile1);
        Picasso.get().load(tile1URL).into(tile1);

        tile2.findViewById(R.id.tile2);
        Picasso.get().load(tile2URL).into(tile2);

        tile3.findViewById(R.id.tile3);
        Picasso.get().load(tile3URL).into(tile3);

        tile4.findViewById(R.id.tile4);
        Picasso.get().load(tile4URL).into(tile4);



    }
}
