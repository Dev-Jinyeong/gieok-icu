package icu.gieok.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icu.gieok.dao.BoardDAO;
import icu.gieok.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDao;

	@Override
	public void noticeInsert(BoardVO b) {
		this.boardDao.noticeInsert(b);
	}

	@Override
	public void eventInsert(BoardVO b) {
		this.boardDao.eventInsert(b);
	}

	@Override
	public BoardVO board_cont(int board_no) {
		return this.boardDao.board_cont(board_no);
	}

	@Override
	public BoardVO getboardDetail(int board_no) {
		return this.boardDao.getboardDetail(board_no);
	}

	@Override
	public BoardVO board_contM(int board_no) {
		return this.boardDao.board_contM(board_no);
	}

	@Override
	public void board_del(int i) {
		this.boardDao.board_del(i);		
	}

	@Override
	public void noticeUpdate(BoardVO b) {
		this.boardDao.noticeUpdate(b);
	}

	@Override
	public void eventUpdate(BoardVO b) {
		this.boardDao.eventUpdate(b);
	}

	@Override
	public List<BoardVO> boardSort(Map<String, Object> bs) {
		return this.boardDao.boardSort(bs);
	}

	@Override
	public List<BoardVO> board_list(Map<String, Object> rows) {
		return this.boardDao.board_list(rows);
	}

	@Override
	public int board_count(Map<String, Object> row_sort) {
		return this.boardDao.board_count(row_sort);
	}

	@Override
	public void board_eventEnd(int i) {
		this.boardDao.board_eventEnd(i);
	}

}
