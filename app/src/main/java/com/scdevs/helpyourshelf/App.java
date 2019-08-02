package com.scdevs.helpyourshelf;

import android.app.Application;

import com.scdevs.helpyourshelf.DBModels.DaoMaster;
import com.scdevs.helpyourshelf.DBModels.DaoSession;

import org.greenrobot.greendao.database.Database;

public class App extends Application {

	HSOpenHelper helper;
	Database db;
	DaoMaster daoMaster;
	DaoSession daoSession;

	@Override
	public void onCreate() {

		super.onCreate();
		helper = new HSOpenHelper(this, "helpyourselfdb",null);
		db = helper.getWritableDb();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();

	}

	public DaoSession getDaoSession(){
		return daoSession;
	}
}
