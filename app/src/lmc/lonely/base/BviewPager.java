package lmc.lonely.base;

import java.util.ArrayList;
import java.util.List;

import lmc.lonely.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class BviewPager extends Activity implements OnPageChangeListener {

    private List<ImageView> imageViews;
	private TextView tvDescription;
	private String[] imageDescriptionArrays;
	private int previousPosition = 0;
	private LinearLayout llPoints;	// 点的父控件
	private ViewPager mViewPager;
	private boolean isLooper = true;
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 每两秒钟执行一下此方法, 切换一下viewpager
			mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
		}
	};

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_viewpager);
 
        init();
        
        new Thread(new Runnable() {

			@Override
			public void run() {
				while(isLooper) {
					// 睡3秒
					SystemClock.sleep(3000);
					// 切换ViewPager
					handler.sendEmptyMessage(0);
				}
			}}).start();
    }
	
    @Override
	protected void onDestroy() {
    	isLooper = false;
		super.onDestroy();
	}

	private void init() {
    	mViewPager = (ViewPager) findViewById(R.id.base_viewPager);
    	tvDescription = (TextView) findViewById(R.id.tv_description);
    	llPoints = (LinearLayout) findViewById(R.id.ll_points);
    	
    	int[] imageResIDs = getImageResIDs(); // 图片的资源id数组
    	imageDescriptionArrays = getImageDescriptionArray();
    	
    	imageViews = new ArrayList<ImageView>();
    	
    	ImageView iv;
    	View view;
    	LayoutParams lp;
    	for (int i = 0; i < imageDescriptionArrays.length; i++) {
			// 创建一个ImageView, 并且添加到集合中
    		iv = new ImageView(this);
    		iv.setBackgroundResource(imageResIDs[i]);
    		imageViews.add(iv);
    		
    		// 把对应的点, 添加到线性布局中
    		view = new View(this);
    		lp = new LayoutParams(10, 10);
    		lp.leftMargin = 5;
			view.setLayoutParams(lp);
    		view.setBackgroundResource(R.drawable.point_background);
    		view.setEnabled(false);
    		llPoints.addView(view);
		}
    	
    	
    	// 填充数据
    	mViewPager.setAdapter(new MyPagerAdapter());
    	mViewPager.setOnPageChangeListener(this);
    	
    	// 得到余数
    	int m = (Integer.MAX_VALUE / 2) % imageViews.size();
    	
    	int item = (Integer.MAX_VALUE / 2) - m;
    	mViewPager.setCurrentItem(item);
    	
    	tvDescription.setText(imageDescriptionArrays[0]);
    	llPoints.getChildAt(previousPosition).setEnabled(true);
	}
    
    class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		/**
		 * 复用View对象
		 * true 复用
		 * false 使用新的
		 */
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		/**
		 * 根据给定索引, 销毁掉View
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// 10000 % 5 = 0;
			container.removeView(imageViews.get(position % imageViews.size()));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(imageViews.get(position % imageViews.size()));
			return imageViews.get(position % imageViews.size());
		}
    }

	private int[] getImageResIDs() {
    	return new int[] {
    			R.drawable.a,
    			R.drawable.b,
    			R.drawable.c,
    			R.drawable.d,
    			R.drawable.e
    	};
    }
    
    private String[] getImageDescriptionArray() {
    	return new String[] {
    			"巩俐不低俗，我就不能低俗",
    			"扑树又回来啦！再唱经典老歌引万人大合唱",
    			"揭秘北京电影如何升级",
    			"乐视网TV版大派送",
    			"热血屌丝的反杀"
    	};
    }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 当ViewPager图片切换时回调
	 */
	@Override
	public void onPageSelected(int position) {
		// 刷新下边的描述信息和切换点的状态
		
		// 取余之后的position
		int newPosition = position % imageViews.size();
		
		tvDescription.setText(imageDescriptionArrays[newPosition]);
		
		llPoints.getChildAt(previousPosition).setEnabled(false);
		llPoints.getChildAt(newPosition).setEnabled(true);
		previousPosition = newPosition;
	}
}
