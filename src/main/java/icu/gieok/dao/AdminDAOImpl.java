package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import icu.gieok.vo.UserVO;

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int userListCount(Map<String, Object> map) {
		return sqlSession.selectOne("userListCount", map);
	}

	@Override
	public List<UserVO> getUserList(Map<String, Object> map) {
		return sqlSession.selectList("getUserList", map);
	}
	
}
