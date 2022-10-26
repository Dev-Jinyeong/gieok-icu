package icu.gieok.controller.member.board;

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

import icu.gieok.service.BoardWithService;
import icu.gieok.utils.CheckMember;
import icu.gieok.vo.AttrVO;
import icu.gieok.vo.BoardVO;
import icu.gieok.vo.CityVO;
import icu.gieok.vo.ProvinceVO;

@Controller
public class WithController {

	@Autowired
	private BoardWithService boardWithService;
	
	private ModelAndView checkUser;
	
	
	// 회원 여부 확인 
	public ModelAndView checkMember(HttpSession session) {
		
		CheckMember checkMember = new CheckMember(session);
		
		return checkMember.getcheckMember();
		
	}
	
	
	// 동행 게시판 작성 뷰
	@GetMapping("/board_with_write")
	public ModelAndView board_with_write(HttpSession session, String category, String keyword, int page) {
		
		checkUser = checkMember(session);
		
		if(checkUser != null) {
			
			return checkUser;
			
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.addObject("category", category);
		mv.addObject("keyword", keyword);
		mv.setViewName("/member/board_with_write");
		
		return mv;
		
	}
	
	
	// 동행 게시판 작성 시 (도 선택)
	@ResponseBody
	@PostMapping("/board_with_province")
	public List<ProvinceVO> listProvince(){
		
		List<ProvinceVO> provinces = boardWithService.getProvinceList();
		
		return provinces;
		
	}
	
	
	// 동행 게시판 작성 시 (시 선택)
	@ResponseBody
	@PostMapping("/board_with_city")
	public List<CityVO> listCity(@RequestBody Map<String, String> place){
		
		String province_id = place.get("province_id");
		List<CityVO> cities = boardWithService.getCityList(province_id);
		
		return cities;
		
	}
	
	
	// 동행 게시판 작성 시 (명소 선택)
	@ResponseBody
	@PostMapping("/board_with_attr")
	public List<AttrVO> listAttr(@RequestBody Map<String, String> place){
		
		String city_id = place.get("city_id");
		List<AttrVO> attres = boardWithService.getAttrList(city_id);
		
		return attres;
		
	}
	
	
	// 동행 게시판 작성 및 저장 폼
	@PostMapping("/board_with_write")
	public ModelAndView board_with_write_ok(String with_province_name, String with_city_name, String with_attr_name,
			BoardVO bw, HttpServletRequest request, HttpSession session,
			String category, String keyword, int page) {
		
		checkUser = checkMember(session);
		
		if(checkUser != null) {
			
			return checkUser;
			
		}
		
		String id = (String)session.getAttribute("id");
		int code = (int)session.getAttribute("code");
		bw.setBoard_writer(id);
		bw.setUser_code(code);
		
//		System.out.println("=================");
//		System.out.println((String)session.getAttribute("id"));
//		System.out.println("=================");
//		System.out.println((int)session.getAttribute("code"));
//		System.out.println("=================");
//		System.out.println(bw.getBoard_startDay());
//		System.out.println("=================");
		
		String with_board_location = with_province_name + " " + with_city_name + " " + with_attr_name;
		
		if (with_city_name == null) {
			
			with_board_location = with_province_name;
			
		}
		
		else if (with_attr_name == null) {

			with_board_location = with_province_name + " " + with_city_name;
			
		}
		
		bw.setBoard_location(with_board_location.trim());
		
//		System.out.println("===================");
//		System.out.println(with_board_location.trim());
//		System.out.println("===================");
//		System.out.println(bw.getBoard_startDay());
//		System.out.println("===================");
//		System.out.println(bw.getBoard_endDay());
//		System.out.println("===================");
		
		int res = boardWithService.insertWith(bw);
		
		String msg = "";
		
		if (res == 1) {
			
			msg = "동행 등록이 완료되었습니다! 😊";
			
		}
		else {
			
			msg = "에러발생! 시스템 관리자에게 문의하세요. 😭";
			
		}
		
		
		String url = "/board_with_list?page=" + page + "&category=" + category + "&keyword=" + keyword;
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		mv.setViewName("message");
		
		return mv;
		
	}
	
	
	// 동행 게시판 목록 뷰
	@GetMapping("/board_with_list")
	public ModelAndView board_with_list(HttpServletRequest request, HttpSession session,
			String category, String keyword) {
		
		// Pagination
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 목록 개수 - Pagination
		int startRow = (page - 1) * 5 + 1;  // 현재 페이지 첫번째 게시물 값
		int endRow = page * 5;  // 현재 페이지 마지막 게시물 값
		
		// 검색 - Pagination
		if (category == null) {
			category = "";
		}
		
		if (keyword == null) {
			keyword = "";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("category", category);
		map.put("keyword", keyword);
		
		// Total - Pagination
		int totalCount = boardWithService.countWith(map); // 테이블 내 해당 게시물 전체 개수
		int totalPage = (totalCount / 5);  // 전체 페이지 수
		if ((totalCount % 5) != 0) {
			totalPage++;  // 나머지 게시물을 위해서 페이지++
		}
		
		// Num - Pagination(한 싸이클의 페이지 개수)
		int startPage = 1;
		int endPage = 1;
		
		if (page / 10 < 1 || page == 10) {  // 1 ~ 10까지는 → endPage = 10
			
			endPage = 10;
			
			if (totalPage < 10) {  // totalPage가 10보다 작으면
				endPage = totalPage;
			}
			
		}
		else {
			startPage = ((page / 10) * 10) + 1;  // startPage = 11..21..31..
			endPage = startPage + 9;  // endPage = 10..20..30..
			
			if (endPage >= totalPage) {  // 만약 endPage가 totalPage보다 크거나 같으면
				endPage = totalPage;	// endPage가 totalPage가 된다.
			}
			
		}
		
		// 게시물 목록
		List<BoardVO> with_li_list = boardWithService.getWithList(map);
		
		if (with_li_list.size() > 0) {
			for (BoardVO with : with_li_list) {
				with.setBoard_startDay(with.getBoard_startDay().substring(0, 10));
				with.setBoard_endDay(with.getBoard_endDay().substring(0, 10));
			}
		}
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("totalPage", totalPage);
		mv.addObject("totalCount", totalCount);
		mv.addObject("category", category);
		mv.addObject("keyword", keyword);
		mv.addObject("with_li_list", with_li_list);
		
		mv.setViewName("/member/board_with_list");
		
		return mv;
		
	}
	
	
}


