package com.javaex.vo;

public class BoardVo {

//	필드
	private int no; // 게시물 식별 번호
	private String title; // 게시판 제목
	private String content; // 게시판 내용
	private int hit; // 조회수 카운터
	private String reg_date; // 등록일자
	private String user_no; // 작성자번호 (로그인 연계)
	private String user_name; // 작성자이름

	
//	생성자 컨스트럭터
	public BoardVo() {
	}

	public BoardVo(int no, String title, String content, int hit, String reg_date, String user_no, String user_name) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.reg_date = reg_date;
		this.user_no = user_no;
		this.user_name = user_name;
	}

//	메서드 gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

//	메서드 일반
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", user_no=" + user_no + ", user_name=" + user_name + "]";
	}

}
