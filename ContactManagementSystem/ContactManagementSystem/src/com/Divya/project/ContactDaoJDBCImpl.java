package com.Divya.project;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactDaoJDBCImpl implements ContactDao {

	public Connection getConnection() {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/javatraining";
		String userName = "root";
		String password = "root";
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public List<Contact> readContact() {
		ResultSet rs = null;
		Connection con = getConnection();
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery("Select * from Contact");
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setName(rs.getString(1));
				contact.setNumber(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setImagePath(rs.getString(4));
				contact.setRingtone(rs.getString(5));

				contactList.add(contact);

			}
			return contactList;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contactList;
	}

	public List<Contact> readContact(String name) {
		ResultSet rs = null;
		Connection con = getConnection();
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery("Select * from Contact where name = '" + name + "'");
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setName(rs.getString(1));
				contact.setNumber(rs.getString(2));
				contact.setEmail(rs.getString(3));
				contact.setImagePath(rs.getString(4));
				contact.setRingtone(rs.getString(5));

				contactList.add(contact);

			}
			return contactList;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contactList;

	}

	public boolean deleteContat(int id) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("Delete from Contact where id = '" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean deleleContact(String name) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("Delete from Contact where name '" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean updateContact(String name, int id) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("update Contact set name = '" + name + "' where id = '" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean updateContact(int id, String eMail) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("update Contact set email = '" + eMail + "' where id = '" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;

	}

	public boolean assignPhoto(int id, String imagePath) {
		Connection con = getConnection();
		PreparedStatement pre;
		try {
			FileInputStream fin;
			File imgfile = new File(imagePath);
			fin = new FileInputStream(imgfile);
			pre = con.prepareStatement("update Contact set photo = ? where id = '" + id + "'");
			pre.setBinaryStream(1, (InputStream) fin, (int) imgfile.length());
			pre.executeUpdate();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean assignRingtone(int id, String ringTone) {

		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			st.executeUpdate("update Contact set ringtone = '" + ringTone + "' where id = '" + id + "'");
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean CreateNewContact(Contact contact) {
		
		Connection con = getConnection();
		PreparedStatement pre;
		FileInputStream fin = null;
		try {
			String querySetLimit = "SET GLOBAL max_allowed_packet=104857600;";  // 10 MB
            Statement stSetLimit = con.createStatement();
            stSetLimit.execute(querySetLimit);
            
			pre = con.prepareStatement("Insert into  Contact values(?,?,?,?,?,?)");
					
			pre.setString(1,contact.getName());
			pre.setString(2,contact.getNumber());
			pre.setString(3,contact.getEmail());
			
			File imgfile = new File(contact.getImagePath());
			fin = new FileInputStream(imgfile);
			
			pre.setBinaryStream(4, (InputStream) fin,  imgfile.length());
			//pre.setb (4,(InputStream)fin);
			pre.setString(5,contact.getRingtone());
			pre.setInt(6, (int) contact.getId());
			pre.executeUpdate();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
				fin.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;	
	}
}
