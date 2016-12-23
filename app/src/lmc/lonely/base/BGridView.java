package lmc.lonely.base;


import lmc.lonely.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author 段芳彪
 * @version 创建时间：2014-1-4 下午8:15:50
 * 类说明
 */
public class BGridView extends Activity {
	
	private GridView gv_home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_gridview);
		
		gv_home = (GridView) findViewById(R.id.gv_home);
		gv_home.setAdapter(new HomeAdapter(this));
		
	}
	
	class HomeAdapter extends BaseAdapter {
		private Context context;

		public HomeAdapter(Context context) {
			this.context = context;
		}

		private  String[] names = { "手机防盗", "通讯卫士", "软件管理", "进程管理",
				"流量统计", "手机杀毒", "系统优化", "高级工具", "系统设置" };
		private int[] icons = { R.drawable.safe_selector,
				R.drawable.callmsgsafe, R.drawable.app, R.drawable.taskmanager,
				R.drawable.netmanager, R.drawable.trojan, R.drawable.sysoptimize,
				R.drawable.atools, R.drawable.settings };

		@Override
		public int getCount() {
			return names.length;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 把好看的布局 转化成 view对象.
			View view = View.inflate(context, R.layout.list_home_item, null);
			TextView tv_name = (TextView) view.findViewById(R.id.tv_home_name_item);
			ImageView iv_icon = (ImageView) view
					.findViewById(R.id.iv_home_icon_item);
			tv_name.setText(names[position]);
			iv_icon.setImageResource(icons[position]);
			if(position==0){
				SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
				String newname = sp.getString("newname", "");
				if(!TextUtils.isEmpty(newname)){
					tv_name.setText(newname);
				}
			}
			
			
			return view;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
	}

}
