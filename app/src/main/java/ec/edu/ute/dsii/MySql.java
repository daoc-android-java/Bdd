package ec.edu.ute.dsii;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySql extends SQLiteOpenHelper {

	public MySql(Context context) {
		super(context, "test_android.db", null, 1);
	}

	//https://www.sqlite.org/datatype3.html
		
	@Override
	public void onCreate(SQLiteDatabase database) {
		String sql = "create table usuarios (id integer primary key autoincrement, usr text, pwd text);";
		database.execSQL(sql);//ejecuta la instrucción SQL en la base
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS usuarios");
		onCreate(database);
	}

}
