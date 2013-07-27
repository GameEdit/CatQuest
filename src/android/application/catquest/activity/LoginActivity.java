package android.application.catquest.activity;

import android.app.Activity;
import android.application.catquest.R;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;


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
			EditText input =(EditText)findViewById(R.id.editText1);
			input.setText("メイジももんじゃ");


			String sql
			= "create table product(" +
			"_id integer primary key autoincrement," +
			"productid text not null,"
			"name text not null," +
			"price integer default 0)";
			//SQL実行
			db.execSQL(sql);

			ContentValues val = new ContentValues();
			val.put("productid",productid.getText().toString);
			val.put("name",name.getText().toString);
			val.put("price", price.getText().toString());

			db.insert("product",null,val);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
