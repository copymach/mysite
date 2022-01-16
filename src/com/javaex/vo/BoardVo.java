package com.javaex.vo;

public class BoardVo {

//	필드
	private int bno; // 게시물 식별 번호
	private String title; // 게시판 제목
	private String content; // 게시판 내용
	private int hit; // 조회수 카운터
	private String reg_date; // 등록일자
	private int uno; // 작성자번호 (로그인 연계)
	private String id; // 아이디
	private String password; // 패스워드
	private String user_name; // 작성자이름
	
//	생성자 컨스트럭터
	public BoardVo() {
	}

	public BoardVo(int bno, String title, String content, int hit, String reg_date, int uno, String id, String password,
			String user_name) {
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.uno = uno;
		this.id = id;
		this.password = password;
		this.user_name = user_name;
	}
	
	public BoardVo(int uno, String title, String content, String id) {
		this.uno = uno;
		this.title = title;
		this.content = content;
		this.id = id;
	}
	
	public BoardVo(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}
	
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "BoardVo [bno=" + bno + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", uno=" + uno + ", id=" + id + ", password=" + password + ", user_name=" + user_name
				+ "]";
	}



}