package com.scdevs.helpyourshelf;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;

public class AddBookDialog extends AppCompatDialog {

    public AddBookDialog(Context context) {
        super(context);
    }

    public Dialog OnCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getOwnerActivity());
        builder.setTitle("Add a Book").setMessage("that's right").setPositiveButton("ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){

            }
        });
        return builder.create();
    }
}
