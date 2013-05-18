package android.application.catquest.dto;

/**
 * ユーザプロフィールのデータクラス
 *
 * @author n.yuuki
 */
public class UserProfileDto {
	// 定数

	// 変数
	/** UUID */
	private String uuid = null;
	/** プレイヤー名 */
	private String playerName = null;
	/** 体力 */
	private int healthPoint = 0;
	/** お金 */
	private int money = 0;
	/** バトル回数上限 */
	private int battleCountLimit = 0;
	/** 装備格納件数上限(上限あり ※課金で追加可) */
	private int equitCountLimit = 0;
	/** ゲーム内マネー */
	private int gameMoney = 0;
	/** キャラクター数 */
	private int characterCount = 0;

	/**
	 * コンストラクタ
	 */
	public UserProfileDto() {
		// フィールドの初期化
		fieldClear();
	}

	/**
	 * フィールドを初期化します。
	 */
	private void fieldClear() {
		uuid = "";
		playerName = "";
		healthPoint = 0;
		money = 0;
		battleCountLimit = 0;
		equitCountLimit = 0;
		gameMoney = 0;
		characterCount = 0;
	}

	/**
	 * UUIDを取得します。
	 *
	 * @return UUID
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * UUIDを設定します。
	 *
	 * @param uuid UUID
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * プレイヤー名を取得します。
	 *
	 * @return プレイヤー名
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * プレイヤー名を設定します。
	 *
	 * @param playerName プレイヤー名
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * 体力を取得します。
	 *
	 * @return 体力
	 */
	public int getHealthPoint() {
		return healthPoint;
	}

	/**
	 * 体力を設定します。
	 *
	 * @param healthPoint 体力
	 */
	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	/**
	 * お金を取得します。
	 *
	 * @return お金
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * お金を設定します。
	 *
	 * @param money お金
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * バトル回数上限を取得します。
	 *
	 * @return バトル回数上限
	 */
	public int getBattleCountLimit() {
		return battleCountLimit;
	}

	/**
	 * バトル回数上限を設定します。
	 *
	 * @param battleCountLimit バトル回数上限
	 */
	public void setBattleCountLimit(int battleCountLimit) {
		this.battleCountLimit = battleCountLimit;
	}

	/**
	 * 装備格納件数上限を取得します。
	 *
	 * @return 装備格納件数上限
	 */
	public int getEquitCountLimit() {
		return equitCountLimit;
	}

	/**
	 * 装備格納件数上限を設定します。
	 *
	 * @param equitCountLimit 装備格納件数上限
	 */
	public void setEquitCountLimit(int equitCountLimit) {
		this.equitCountLimit = equitCountLimit;
	}

	/**
	 * ゲーム内マネーを取得します。
	 *
	 * @return ゲーム内マネー
	 */
	public int getGameMoney() {
		return gameMoney;
	}

	/**
	 * ゲーム内マネーを設定します。
	 *
	 * @param gameMoney ゲーム内マネー
	 */
	public void setGameMoney(int gameMoney) {
		this.gameMoney = gameMoney;
	}

	/**
	 * キャラクター数を取得します。
	 *
	 * @return キャラクター数
	 */
	public int getCharacterCount() {
		return characterCount;
	}

	/**
	 * キャラクター数を設定します
	 *
	 * @param characterCount キャラクター数
	 */
	public void setCharacterCount(int characterCount) {
		this.characterCount = characterCount;
	}


}
