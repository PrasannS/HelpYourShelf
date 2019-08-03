package com.scdevs.helpyourshelf;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scdevs.helpyourshelf.BooksAPI.VolumeInfo;
import com.scdevs.helpyourshelf.DBModels.Book;
import com.scdevs.helpyourshelf.DBModels.BookDao;
import com.scdevs.helpyourshelf.DBModels.BookShelfDao;
import com.scdevs.helpyourshelf.DBModels.DaoSession;
import com.scdevs.helpyourshelf.DBModels.Shelf;
import com.scdevs.helpyourshelf.DBModels.Volume;
import com.scdevs.helpyourshelf.DBModels.VolumeDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShelfActivity extends AppCompatActivity implements BooksRecyclerView.ItemClickListener, APIClient.responseCallbackListener{

    //TODO: What is that weird thing btwn books and volumes help pls
    BooksRecyclerView adapter;
    ArrayList<Volume> books = new ArrayList<Volume>();
    public DaoSession daoSession;
    VolumeDao volDao;
    APIClient client;
    public String name;
    public Long id;

    @Override
    public void onCallback(VolumeInfo vi,Long bksid) {
        List<Volume>vols= daoSession.getVolumeDao().queryBuilder().where(VolumeDao.Properties.Title.like(vi.getTitle())).list();
        if(vols.size()!=0) {
            books.add(vols.get(0));
            daoSession.getBookDao().insert(new Book(null,vols.get(0).getID() ,bksid, null, 0.0,false ));
        }
    }

    @Override
    public void onCallback(String s) {

    }

    @Override
    public void onCallback(ArrayList<BookHolder> response) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);


        daoSession = ((App) getApplication()).daoSession;
         name = getIntent().getStringExtra("name");
         id = daoSession.getBookShelfDao().queryBuilder().where(BookShelfDao.Properties.Name.like(name)).list().get(0).getID();
        FloatingActionButton fab = findViewById(R.id.addbook);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        TextView title = findViewById(R.id.shelftitle);
        title.setText(name);


        client = new APIClient(this,getApplicationContext());
        volDao = daoSession.getVolumeDao();
        //QueryBuilder builder = daoSession.getBookDao().queryBuilder().where(BookDao.Properties.BookshelfID.eq(i.getStringExtra("name"));
        //books = builder.list();
        RecyclerView recyclerView = findViewById(R.id.booksrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BooksRecyclerView(this, books);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent i = new Intent(this, book_info_activity.class);
        i.putExtra("name", adapter.getItem(position));
        startActivity(i);
    }

    public void openDialog(){
        //AddBookDialog addBookDialog = new AddBookDialog(this);
        //addBookDialog.show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Add a Book");
        builder.setMessage("Title");

        final EditText input = new EditText(this);

        builder.setView(input);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //ShelfActivity.this.finish();
                System.out.println("" + input.getText());
                client.getBookByTitle(input.getText().toString());
                books.add(new Volume("" + input.getText()));
                //TODO: Query the volume from the book title

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
