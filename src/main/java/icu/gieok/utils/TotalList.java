package icu.gieok.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import icu.gieok.service.BoardService;
import icu.gieok.service.BoardWithService;
import icu.gieok.service.MapService;
import icu.gieok.service.PhotoService;
import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;

public class TotalList {

	Map<String, Object> m = new HashMap<>();
	
	List<BoardVO> list = new ArrayList<>();
	
	int list_count;
	
	
	public List<BoardVO> board_list(int board_type, String search, Object service){
		
		m.put("startRow", 1);
		m.put("endRow", 5);
		
		if(board_type == 3) {
			m.put("category", "board_location");
			m.put("keyword", search);
			
			list = ((BoardWithService)service).getWithList(m);
		}else if(board_type == 4) {
			m.put("category", "board_title");
			m.put("sortBy", "board_regDate");
			m.put("keyword", search);
			
			list = ((PhotoService) service).getBoardList(m);
		}else {
			m.put("board_sort", "");
			m.put("list_search", search);
			
			list = ((BoardService) service).board_list(m);
		}
		
		return list;
		
	}

	public int board_count(int board_type, String search, Object service){
		
		if(board_type == 3) {
			m.put("category", "board_location");
			m.put("keyword", search);
			list_count = ((BoardWithService) service).countWith(m);
		}else if(board_type == 4) {
			m.put("category", "board_title");
			m.put("keyword", search);
			list_count = ((PhotoService) service).boardListCount(m);
		}else {
			m.put("list_search", search);
			m.put("board_sort", "");
			list_count = ((BoardService) service).board_count(m);
		}
		
		return list_count;
	}
	
	public List<AttrVO> attr_list(String search, Object service){
		
		m.put("startRow", 1);
		m.put("endRow", 5);
		m.put("category", "attr_name");
		m.put("sortBy", "attr_code");
		m.put("keyword", search);
		List<AttrVO> list = ((MapService) service).getAttrList(m);

		return list;
	}
	
	public int att_count(String search, Object service) {
		
		m.put("category", "attr_name");
		m.put("keyword", search);
		
		list_count = ((MapService) service).countAttr(m);
		return list_count;
	}
	
	
}
