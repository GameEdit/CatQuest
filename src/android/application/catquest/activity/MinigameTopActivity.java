package android.application.catquest.activity;

import android.app.Activity;
import android.application.catquest.R;
import android.application.catquest.activity.LoginActivity.ButtonClickListener;
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
import android.content.Intent;

public class MinigameTopActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.minigametop_activity_layout);
		//ボタンオブオブジェクト取得
		Button button =(Button)findViewById(R.id.button1);
		button.setOnClickListener(new ButtonClickListener());

		  Intent intent = getIntent();
	        Bundle extras = intent.getExtras();
	}

	class ButtonClickListener implements OnClickListener{
		public void onClick(View v){

			Intent intent = new Intent(MinigameTopActivity.this, QuestTopActivity.class);
			intent.putExtra("ASIN", "tag");
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
