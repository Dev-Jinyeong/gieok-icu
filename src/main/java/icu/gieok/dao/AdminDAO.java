package icu.gieok.dao;

import java.util.List;
import java.util.Map;

import icu.gieok.vo.UserVO;

public interface AdminDAO {

	int userListCount(Map<String, Object> map);

	List<UserVO> getUserList(Map<String, Object> map);

}
