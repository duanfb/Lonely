package lmc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author �η���
 * @version ����ʱ�䣺2014-1-4 ����8:50:03
 * ��˵��
 */
public class FocusedTextView extends TextView{

	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	//��TextViewһֱ��ȡ����
	@Override
	public boolean isFocused() {
		return true;
	}

}
