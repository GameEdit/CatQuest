package android.application.catquest.dao;

import android.application.catquest.constant.DBConstant;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBアクセスヘルパークラス。
 * ※2013-05-20 > 永田 > 作成中
 *
 * @author n.yuuki
 */
public class DBAccessOpenHelper extends SQLiteOpenHelper {

	/**
	 * コンストラクタ
	 *
	 * @param con コンテキスト
	 */
	public DBAccessOpenHelper(Context con) {
		super(con, DBConstant.DB_NAME, null, 1);
	}

	/**
	 * データベース作成時に呼び出される。
	 */
	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	/**
	 * データベースのバージョン変更時に呼び出される。
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
