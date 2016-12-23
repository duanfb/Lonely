package lmc.lonely.view;

/**
 * @author andong
 * 监听开关状态的事件接口
 */
public interface OnToggleStateChangedListener {

	/**
	 * 当开关状态改变时回调此方法
	 * @param state 开关当前的状态
	 */
	void onToggleStateChanged(boolean state);
}
