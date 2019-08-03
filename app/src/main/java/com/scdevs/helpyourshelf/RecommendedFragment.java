package com.scdevs.helpyourshelf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.scdevs.helpyourshelf.DBModels.Book;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecommendedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecommendedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendedFragment extends Fragment implements BooksRecyclerView.ItemClickListener{

    private ImageView genreRecommendation1;
    private ImageView genreRecommendation2;
    private ImageView genreRecommendation3;
    private ImageView genreRecommendation4;

    private ImageView authorRecommendation1;
    private ImageView authorREcommendation2;
    private ImageView authorRecommendation3;
    private ImageView authorRecommendation4;

    private String genreRecommendationURL1 = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
    private String genreRecommendationURL2 = "http://books.google.com/books/content?id=EOVjmwEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api";
    private String genreRecommendationURL3 = "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg";
    private String genreRecommendationURL4 = "https://hpmedia.bloomsbury.com/rep/s/9781408855898_309038.jpeg";

    private String authorRecommendationURL1 = "http://books.google.com/books/content?id=RFVoAwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api";
    private String authorRecommendationURL2 = "http://books.google.com/books/content?id=EOVjmwEACAAJ&printsec=frontcover&img=1&zoom=5&source=gbs_api";
    private String authorRecommendationURL3 = "https://images-na.ssl-images-amazon.com/images/I/81iqZ2HHD-L.jpg";
    private String authorRecommendationURL4 = "https://hpmedia.bloomsbury.com/rep/s/9781408855898_309038.jpeg";

    BooksRecyclerView adapter;
    public static RecommendedFragment newInstance(){
        RecommendedFragment fragment = new RecommendedFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recommended , container, false);


        ArrayList<String> books = new ArrayList<String>();

        RecyclerView recyclerView = view.findViewById(R.id.recommendedrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksRecyclerView(getContext(), books);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
