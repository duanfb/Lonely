package lmc.lonely.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToggleButton extends View {

	private Bitmap switchBitmap;		// �ؼ��ı���
	private Bitmap slideBtnBitmap;		// ������ť�ı���
	
	private boolean toggleButtonCurrentState = false;		// ���ص�ǰ��״̬ false �����ǹر�
	private int currentX;		// ��ָ���µ�x���ƫ����
	private boolean isSliding = false;	// �Ƿ����ڻ�����, true ��
	
	private OnToggleStateChangedListener mOnToggleStateChangedListener;		// ��������״̬�Ľӿ�

	public ToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * ��view�������ʱ�ص�
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		// �趨�������صĿ�͸�
		setMeasuredDimension(switchBitmap.getWidth(), switchBitmap.getHeight());
	}

	/**
	 * ��������Ҫ������ʱ�ص�
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// ���ƻ������صı���
		canvas.drawBitmap(switchBitmap, 0, 0, null);
		
		
		// ���ƻ�����ť�ı���
		
		if(isSliding) {		// ���ڻ�����
			// �������еĴ���
			int left = currentX - slideBtnBitmap.getWidth() / 2;
			
			if(left < 0) {		// ��ǰ�Ļ�����ť��������߽�
				left = 0;
			} else if(left > switchBitmap.getWidth() - slideBtnBitmap.getWidth()) {
				// ��ǰ�����İ�ť�������ұ߽�
				left = switchBitmap.getWidth() - slideBtnBitmap.getWidth();
			}
			
			canvas.drawBitmap(slideBtnBitmap, left, 0, null);
		} else {
			// �̶�������ť�Ĵ���
			if(toggleButtonCurrentState) {
				// ���ƿ���Ϊ������״̬
				int left = switchBitmap.getWidth() - slideBtnBitmap.getWidth();
				canvas.drawBitmap(slideBtnBitmap, left, 0, null);
			} else {
				// ���ƿ���Ϊ�رյ�״̬
				canvas.drawBitmap(slideBtnBitmap, 0, 0, null);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			isSliding = true;
			currentX = (int) event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			currentX = (int) event.getX();
			break;
		case MotionEvent.ACTION_UP:
			isSliding = false;
			currentX = (int) event.getX();
			
			int center = switchBitmap.getWidth() / 2;
			
			// ��ǰ��״̬, �����true �����ǿ���״̬
			boolean state = currentX > center;
			
			if(state != toggleButtonCurrentState
					&& mOnToggleStateChangedListener != null) {

				toggleButtonCurrentState = state;
				// �ص��û����õļ����¼�
				mOnToggleStateChangedListener.onToggleStateChanged(state);
			}
			break;
		default:
			break;
		}
		
		invalidate();		// ˢ�½���, ����onDraw����
		return true;
	}

	/**
	 * ���û������ص�ͼƬ��Դ
	 * @param switchBackground ���صı���
	 * @param slideButtonBackground ������ť�ı���
	 */
	public void setImageResIDs(int switchBackground, int slideButtonBackground) {
		switchBitmap = BitmapFactory.decodeResource(getResources(), switchBackground);
		slideBtnBitmap = BitmapFactory.decodeResource(getResources(), slideButtonBackground);
		
	}

	/**
	 * ��ȡ���ص�ǰ��״̬
	 * @return
	 */
	public boolean isToggleButtonCurrentState() {
		return toggleButtonCurrentState;
	}

	/**
	 * ���ÿ��ص�ǰ��״̬
	 * @param toggleButtonCurrentState
	 */
	public void setToggleButtonCurrentState(boolean toggleButtonCurrentState) {
		this.toggleButtonCurrentState = toggleButtonCurrentState;
	}

	public void setOnToggleStateChangedListener(
			OnToggleStateChangedListener mOnToggleStateChangedListener) {
		this.mOnToggleStateChangedListener = mOnToggleStateChangedListener;
	}
}
