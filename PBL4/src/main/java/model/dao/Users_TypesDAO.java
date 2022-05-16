package model.dao;

import java.sql.*;
import java.util.List;

import connect_database.*;
import model.bean.*;

public class Users_TypesDAO {
	
	private static Users_TypesDAO instance;
	private Users_TypesDAO() {};
	
	public Users_TypesDAO GetInstace() {
		if(instance == null) {
			instance = new Users_TypesDAO();
		}
		return instance;
	}
	
	public List<Users_Types> GetAllUser_Types(){
		Users_Types usertypes = null;
		List<Users_Types> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from user_types");
				rs = ps.executeQuery();
				while(rs.next()) {
					usertypes = new Users_Types(rs.getInt(1), rs.getInt(2));
					list.add(usertypes);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print(e);
				e.printStackTrace();
			} finally {
				ConnectDatabase.getInstance().close(ps, rs);
			}
		}
		return list;
	}
	
	public Users_Types InsertUser_Types(Users_Types usertypes) {
		PreparedStatement ps = null;
		if(ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement(
						"insert into user_types values (?,?)");
				ps.setInt(1, usertypes.getIDUser());
				ps.setInt(2, usertypes.getIDNew());
				int row = ps.executeUpdate();
				if(row < 1 )
					usertypes = null;
			}catch(SQLException e) {
				System.out.print(e);
				e.printStackTrace();
				usertypes = null;
			}finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
		return usertypes;
	}
	
	public void DeleteByIDUser(int IDUSer) {
		PreparedStatement ps = null;
		if(ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement(
						"delete from user_types where IDUser = ?");
				ps.setInt(1, IDUSer);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
			}finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
	}
	
	public void DeleteByIDNew(int IDNew) {
		PreparedStatement ps = null;
		if(ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement(
						"delete from user_types where IDNew = ?");
				ps.setInt(1, IDNew);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
			}finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
	}
	
	public void UpdateUser_Types(int IDUser, int IDNew) {
		PreparedStatement ps = null;
		if(ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement(
						"update user_types set IDNew = ? where IDUser = ?");
				ps.setInt(1, IDNew);
				ps.setInt(2, IDUser);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
			}finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
	}
	public static void main(String[] args) {
		Users_TypesDAO nd = new Users_TypesDAO();
		Users_Types ut = new Users_Types(3, 3);
		nd.UpdateUser_Types(3,6);
		
	}
	
}
