package com.five.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Dao {


	protected Connection con;
	protected PreparedStatement ps;
	protected ResultSet rs;
	
	public Dao() {
		try {
			String OracleUrl = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String OracleUser = "board";
			String OraclePasswd = "tiger";

			con = DriverManager.getConnection(OracleUrl, OracleUser, OraclePasswd);
			System.out.println("DB연결 성공");

			
		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Dao();
	}

}