package com.heyzqt.tvapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
		movie1.resId = R.drawable.img1;
		Movie movie2 = new Movie();
		movie2.title = "movie 2";
		movie2.studio = "studio 2";
		movie2.description = "description 2";
		movie2.resId = R.drawable.img2;
		Movie movie3 = new Movie();
		movie3.title = "movie 3";
		movie3.studio = "studio 3";
		movie3.description = "description 3";
		movie3.resId = R.drawable.img3;
		gridItemAdapter.add(movie1);
		gridItemAdapter.add(movie2);
		gridItemAdapter.add(movie3);

		rowsAdapter.add(new ListRow(headerItem, gridItemAdapter));
		rowsAdapter.add(new ListRow(secondItem, gridItemAdapter));
		setAdapter(rowsAdapter);
	}

	public class GridItemAdapter extends Presenter {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent) {
			ImageCardView cardView = new ImageCardView(parent.getContext());
			cardView.setMinimumWidth(CARD_WIDTH);
			cardView.setMinimumHeight(CARD_HEIGHT);
			cardView.setFocusable(true);
			return new ViewHolder(cardView);
		}

		@Override
		public void onBindViewHolder(ViewHolder viewHolder, Object item) {
			final Movie movie = (Movie) item;
			ImageCardView cardView = (ImageCardView) viewHolder.view;

			//set the image to a 300x200 bitmap
			Bitmap bitmap = decodeImageToBitmap(mContext.getResources(), movie.resId, CARD_WIDTH,
					CARD_HEIGHT);

			cardView.setMainImage(new BitmapDrawable(mContext.getResources(), bitmap));
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

		private Bitmap decodeImageToBitmap(Resources res, int resId, int reqWidth, int
				reqHeight) {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(res, resId, options);

			int sample = getDecodeSample(options, reqWidth, reqHeight);
			options.inSampleSize = sample;
			options.inJustDecodeBounds = false;

			return BitmapFactory.decodeResource(res, resId, options);
		}

		private int getDecodeSample(BitmapFactory.Options options, int reqWidth,
				int reqHeight) {
			int height = options.outHeight;
			int width = options.outWidth;
			int sample = 1;

			int halfHeight = height / 2;
			int halfWidth = width / 2;

			while ((halfHeight / sample > reqHeight) && (halfWidth / sample > reqWidth)) {
				sample *= 2;
			}

			return sample;
		}
	}
}
