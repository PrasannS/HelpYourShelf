package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class BookRecommendation extends AppCompatActivity {

    //URL from Samuel (summer reading book)
    private String tile1URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile2URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile3URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";
    private String tile4URL = "https://cdn.vox-cdn.com/thumbor/LPFPz-pGRHhIVmxVCgG9C9uJdPg=/0x0:2040x1360/1200x800/filters:focal(858x574:1184x900)/cdn.vox-cdn.com/uploads/chorus_image/image/64020108/acastro_190322_1777_apple_streaming_0003.0.jpg";


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
