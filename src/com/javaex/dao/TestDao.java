package com.javaex.dao;

import com.javaex.vo.UserVo;

public class TestDao {

	public static void main (String [] args) {

//		insert 기능 정상 동작하는지 테스트
		
		UserDao userDao = new UserDao();
		UserVo userVo = new UserVo("ccc", "1234", "둘리", "male");
		
//		회원정보 넣어보기
		userDao.insert(userVo);

		
	}
	

}
