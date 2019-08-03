package com.scdevs.helpyourshelf;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;
import com.scdevs.helpyourshelf.BooksAPI.VolumeInfo;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class book_info_activity extends AppCompatActivity {

    BooksInterface booksInterface;
    public TextView title, author, desc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_activity);

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        booksInterface = builder.create(BooksInterface.class);
        title = (TextView) findViewById(R.id.bookTitle);
        author = (TextView) findViewById(R.id.bookAuthor);
        desc = (TextView) findViewById(R.id.bookDescription);

        setBooksName(getIntent().getExtras().get("name").toString());
    }

    public void setBooksName(String titleName)
    {
        Call<BooksResult> call = booksInterface.getBooks(titleName, "AIzaSyBjz1Zdri5qruEOwT3-uRvg613pXtFzFwM");
        call.enqueue(new Callback<BooksResult>() {
            @Override
            public void onResponse(Call<BooksResult> call, Response<BooksResult> response) {
                if (response.isSuccessful())
                {
                    VolumeInfo info = response.body().getItems().get(0).getVolumeInfo();
                    title.setText("Title: " + info.getTitle() + "\n");
                    author.setText("Author(s): " + info.getAuthors().toString().substring(1, info.getAuthors().toString().length() - 1) + "\n");
                    desc.setText("Description: " + info.getDescription());
                }
                else{
                }
            }
            @Override
            public void onFailure(Call<BooksResult> call, Throwable t) {
            }
        });
    }
}
