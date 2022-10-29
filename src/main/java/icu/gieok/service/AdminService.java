package icu.gieok.service;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.UserVO;

public interface AdminService {

	int userListCount(Map<String, Object> map);

	List<UserVO> getUserList(Map<String, Object> map);

}
