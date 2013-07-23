package android.application.catquest.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.application.catquest.constant.DBConstant;
import android.application.catquest.constant.SQLConstant;
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
	/** コンテキスト */
	private Context con = null;
	/** SQL文 */
	private StringBuffer sql = null;
	/** データ取得用 */
	protected Cursor cur = null;

	/**
	 * コンストラクタ
	 *
	 * @param con コンテキスト
	 */
	public DBAccessBase(Context con) {
		this.con = con;
	}

	/**
	 * SQL文の実行。
	 *
	 * @param execFlg SQL実行フラグ
	 * @throws Exception 全ての例外
	 */
	protected void execSql(int execFlg) throws Exception {
		// コンテキストがnull之場合
		if (con == null) {
			throw new SQLiteException("コンテキストNULLエラー");

		// SQLの記載が無い場合 または 実行フラグが不定の場合
		} else if (sql == null || (execFlg != DBConstant.EXEC_FLG_READABLE && execFlg != DBConstant.EXEC_FLG_WRITABLE)) {
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
	 * @param tableNameList 取得対象項目名リスト
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createSelectSQL(String tableName, ArrayList<String> tableNameList) throws Exception {
		// StringBufferのインスタンス生成
		sql = new StringBuffer();

		// SQL文を作成
		sql.append(SQLConstant.SELECT_SQL_1);

		// 項目名の件数分ループ。
		for (int i = 0; i < tableNameList.size(); i++) {
			String keyItem = tableNameList.get(i);

			// Listの最後の場合
			if (i == (tableNameList.size() - 1)) {
				sql.append(keyItem);
			// 上記以外
			} else {
				sql.append(keyItem + SQLConstant.SQL_1);
			}
		}

		sql.append(SQLConstant.SELECT_SQL_2);
		sql.append(tableName);
	}

	/**
	 * selectのSQL文を発行します。<br />
	 * ※その他条件あり
	 *
	 * @param tableName テーブル名
	 * @param tableNameList 取得対象項目名リスト
	 * @param otherSQL その他の条件文(where,order by など)
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createSelectOtherSQL(String tableName, ArrayList<String> tableNameList, String otherSQL) throws Exception {
		// その他条件以外のSQL文の作成
		createSelectSQL(tableName, tableNameList);

		// その他条件の追加
		sql.append(otherSQL);
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
				recName.append(keyItem + SQLConstant.SQL_1);
				recValue.append(tabelKeyValue.get(keyItem) + SQLConstant.SQL_1);
			}
		}

		// SQL文を作成
		sql.append(SQLConstant.INSERT_SQL_1);
		sql.append(tableName);
		sql.append(SQLConstant.INSERT_SQL_2);
		sql.append(recName);
		sql.append(SQLConstant.INSERT_SQL_3);
		sql.append(recValue);
		sql.append(SQLConstant.SQL_2);
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
		sql.append(SQLConstant.UPDATE_SQL_1);
		sql.append(tableName);
		sql.append(SQLConstant.UPDATE_SQL_2);

		// Listの件数分ループ。
		for (int i = 0; i < updateList.size(); i++) {
			String keyItem = updateList.get(i);

			// Listの最後の場合
			if (i == updateList.size()) {
				sql.append(keyItem);

			// 上記以外
			} else {
				sql.append(keyItem + SQLConstant.SQL_1);
			}
		}

		sql.append(SQLConstant.SQL_2);
	}

	/**
	 * updateのSQL文を発行します。<br />
	 * ※その他条件あり
	 *
	 * @param tableName テーブル名
	 * @param updateList updateの情報のリスト
	 * @param otherSQL その他の条件文(where,order by など)
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createUpdateOtherSQL(String tableName, ArrayList<String> updateList, String otherSQL) throws Exception {
		// その他条件以外のSQL文の作成
		createUpdateSQL(tableName, updateList);

		// その他条件の追加
		sql.append(otherSQL);
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

		sql.append(SQLConstant.DELETE_SQL_1);
		sql.append(tableName);
	}

	/**
	 * deleteのSQL文を発行します。
	 * ※その他条件あり
	 *
	 * @param tableName テーブル名
	 * @param otherSQL その他の条件文(where,order by など)
	 *
	 * @throws Exception 全ての例外
	 */
	protected void createDeleteOtherSQL(String tableName, String otherSQL) throws Exception {
		// その他条件以外のSQL文の作成
		createDeleteSQL(tableName);

		// その他条件の追加
		sql.append(otherSQL);
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
	 * ※特殊な構文の場合に利用。
	 *
	 * @param sql SQL文
	 * @throws Exception 全ての例外
	 */
	public void setSql(StringBuffer sql) {
		// StringBufferのインスタンス生成
		this.sql = new StringBuffer();
		this.sql.append(sql);
	}

}
