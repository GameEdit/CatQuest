package android.application.catquest.constant;

/**
 * SQL関連定数値クラス
 *
 * @author n.yuuki
 */
public class SQLConstant {
	/**
	 * コンストラクタのインスタンス化を禁止する。
	 */
	private SQLConstant() {}

	/** SELECT文(固定文)1 */
	public static final String SELECT_SQL_1 = " SELECT ( ";
	/** SELECT文(固定文)2 */
	public static final String SELECT_SQL_2 = " ) FROM ";
	/** INSERT文(固定文)1 */
	public static final String INSERT_SQL_1 = " INSERT INTO ";
	/** INSERT文(固定文)2 */
	public static final String INSERT_SQL_2 = " ( ";
	/** INSERT文(固定文)3 */
	public static final String INSERT_SQL_3 = " ) VALUES ( ";
	/** UPDATE文(固定文)1 */
	public static final String UPDATE_SQL_1 = " UPDATE ";
	/** UPDATE文(固定文)2 */
	public static final String UPDATE_SQL_2 = " SET ( ";
	/** DELETE文(固定文)1 */
	public static final String DELETE_SQL_1 = " DELETE FROM ";
	/** WHERE文(固定文)1 */
	public static final String WHERE_SQL_1 = " WHERE ";
	/** SQL共通文(固定文)1 */
	public static final String SQL_1 = ",";
	/** SQL共通文(固定文)2 */
	public static final String SQL_2 = " ) ";
	/** SQL共通文(固定文)3 */
	public static final String SQL_3 = ";";

}
