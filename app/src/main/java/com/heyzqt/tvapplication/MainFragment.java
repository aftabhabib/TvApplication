package com.heyzqt.tvapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by heyzqt on 1/7/2018.
 */

public class MainFragment extends BrowseFragment {

	private final int CARD_WIDTH = 300;

	private final int CARD_HEIGHT = 200;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setElementUI();

		loadRows();
	}

	public void setElementUI() {
		setTitle("hello world");
		setBrandColor(Color.BLUE);
	}

	public void loadRows() {
		ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

		HeaderItem headerItem = new HeaderItem(0, "HEAD 1");
		HeaderItem secondItem = new HeaderItem(1, "HEAD 2");

		ArrayObjectAdapter gridItemAdapter = new ArrayObjectAdapter(new GridItemAdapter());
		gridItemAdapter.add("item 1");
		gridItemAdapter.add("item 2");
		gridItemAdapter.add("item 3");

		rowsAdapter.add(new ListRow(headerItem, gridItemAdapter));
		rowsAdapter.add(new ListRow(secondItem, gridItemAdapter));
		setAdapter(rowsAdapter);
	}

	public class GridItemAdapter extends Presenter {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent) {
			TextView textView = new TextView(parent.getContext());
			textView.setHeight(CARD_HEIGHT);
			textView.setWidth(CARD_WIDTH);
			textView.setBackgroundColor(Color.GRAY);
			textView.setFocusable(true);
			textView.setGravity(Gravity.CENTER);
			return new ViewHolder(textView);
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, Object item) {
			TextView textView = (TextView) viewHolder.view;
			textView.setText(item.toString());
		}

		@Override
		public void onUnbindViewHolder(ViewHolder viewHolder) {

		}
	}
}
