package com.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.R;
import com.example.entity.ProductEntity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;


public class ExampleFragment extends Fragment
{
	private View mRootView;
	private ClusterManager<ProductEntity> mClusterManager;


	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		setupClusterManager();
		bindData();
	}


	private void setupClusterManager()
	{
		mRootView.findViewById(R.id.example_map).getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				mClusterManager = new ClusterManager<>(getActivity(), googleMap);
				mClusterManager.setRenderer(new DefaultClusterRenderer<ProductEntity>(getActivity(), googleMap, mClusterManager)
				{
					@Override
					protected void onBeforeClusterItemRendered(ProductEntity product, MarkerOptions markerOptions)
					{
						BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
						markerOptions.title(product.getName());
						markerOptions.icon(bitmapDescriptor);
						super.onBeforeClusterItemRendered(product, markerOptions);
					}
				});
				googleMap.setOnCameraIdleListener(mClusterManager);
			}
		});
	}


	private void bindData()
	{
		mRootView.findViewById(R.id.example_map).getMapAsync(new OnMapReadyCallback()
		{
			@Override
			public void onMapReady(GoogleMap googleMap)
			{
				for(int i = 0; i < 16; i++)
				{
					ProductEntity product = new ProductEntity();
					product.setName("Example " + i);
					product.setPosition(new LatLng(49.194696 + 0.1 * Math.sin(i * Math.PI / 8), 16.608595 + 0.1 * Math.cos(i * Math.PI / 8)));
					mClusterManager.addItem(product);
				}
			}
		});
	}
}
