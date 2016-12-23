package lmc.lonely.base;

import lmc.lonely.R;
import lmc.lonely.view.ZHView;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author �η���
 * @version ����ʱ�䣺2014-1-4 ����10:29:14
 * ��˵��
 */
public class BZHView extends Activity {
	private ZHView zuhe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myview_zuhe);
		
		zuhe = (ZHView) findViewById(R.id.setting_update);
		
		zuhe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (zuhe.isChecked()){
					zuhe.setChecked(false);
				}else {
					zuhe.setChecked(true);
				}
			}
		});
		
	}
}
