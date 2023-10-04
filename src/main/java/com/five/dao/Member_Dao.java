package com.five.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.five.dto.Account;
import com.five.dto.Notice;
import com.five.dto.Qna;

import util.DBManager;

public class Member_Dao {

	public static Member_Dao m_dao = new Member_Dao();

private Member_Dao() {
		
	}
	private static Member_Dao instance = new Member_Dao();
	public static Member_Dao getInstance() {
		return instance;
	}
	
/////// ouath 2.0 로그인 시스템 ///////////////////////////////////////////////////////////////////////////////
	public Account snsLoginCheck(Account account) {
		String sql = "";
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			
			sql = "SELECT * FROM account where ac_id = '" + account.getAc_id()+"'";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				
				Account account2 = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0, rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account2;
			}
			else {
				
				String sql2 ="insert into account (  ac_id, ac_pw, ac_email, ac_name, ac_nickname,ac_profileimg,ac_phone, ac_type) values (?,?,?,?,?,?,?,?)";
				psmt = conn.prepareStatement(sql2);
				
				psmt.setString(1,account.getAc_id()); 	
				psmt.setString(2,account.getAc_pw()); 	
				psmt.setString(3,account.getAc_id()); 	
				psmt.setString(4,account.getAc_name()); 	
				psmt.setString(5,account.getAc_nickname()); 	
				psmt.setString(6,account.getAc_profileimg()); 
				psmt.setString(7,account.getAc_phone()); 	
				psmt.setInt(8,account.getAc_type()); 	
				psmt.executeUpdate(); 				
				snsLoginCheck(account);
			}
		}
		catch (Exception e) {
			System.out.println("소셜 계정 로그인 오류 : " + e );
		}
		return null;
	}
/////// ouath 2.0 로그인 시스템 ///////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	public boolean email_check(String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_email from account where ac_email=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일 중복체크 오류  : " + e );
		}
		return false;
	}
/////// 이메일 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 인증번호 저장 ////////////////////////////////////////////////////////////////////////////////////
	@SuppressWarnings("resource")
	public boolean email_authentification(String email,String authentification) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select email from email_auth where email=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();

			if(rs.next()) {
				String sql2 = "update email_auth set emaill_auth=? where email=?";
				conn = DBManager.getConnection();
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, authentification);
				psmt.setString(2, email);
				psmt.executeUpdate();
				return true;
				
			}
			else {
				String sql2 = "insert into email_auth ( emaill_auth, email ) values (?,?)";
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, authentification);
				psmt.setString(2, email);
				psmt.executeUpdate();
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일 인증번호 저장 오류  : " + e );
		}
		return false;
	}
/////// 이메일 인증번호 저장 ////////////////////////////////////////////////////////////////////////////////////
	
/////// 이메일 인증번호 ////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean authentification_check(String email,String authentification) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from email_auth where emaill_auth = ? and email = ? ";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, authentification);
			psmt.setString(2, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				// "delete from board where bno="+bno;
				String sql2 = "delete from email_auth where email = ?";
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, email);
				psmt.executeUpdate();
				
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("인증번호 인증 오류  : " + e );
		}
		return false;
	}
	
/////// 이메일 인증번호 ////////////////////////////////////////////////////////////////////////////////////////

	
/////// 아이디 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean id_check(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT ac_id FROM account where ac_id = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("아이디 중복체크 오류 : " + e );
		}
		return false;
	}
	
/////// 아이디 중복체크 ////////////////////////////////////////////////////////////////////////////////////////
	
/////// 맴버 회원가입  ////////////////////////////////////////////////////////////////////////////////////////

	public boolean signup(Account account) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "insert into account ( ac_no,ac_id, ac_pw,ac_email,ac_name,ac_nickname,ac_profileimg,ac_phone ) values (ac_no_seq.nextval,?,?,?,?,?,?,?)";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, account.getAc_id());
			psmt.setString(2, account.getAc_pw());
			psmt.setString(3, account.getAc_email());
			psmt.setString(4, account.getAc_name());
			psmt.setString(5, account.getAc_nickname());
			psmt.setString(6, account.getAc_profileimg());
			psmt.setString(7, account.getAc_phone());
			psmt.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("회원가입 오류 : " + e );
		}
		return false;
	}
	
/////// 맴버 회원가입  ////////////////////////////////////////////////////////////////////////////////////////

/////// 맴버 로그인   ////////////////////////////////////////////////////////////////////////////////////////
	
	public Account account_Login(String id, String pw) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from account where ac_id = ? and ac_pw = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			if(rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account;
			}
		}
		catch (Exception e) {
			System.out.println("로그인 오류 : " + e );
		}
		return null;
	}
	
/////// 맴버 로그인   ////////////////////////////////////////////////////////////////////////////////////////

