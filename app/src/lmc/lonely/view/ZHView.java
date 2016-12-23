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

	//attrsװ��attrs.xml�е����Լ���
	public ZHView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		//��ȡ���Լ���attrs.xml���ֶ�
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
		//�Ѳ����ļ�ת��ΪView����
		View view = View.inflate(context, R.layout.myview_zuhe_view, this);//�����������Ǵ�����view��˭������this��ǰ��SettingView
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_description = (TextView) view.findViewById(R.id.tv_descrpiton);
		cb = (CheckBox) view.findViewById(R.id.cb_status);
		this.setBackgroundResource(R.drawable.home_item_selector);
	}

	/**
	 * �жϵ�ǰ�ؼ��Ƿ�ѡ��
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
