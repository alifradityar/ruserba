package coreservlets;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Process {

	private static Process instance;
	private Connection conn = null;
	private Process() {
		// TODO Auto-generated constructor stub
		try {
			// Step 1: Allocate a database Connection object
			MysqlDataSource d = new MysqlDataSource();
			d.setUser("root");
			d.setPassword("");
			d.setServerName("localhost");
			d.setDatabaseName("tugas_wbd1");
			conn = d.getConnection();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		return getInstance().conn;
	}
	
	
	public static Process getInstance(){
		if (instance == null){
			instance = new Process();
		}
		return instance;
	}
	
	
	public static ResultSet showBarang(int barang){
		String sql = "select barang_id, nama, harga, image_url, deskripsi from barang_data where barang_id = " + barang + ";";
		try{
			Statement stmt = getInstance().conn.createStatement();
			return stmt.executeQuery(sql);
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<ResultSet> showKategoriNonFilter(int id, int page){
		String sql="select kategori_nama from barang_kategori where kategori_id = " + id + ";";
		ArrayList<ResultSet> result = new ArrayList<ResultSet>();
		
		int base = (page-1)*10;
		int limit = 10;
		if (base < 0) base = 0;
		if (limit < 0) limit = 1;
		try{
			Statement stmt = getInstance().conn.createStatement();
			result.add(stmt.executeQuery(sql));
			
			Statement page_stmt = getInstance().conn.createStatement();
			sql = "select distinct count(*) as total from barang_data where kategori_id = " + id + ";";
			result.add(page_stmt.executeQuery(sql));
			
			Statement barang_stmt = getInstance().conn.createStatement();
			sql = "select barang_id, nama, harga, image_url from barang_data where kategori_id = " + id +" limit " + base + "," + limit + ";";
			result.add(barang_stmt.executeQuery(sql));
			return result;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<ResultSet> showKategoriWithFilter(int id, int page, String by, String sort){
		String sql="select kategori_nama from barang_kategori where kategori_id = " + id + ";";
		ArrayList<ResultSet> result = new ArrayList<ResultSet>();
		
		int base = (page-1)*10;
		int limit = 10;
		if (base < 0) base = 0;
		if (limit < 0) limit = 1;
		try{
			Statement stmt = getInstance().conn.createStatement();
			result.add(stmt.executeQuery(sql));
			
			Statement page_stmt = getInstance().conn.createStatement();
			sql = "select distinct count(*) as total from barang_data where kategori_id = " + id + ";";
			result.add(page_stmt.executeQuery(sql));
			
			Statement barang_stmt = getInstance().conn.createStatement();
			if (by == null || sort == null){
				sql = "select barang_id, nama, harga, image_url from barang_data where kategori_id = " + id +" limit " + base + "," + limit + ";";
			} else {
				sql = "select barang_id, nama, harga, image_url from barang_data where kategori_id = " + id + " order by " + by + " " + sort + " limit " + base + "," + limit + ";";
			}
			
			result.add(barang_stmt.executeQuery(sql));
			return result;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

}
