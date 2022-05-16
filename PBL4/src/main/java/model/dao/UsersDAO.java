package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import connect_database.*;
import model.bean.*;

public class UsersDAO {
	private static UsersDAO instance;

	private UsersDAO() {
	};

	public UsersDAO GetInstace() {
		if (instance == null) {
			instance = new UsersDAO();
		}
		return instance;
	}

	public List<Users> GetAllUsers() {
		Users users = null;
		List<Users> list = new ArrayList<Users>();
		PreparedStatement ps = null;
		ResultSet rs = null;// con trỏ
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from users");
				rs = ps.executeQuery();
				while (rs.next()) {
					users = new Users(rs.getInt(1), rs.getString(2), rs.getString(3));
					list.add(users);
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

	public int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(IDUser) from Users");
				rs = ps.executeQuery();
				if (rs.next()) {
					value = rs.getInt(1);
				} else {
					value = 0;
				}
			} catch (SQLException ex) {
				System.out.println("Get next id user fail!");
				ex.printStackTrace();
			} finally {
				ConnectDatabase.getInstance().close(ps, rs);
			}
		}
		return value + 1;
	}

	public Users InsertUser(Users users) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into users values (?,?,?)");
				// ps.setNull(1, java.sql.Types.INTEGER);
				ps.setInt(1, users.getIDUser());
				ps.setString(2, users.getMail());
				ps.setString(3, users.getPass());
				int row = ps.executeUpdate();
				if (row < 1)
					users = null;
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
				users = null;
			} finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
		return users;
	}

	public Users updateUser(Users users) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn()
						.prepareStatement("update users set Mail = ?, Pass = ? where IDUser = ? ");

				ps.setString(1, users.getMail());
				ps.setString(2, users.getPass());
				ps.setString(3, String.valueOf(users.getIDUser()));
				int row = ps.executeUpdate();
				if (row < 1) {
					users = null;
				}
			} catch (SQLException ex) {
				System.out.println("Update users fail!" + ex.toString());
				users = null;
			} finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
		return users;
	}

	public void DeleteByID(int ID) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from users where IDUser = ?");
				ps.setString(1, String.valueOf(ID));
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
			} finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
	}
}
