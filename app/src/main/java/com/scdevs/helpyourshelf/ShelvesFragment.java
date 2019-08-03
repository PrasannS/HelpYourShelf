package com.scdevs.helpyourshelf;

import android.content.Context;
import android.content.Intent;
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

import com.scdevs.helpyourshelf.DBModels.Book;
import com.scdevs.helpyourshelf.DBModels.BookShelf;
import com.scdevs.helpyourshelf.DBModels.DaoSession;

import java.util.ArrayList;
import java.util.List;


public class ShelvesFragment extends Fragment implements ShelvesRecyclerView.ItemClickListener{
    public static ShelvesFragment newInstance(){
        ShelvesFragment fragment = new ShelvesFragment();
        return fragment;
    }


    public DaoSession daoSession;
    ShelvesRecyclerView adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shelves , container, false);

        daoSession = ((App) getActivity().getApplication()).getDaoSession();
        ArrayList<String> shelfnames = (ArrayList<String>) getShelfNames();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.shelvesrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShelvesRecyclerView(getContext(), shelfnames);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        return view;
    }

    public List<String> getShelfNames(){
        List<BookShelf> bks = daoSession.getBookShelfDao().loadAll();
        List<String> ans = new ArrayList<>();
        for(int i  = 0; i<bks.size();i++){
            ans.add(bks.get(i).getName());
        }
        return ans;
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), ShelfActivity.class);
        i.putExtra("name", adapter.getItem(position));
        startActivity(i);
    }

}
