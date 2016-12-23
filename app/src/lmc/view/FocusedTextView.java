package lmc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author 段芳彪
 * @version 创建时间：2014-1-4 下午8:50:03
 * 类说明
 */
public class FocusedTextView extends TextView{

	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//让TextView一直获取焦点
	@Override
	public boolean isFocused() {
		return true;
	}

}
