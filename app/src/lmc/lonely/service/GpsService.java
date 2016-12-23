package lmc.lonely.service;


import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

/**
 * @author NEO
 * @version ����ʱ�䣺2014-1-4 ����11:27:13 ��˵��
 */
public class GpsService extends Service {
	private LocationManager lm;// λ�ù�����
	private MyListener listener;
	private SharedPreferences sp;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sp = getSharedPreferences("config", MODE_PRIVATE);
		// ��λ�ֻ�λ��
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		// λ���ṩ�ߵĲ�ѯ����
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// ����
		criteria.setAltitudeRequired(true);
		// ���в�������
		criteria.setCostAllowed(true);
		// �Ƿ�ѵ�.
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		// ͬʱ�����豸���ƶ��ٶ�
		criteria.setSpeedRequired(true);
		String provider = lm.getBestProvider(criteria, true);
		
		listener = new MyListener();
		lm.requestLocationUpdates(provider, 0, 0, listener);
		
	}
	
	private class MyListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			String accuracy = "accuracy:" + location.getAccuracy();
			String latitude = "latitude:" + location.getLatitude();
			String longitude = "longitude:" + location.getLongitude();
			// ������. ���һ�θ��µ����ֻ���λ��.
			Editor editor = sp.edit();
			editor.putString("lastposition", accuracy + "\n" + latitude + "\n"
					+ longitude);
			editor.commit();
		}

		// ��״̬�仯 ���� λ���ṩ�� �ӿ���->������ ������->����
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		// ������->����
		@Override
		public void onProviderEnabled(String provider) {

		}

		// ����->������
		@Override
		public void onProviderDisabled(String provider) {

		}

	}


	@Override
	public void onDestroy() {
		lm.removeUpdates(listener);
		listener = null;
	}
}
