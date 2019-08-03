package com.scdevs.helpyourshelf;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scdevs.helpyourshelf.BooksAPI.BooksResult;
import com.scdevs.helpyourshelf.DBModels.Volume;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link RecommendedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendedFragment extends Fragment implements APIClient.responseCallbackListener {

    ArrayList<BookHolder> recommendations;

    public static RecommendedFragment newInstance(){
        RecommendedFragment fragment = new RecommendedFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_recommended , container, false);
        recommendations = new ArrayList<>();
        APIClient client = new APIClient(this, getActivity().getApplication());

        return view;
    }

    public ArrayList<Volume> pareRecommendations()
    {
        ArrayList<Volume> complete = new ArrayList<>();
        Collections.sort(recommendations);
        for (int i = 0; i < Math.min(recommendations.size(), 20); i++)
            complete.add(recommendations.get(i).vol);
        return complete;
    }

    @Override
    public void onCallback(ArrayList<BookHolder> response) {
        for (int i = 0; i < response.size(); i++)
            recommendations.add(response.get(i));
    }

    @Override
    public void onCallback(String s) {

    }
}
