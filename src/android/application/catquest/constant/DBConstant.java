package android.application.catquest.constant;

/**
 * DB関連定数値クラス
 *
 * @author n.yuuki
 */
public class DBConstant {
	/**
	 * コンストラクタのインスタンス化を禁止する。
	 */
	private DBConstant() {}

	// 共通利用
	/** データベース名 */
	public static final String DB_NAME = "CAT_QUEST_DB";
	/** ReadableDatabaseフラグ */
	public static final int EXEC_FLG_READABLE = 1;
	/** WritableDatabaseフラグ */
	public static final int EXEC_FLG_WRITABLE = 2;

	// ユーザプロフィールテーブル
	/** (ユーザプロフィール)テーブル名 */
	public static final String TABLE_NAME_USER_PROFILE = "UserProfile";
	/** (ユーザプロフィール)UUID */
	public static final String USER_PROFILE_UUID = "UUID";
	/** (ユーザプロフィール)プレイヤー名 */
	public static final String USER_PROFILE_PLAYER_NAME = "PlayerName";
	/** (ユーザプロフィール)体力 */
	public static final String USER_PROFILE_HEALTH_POINT = "HealthPoint";
	/** (ユーザプロフィール)お金 */
	public static final String USER_PROFILE_MONEY = "Money";
	/** (ユーザプロフィール)バトル回数上限 */
	public static final String USER_PROFILE_BATTLE_COUNT_LIMIT = "BattleCountLimit";
	/** (ユーザプロフィール)装備格納件数上限 */
	public static final String USER_PROFILE_EQUIT_COUNT_LIMIT = "EquitCountLimit";
	/** (ユーザプロフィール)ゲーム内マネー */
	public static final String USER_PROFILE_GAME_MONEY = "GameMoney";
	/** (ユーザプロフィール)キャラクター数 */
	public static final String USER_PROFILE_CHARACTER_COUNT = "CharacterCount";

}
