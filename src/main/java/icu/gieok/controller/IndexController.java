package icu.gieok.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icu.gieok.service.BoardService;
import icu.gieok.service.BoardWithService;
import icu.gieok.service.IndexService;
import icu.gieok.service.MapService;
import icu.gieok.service.PhotoService;
import icu.gieok.utils.TotalList;
import icu.gieok.vo.UserVO;
	
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
		m.addObject("photoimg", list.photo_likeImg(indexService));
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
	
	
	/*======로그인 ======*/
	
	@GetMapping("/login")
	public String userLogin(HttpServletRequest request) {

		List<String> exception = new ArrayList<>();
		exception.add("http://localhost:8080/member/join");
		exception.add("http://localhost:8080/login");
		String referer = request.getHeader("Referer");
		if(!exception.contains(referer)) {
			request.getSession().setAttribute("referer", referer);
		}
		
		exception.add("joinOK");
	    exception.add("find_id_pw");
	    exception.add("board_with_sinchung");
	    if(referer!=null) {
		    if(referer.contains("joinOK")||referer.contains("find_id_pw")) {
		    	request.getSession().setAttribute("referer", "/");
		    }
		      
		    if(referer.contains("board_with_sinchung")) {
		         request.getSession().setAttribute("referer", "/board_with_list");
		    }
	    }


		return "/member/login";
	}
	
//	@ResponseBody
//	@PostMapping("/login")
//	public Map<String, String> userLogin(@RequestBody Map<String, String> map,
//										HttpSession session, HttpServletRequest request) {
//		
//		String msg = "";
//		String url = "";
//		
//		UserVO user = userService.checkAuth(map);
//		
//		if(user==null) {
//			msg = "아이디 혹은 비밀번호가 일치하지 않아요 :(";
//			url = "/login";
//		}else {
//			int user_auth = user.getUser_auth();
//			
//			if(user_auth==0) {
//				msg = "이메일 인증이 필요합니다! :(";
//				url = "/login";
//			}else if(user_auth==1) {
//				url = (String) session.getAttribute("referer");
//				if(url==null) {
//					url = "/";
//				}
//				
//				Map<String, Object> countMsg = new HashMap<>();
//				countMsg.put("message_receiver", user.getUser_id());
//				
//				int msgCount = userService.countUnreadMessage(countMsg);
//				
//				session.setAttribute("code", user.getUser_code());
//				session.setAttribute("id", user.getUser_id());
//				session.setAttribute("name", user.getUser_name());
//				session.setAttribute("grade", user.getUser_grade());
//				session.setAttribute("profile", user.getUser_profile());
//				session.setAttribute("msgCount", msgCount);
//			}
//		}
//		
//		
//		Map<String, String> result = new HashMap<>();
//		result.put("msg", msg);
//		result.put("url", url);
//		
//		
//		return result;
//	}
	
	
	
	
	
	
	
	
}
