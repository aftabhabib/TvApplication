package com.heyzqt.tvapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.heyzqt.tvapplication.entity.Movie;

/**
 * Created by heyzqt on 1/7/2018.
 */

public class MainFragment extends BrowseFragment {

	private final int CARD_WIDTH = 200;

	private final int CARD_HEIGHT = 100;

	private Context mContext;

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setElementUI();

		loadRows();
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mContext = context;
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
		Movie movie1 = new Movie();
		movie1.title = "movie 1";
		movie1.studio = "studio 1";
		movie1.description = "description 1";
		movie1.img = getResources().getDrawable(R.drawable.img1);
		gridItemAdapter.add(movie1);
		gridItemAdapter.add(movie1);
		gridItemAdapter.add(movie1);
//		gridItemAdapter.add("item 1");
//		gridItemAdapter.add("item 2");
//		gridItemAdapter.add("item 3");

		rowsAdapter.add(new ListRow(headerItem, gridItemAdapter));
		rowsAdapter.add(new ListRow(secondItem, gridItemAdapter));
		setAdapter(rowsAdapter);
	}

	public class GridItemAdapter extends Presenter {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent) {
//			TextView textView = new TextView(parent.getContext());
//			textView.setHeight(CARD_HEIGHT);
//			textView.setWidth(CARD_WIDTH);
//			textView.setBackgroundColor(Color.GRAY);
//			textView.setFocusable(true);
//			textView.setGravity(Gravity.CENTER);
			ImageCardView cardView = new ImageCardView(parent.getContext());
			cardView.setMinimumWidth(CARD_WIDTH);
			cardView.setMinimumHeight(CARD_HEIGHT);
			cardView.setFocusable(true);
			return new ViewHolder(cardView);
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, Object item) {
//			TextView textView = (TextView) viewHolder.view;
//			textView.setText(item.toString());

			final Movie movie = (Movie) item;
			ImageCardView cardView = (ImageCardView) viewHolder.view;
			cardView.setMainImage(movie.img);
			cardView.setTitleText(movie.title);
			cardView.setContentText(movie.studio);

			cardView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(mContext, movie.title, Toast.LENGTH_SHORT).show();
				}
			});
		}

		@Override
		public void onUnbindViewHolder(ViewHolder viewHolder) {

		}
	}
}
