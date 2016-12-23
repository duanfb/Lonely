package lmc.lonely.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
public class DbParHper extends SQLiteOpenHelper {
	private static int Version = 1;
	
	private static final int START_VERSION = 3;
	private static final int HISTORY_VERSION = 4;

	private static final String TAG = "DbParHper";
	
	private Context con = null;
	public DbParHper(Context con, String dbName, CursorFactory fac, int ver) {
		super(con, dbName, fac, ver);
		this.con = con;
	}
	public DbParHper(Context con, String dbName, int ver) {
		this(con, dbName, null, ver);
		this.con = con;
	}
	public DbParHper(Context con, String dbName) {
		this(con,dbName,Version);
		this.con = con;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table if not exists user(id int,name varchar(20))");
		Toast.makeText(con,"创建数据库",Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Toast.makeText(con,"根据版本号对表结构增删改更新数据库",Toast.LENGTH_SHORT).show();

		Log.i(TAG, "oldVersion:" + oldVersion + "&newVersion:" + newVersion);
		// 数据库更行

		switch (oldVersion) {
			case START_VERSION:// 版本一升级的用户
				// 版本二添加的表
				db.execSQL("操作表SQL语句");
			case HISTORY_VERSION:// 版本二升级的用户
				// 版本三添加表
				db.execSQL("操作表SQL语句");
				// case 增加或修改表
				break;
		}

	
	}
}