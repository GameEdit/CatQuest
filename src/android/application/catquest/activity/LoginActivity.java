package android.application.catquest.activity;

import android.app.Activity;
import android.application.catquest.R;
import android.os.Bundle;
import android.view.Menu;

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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
