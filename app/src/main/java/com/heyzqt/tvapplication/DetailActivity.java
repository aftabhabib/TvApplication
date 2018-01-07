package com.heyzqt.tvapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by heyzqt on 1/7/2018.
 */

public class DetailActivity extends FragmentActivity {

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		FragmentManager fragmentManager = getSupportFragmentManager();
		DetailFragment detailFragment = DetailFragment.newInstance();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(R.id.detail_frame, detailFragment);
		transaction.commit();
	}
}
