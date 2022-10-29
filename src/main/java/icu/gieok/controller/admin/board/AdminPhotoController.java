package icu.gieok.controller.admin.board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import icu.gieok.service.PhotoService;
import icu.gieok.utils.CheckAdmin;
import icu.gieok.vo.BoardVO;

@Controller
@RequestMapping("/admin")
public class AdminPhotoController {
	
	@Autowired
	private PhotoService photoService;

	@Resource(name="uploadPath")
	private String uploadPath;
	
	private ModelAndView checkUser;
	
	public ModelAndView checkAdmin(HttpSession session) {
		
		CheckAdmin checkAdmin = new CheckAdmin(session);
		
		return checkAdmin.getCheckAdmin();
	}
	


    /* =====// 게시판 목록 //===== */
    @GetMapping("/photo_event_list")
    public ModelAndView photoEventList(HttpSession session,
            HttpServletRequest request, String sortBy, String category, String keyword) {

    	checkUser = checkAdmin(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}
    	
        int page = 1;
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        int startRow = (page - 1) * 12 + 1;
        int endRow = page * 12;
        
        if(sortBy == null) {
            sortBy = "board_regDate";
        }
        
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
        map.put("sortBy", sortBy);

        int totCnt = photoService.boardListCount(map);
        int totPage = totCnt / 12;
        
        if(totCnt % 12 != 0) {
            totPage++;
        }
        
        int startPage = 0;
        int endPage = 0;
        
        if(page / 10 < 1 || page == 10) {
            startPage = 1;
            endPage = 10;
            
            if(totPage < 10) {
                endPage = totPage;
            }
        } else {
            startPage = page / 10 * 10 + 1;
            endPage = startPage + 9;
            
            if(endPage >= totPage) {
                endPage = totPage;
            }
        }
        
        List<BoardVO> list = photoService.getBoardList(map);
        
        if(list.size() > 0) {
            for(BoardVO board : list) {
                board.setBoard_regDate(board.getBoard_regDate().substring(0, 10));
            }
        }
        
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("board_list", list);
        mv.addObject("page", page);
        mv.addObject("startPage", startPage);
        mv.addObject("endPage", endPage);
        mv.addObject("totCnt", totCnt);
        mv.addObject("totPage", totPage);
        mv.addObject("sortBy", sortBy);
        mv.addObject("category", category);
        mv.addObject("keyword", keyword);
        mv.setViewName("/member/photo_event_list");
        
        return mv;
    }
	
	@PostMapping("/photo_event_listDelete")
	public ModelAndView photoEventListDelete(
			HttpSession session,
			@RequestParam List<Integer> photo_no, int page,
			String sortBy, String category, String keyword) {
		
    	System.out.println(12);
    	
		checkUser = checkAdmin(session);
		
		if(checkUser != null) {
			return null;
		}
		
		List<BoardVO> list = photoService.selectPhotoEventList(photo_no);
		
		for(BoardVO board : list) {
			int index = board.getBoard_img().lastIndexOf("/");
			String dir = board.getBoard_img().substring(0, index);
			String img = board.getBoard_img().substring(index + 1);
			
			System.out.println(uploadPath + dir);
			
			File deleteFile = new File(uploadPath + dir, img);
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}
		
		int listSize = photo_no.size();
		int result = photoService.deletePhotoEventList(photo_no);
		
		String msg = "";
		
		if(listSize == result) {
			msg = "정상적으로 삭제되었습니다";
		} else {
			msg = "삭제 실패";
		}
		
		String url = "/admin/photo_event_list?page=" + page + "&sortBy=" + sortBy + "&category=" + category + "&keyword=" + keyword;

		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		mv.setViewName("message");
		
		return mv;
	}
}

