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
import android.widget.Toast;

import java.util.ArrayList;



public class ShelvesFragment extends Fragment implements ShelvesRecyclerView.ItemClickListener{
    public static ShelvesFragment newInstance(){
        ShelvesFragment fragment = new ShelvesFragment();
        return fragment;
    }

    ShelvesRecyclerView adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shelves , container, false);


        ArrayList<String> shelfnames = new ArrayList<>();
        shelfnames.add("Home Bookshelf");
        shelfnames.add("School Bookshelf");
        shelfnames.add("Library Bookshelf");
        shelfnames.add("Something Bookshelf");


        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.shelvesrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShelvesRecyclerView(getContext(), shelfnames);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return view;
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

}