/////// 회원정보 가져오기 //////////////////////////////////////////////////////////////////////////////////////

	public JSONObject get_account(int ac_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			JSONObject object = new JSONObject();
			String sql = "select * from account where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,ac_no);
			ResultSet rs3;
			rs3 = psmt.executeQuery();
			if(rs3.next()) {
				
				object.put("ac_no", rs3.getInt(1));
				object.put("ac_id", rs3.getString(2));
				object.put("ac_email", rs3.getString(4));
				object.put("ac_name", rs3.getString(5));
				object.put("ac_nickname", rs3.getString(6));
				object.put("ac_profileimg", rs3.getString(7));
				object.put("ac_phone", rs3.getString(8));


				return object;
			}
		}
		catch (Exception e) {
			System.out.println("회원 정보 JSON 오브젝트 오류: " + e );
		}
		return null;
	}
	
/////// 회원정보 가져오기 //////////////////////////////////////////////////////////////////////////////////////
	
/////// 닉네임 중복체크   //////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean nick_check(String nick) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_nickname from account where ac_nickname = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nick);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
/////// 닉네임 중복체크    //////////////////////////////////////////////////////////////////////////////////////	

/////// 핸드폰 중복체크   //////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean phone_check(String phone) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_phone from account where ac_phone = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, phone);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			
		}
		return false;
	}
	
/////// 핸드폰 중복체크    //////////////////////////////////////////////////////////////////////////////////////	
	
/////// 회원정보 수정    //////////////////////////////////////////////////////////////////////////////////////	
	public boolean nick_change(String nickname, int ac_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			String sql = "update account set ac_nickname=? where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			psmt.setInt(2, ac_no);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean email_change(String email, int ac_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			String sql = "update account set ac_email=? where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setInt(2, ac_no);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean phone_change(String phone, int ac_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "update account set ac_phone=? where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, phone);
			psmt.setInt(2, ac_no);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			
		}
		return false;
	}
	
/////// 회원정보 수정    //////////////////////////////////////////////////////////////////////////////////////
	
/////// 회원프로필 수정   //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean profile_change(String img, int ac_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "update account set ac_profileimg=? where ac_no = ?";
			conn = DBManager.getConnection();
			psmt= conn.prepareStatement(sql);
			psmt.setString(1, img);
			psmt.setInt(2, ac_no);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception e) {
			System.out.println("프로필사진 업로드 오류 : " + e);
		}
		return false;
	}
	
/////// 회원프로필 수정    //////////////////////////////////////////////////////////////////////////////////////
	
/////// 회원비밀번호 수정   //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean pw_check(int no, String pw) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from account where ac_pw=? and ac_no=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setInt(2, no);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("비밀번호 체크 오류 :: " + e );
		}
		return false;
	}
	
	public boolean pw_update(int no, String pw) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "update account set ac_pw=? where ac_no=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pw);
			psmt.setInt(2, no);
			psmt.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("비밀번호 수정 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 회원비밀번호 수정   //////////////////////////////////////////////////////////////////////////////////////
	
/////// 아이디  찾기      //////////////////////////////////////////////////////////////////////////////////////
	
	public String find_email(String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_id from account where ac_email = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("이메일로 찾기 오류 :: " + e );
		}
		return null;
	}
	
	public String find_phone(String phone) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_id from account where ac_phone = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, phone);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("핸드폰으로ㄴ 찾기 오류 :: " + e );
		}
		return null;
	}
	
	
/////// 아이디  찾기      //////////////////////////////////////////////////////////////////////////////////////
	
/////// 비밀번호   찾기    //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean email_find2(String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from account where ac_email = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}
		catch (Exception e) {
			System.out.println("이메일로 찾기 오류 :: " + e );
		}
		return false;
	}
	
	public String phone_find2(String phone) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_email from account where ac_phone = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, phone);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("핸드폰으로 찾기 오류 :: " + e );
		}
		return null;
	}
	
	public boolean email_instance_pw(String temp, String email) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "update account set ac_pw=? where ac_email=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, temp);
			psmt.setString(2, email);
			psmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("임시 비밀번호 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 비밀번호  찾기     //////////////////////////////////////////////////////////////////////////////////////

/////// 회원탈퇴    //////////////////////////////////////////////////////////////////////////////////////
	
	public boolean memberout(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "delete from account where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.executeUpdate();
			return true;
		}
		catch (Exception e) {
			System.out.println("회원탈퇴 오류 :: " + e );
		}
		return false;
	}
	
	
/////// 회원탈퇴     //////////////////////////////////////////////////////////////////////////////////////
	
