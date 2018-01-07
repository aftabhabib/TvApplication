package com.heyzqt.tvapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by heyzqt on 1/7/2018.
 */

public class DetailFragment extends Fragment {

	private static DetailFragment mDetailFragment;

	public static DetailFragment newInstance() {
		if (mDetailFragment == null) {
			mDetailFragment = new DetailFragment();
		}
		return mDetailFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("heyzqt", "onCreateView: ");
		return inflater.inflate(R.layout.fragment_detail, container, false);
	}
}
