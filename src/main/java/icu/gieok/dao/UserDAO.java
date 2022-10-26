package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.UserVO;

public interface UserDAO {

	UserVO idDupCheck(String id_input);

	void userInsert(UserVO user);

	void updateAuthKey(Map<String, String> map);

	void updateAuth(Map<String, String> map);

	UserVO checkAuth(Map<String, String> map);

	UserVO userSelect(String user_id);
	
	int updateUserInform(UserVO user);

	int userDelete(String user_id);

	int updateUserPw(Map<String, String> map);

	UserVO userIdPwFind(Map<String, String> map);

	int emailDupCheck(Map<String, String> map);

}
