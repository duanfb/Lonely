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
 * @author �η���
 * @version ����ʱ�䣺2014-1-4 ����8:15:50
 * ��˵��
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

		private  String[] names = { "�ֻ�����", "ͨѶ��ʿ", "�������", "���̹���",
				"����ͳ��", "�ֻ�ɱ��", "ϵͳ�Ż�", "�߼�����", "ϵͳ����" };
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
			// �Ѻÿ��Ĳ��� ת���� view����.
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
