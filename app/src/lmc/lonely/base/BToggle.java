package lmc.lonely.base;


import lmc.lonely.R;
import lmc.lonely.view.OnToggleStateChangedListener;
import lmc.lonely.view.ToggleButton;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
/**
 * @author 段芳彪
 * @version 创建时间：2014-1-4 下午1:49:17
 * 类说明
 */
public class BToggle extends Activity implements OnToggleStateChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myview_toggle);
        
        ToggleButton mToggleButton = (ToggleButton) findViewById(R.id.togglebutton);
        mToggleButton.setImageResIDs(R.drawable.switch_background, R.drawable.slide_button_background);
        
        mToggleButton.setOnToggleStateChangedListener(this);
    }

	@Override
	public void onToggleStateChanged(boolean state) {
		if(state) {
			Toast.makeText(this, "开关开启", 0).show();
		} else {
			Toast.makeText(this, "开关关闭", 0).show();
		}
	}
}
