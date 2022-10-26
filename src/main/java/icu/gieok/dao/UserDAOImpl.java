package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import icu.gieok.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public UserVO idDupCheck(String id_input) {
		return sqlSession.selectOne("idDupCheck", id_input);
	}
	
	@Override
	public void userInsert(UserVO user) {
		sqlSession.insert("userInsert", user);
	}
	
	@Override
	public void updateAuthKey(Map<String, String> map) {
		sqlSession.update("updateAuthKey", map);
	}
	
	@Override
	public void updateAuth(Map<String, String> map) {
		sqlSession.update("updateAuth", map);
	}
	
	@Override
	public UserVO checkAuth(Map<String, String> map) {
		return sqlSession.selectOne("checkAuth", map);
	}

	@Override
	public UserVO userSelect(String user_id) {
		return sqlSession.selectOne("userSelect", user_id);
	}
	
	@Override
	public int updateUserInform(UserVO user) {
		return sqlSession.update("updateUserInform", user);
	}

	@Override
	public int userDelete(String user_id) {
		return sqlSession.update("userDelete", user_id);
	}

	@Override
	public int updateUserPw(Map<String, String> map) {
		return sqlSession.update("updateUserPw", map);
	}

	@Override
	public UserVO userIdPwFind(Map<String, String> map) {
		return sqlSession.selectOne("userIdPwFind", map);
	}

	@Override
	public int emailDupCheck(Map<String, String> map) {
		return sqlSession.selectOne("emailDupCheck", map);
	}
}
