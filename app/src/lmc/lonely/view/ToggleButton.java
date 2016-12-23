package lmc.lonely.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ToggleButton extends View {

	private Bitmap switchBitmap;		// 控件的背景
	private Bitmap slideBtnBitmap;		// 滑动按钮的背景
	
	private boolean toggleButtonCurrentState = false;		// 开关当前的状态 false 代表是关闭
	private int currentX;		// 手指按下的x轴的偏移量
	private boolean isSliding = false;	// 是否正在滑动中, true 是
	
	private OnToggleStateChangedListener mOnToggleStateChangedListener;		// 监听开关状态的接口

	public ToggleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 当view测量宽高时回调
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		// 设定滑动开关的宽和高
		setMeasuredDimension(switchBitmap.getWidth(), switchBitmap.getHeight());
	}

	/**
	 * 当开关需要被绘制时回调
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// 绘制滑动开关的背景
		canvas.drawBitmap(switchBitmap, 0, 0, null);
		
		
		// 绘制滑动按钮的背景
		
		if(isSliding) {		// 正在滑动中
			// 处理滑动中的代码
			int left = currentX - slideBtnBitmap.getWidth() / 2;
			
			if(left < 0) {		// 当前的滑动按钮超出了左边界
				left = 0;
			} else if(left > switchBitmap.getWidth() - slideBtnBitmap.getWidth()) {
				// 当前滑动的按钮超出了右边界
				left = switchBitmap.getWidth() - slideBtnBitmap.getWidth();
			}
			
			canvas.drawBitmap(slideBtnBitmap, left, 0, null);
		} else {
			// 固定滑动按钮的代码
			if(toggleButtonCurrentState) {
				// 绘制开关为开启的状态
				int left = switchBitmap.getWidth() - slideBtnBitmap.getWidth();
				canvas.drawBitmap(slideBtnBitmap, left, 0, null);
			} else {
				// 绘制开关为关闭的状态
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
			
			// 当前的状态, 如果是true 代表是开启状态
			boolean state = currentX > center;
			
			if(state != toggleButtonCurrentState
					&& mOnToggleStateChangedListener != null) {

				toggleButtonCurrentState = state;
				// 回调用户设置的监听事件
				mOnToggleStateChangedListener.onToggleStateChanged(state);
			}
			break;
		default:
			break;
		}
		
		invalidate();		// 刷新界面, 触发onDraw方法
		return true;
	}

	/**
	 * 设置滑动开关的图片资源
	 * @param switchBackground 开关的背景
	 * @param slideButtonBackground 滑动按钮的背景
	 */
	public void setImageResIDs(int switchBackground, int slideButtonBackground) {
		switchBitmap = BitmapFactory.decodeResource(getResources(), switchBackground);
		slideBtnBitmap = BitmapFactory.decodeResource(getResources(), slideButtonBackground);
		
	}

	/**
	 * 获取开关当前的状态
	 * @return
	 */
	public boolean isToggleButtonCurrentState() {
		return toggleButtonCurrentState;
	}

	/**
	 * 设置开关当前的状态
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
