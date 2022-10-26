package icu.gieok.service;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;
import icu.gieok.vo.CityVO;
import icu.gieok.vo.ProvinceVO;

public interface BoardWithService {

	List<ProvinceVO> getProvinceList();
	
	List<CityVO> getCityList(String province_id);

	List<AttrVO> getAttrList(String city_id);

	int insertWith(BoardVO bw);

	int countWith(Map<String, Object> map);

	List<BoardVO> getWithList(Map<String, Object> map);

}
