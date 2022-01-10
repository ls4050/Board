package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.FBoardVO;
import model.vo.MemberVO;

public class MemberDAO {
	   public boolean insert(MemberVO vo) {
		      boolean result = true;
		      Connection conn = MySQL.connect();
		      try (PreparedStatement pstmt = conn.prepareStatement("insert into member (id, name, password, phone) values(?,?,?,?);");){
		         //System.out.println(vo.getMeetingDate());
		         pstmt.setString(1, vo.getId());
		         pstmt.setString(2, vo.getName());
		         pstmt.setString(3, vo.getPwd());
		         pstmt.setString(4, vo.getPhone());
		         pstmt.executeUpdate();         
		      }catch(SQLException e){
		         result = false;
		         e.printStackTrace();
		      }
		      MySQL.close(conn);
		      return result;
		   }
	   
	   public boolean checkID(String id, String pwd) {
		   boolean result = false;
		      Connection conn = MySQL.connect();
		      try (Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery("select id, password from member where id= '" + id + "' and password='" + pwd +"'");){   
		         MemberVO vo = new MemberVO();;
		         while(rs.next()) {
		            vo.setId(rs.getString(1));
		            vo.setPwd(rs.getString(2));
		         }
		         if(vo.getId()!=null) {
		        	 result=true;
		         }
		      }catch(SQLException e) {
		         e.printStackTrace();
		      }
		      MySQL.close(conn);
		      return result;
		   
	   }
}
