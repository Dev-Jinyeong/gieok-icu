package icu.gieok.service;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.AttrLikeVO;
import icu.gieok.vo.AttrReviewReportVO;
import icu.gieok.vo.AttrReviewVO;
import icu.gieok.vo.FacVO;

public interface AttrService {

	List<FacVO> getRestaurantList(int attr_code);

	List<FacVO> getCafeList(int attr_code);

	List<AttrReviewVO> getReviewList(int attr_code);

	int insertReview(AttrReviewVO review);

	String avgReviewRate(int attr_code);

	AttrReviewReportVO getAttrReviewReport(Map<String, Integer> codes);

	int insertAttrReviewReport(Map<String, Integer> codes);

	int updateAttrReviewReport(Map<String, Integer> codes);

	AttrLikeVO getAttrLike(Map<String, Integer> map);

	int insertAttrLike(AttrLikeVO attrLike);

	int updateAttrLike(AttrLikeVO attrLike);



}