/////// 게임 전적  //////////////////////////////////////////////////////////////////////////////////////
	
	public JSONObject getwin(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT win FROM account where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			JSONObject jsonObject = new JSONObject();
			while(rs.next()) {
				
				
				jsonObject.put("category", "win");
				jsonObject.put("value", rs.getInt(1));
			

			}
			return jsonObject;
		}
		catch (Exception e) {
			System.out.println("전적 가져오기 어류 :: " + e );
		}
		return null;
	}
	public JSONObject getlose(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT lose FROM account where ac_no = ?";
			conn = DBManager.getConnection();
			psmt= conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			JSONObject jsonObject = new JSONObject();
			while(rs.next()) {
				
				
				jsonObject.put("category", "lose");
				jsonObject.put("value", rs.getInt(1));
			

			}
			return jsonObject;
		}
		catch (Exception e) {
			System.out.println("전적 가져오기 어류 :: " + e );
		}
		return null;
	}
/////// 게임 전적     //////////////////////////////////////////////////////////////////////////////////////
	
/////// 다른플레이어 정보//////////////////////////////////////////////////////////////////////////////////////

	public Account getother(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from account where ac_no = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				Account account = new Account(rs.getInt(1), rs.getString(2), null, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 0, rs.getString(10),null,rs.getInt(12),rs.getInt(13));
				return account;
			}
		}
		catch (Exception e) {
			System.out.println("다른회원 가져오기 어류 :: " + e );
		}
		return null;
	}
	
	public String getpro(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_profileimg from account where ac_id = ?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			System.out.println("다른회원 가져오기 어류 :: " + e );
		}
		return null;
	}
	
/////// 다른플레이어 정보//////////////////////////////////////////////////////////////////////////////////////
	
/////// 내글 호출 //////////////////////////////////////////////////////////////////////////////////////


	public JSONArray getallmember() {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_id,ac_profileimg,win,lose from account order by win desc";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			JSONArray array = new JSONArray();
			while(rs.next()) {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("id", rs.getString(1));
				jsonObject.put("profile", rs.getString(2));
				jsonObject.put("win", rs.getInt(3));
				jsonObject.put("lose", rs.getInt(4));
				
				array.put(jsonObject);
			}
			return array;
		}
		catch (Exception e) {
			System.out.println("모든 회원 가져오기 오류 :: " + e );
		}
		return null;
	}
	
	
	public boolean deletepro(int no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "update account set ac_profileimg='default.jpg' where ac_no=?";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			psmt.executeUpdate();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public String getnick(String id) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select ac_nickname from account where ac_id = ?";
			conn = DBManager.getConnection();
			psmt=conn.prepareStatement(sql); 
			psmt.setString(1, id);
			rs=psmt.executeQuery(); 
			if( rs.next() ) {
				return rs.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println("오류나니 ? : " + e);
		}
		return null;
	}
	
	public ArrayList<Notice> getNotice() {
		String sql = "select * from notice";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> nlist = new ArrayList<Notice>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNum(rs.getInt("num"));
				notice.setNtitle(rs.getString("ntitle"));
				notice.setNcontent(rs.getString("ncontent"));
				nlist.add(notice);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nlist;
	}
	public ArrayList<Notice> getNoticeOne(String t) {
		String sql = "select * from notice where ntitle=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> nlist = new ArrayList<Notice>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Notice notice = new Notice();
				notice.setNum(rs.getInt("num"));
				notice.setNtitle(rs.getString("ntitle"));
				notice.setNcontent(rs.getString("ncontent"));
				nlist.add(notice);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nlist;
	}
	public ArrayList<Notice> getNoticeC(String c) {
		String sql = "select * from notice where ntitle = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> nlist = new ArrayList<Notice>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Notice notice = new Notice();
				notice.setNum(rs.getInt("num"));
				notice.setNtitle(rs.getString("ntitle"));
				notice.setNcontent(rs.getString("ncontent"));
				nlist.add(notice);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nlist;
	}
	public void noticeInsert(Notice no) {
		String sql = "insert into notice values(no.nextval, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNtitle());
			pstmt.setString(2, no.getNcontent());
			pstmt.executeUpdate();
		}catch(Exception e) {
			
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	public void noticeDelete(int n) {
		String sql = "delete from notice where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n);
			pstmt.executeUpdate();
		}catch(Exception e) {
			
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	public void noticeUpdate(Notice no, String t) {
		String sql = "update notice set ntitle = ?, ncontent = ? where ntitle = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no.getNtitle());
			pstmt.setString(2, no.getNcontent());
			pstmt.setString(3, t);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public ArrayList<Qna> QnaAll() {
		String sql = "select * from qna";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Qna> nlist = new ArrayList<Qna>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Qna qna = new Qna();
				qna.setQtitle(rs.getString("qtitle"));
				qna.setQuestion(rs.getString("question"));
				qna.setAnswer(rs.getString("anwer"));
				nlist.add(qna);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nlist;
	} 
}
