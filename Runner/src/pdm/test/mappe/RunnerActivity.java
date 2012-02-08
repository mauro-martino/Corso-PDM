package pdm.test.mappe;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RunnerActivity extends MapActivity {
	MapView mapView;
	MyLocationOverlay myLocaltionOverlay;
	RadiusOverlay prima;
	RadiusOverlay seconda;
	RadiusOverlay terza;
	RadiusOverlay quarta;
	PendingIntent mPendingTermini;
	PendingIntent mPendingPiazzadellarepubblica;
	PendingIntent mPendingColosseo;
	PendingIntent mPendingCasaromoloeremo;
	ProxymityBroadcast mProxymityBroadcast;
	LocationManager locationManager;
	GeoPoint termini;
    GeoPoint piazzadellarepubblica;
    GeoPoint colosseo;
    GeoPoint casaromoloeremo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mProxymityBroadcast = new ProxymityBroadcast();
        mapView = (MapView)findViewById(R.id.mapView);
        mapView.setClickable(true);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        myLocaltionOverlay = new MyLocationOverlay(this, mapView);
        termini = new GeoPoint(41901222,12500882);
        piazzadellarepubblica = new GeoPoint(41902622,12495482);
        colosseo = new GeoPoint(41890310,12492410);
        casaromoloeremo = new GeoPoint(41890492,12484823);
        myLocaltionOverlay.runOnFirstFix(new Runnable() {
			
			@Override
			public void run() {
				mapView.getController().animateTo(myLocaltionOverlay.getMyLocation());
				mapView.getController().setZoom(14);
			}
		});
        prima = new RadiusOverlay(termini, 400, android.graphics.Color.BLUE);
        seconda = new RadiusOverlay(piazzadellarepubblica, 300, android.graphics.Color.BLUE);
        terza = new RadiusOverlay(colosseo, 500, android.graphics.Color.BLUE);
        quarta = new RadiusOverlay(casaromoloeremo, 450, android.graphics.Color.BLUE);
        mapView.getOverlays().add(prima);
        mapView.getOverlays().add(seconda);
        mapView.getOverlays().add(terza);
        mapView.getOverlays().add(quarta);
        mapView.getOverlays().add(myLocaltionOverlay);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		myLocaltionOverlay.enableMyLocation();
		locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
		registerReceiver(mProxymityBroadcast, new IntentFilter("pdm.test.mappe"));
		Intent intentTermini = new Intent("pdm.test.mappe");
		intentTermini.putExtra("overlay", 1);
		Intent intentPiazzadellarepublica = new Intent("pdm.test.mappe");
		intentPiazzadellarepublica.putExtra("overlay", 2);
		Intent intentColosseo = new Intent("pdm.test.mappe");
		intentColosseo.putExtra("overlay", 3);
		Intent intentCasaromoloeremo = new Intent("pdm.test.mappe");
		intentCasaromoloeremo.putExtra("overlay", 4);
		mPendingTermini = PendingIntent.getBroadcast(this, 1, intentTermini, PendingIntent.FLAG_CANCEL_CURRENT);
		mPendingPiazzadellarepubblica = PendingIntent.getBroadcast(this, 2, intentPiazzadellarepublica, PendingIntent.FLAG_CANCEL_CURRENT);
		mPendingColosseo = PendingIntent.getBroadcast(this, 3, intentColosseo, PendingIntent.FLAG_CANCEL_CURRENT);
		mPendingCasaromoloeremo = PendingIntent.getBroadcast(this, 4, intentCasaromoloeremo, PendingIntent.FLAG_CANCEL_CURRENT);
		locationManager.addProximityAlert(termini.getLatitudeE6() * 0.000001, termini.getLongitudeE6() * 0.000001, 400, -1, mPendingTermini);
		locationManager.addProximityAlert(piazzadellarepubblica.getLatitudeE6() * 0.000001, piazzadellarepubblica.getLongitudeE6() * 0.000001, 300, -1, mPendingPiazzadellarepubblica);
		locationManager.addProximityAlert(colosseo.getLatitudeE6() * 0.000001, colosseo.getLongitudeE6() * 0.000001, 500, -1, mPendingColosseo);
		locationManager.addProximityAlert(casaromoloeremo.getLatitudeE6() * 0.000001, casaromoloeremo.getLongitudeE6() * 0.000001, 350, -1, mPendingCasaromoloeremo);
	}
	
	@Override
	public void onPause(){
		super.onPause();
		myLocaltionOverlay.disableMyLocation();
		locationManager.removeProximityAlert(mPendingTermini);
		locationManager.removeProximityAlert(mPendingPiazzadellarepubblica);
		locationManager.removeProximityAlert(mPendingColosseo);
		locationManager.removeProximityAlert(mPendingCasaromoloeremo);
		unregisterReceiver(mProxymityBroadcast);
	}
	
	class ProxymityBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Log.d("TAG","Proximity Alert");
			int area = arg1.getIntExtra("overlay", -1);
			Toast.makeText(getApplicationContext(), "Alert di prossimità", Toast.LENGTH_LONG);
			Boolean stoEntrando = arg1.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
			if (stoEntrando) {
				switch (area) {
				case 1:
					Toast.makeText(getApplicationContext(), "Benvenuto a Termini", Toast.LENGTH_SHORT).show();
					prima.setColor(android.graphics.Color.GREEN);
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "Benvenuto a Piazza della Repubblica", Toast.LENGTH_SHORT).show();
					seconda.setColor(android.graphics.Color.GREEN);
					break;
				case 3:
					Toast.makeText(getApplicationContext(), "Benvenuto al Colosseo", Toast.LENGTH_SHORT).show();
					terza.setColor(android.graphics.Color.GREEN);
					break;
				case 4:
					Toast.makeText(getApplicationContext(), "Benvenuto a Casa di Romolo e Remo", Toast.LENGTH_SHORT).show();
					quarta.setColor(android.graphics.Color.GREEN);
					break;
				}
			}
			else {
				Toast.makeText(getApplicationContext(), "Arrivederci", Toast.LENGTH_SHORT).show();
				switch (area) {
				case 1:
					prima.setColor(android.graphics.Color.GRAY);
					break;
				case 2:
					seconda.setColor(android.graphics.Color.GRAY);
					break;
				case 3:
					terza.setColor(android.graphics.Color.GRAY);
					break;
				case 4:
					quarta.setColor(android.graphics.Color.GRAY);
					break;
				}
			}
		}
		
	}
}