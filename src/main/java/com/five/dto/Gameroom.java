package com.five.dto;

public class Gameroom {

	private int groom_no;
	private String groom_name;
	private String groom_pw;
	
	public Gameroom() {
		// TODO Auto-generated constructor stub
	}

	public Gameroom(int groom_no, String groom_name, String groom_pw) {
		super();
		this.groom_no = groom_no;
		this.groom_name = groom_name;
		this.groom_pw = groom_pw;
	}

	@Override
	public String toString() {
		return "Gameroom [groom_no=" + groom_no + ", groom_name=" + groom_name + ", groom_pw=" + groom_pw + "]";
	}

	public int getGroom_no() {
		return groom_no;
	}

	public void setGroom_no(int groom_no) {
		this.groom_no = groom_no;
	}

	public String getGroom_name() {
		return groom_name;
	}

	public void setGroom_name(String groom_name) {
		this.groom_name = groom_name;
	}

	public String getGroom_pw() {
		return groom_pw;
	}

	public void setGroom_pw(String groom_pw) {
		this.groom_pw = groom_pw;
	}
	
	
	
	
}
