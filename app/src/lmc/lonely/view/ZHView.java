package lmc.lonely.view;


import lmc.lonely.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ZHView extends RelativeLayout {
	private TextView tv_title;
	private TextView tv_description;
	private String desc_on;
	private String desc_off;
	private CheckBox cb;

	//attrs装着attrs.xml中的属性集合
	public ZHView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		//获取属性集合attrs.xml的字段
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.SettingView);
		String title = a.getString(R.styleable.SettingView_title);
		desc_on = a.getString(R.styleable.SettingView_desc_on);
		desc_off = a.getString(R.styleable.SettingView_desc_off);
		tv_title.setText(title);
		if (isChecked()) {
			tv_description.setText(desc_on);
		} else {
			tv_description.setText(desc_off);
		}

		a.recycle();
	}

	private void initView(Context context) {
		//把布局文件转换为View对象
		View view = View.inflate(context, R.layout.myview_zuhe_view, this);//第三个参数是创建的view任谁当爹，this当前的SettingView
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_description = (TextView) view.findViewById(R.id.tv_descrpiton);
		cb = (CheckBox) view.findViewById(R.id.cb_status);
		this.setBackgroundResource(R.drawable.home_item_selector);
	}

	/**
	 * 判断当前控件是否被选中
	 * 
	 * @return
	 */
	public boolean isChecked() {
		return cb.isChecked();
	}
	
	public void setChecked(boolean checked){
		cb.setChecked(checked);
		if(checked){
			tv_description.setText(desc_on);
			tv_description.setTextColor(Color.GREEN);
		}else{
			tv_description.setText(desc_off);
			tv_description.setTextColor(Color.RED);
		}
	}

}
