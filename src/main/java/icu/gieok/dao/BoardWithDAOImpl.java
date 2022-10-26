package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;
import icu.gieok.vo.CityVO;
import icu.gieok.vo.ProvinceVO;

@Repository
public class BoardWithDAOImpl implements BoardWithDAO {

	@Autowired
	private SqlSession sqlSession;

	// 도 목록
	@Override
	public List<ProvinceVO> getProvinceList() {
		
		return sqlSession.selectList("with_list_Province");
		
	}
	
	// 시 목록
	@Override
	public List<CityVO> getCityList(String province_id) {
		
		return sqlSession.selectList("with_list_City", province_id);
		
	}

	// 명소 목록
	@Override
	public List<AttrVO> getAttrList(String city_id) {
		
		return sqlSession.selectList("with_list_Attr", city_id);
		
	}

	// 동행 등록
	@Override
	public int insertWith(BoardVO bw) {
		
		return sqlSession.insert("insertWith", bw);
		
	}

	// Pagination
	@Override
	public int countWith(Map<String, Object> map) {
		
		return sqlSession.selectOne("countWith", map);
	}

	// 게시물 목록
	@Override
	public List<BoardVO> getWithList(Map<String, Object> map) {
		
		return sqlSession.selectList("getWithList", map);
	}

}
