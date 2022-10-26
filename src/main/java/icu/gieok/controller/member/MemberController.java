package icu.gieok.controller.member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import icu.gieok.service.UserService;
import icu.gieok.utils.CheckAdmin;
import icu.gieok.utils.CheckMember;
import icu.gieok.utils.MailSendService;
import icu.gieok.vo.UserVO;

@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Autowired
	private UserService userService;

	@Autowired 
	private MailSendService mss;
	
	private ModelAndView checkUser; 
	 
	
	// 회원 여부 확인 
	public ModelAndView checkMember(HttpSession session) {
		
		CheckMember checkMember = new CheckMember(session);
		
		return checkMember.getcheckMember();
		
	}
	
	
	@GetMapping("/join")
	public String userJoin() {
		return "/member/join";
	}
	
	@ResponseBody
	@PostMapping("/idDupCheck")
	public boolean idDupCheck(@RequestBody Map<String, String> map) {
		
		String id_input = map.get("id_input");
		boolean idDup = true;
		UserVO user = userService.idDupCheck(id_input);
		if(user==null) {
			idDup = false;
		}
		
		return idDup;
	}
	
	@PostMapping("/joinEmail")
	public ModelAndView userJoinEmail(UserVO user) {
		
		if(user.getUser_terms()==null) {
			user.setUser_terms("disagree");
		}else {
			user.setUser_terms("agree");
		}
		
		userService.userInsert(user);

        //임의의 authKey 생성 & 이메일 발송
		
		String email = user.getUser_email()+"@"+user.getUser_domain();
        String user_key = mss.sendAuthMail(email);
        user.setUser_key(user_key);

        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user.getUser_id());
        map.put("authKey", user.getUser_key());

      //DB에 authKey 업데이트
      userService.updateAuthKey(map);
      
      ModelAndView mv = new ModelAndView();

      String msg = "인증메일이 발송되었습니다!";
      String url = "/";
      
      mv.addObject("msg", msg);
      mv.addObject("url", url);
      mv.setViewName("message");
      
      return mv;
	}
	
	@GetMapping("/joinOK")
	public ModelAndView userJoinOK(@RequestParam Map<String, String> map) {
		
		String user_email = map.get("email").split("@")[0];
		String user_domain = map.get("email").split("@")[1];
		
		map.put("user_email", user_email);
		map.put("user_domain", user_domain);
		
		userService.updateAuth(map);
		
		ModelAndView mv = new ModelAndView();
		String msg = "인증이 완료되었습니다! 로그인을 해주세요:)";
		String url = "login";
		mv.addObject("msg", msg);
		mv.addObject("url", url);
		mv.setViewName("message");
		
		return mv;
	}
	
	
	@GetMapping("/login")
	public String userLogin(HttpServletRequest request) {

		List<String> exception = new ArrayList<>();
		exception.add("http://localhost:8080/member/join");
		exception.add("http://localhost:8080/member/login");
		String referer = request.getHeader("Referer");
		if(!exception.contains(referer)) {
			request.getSession().setAttribute("referer", referer);
		}
		
		exception.add("joinOK");
		exception.add("find_id_pw");
		if(referer.contains("joinOK")||referer.contains("find_id_pw")) {
			request.getSession().setAttribute("referer", "/");
		}

		return "/member/login";
	}
	
	@ResponseBody
	@PostMapping("/login")
	public Map<String, String> userLogin(@RequestBody Map<String, String> map,
										HttpSession session, HttpServletRequest request) {
		
		String msg = "";
		String url = "";
		
		UserVO user = userService.checkAuth(map);
		
		if(user==null) {
			msg = "아이디 혹은 비밀번호가 일치하지 않아요 :(";
			url = "/member/login";
		}else {
			int user_auth = user.getUser_auth();
			
			if(user_auth==0) {
				msg = "이메일 인증이 필요합니다! :(";
				url = "/member/login";
			}else if(user_auth==1) {
				url = (String) session.getAttribute("referer");
				if(url==null) {
					url = "/";
				}
				session.setAttribute("code", user.getUser_code());
				session.setAttribute("id", user.getUser_id());
				session.setAttribute("name", user.getUser_name());
				session.setAttribute("grade", user.getUser_grade());
				session.setAttribute("profile", user.getUser_profile());
			}
		}
		
		
		Map<String, String> result = new HashMap<>();
		result.put("msg", msg);
		result.put("url", url);
		
		
		return result;
	}
	
	@GetMapping("/logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
	
	  /* =====// 회원 정보 수정 페이지 이동 //===== */
    @GetMapping("/inform_edit")
    public ModelAndView userInformEdit(HttpSession session) {
    	
    	checkUser = checkMember(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}

        String user_id = (String) session.getAttribute("id");
        UserVO user = userService.userSelect(user_id);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/member/inform_edit");

        return mv;
    }

    /* =====// 회원 정보 수정 //===== */
    @ResponseBody
    @PostMapping("/inform_edit")
    public UserVO userInformEditOK(
            @RequestParam("profile") MultipartFile file,
            @ModelAttribute UserVO user,
            HttpSession session) {
        /**
         * BindingResult -> 프로필 이름값"profile", 멀티파일 file을 연결한 결과값
         * ==> 연결 성공 여부 확인
         * :: 검증시 오류?
         */

        // 파일 객체 생성
        String user_profile = (String) session.getAttribute("profile");

        // if(result.hasErrors()) { // 매칭되지않으면(에러발생시)
        // }
        user.setUser_profile(user_profile); // 로그인한 사용자의 프로필 이미지

        int user_code = (int) session.getAttribute("code");
        String user_id = (String) session.getAttribute("id");

        if (file.getSize() > 0) {
            try {
                String originalFileName = file.getOriginalFilename();
                String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

                String reFileName = user_code + user_id + "profile" + extension;
                File user_image = new File(uploadPath + "profile", reFileName); // 경로, 파일명

                file.transferTo(user_image); // 멀티파트 파일로 프로필 이름값이 담긴 사진을 불러와서 옮겨줌 -> user_image
                user.setUser_profile(reFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // 로그인 할때 => default.jpg
        // 수정 => image1.jpg

        int re = userService.updateUserInform(user);

        if (re != 1) {
            user = null;
        }

        session.setAttribute("profile", user.getUser_profile());

        return user;
    }

    /* =====// 비밀번호 변경 //===== */
    @GetMapping("/pw_edit")
    public ModelAndView userPwEdit(HttpSession session) {
    	
    	checkUser = checkMember(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}
    	
    	
        return new ModelAndView("/member/pw_edit");
    }

    @PostMapping("/pw_editOK")
    public ModelAndView userPwEditOK(
            @RequestParam String pw,
            @RequestParam String user_pw,
            HttpSession session) {

    	checkUser = checkMember(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}
    	
    	
        String user_id = (String) session.getAttribute("id");

        Map<String, String> map = new HashMap<>();
        map.put("user_id", user_id);
        map.put("user_pw", user_pw);

        String msg = "";
        String url = "";

        UserVO user = userService.userSelect(user_id);

        if (pw.equals(user.getUser_pw())) {
            if (user_pw.equals(pw)) {
                msg = "기존과 다른 비밀번호를 입력해주세요";
                url = "/pw_edit";
            } else {
                int result = userService.updateUserPw(map);

                if (result == 1) {
                    msg = "변경 완료";
                    url = "/";
                } else {
                    msg = "시스템 오류";
                    url = "/pw_edit";
                }
            }
        } else {
            msg = "비밀번호가 다릅니다";
            url = "/pw_edit";
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", msg);
        mv.addObject("url", url);
        mv.setViewName("message");

        return mv;
    }

    /* =====// 아이디, 비밀번호 찾기 //===== */
    @GetMapping("/find_id_pw")
    public String userIdPwFind(HttpSession session) {
    	
        return "/member/find_id_pw";
    }

    @ResponseBody
    @PostMapping("/find_id_pwOK")
    public Map<String, String> userIdPwFindOK(@RequestParam Map<String, String> map) {
    	
    	
    	
    	if(checkUser != null) {
    		String error = "세션이 만료되었습니다. 다시 로그인 해주세요";
    		Map<String, String> errorMap = new HashMap<>();
    		errorMap.put("msg", error);
    		errorMap.put("fail", "1");
    		return errorMap;
    	}

        String user_email = map.get("user_email");
        String user_domain = map.get("user_domain");
        String user_id = map.get("user_id"); // 아이디찾기, 비밀번호찾기 구분

        String msg = "";

        UserVO user = userService.userIdPwFind(map);
        if (user == null) {
            msg = "해당하는 정보가 없습니다.";
        } else {
            if (user_id == null) { // 아이디 찾기
                msg = "아이디는 " + user.getUser_id() + " 입니다";
            } else { // 아이디 찾기
                if (user_id == "") { // 비밀번호 찾기
                    msg = "아이디를 입력해주세요";
                } else {
                    msg = "비밀번호는 " + user.getUser_pw() + " 입니다";
                }
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put("msg", msg);

        return result;
    }

    /* =====// 회원 탈퇴 //===== */
    @GetMapping("/leave")
    public ModelAndView userLeave(HttpSession session) {
    	checkUser = checkMember(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}
    	
        return new ModelAndView("/member/leave");
    }

    @PostMapping("/leaveOK") // name이 user_pw인 값을 찾아서 String 'user_pw'에 넣음(변수명을 기준으로)
    public ModelAndView userLeaveOK(
            @RequestParam String user_pw,
            HttpSession session) {
    	
    	checkUser = checkMember(session);
    	
    	if(checkUser != null) {
    		return checkUser;
    	}
    	
    	
        // @RequestParam("네임값")
        String user_id = (String) session.getAttribute("id");

        String msg = "";
        String url = "";

        UserVO user = userService.userSelect(user_id);
        if (user_pw.equals(user.getUser_pw())) {
            int result = userService.userDelete(user_id);
            if (result == 1) {
                msg = "탈퇴 완료";
                url = "/";
                session.invalidate(); // 세션값 삭제
            } else {
                msg = "시스템 오류";
                url = "/leave";
            }
        } else {
            msg = "비밀번호가 다릅니다";
            url = "/leave";
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", msg);
        mv.addObject("url", url);
        mv.setViewName("message");

        return mv;
    }
    
    /* =====// 이메일 중복확인 //===== */
    @ResponseBody
    @PostMapping("/emailDupCheck")
    public boolean emailDupCheck(@RequestBody Map<String, String> map) {

        boolean emailDup = true;
        int user = userService.emailDupCheck(map);

        if (user == 0) {
            emailDup = false;
        }

        return emailDup;

    }


	

}
