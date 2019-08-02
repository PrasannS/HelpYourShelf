package com.scdevs.helpyourshelf;

import org.greenrobot.greendao.database.Database;

public class App {

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
