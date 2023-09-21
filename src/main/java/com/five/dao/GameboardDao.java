package com.five.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.five.dto.Building;
import com.five.dto.Gameboard;
import com.five.dto.Goldkey;

import util.DBManager;

public class GameboardDao{

	public static GameboardDao gameboardDao = new GameboardDao();
	
	
	// 1. 게임판 출력
	public ArrayList<Gameboard> getgameboardlist (){
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			ArrayList<Gameboard> gblist = new ArrayList<>();
			String sql = "select * from gameboard";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Gameboard gameboard = new Gameboard(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				gblist.add(gameboard);
			}
			return gblist;
		}
		catch(Exception e) {System.out.println("게임판 출력 오류 : "+e);}
		return null;
	}

	// 2. 도시 정보 출력
	public Gameboard getcityinfo(int gb_location) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from gameboard where gb_location="+gb_location;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				Gameboard gameboard = new Gameboard(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
				return gameboard;
			}
		}catch(Exception e) {System.out.println("도시정보출력 오류 : "+e);}
		return null;
	}
	
	// 3. 도시구매
	public boolean buycity(int p_no, int gb_location, int bc_no, int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "insert into building(p_no,bc_no,gb_location,groom_no) values(?,?,?,?)";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, p_no);
			psmt.setInt(2, bc_no);
			psmt.setInt(3, gb_location);
			psmt.setInt(4, groom_no);
			psmt.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("도시구매 오류 : "+e);}
		return false;
	}
	
	// 4. 건물 판매 금액 출력
	public int sellcity(int gb_location, int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from gameboard left join building on building.gb_location = gameboard.gb_location where gameboard.gb_location="+gb_location+" and building.groom_no="+groom_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int sum=0;
			while(rs.next()) {
				if(rs.getInt(12)==1) {
					sum += rs.getInt(3)/2;
				}else if(rs.getInt(12)==2) {
					sum += rs.getInt(4)/2;
				}else if(rs.getInt(12)==3) {
					sum += rs.getInt(5)/2;
				}else if(rs.getInt(12)==4) {
					sum += rs.getInt(6)/2;
				}
			}
			return sum;
			
		}catch(Exception e) {System.out.println("건물판매 금액 출력 오류 : "+e);}
		return 0;
	}
	
	// 5. 통행료 출력
	public int getfee(int gb_location,int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs5 = null;
		try {
			int fee = 0;
			String sql = "select * from gameboard left join building on building.gb_location = gameboard.gb_location where gameboard.gb_location="+gb_location+" and building.groom_no="+groom_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs5 = psmt.executeQuery();
			while(rs5.next()) {
				if(rs5.getInt(12)==1) {
					fee += rs5.getInt(7);
				}else if(rs5.getInt(12)==2) {
					fee += rs5.getInt(8);
				}else if(rs5.getInt(12)==3) {
					fee += rs5.getInt(9);
				}else if(rs5.getInt(12)==4) {
					fee += rs5.getInt(10);
				}
			}
			return fee;
		}catch(Exception e) {System.out.println("통행료 출력 오류 : "+e);}
		return 0;
	}
	
	// 6. 건물 정보 출력
	public ArrayList<Building> getbuilding(int gb_location, int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs4 = null;
		try {
			ArrayList<Building> blist = new ArrayList<Building>();
			String sql = "select * from building where gb_location="+gb_location+" and groom_no ="+groom_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs4 = psmt.executeQuery();
			while(rs4.next()) {
				Building building = new Building(rs4.getInt(1), rs4.getInt(2), rs4.getInt(3), rs4.getInt(4), rs4.getInt(5));
				blist.add(building);
			}
			return blist;
		}catch(Exception e) {System.out.println("건물 정보 출력 오류 : "+e);}
		return null;
	}
	
	// 7. 내도시 정보 출력
	public JSONArray getmycity(int p_no, int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs2 = null;
		try {
			String sql = "SELECT * FROM gameboard join building on gameboard.gb_location=building.gb_location where p_no="+p_no+" and groom_no="+groom_no+" order by gameboard.gb_location";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs2 = psmt.executeQuery();
			JSONArray jsonArray = new JSONArray();
			while(rs2.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("gb_location", rs2.getInt(1));
				jsonObject.put("cityname", rs2.getString(2));
				jsonObject.put("gb_emptyprice", rs2.getInt(3));
				jsonObject.put("gb_villaprice", rs2.getInt(4));
				jsonObject.put("gb_buildingprice", rs2.getInt(5));
				jsonObject.put("gb_hotelprice", rs2.getInt(6));
				jsonObject.put("gb_emptyfee", rs2.getInt(7));
				jsonObject.put("gb_villafee", rs2.getInt(8));
				jsonObject.put("gb_buildingfee", rs2.getInt(9));
				jsonObject.put("gb_hotelfee", rs2.getInt(10));
				jsonObject.put("bc_no", rs2.getInt(12));
				jsonArray.put(jsonObject);
			}
			return jsonArray;
		}catch(Exception e) {System.out.println("내 도시정보 출력 오류 : "+e);}
		return null;
	}
	
	// 8. 선택한 도시 판매
	public boolean deletebuilding(int gb_location, int p_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "delete from building where gb_location="+gb_location+" and p_no="+p_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("도시 판매 오류 : "+e);}
		return false;
	}
	
	// 8-2 내 도시 전체 판매
	public boolean deleteallmyb(int groom_no, int p_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			String sql = "delete from building where p_no="+p_no+" and groom_no="+groom_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("내 도시 전체 판매 오류  : "+e);}
		return false;
	}
	
	// 9. 황금열쇠 정보 출력
	public Goldkey getgoldkey(int gk_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs3 = null;
		try {
			String sql = "select * from goldkey where gk_no="+gk_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs3 = psmt.executeQuery();
			if(rs3.next()) {
				Goldkey goldkey = new Goldkey(rs3.getInt(1), rs3.getString(2), rs3.getString(3), rs3.getInt(4));
				return goldkey;
			}
		}catch(Exception e) {System.out.println("황금열쇠 정보 출력 오류 : "+e);}
		return null;
	}
	
	// 건물 종류 출력(황금열쇠)
	public JSONObject getbclist(int p_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {			
			String sql = "SELECT count(*),bc_no FROM building where p_no="+p_no+" group by bc_no";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("empty", 0);
			jsonObject.put("villa", 0);
			jsonObject.put("building", 0);
			jsonObject.put("hotel", 0);
			while(rs.next()) {
				if(rs.getInt(2)==1) {
					jsonObject.put("empty", rs.getInt(1));
				}else if(rs.getInt(2)==2) {
					jsonObject.put("villa", rs.getInt(1));
				}else if(rs.getInt(2)==3) {
					jsonObject.put("building", rs.getInt(1));
				}else if(rs.getInt(2)==4) {
					jsonObject.put("hotel", rs.getInt(1));
				}
			}
			return jsonObject;
		}catch(Exception e) {System.out.println("건물 종류 출력 오류 : "+e);}
		return null;
	}
	
	// 각 도시 통행료 출력
	public JSONArray getcityfee(int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rss = null;
		try {
			String sql = "SELECT a.*,b.*,c.p_order FROM building as a join gameboard as b on a.gb_location= b.gb_location join player as c on a.p_no = c.p_no where a.groom_no ="+groom_no+" order by b.gb_location";
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			rss = psmt.executeQuery();
			JSONArray jsonArray = new JSONArray();
			while(rss.next()) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("type", "cityfeeinfo");
				jsonObject.put("gb_location", rss.getInt(4));
				jsonObject.put("cityname", rss.getString(7));
				jsonObject.put("gb_emptyprice", rss.getInt(8));
				jsonObject.put("gb_villaprice", rss.getInt(9));
				jsonObject.put("gb_buildingprice", rss.getInt(10));
				jsonObject.put("gb_hotelprice", rss.getInt(11));
				jsonObject.put("gb_emptyfee", rss.getInt(12));
				jsonObject.put("gb_villafee", rss.getInt(13));
				jsonObject.put("gb_buildingfee", rss.getInt(14));
				jsonObject.put("gb_hotelfee", rss.getInt(15));
				jsonObject.put("bc_no", rss.getInt(2));
				jsonObject.put("p_no", rss.getInt(3));
				jsonObject.put("p_order", rss.getInt(16));
				jsonArray.put(jsonObject);
			}
			return jsonArray;
		}catch(Exception e) {System.out.println("각 도시 통행료 출력 오류 : "+e);}
		return null;
	}
	
	// 게임 종료 후 방 삭제
	public boolean deletegroom(int groom_no) {
		Connection conn = null;
		PreparedStatement psmt = null;
		try {
			String sql = "delete from gameroom where groom_no="+groom_no;
			conn = DBManager.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
			return true;
		}catch(Exception e) {System.out.println("게임 종료 후 방삭제 오류 : "+e);}
		return false;
	}
	
	
}
