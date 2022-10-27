package icu.gieok.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import icu.gieok.service.BoardService;
import icu.gieok.service.BoardWithService;
import icu.gieok.service.IndexService;
import icu.gieok.service.MapService;
import icu.gieok.service.PhotoService;
import icu.gieok.utils.TotalList;
	
@Controller
public class IndexController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private IndexService indexService;

	private TotalList list;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView m, HttpSession session) {
		
		list = new TotalList();
		
		m.addObject("blist", list.board_list(1,"", boardService));
		m.addObject("attrlike", list.attr_list_like(indexService));
		m.addObject("attrTotalCount", list.attr_count("total", indexService));
		m.addObject("attrimg", list.attr_ranImg(indexService));
		m.setViewName("index");

		return m;
	}
	
	@GetMapping("/main_search")
	public ModelAndView index_search(ModelAndView m, String search) {
		
		list = new TotalList();
		
		m.addObject("alist", list.attr_list(search, indexService));
		m.addObject("acount", list.attr_count(search, indexService));
		m.addObject("blist", list.board_list(1,search, boardService));
		m.addObject("bcount", list.board_count(1, search, boardService));
		m.addObject("withlist", list.board_list(3, search, indexService));
		m.addObject("withcount", list.board_count(3, search, indexService));
		m.addObject("photolist", list.board_list(4, search, indexService));
		m.addObject("photocount", list.board_count(4, search, indexService));
		m.addObject("search", search);
		
		m.setViewName("member/main_search");
		
		return m;
	}
	
	
}
