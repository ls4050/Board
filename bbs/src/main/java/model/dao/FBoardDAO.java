package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.vo.FBoardVO;

public class FBoardDAO {
	public ArrayList<FBoardVO> listAll(int page) {
		ArrayList<FBoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select id, title, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate ,cnt from fboard where writer = \"admin\"\n"
						+ "union\n"
						+ "select id, title, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate ,cnt from fboard order by field(writer, \"admin\") desc, writedate desc, id desc limit 10 offset "+ page*10 + ";")) {
			FBoardVO vo;
			while (rs.next()) {
				vo = new FBoardVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}
	
	public FBoardVO searchBoardID(String id) {
		FBoardVO vo = null;
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, title, writer, content,date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate,cnt from fboard where id = "+id);) {
			while(rs.next()) {
				vo=new FBoardVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setWriteDate(rs.getString(5));
				vo.setCnt(rs.getInt(6));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return vo;
	}

	public List<FBoardVO> searchTitle(String keyword) {
		List<FBoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, title, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate ,"
								+ "cnt from fboard where title like '%" + keyword + "%'");) {
			FBoardVO vo;
			System.out.println(keyword);
			while (rs.next()) {
				vo = new FBoardVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}
	public List<FBoardVO> searchWriter(String keyword) {
		List<FBoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, title, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate ,"
								+ "cnt from fboard where writer like '%" + keyword + "%'");) {
			FBoardVO vo;
			while (rs.next()) {
				vo = new FBoardVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}
	public List<FBoardVO> searchContent(String keyword) {
		List<FBoardVO> list = new ArrayList<>();
		Connection conn = MySQL.connect();
		try (Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select id, title, writer, date_format(writedate, '%Y년 %m월 %d일 %H시 %i분') writedate ,"
								+ "cnt from fboard where content like '%" + keyword + "%'");) {
			FBoardVO vo;
			while (rs.next()) {
				vo = new FBoardVO();
				vo.setId(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setWriter(rs.getString(3));
				vo.setWriteDate(rs.getString(4));
				vo.setCnt(rs.getInt(5));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MySQL.close(conn);
		return list;
	}

	public boolean insert(FBoardVO vo, HttpSession session) {
		boolean result = true;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into fboard (writer,title, content, writedate) values(?,?,?,now());");) {
//			System.out.println(vo.getMeetingDate());
			pstmt.setString(1, (String) session.getAttribute("id"));
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}

	public boolean delete(int id) {
		boolean result = true;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from fboard where id = ?");) {
			pstmt.setInt(1, id);
			int deleteNum = pstmt.executeUpdate();
			if (deleteNum != 1)
				result = false;
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}

	public boolean update(FBoardVO vo) {
		boolean result = true;
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update fboard set " + "title = ?, " + "content = ?, " + "writedate = now() " + "where id = ?");) {
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		MySQL.close(conn);
		return result;
	}
	
	public void cntUp(int id) {
		Connection conn = MySQL.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("update fboard set cnt=cnt+1 where id = ?");) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int boardNum() {
		int count =0;
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select count(*) from fboard;")) {
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
		return count;
	}
	
	public int boardNum2(String keyword) {
		int count =0;
		Connection conn = MySQL.connect();
		try(Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(
						"select count(*) from fboard where writer like '%"+keyword+"%'");) {
			while(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(SQLException e) {
			e.getStackTrace();
		}
		return count;
	}
	
	
}
