package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.BoardLikeReportVO;
import icu.gieok.vo.BoardVO;

public interface PhotoDAO {

    int updateEventPhoto(BoardVO board);

    int eventPhotoInsert(BoardVO board);

    int boardListCount(Map<String, Object> map);

    List<BoardVO> getBoardList(Map<String, Object> map);

    BoardVO getBoardDetail(int board_no);

    int updateBoardHit(int board_no);

    int updatePhotoEvent(BoardVO board);

    int deletePhotoEvent(int board_no);

    int updatePhotoEventLike(Map<String, Object> map);

    BoardLikeReportVO getBoardLikeReport(Map<String, Object> map);

    int boardLikeReportInsert(Map<String, Object> map);

    int updatePhotoEventLikeCheck(Map<String, Object> map);

	int updatePhotoEventReportCheck(Map<String, Object> map);

	int updatePhotoEventReport(Map<String, Object> map);

}
