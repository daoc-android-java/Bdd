package ec.edu.ute.dsii;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private MySql bdd;
	private SQLiteDatabase database;
	private Cursor cursor;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.tv);

		bdd = new MySql(this);
		database = bdd.getWritableDatabase();
	}

	@Override
	protected void onResume() {
		super.onResume();
		leerRegistros();
	}

	public void crearRegistro(View view) {
		EditText usr = (EditText) findViewById(R.id.usr);
		EditText pwd = (EditText) findViewById(R.id.pwd);
		
		String sql = String.format("insert into usuarios(usr, pwd) values('%s', '%s')",
				usr.getText().toString(), pwd.getText().toString());
		
		database.execSQL(sql);
		
		usr.setText("");
		pwd.setText("");

		leerRegistros();
	}

	//Ejemplifica cómo leer un cursor
	private void leerRegistros() {
	    Cursor cursor = database.rawQuery("select * from usuarios", null);
		String datos = "id; usr; pwd\n";

	    int filas = cursor.getCount();

		for(int i = 0; i < filas; i++) {

			cursor.moveToPosition(i);

			datos += String.format("%d; %s; %s\n",
					cursor.getInt(0),
					cursor.getString(1),
					cursor.getString(2));
		}
		
		cursor.close();

		tv.setText(datos);
	}

	public void irAlView(View view) {
		Intent intent = new Intent(this, LViewActivity.class);
		startActivity(intent);
	}

}
