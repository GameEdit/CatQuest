package android.application.catquest.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.application.catquest.R;
import android.application.catquest.constant.SQLConstant;
import android.application.catquest.dao.DBAccessOpenHelper;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.application.catquest.base.DBAccessBase;
import android.util.Log;

/**
 * ログイン画面
 *
 * @author n.yuuki
 */
public class LoginActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity_layout);
		//ボタンオブオブジェクト取得
		Button button =(Button)findViewById(R.id.button2);
		button.setOnClickListener(new ButtonClickListener());

	}



	class ButtonClickListener implements OnClickListener{
		public void onClick(View v){
			Log.d("tag_name", "debuglog text");
			EditText input =(EditText)findViewById(R.id.editText1);
			DBAccessOpenHelper helper = new DBAccessOpenHelper(LoginActivity.this);
			SQLiteDatabase db = helper.getReadableDatabase();

			db.beginTransaction();
			/*String sql
			= "create table product(" +
			"_id integer primary key autoincrement," +
			"productid text not null," +
			"name text not null," +
			"price integer default 0)";
			//SQL実行
			db.execSQL(sql);*/

			ContentValues val = new ContentValues();
			//val.put("productid","1");
			val.put("name","namae");
			val.put("price", "kakaku");


			//db.insert("product",null,val);


			//String selectsql = "select name from " + "product " + "where id = 1 ;";
			//Cursor cursor = db.query("product", null,null,null,null,null,null);
//			input.setText(cursor.getString(0));

			db.setTransactionSuccessful();
			db.endTransaction();
			//String sql = "select name from product where _id = 1 ;";
			String sql = "select * from product where _id = 1 ;";
			SQLiteCursor c = (SQLiteCursor)db.rawQuery(sql, null);
//			SQLiteCursor c = (SQLiteCursor)db.query("product",new String[]{"name"},null, null,null,null,null);
			//createSelectOtherSQL("product","name","where _id = 1");

			c.moveToFirst();
			Log.d("tag_name", "debug counter :" + c );
			Log.d("tag_name", "debug counter :" + c.getString(1) );
			String a = c.getString(2);

			Log.d("tag_name", "debug counter :" + a );
			input.setText(a);

			db.close();
			//input.setText("あ");
}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
