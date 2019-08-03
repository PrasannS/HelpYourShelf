package com.scdevs.helpyourshelf;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.scdevs.helpyourshelf.DBModels.Book;
import com.scdevs.helpyourshelf.DBModels.BookShelf;
import com.scdevs.helpyourshelf.DBModels.BookShelfDao;
import com.scdevs.helpyourshelf.DBModels.DaoSession;
import com.scdevs.helpyourshelf.DBModels.VolumeDao;

import java.util.ArrayList;
import java.util.List;


public class ShelvesFragment extends Fragment implements ShelvesRecyclerView.ItemClickListener{
    public static ShelvesFragment newInstance(){
        ShelvesFragment fragment = new ShelvesFragment();
        return fragment;
    }

    FloatingActionButton fab;

    public DaoSession daoSession;
    BookShelfDao bookshelfDao;
    ShelvesRecyclerView adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_shelves , container, false);

        daoSession = ((App) this.getActivity().getApplication()).getDaoSession();
        bookshelfDao = daoSession.getBookShelfDao();
        ArrayList<String> shelfnames = (ArrayList<String>) getShelfNames();

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.shelvesrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShelvesRecyclerView(getContext(), shelfnames);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        fab = view.findViewById(R.id.addshelf);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

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
        openShelf(position);
    }

    public void openShelf(int a){
        Intent i = new Intent(getActivity(), ShelfActivity.class);
        i.putExtra("name", getShelfNames().get(a));
        startActivity(i);
    }

    public void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Add a Shelf");
        builder.setMessage("Name");

        final EditText input = new EditText(getContext());

        builder.setView(input);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //ShelfActivity.this.finish();
                System.out.println("" + input.getText());
                bookshelfDao.insert(createBookshelf(input.getText().toString()));
                openShelf(getShelfNames().size()-1);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    BookShelf createBookshelf(String name)
    {
        BookShelf shelf = new BookShelf();
        shelf.setName(name);
        return shelf;
    }

}
