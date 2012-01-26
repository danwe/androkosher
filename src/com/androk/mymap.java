package com.androk;

import java.io.IOException;
//import java.lang.reflect.Array;
import java.util.List;
import java.util.Locale;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class mymap extends MapActivity  implements LocationListener, OnClickListener {


	MapView map;
	long start;
	long stop;
	MyLocationOverlay compass;
	MapController controller;
	int x, y;
	GeoPoint touchedPoint, rest1, rest2, rest3;
	Drawable d, gpsSign;
	List<Overlay> overlayList;
	LocationManager lm;
	String rest;
	int lat;
	int longi;



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mymap);

		View searchButton=findViewById(R.id.search_button);
		searchButton.setOnClickListener(this);


		map = (MapView) findViewById(R.id.map);
		map.setBuiltInZoomControls(true);

		Touchy t = new Touchy();
		overlayList = map.getOverlays();
		overlayList.add(t);
		compass = new MyLocationOverlay(mymap.this, map);
		overlayList.add(compass);
		controller = map.getController();
		//GeoPoint point = new GeoPoint(31783333, 35216667);

		//controller.animateTo(point);
		//controller.setZoom(19);



		d = getResources().getDrawable(R.drawable.kosherhuman);
		gpsSign = getResources().getDrawable(R.drawable.smiley111);

		//placing pinpoint at location
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();

		rest = lm.getBestProvider(crit, false);
		Location location = lm.getLastKnownLocation(rest);

		
		if (location != null){
			
			lat = (int) location.getLatitude();
			longi = (int) location.getLongitude();
			GeoPoint ourLocation = new GeoPoint(lat, longi);
			OverlayItem overlayItem = new OverlayItem(ourLocation, "wazzap", "wazzap2");
			CustomPinpoint custom = new CustomPinpoint(gpsSign, mymap.this);
			custom.insertPinpoint(overlayItem);
			overlayList.add(custom);
			
			initMyLocation();

		}else{
			Toast.makeText(mymap.this, "Couldn't get provider", Toast.LENGTH_SHORT).show();
		}

		createArray();
		play();
	}

	
	public void onClick(View v)
	{

		Intent i = new Intent(this, Search.class);
		startActivity(i);

	}

	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
		overlay.enableMyLocation();
		//overlay.enableCompass(); // does not work in emulator
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				// Zoom in to current location
				
				
				controller.setZoom(17);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		map.getOverlays().add(overlay);
	}

	public void createArray(){


/*
		Item it1 = new Item(31.772725, 35.189767, "ima","jerusalem");
		Item it2 = new Item(31.774513, 35.191183, "good","jerusalem");

		oncreatepoint(it1);	
		oncreatepoint(it2);
*/
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		compass.disableCompass();
		super.onPause();
		lm.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		compass.enableCompass();
		super.onResume();
		lm.requestLocationUpdates(rest, 500, 1, this);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// Required by MapActivity
		return false;
	}


	class Touchy extends Overlay{
		public boolean onTouchEvent(MotionEvent e, MapView m){
			if (e.getAction() == MotionEvent.ACTION_DOWN){
				start = e.getEventTime();
				x = (int) e.getX();
				y = (int) e.getY();
				touchedPoint = map.getProjection().fromPixels(x, y);

			}
			if (e.getAction()== MotionEvent.ACTION_UP){
				stop = e.getEventTime();
			}
			if (stop - start > 1500){ 
				AlertDialog alert = new AlertDialog.Builder(mymap.this).create();

				alert.setTitle("Pick an option");

				alert.setButton("Place a pinpoint", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						OverlayItem overlayItem = new OverlayItem(touchedPoint, "wazzap", "wazzap2");
						CustomPinpoint custom = new CustomPinpoint(d, mymap.this);
						custom.insertPinpoint(overlayItem);
						overlayList.add(custom);
					}
				});

				alert.setButton2("get address", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
						try{
							List<Address> address = geocoder.getFromLocation(touchedPoint.getLatitudeE6() / 1E6, touchedPoint.getLongitudeE6() / 1E6, 1);
							if (address.size() > 0){
								String display = "";
								for (int i = 0; i<address.get(0).getMaxAddressLineIndex(); i++){
									display += address.get(0).getAddressLine(i) + "\n";
								}
								Toast t3 = Toast.makeText(getBaseContext(), display, Toast.LENGTH_LONG);
								t3.show();
							}
						} catch (IOException e) {
							e.printStackTrace();
						}finally{

						}
					}
				});

				alert.setButton3("Toggle view", new DialogInterface.OnClickListener() {

					@SuppressWarnings("deprecation")
					public void onClick(DialogInterface dialog, int which) {
						if (map.isSatellite()){
							map.setSatellite(false);
							map.setStreetView(true);
						}else{
							map.setStreetView(false);
							map.setSatellite(true);
						}
					}
				});

				alert.show();
			}

			return false;
		}
	}


	public void onLocationChanged(Location l) {
		// TODO Auto-generated method stub
		lat = (int) (l.getLatitude() *1E6);
		longi = (int) (l.getLongitude() *1E6);		
		GeoPoint ourLocation = new GeoPoint(lat, longi);
		OverlayItem overlayItem = new OverlayItem(ourLocation, "wazzap", "wazzap2");

		CustomPinpoint custom = new CustomPinpoint(gpsSign, mymap.this);
		custom.insertPinpoint(overlayItem);
		overlayList.add(custom);
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
	public void oncreatepoint(Item it3) {
		onResume();
		// TODO Auto-generated method stub
		lat = (int) (it3.getx()*1E6);
		longi = (int) (it3.gety()*1E6);		
		GeoPoint resti = new GeoPoint(lat, longi);
		OverlayItem overlayItem = new OverlayItem(resti, it3.getrest(), it3.getcity());


		CustomPinpoint custom = new CustomPinpoint(d, mymap.this);
		custom.insertPinpoint(overlayItem);
		overlayList.add(custom);
		onPause();
	}
	public void play(){
		Item it1 = new Item(31.772725, 35.189767, "ima","jerusalem");
		Item it2 = new Item(31.774513, 35.191183, "good","jerusalem");

		oncreatepoint(it1);	
		oncreatepoint(it2);
	}
}
