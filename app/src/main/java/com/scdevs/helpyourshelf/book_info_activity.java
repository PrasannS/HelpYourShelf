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

    private String url = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_activity);

        book_image = findViewById(R.id.book_image);
        Picasso.get().load(url).into(book_image);

    }
}
