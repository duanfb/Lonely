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
 * @version 创建时间：2014-1-4 下午11:27:13 类说明
 */
public class GpsService extends Service {
	private LocationManager lm;// 位置管理器
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
		// 定位手机位置
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		// 位置提供者的查询条件
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		// 海拔
		criteria.setAltitudeRequired(true);
		// 运行产生费用
		criteria.setCostAllowed(true);
		// 是否费电.
		criteria.setPowerRequirement(Criteria.POWER_HIGH);
		// 同时测试设备的移动速度
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
			// 存起来. 最后一次更新到的手机的位置.
			Editor editor = sp.edit();
			editor.putString("lastposition", accuracy + "\n" + latitude + "\n"
					+ longitude);
			editor.commit();
		}

		// 当状态变化 调用 位置提供者 从可用->不可用 不可用->可用
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		// 不可用->可用
		@Override
		public void onProviderEnabled(String provider) {

		}

		// 可用->不可用
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
