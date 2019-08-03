package com.scdevs.helpyourshelf;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scdevs.helpyourshelf.CameraFragment;
import com.scdevs.helpyourshelf.RecommendedFragment;
import com.scdevs.helpyourshelf.ShelvesFragment;

public class MainActivity extends AppCompatActivity {
	private Button to_book_info;

	FragmentPagerAdapter adapterViewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		ViewPager viewPager = findViewById(R.id.viewPager);

		adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapterViewPager);

		viewPager.setCurrentItem(1);
	}

	public static class MyPagerAdapter extends FragmentPagerAdapter{

		public MyPagerAdapter(FragmentManager fm){
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position){
				case 0:
					return ShelvesFragment.newInstance();
				case 1:
					return CameraFragment.newInstance();
				case 2:
					return RecommendedFragment.newInstance();
			}

			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}
	}

}
