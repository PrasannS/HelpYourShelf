package com.scdevs.helpyourshelf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.scdevs.helpyourshelf.DBModels.DaoMaster;

public class HSOpenHelper extends DaoMaster.OpenHelper {

	public HSOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory){
		super(context,name,factory);
	}
}
