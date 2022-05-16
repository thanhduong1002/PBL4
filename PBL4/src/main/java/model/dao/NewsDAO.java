package model.dao;

import connect_database.*;
import model.bean.*;
import java.sql.*;
import java.util.List;

public class NewsDAO {
	private static NewsDAO instance;

	private NewsDAO() {
	};

	public static NewsDAO getInstace() {
		if (instance == null) {
			instance = new NewsDAO();
		}
		return instance;
	}

	public List<News> getAllNews() {
		News news = null;
		List<News> list = null;
		PreparedStatement ps = null;
		ResultSet rs = null;// con trỏ
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from news");
				rs = ps.executeQuery();
				while (rs.next()) {
					news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(5), rs.getInt(6));
					list.add(news);
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

	public News InsertNews(News news) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into news values (?,?,?,?,?,?,?)");
				// ps.setNull(1, java.sql.Types.INTEGER);
				ps.setInt(1, news.getID());
				ps.setString(2, news.getTitle());
				ps.setString(3, news.getLink());
				ps.setString(4, news.getDateP());
				ps.setString(5, news.getLinkImg());
				ps.setString(6, news.getType());
				ps.setInt(7, news.getGroup());
				int row = ps.executeUpdate();
				if (row < 1)
					news = null;
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
				news = null;
			} finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
		return news;
	}

	public int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idnew) from news");
				rs = ps.executeQuery();
				if (rs.next()) {
					value = rs.getInt(1);
				} else {
					value = 0;
				}
			} catch (SQLException ex) {
				System.out.println("Get next id new fail!");
				ex.printStackTrace();
			} finally {
				ConnectDatabase.getInstance().close(ps, rs);
			}
		}
		return value + 1;
	}

	public void DeleteByDate(String date1, String date2) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
			try {
				ps = ConnectDatabase.getInstance().getCnn()
						.prepareStatement("delete from news where date BETWEEN ? AND ?");
				ps.setString(1, date1);
				ps.setString(2, date2);
				ps.executeUpdate();
			} catch (SQLException e) {
				System.out.print(e);
				e.printStackTrace();
			} finally {
				ConnectDatabase.getInstance().close(ps);
			}
		}
	}

	public static void main(String[] args) {
		NewsDAO nd = new NewsDAO();
		News news = new News(nd.nextId(), "tieude", "date", "link", "linkimg", "type", 2);
		nd.InsertNews(news);

	}
}
