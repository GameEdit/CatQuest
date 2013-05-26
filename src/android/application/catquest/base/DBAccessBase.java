package android.application.catquest.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.application.catquest.constant.DBConstant;
import android.application.catquest.dao.DBAccessOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * DBアクセスベースクラス。
 *
 * @author n.yuuki
 */
public class DBAccessBase {
	/** SQL文 */
	private StringBuffer sql = null;
	/**
	 * SQL実行フラグ<br />
	 * ※0 > フラグセットなし
	 * 　1 > ReadableDatabase
	 * 　2 > WritableDatabase
	 */
	private int execFlg = 0;
	/** データ取得用 */
	protected Cursor cur = null;

	/**
	 * SQL文の実行。
	 *
	 * @param con 実行クラス
	 * @throws Exception 全ての例外
	 */
	public void execSql(Context con) throws Exception {
		// SQLの記載が無い場合 または 実行フラグが不定の場合
		if (sql == null || (execFlg != DBConstant.EXEC_FLG_READABLE && execFlg != DBConstant.EXEC_FLG_WRITABLE)) {
			throw new SQLiteException("SQL実行不可エラー");
		}

		// DBAccess
		DBAccessOpenHelper helper = new DBAccessOpenHelper(con);
		SQLiteDatabase db = null;
		// WritableDatabaseの場合
		if (execFlg == DBConstant.EXEC_FLG_WRITABLE) {
			db = helper.getWritableDatabase();

			// トランザクション制御開始
			db.beginTransaction();
			// SQLの実行
			db.execSQL(getSql());
			// コミット処理
			db.setTransactionSuccessful();
			// トランザクション制御終了
			db.endTransaction();

		// 上記以外
		} else if (execFlg == DBConstant.EXEC_FLG_READABLE) {
			db = helper.getReadableDatabase();

			cur = db.rawQuery(getSql(), null);
		}

		// DBクローズ処理
		db.close();
	}

