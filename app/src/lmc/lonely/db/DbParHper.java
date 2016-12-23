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
		Toast.makeText(con,"�������ݿ�",Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Toast.makeText(con,"���ݰ汾�ŶԱ�ṹ��ɾ�ĸ������ݿ�",Toast.LENGTH_SHORT).show();

		Log.i(TAG, "oldVersion:" + oldVersion + "&newVersion:" + newVersion);
		// ���ݿ����

		switch (oldVersion) {
			case START_VERSION:// �汾һ�������û�
				// �汾����ӵı�
				db.execSQL("������SQL���");
			case HISTORY_VERSION:// �汾���������û�
				// �汾����ӱ�
				db.execSQL("������SQL���");
				// case ���ӻ��޸ı�
				break;
		}

	
	}
}