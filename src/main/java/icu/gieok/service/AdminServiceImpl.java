package icu.gieok.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import icu.gieok.dao.AdminDAO;
import icu.gieok.vo.UserVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	public int userListCount(Map<String, Object> map) {
		System.out.println(map.get("startRow"));
		return adminDAO.userListCount(map);
	}

	@Override
	public List<UserVO> getUserList(Map<String, Object> map) {
		return adminDAO.getUserList(map);
	}

}