	/**
	 * selectのSQL文を発行します。
	 *
	 * @param tableName テーブル名
	 * @param tabelKeyValue レコード名と値の対応付けMAP
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createSelectSQL(String tableName, HashMap<String, String> tabelKeyValue) throws Exception {
		// StringBufferのインスタンス生成
		sql = new StringBuffer();
		// Key値取得
		List<String> keyList = new ArrayList<String>(tabelKeyValue.keySet());
		// レコード名
		StringBuffer recName = new StringBuffer();
		// レコード値
		StringBuffer recValue = new StringBuffer();

		// Key値の件数分ループ。
		for (int i = 0; i < keyList.size(); i++) {
			String keyItem = keyList.get(i);

			// Listの最後の場合
			if (i == keyList.size()) {
				recName.append(keyItem);
				recValue.append(tabelKeyValue.get(keyItem));
			// 上記以外
			} else {
				recName.append(keyItem + DBConstant.SQL_1);
				recValue.append(tabelKeyValue.get(keyItem) + DBConstant.SQL_1);
			}
		}

		// SQL文を作成
		sql.append(DBConstant.SELECT_SQL_1);
		sql.append(recName);
		sql.append(DBConstant.SELECT_SQL_2);
		sql.append(tableName);
	}

	/**
	 * selectのSQL文を発行します。<br />
	 * ※where句あり
	 *
	 * @param tableName テーブル名
	 * @param tabelKeyValue レコード名と値の対応付けMAP
	 * @param whereSql where条件
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createSelectWhereSQL(String tableName, HashMap<String, String> tabelKeyValue, String whereSql) throws Exception {
		// where句以外のSQL文の作成
		createSelectSQL(tableName, tabelKeyValue);

		// where句の追加
		sql.append(DBConstant.WHERE_SQL_1);
		sql.append(whereSql);
		sql.append(DBConstant.SQL_2);
	}

	/**
	 * insertのSQL文を発行します。
	 *
	 * @param tableName テーブル名
	 * @param tabelKeyValue レコード名と値の対応付けMAP
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createInsertSQL(String tableName, HashMap<String, String> tabelKeyValue) throws Exception {
		// StringBufferのインスタンス生成
		sql = new StringBuffer();

		// Key値取得
		List<String> keyList = new ArrayList<String>(tabelKeyValue.keySet());
		// レコード名
		StringBuffer recName = new StringBuffer();
		// レコード値
		StringBuffer recValue = new StringBuffer();

		// Key値の件数分ループ。
		for (int i = 0; i < keyList.size(); i++) {
			String keyItem = keyList.get(i);

			// Listの最後の場合
			if (i == keyList.size()) {
				recName.append(keyItem);
				recValue.append(tabelKeyValue.get(keyItem));
			// 上記以外
			} else {
				recName.append(keyItem + DBConstant.SQL_1);
				recValue.append(tabelKeyValue.get(keyItem) + DBConstant.SQL_1);
			}
		}

		// SQL文を作成
		sql.append(DBConstant.INSERT_SQL_1);
		sql.append(tableName);
		sql.append(DBConstant.INSERT_SQL_2);
		sql.append(recName);
		sql.append(DBConstant.INSERT_SQL_3);
		sql.append(recValue);
		sql.append(DBConstant.SQL_2);
	}

	/**
	 * updateのSQL文を発行します。
	 *
	 * @param tableName テーブル名
	 * @param updateList updateの情報のリスト
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createUpdateSQL(String tableName, ArrayList<String> updateList) throws Exception {
		// StringBufferのインスタンス生成
		sql = new StringBuffer();

		// SQL文を作成
		sql.append(DBConstant.UPDATE_SQL_1);
		sql.append(tableName);
		sql.append(DBConstant.UPDATE_SQL_2);

		// Listの件数分ループ。
		for (int i = 0; i < updateList.size(); i++) {
			String keyItem = updateList.get(i);

			// Listの最後の場合
			if (i == updateList.size()) {
				sql.append(keyItem);

			// 上記以外
			} else {
				sql.append(keyItem + DBConstant.SQL_1);
			}
		}

		sql.append(DBConstant.SQL_2);
	}

	/**
	 * updateのSQL文を発行します。<br />
	 * ※where句あり
	 *
	 * @param tableName テーブル名
	 * @param updateList updateの情報のリスト
	 * @param whereSql where条件
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createUpdateWhereSQL(String tableName, ArrayList<String> updateList, String whereSql) throws Exception {
		// where句以外のSQL文の作成
		createUpdateSQL(tableName, updateList);

		// where句の追加
		sql.append(DBConstant.WHERE_SQL_1);
		sql.append(whereSql);
		sql.append(DBConstant.SQL_2);
	}

	/**
	 * deleteのSQL文を発行します。
	 *
	 * @param tableName テーブル名
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createDeleteSQL(String tableName) throws Exception {
		// StringBufferのインスタンス生成
		sql = new StringBuffer();

		sql.append(DBConstant.DELETE_SQL_1);
		sql.append(tableName);
	}

	/**
	 * deleteのSQL文を発行します。
	 * ※where句あり
	 *
	 * @param tableName テーブル名
	 * @param whereSql where条件
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createDeleteWhereSQL(String tableName, String whereSql) throws Exception {
		// where句以外のSQL文の作成
		createDeleteSQL(tableName);

		// where句の追加
		sql.append(DBConstant.WHERE_SQL_1);
		sql.append(whereSql);
		sql.append(DBConstant.SQL_2);
	}

	/**
	 * SQL文(String型)を取得します。
	 *
	 * @return SQL文(String型)
	 */
	public String getSql() {
		return sql.toString();
	}

	/**
	 * SQL文の設定。<br />
	 * ※サブクエリ等がある場合に利用。
	 *
	 * @param sql SQL文
	 * @throws Exception 全ての例外
	 */
	public void setSql(StringBuffer sql) {
		// StringBufferのインスタンス生成
		this.sql = new StringBuffer();
		this.sql.append(sql);
	}

	/**
	 * カーソルを取得する。
	 *
	 * @return カーソル
	 */
	public Cursor getCur() {
		return cur;
	}

	/**
	 * カーソルを設定する。
	 *
	 * @param cur カーソル
	 */
	public void setCur(Cursor cur) {
		this.cur = cur;
	}


}
