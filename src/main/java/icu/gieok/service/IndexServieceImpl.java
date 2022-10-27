package icu.gieok.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icu.gieok.dao.IndexDAO;
import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;

@Service
public class IndexServieceImpl implements IndexService {
	
	@Autowired
	private IndexDAO indexDao;

	@Override
	public List<AttrVO> getAttrList_index(Map<String, Object> m) {
		return this.indexDao.getAttrList_index(m);
	}

	@Override
	public int countAttr_index(Map<String, Object> m) {
		return this.indexDao.countAttr_index(m);
	}

	@Override
	public List<BoardVO> getWithList_index(Map<String, Object> m) {
		return this.indexDao.getWiteList_index(m);
	}

	@Override
	public int countWith_index(Map<String, Object> m) {
		return this.indexDao.countWith_index(m);
	}

	@Override
	public List<BoardVO> getBoardList_index(Map<String, Object> m) {
		return this.indexDao.getBoardList_index(m);
	}

	@Override
	public int boardListCount_index(Map<String, Object> m) {
		return this.indexDao.boardListCount_index(m);
	}

	@Override
	public List<AttrVO> getAttrLike_index(Map<String, Object> m) {
		return this.indexDao.getAttrLike_index(m);
	}

	@Override
	public int maxNumAttr_index() {
		return this.indexDao.maxNumAttr_index();
	}

	@Override
	public List<AttrVO> getAttrAll() {
		return this.indexDao.getAttrAll();
	}
}
