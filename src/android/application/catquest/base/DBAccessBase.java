package android.application.catquest.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DBアクセスベースクラス。
 * ※2013-05-20 > 永田 > 作成中
 *
 * @author n.yuuki
 */
public class DBAccessBase{
	// 定数
	/** SELECT文(固定文)1 */
	private static final String SELECT_SQL_1 = " SELECT ( ";
	/** SELECT文(固定文)2 */
	private static final String SELECT_SQL_2 = " ) FROM ";
	/** INSERT文(固定文)1 */
	private static final String INSERT_SQL_1 = " INSERT INTO ";
	/** INSERT文(固定文)2 */
	private static final String INSERT_SQL_2 = " ( ";
	/** INSERT文(固定文)3 */
	private static final String INSERT_SQL_3 = " VALUES ( ";
	/** UPDATE文(固定文)1 */
	private static final String UPDATE_SQL_1 = " UPDATE ";
	/** UPDATE文(固定文)2 */
	private static final String UPDATE_SQL_2 = " SET ( ";
	/** DELETE文(固定文)1 */
	private static final String DELETE_SQL_1 = " DELETE FROM ";
	/** WHERE文(固定文)1 */
	private static final String WHERE_SQL_1 = " WHERE (";
	/** SQL共通文(固定文)1 */
	private static final String SQL_1 = ",";
	/** SQL共通文(固定文)2 */
	private static final String SQL_2 = " ) ";
	/** SQL共通文(固定文)3 */
	private static final String SQL_3 = ";";

	// 変数
	/** SQL文 */
	private StringBuffer sql = null;

	/**
	 * SQL文の実行。
	 *
	 * @throws Exception 全ての例外
	 */
	public void execSQL() throws Exception {

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
				recName.append(keyItem + SQL_1);
				recValue.append(tabelKeyValue.get(keyItem) + SQL_1);
			}
		}

		// SQL文を作成
		sql.append(SELECT_SQL_1);
		sql.append(recName);
		sql.append(SELECT_SQL_2);
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
		sql.append(WHERE_SQL_1);
		sql.append(whereSql);
		sql.append(SQL_2);
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
		this.sql = new StringBuffer();

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

	}

	/**
	 * SQL文の実行。<br />
	 * ※サブクエリ等がある場合に利用。
	 *
	 * @param sql SQL文
	 * @throws Exception 全ての例外
	 */
	public void setSQL(String sql) throws Exception {
		// StringBufferのインスタンス生成
		this.sql = new StringBuffer();
		this.sql.append(sql);
	}

}
