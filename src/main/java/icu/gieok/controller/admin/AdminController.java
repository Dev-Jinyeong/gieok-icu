package icu.gieok.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import icu.gieok.service.AdminService;
import icu.gieok.utils.CheckAdmin;
import icu.gieok.vo.BoardVO;
import icu.gieok.vo.UserVO;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private ModelAndView checkUser;
	
	public ModelAndView checkAdmin(HttpSession session) {
		CheckAdmin checkAdmin = new CheckAdmin(session);
		
		return checkAdmin.getCheckAdmin();
	}
	
	/* ===== 유저 목록 ===== */
	@GetMapping("/user_list")
	private ModelAndView userList(HttpSession session,
			HttpServletRequest request, String category, String keyword) {
		
		checkUser = checkAdmin(session);
		
		if(checkUser != null) {
			return checkUser;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		System.out.println(page);
		
		int startRow = (page - 1) * 10 + 1;
		int endRow = page * 10;
		
		if(category == null) {
			category = "";
		}
		
		if(keyword == null) {
			keyword = "";
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("category", category);
		map.put("keyword", keyword);
		
		System.out.println(startRow + " " + endRow);
		System.out.println(category);
		
		int totalCount = adminService.userListCount(map);
		int totalPage = totalCount / 10;
		
		if(totalCount % 10 != 0) {
			totalPage++;
		}
		
		int startPage = 0;
		int endPage = 0;
		
		if(page / 10 < 1 || page == 10) {
			startPage = 1;
			endPage = 10;
			
			if(totalPage < 10) {
				endPage = totalPage;
			}
		} else {
			startPage = page / 10 * 10 + 1;
			endPage = startPage + 9;
			
			if(endPage >= totalPage) {
				endPage = totalPage;
			}
		}
		
		List<UserVO> list = adminService.getUserList(map);
		
		if(list.size() > 0) {
            for(UserVO user : list) {
            	user.setUser_regDate(user.getUser_regDate().substring(0, 10));
            }
        }

		ModelAndView mv = new ModelAndView();
		mv.addObject("user_list", list);
		mv.addObject("page", page);
		mv.addObject("startPage", startPage);
		mv.addObject("endPage", endPage);
		mv.addObject("totalCount", totalCount);
		mv.addObject("totalPage", totalPage);
		mv.addObject("category", category);
		mv.addObject("keyword", keyword);
		mv.setViewName("/admin/user_list");
		
		return mv;
	}
	
}
