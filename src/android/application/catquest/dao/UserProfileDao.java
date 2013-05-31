package android.application.catquest.dao;

import java.util.ArrayList;

import android.application.catquest.base.DBAccessBase;
import android.application.catquest.constant.DBConstant;
import android.application.catquest.constant.SQLConstant;
import android.application.catquest.dto.UserProfileDto;
import android.content.Context;

/**
 * ユーザプロフィールのDAOクラス
 *
 * @author n.yuuki
 */
public class UserProfileDao extends DBAccessBase {
	/**
	 * コンストラクタ
	 *
	 * @param con コンテキスト
	 */
	public UserProfileDao(Context con) {
		super(con);
	}

	/**
	 * DBからユーザプロフィールの内容を全て取得します。
	 *
	 * @return データ取得結果
	 * @throws Exception 全ての例外
	 */
	public ArrayList<UserProfileDto> selectAll() throws Exception{
		// 取得対象項目名リスト
		ArrayList<String> tableNameList = new ArrayList<String>();
		tableNameList.add(DBConstant.USER_PROFILE_UUID);
		tableNameList.add(DBConstant.USER_PROFILE_PLAYER_NAME);
		tableNameList.add(DBConstant.USER_PROFILE_HEALTH_POINT);
		tableNameList.add(DBConstant.USER_PROFILE_MONEY);
		tableNameList.add(DBConstant.USER_PROFILE_BATTLE_COUNT_LIMIT);
		tableNameList.add(DBConstant.USER_PROFILE_EQUIT_COUNT_LIMIT);
		tableNameList.add(DBConstant.USER_PROFILE_GAME_MONEY);
		tableNameList.add(DBConstant.USER_PROFILE_CHARACTER_COUNT);

		// SQL文の作成
		createSelectSQL(DBConstant.TABLE_NAME_USER_PROFILE, tableNameList);

		// SQL実行と結果取得
		execSql(DBConstant.EXEC_FLG_READABLE);

		// ユーザプロフィール情報のリスト作成
		ArrayList<UserProfileDto> items = new ArrayList<UserProfileDto>();
		UserProfileDto item = null;
		int index = 0;
		while (cur.moveToNext()) {
			index = 0;
			item = new UserProfileDto();
			item.setUuid(cur.getString(index++));
			item.setPlayerName(cur.getString(index++));
			item.setHealthPoint(cur.getInt(index++));
			item.setMoney(cur.getInt(index++));
			item.setBattleCountLimit(cur.getInt(index++));
			item.setEquitCountLimit(cur.getInt(index++));
			item.setGameMoney(cur.getInt(index++));
			item.setCharacterCount(cur.getInt(index++));
			items.add(item);
		}

		// 取得内容を返却
		return items;
	}

	/**
	 * ユーザプロフィールテーブルから主キーで検索し、<br />
	 * ユーザプロフィールの情報を返す。
	 *
	 * @param searchKey 検索主キー
	 * @return ユーザプロフィール情報
	 * @throws Exception 全ての例外
	 */
	public UserProfileDto selectByPK(String searchKey) throws Exception {
		// 取得対象項目名リスト
		ArrayList<String> tableNameList = new ArrayList<String>();
		tableNameList.add(DBConstant.USER_PROFILE_UUID);
		tableNameList.add(DBConstant.USER_PROFILE_PLAYER_NAME);
		tableNameList.add(DBConstant.USER_PROFILE_HEALTH_POINT);
		tableNameList.add(DBConstant.USER_PROFILE_MONEY);
		tableNameList.add(DBConstant.USER_PROFILE_BATTLE_COUNT_LIMIT);
		tableNameList.add(DBConstant.USER_PROFILE_EQUIT_COUNT_LIMIT);
		tableNameList.add(DBConstant.USER_PROFILE_GAME_MONEY);
		tableNameList.add(DBConstant.USER_PROFILE_CHARACTER_COUNT);
		// where条件を追加
		String otherSQL =
			SQLConstant.WHERE_SQL_1
			+ DBConstant.USER_PROFILE_UUID + " = " + searchKey;

		// SQL文の作成
		createSelectOtherSQL(DBConstant.TABLE_NAME_USER_PROFILE, tableNameList, otherSQL);

		// SQL実行と結果取得
		execSql(DBConstant.EXEC_FLG_READABLE);

		// ユーザプロフィール情報の作成
		UserProfileDto item = null;
		int index = 0;
		while (cur.moveToNext()) {
			item = new UserProfileDto();
			item.setUuid(cur.getString(index++));
			item.setPlayerName(cur.getString(index++));
			item.setHealthPoint(cur.getInt(index++));
			item.setMoney(cur.getInt(index++));
			item.setBattleCountLimit(cur.getInt(index++));
			item.setEquitCountLimit(cur.getInt(index++));
			item.setGameMoney(cur.getInt(index++));
			item.setCharacterCount(cur.getInt(index++));
		}

		// 取得内容を返却
		return item;
	}
}
