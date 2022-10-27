package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;

@Repository
public class IndexDAOImpl implements IndexDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AttrVO> getAttrList_index(Map<String, Object> m) {
		return this.sqlSession.selectList("getAttrList_index", m);
	}

	@Override
	public int countAttr_index(Map<String, Object> m) {
		return this.sqlSession.selectOne("countAttr_index", m);
	}

	@Override
	public List<BoardVO> getWiteList_index(Map<String, Object> m) {
		return this.sqlSession.selectList("getWithList_index", m);
	}

	@Override
	public int countWith_index(Map<String, Object> m) {
		return this.sqlSession.selectOne("countWith_index", m);
	}

	@Override
	public List<BoardVO> getBoardList_index(Map<String, Object> m) {
		return this.sqlSession.selectList("getBoardList_index", m);
	}

	@Override
	public int boardListCount_index(Map<String, Object> m) {
		return this.sqlSession.selectOne("boardListCount_index", m);
	}

	@Override
	public List<AttrVO> getAttrLike_index(Map<String, Object> m) {
		return this.sqlSession.selectList("getAttrLike_index", m);
	}

	@Override
	public int maxNumAttr_index() {
		return this.sqlSession.selectOne("maxNumAttr_index");
	}

	@Override
	public List<AttrVO> getAttrAll() {
		return this.sqlSession.selectList("getAttrAll");
	}
	
	
}
