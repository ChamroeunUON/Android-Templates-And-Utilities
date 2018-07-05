package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.R;

import org.alfonz.utility.NetworkUtility;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_recycler, container, false);
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// pull to refresh
		setupSwipeRefreshLayout();

		// progress popup
		showProgress(isProgress());
	}

	@Override
	public void onRefresh() {
		refreshData();

//		// testing task
//		showProgress(true);
//		new AsyncTask<Void, Void, Void>()
//		{
//			@Override
//			protected Void doInBackground(Void... params)
//			{
//				try
//				{
//					// TODO: do something
//					Thread.sleep(2000);
//				}
//				catch(InterruptedException e)
//				{
//					e.printStackTrace();
//				}
//				return null;
//			}
//
//			@Override
//			protected void onPostExecute(Void result)
//			{
//				super.onPostExecute(result);
//				showProgress(false);
//			}
//		}.execute();
	}

	public void refreshData() {
		if (NetworkUtility.isOnline(getActivity())) {
			// show progress popup
			showProgress(true);

			// TODO
		} else {
			showProgress(false);
			Toast.makeText(getActivity(), R.string.global_network_offline, Toast.LENGTH_LONG).show();
		}
	}

	private void showProgress(boolean visible) {
		// show pull to refresh progress bar
		SwipeRefreshLayout contentSwipeRefreshLayout = mRootView.findViewById(R.id.container_content_swipeable);
		SwipeRefreshLayout offlineSwipeRefreshLayout = mRootView.findViewById(R.id.container_offline_swipeable);
		SwipeRefreshLayout emptySwipeRefreshLayout = mRootView.findViewById(R.id.container_empty_swipeable);

		contentSwipeRefreshLayout.setRefreshing(visible);
		contentSwipeRefreshLayout.setEnabled(!visible);

		offlineSwipeRefreshLayout.setRefreshing(visible);
		offlineSwipeRefreshLayout.setEnabled(!visible);

		emptySwipeRefreshLayout.setRefreshing(visible);
		emptySwipeRefreshLayout.setEnabled(!visible);

		setProgress(visible);
	}

	private void setupSwipeRefreshLayout() {
		SwipeRefreshLayout contentSwipeRefreshLayout = mRootView.findViewById(R.id.container_content_swipeable);
		SwipeRefreshLayout offlineSwipeRefreshLayout = mRootView.findViewById(R.id.container_offline_swipeable);
		SwipeRefreshLayout emptySwipeRefreshLayout = mRootView.findViewById(R.id.container_empty_swipeable);

		contentSwipeRefreshLayout.setColorSchemeResources(R.color.global_color_primary, R.color.global_color_accent);
		contentSwipeRefreshLayout.setOnRefreshListener(this);

		offlineSwipeRefreshLayout.setColorSchemeResources(R.color.global_color_primary, R.color.global_color_accent);
		offlineSwipeRefreshLayout.setOnRefreshListener(this);

		emptySwipeRefreshLayout.setColorSchemeResources(R.color.global_color_primary, R.color.global_color_accent);
		emptySwipeRefreshLayout.setOnRefreshListener(this);
	}

	private boolean isProgress() {
		// TODO
		return false;
	}

	private void setProgress(boolean progress) {
		// TODO
	}
}
