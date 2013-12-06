package coreservlets;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public MySQLAccess(PrintWriter out){
	  try {
		  Class.forName("com.mysql.jdbc.Driver");
		  out.println("Coi");
	  }
	  catch (Exception e){
		  out.println("Error");
	  }
  }
  public MySQLAccess(){
	  
  }
  
  public List<String> getAllName(){
	  
	  List<String> list = new ArrayList<String>();
	  // This will load the MySQL driver, each DB has its own driver
	  try {
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	              + "user=root&password=");
	      
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("SELECT nama_pengguna FROM __user_login");
	      while (resultSet.next()) {
	          String user = resultSet.getString("nama_pengguna");
	          list.add(user);
	        }
	  }
	  catch (Exception e) {
		  
	  }
	  return list;
  }
  
public List<String> getAllEmail(){
	  
	  List<String> list = new ArrayList<String>();
	  // This will load the MySQL driver, each DB has its own driver
	  try {
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	              + "user=root&password=");
	
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("SELECT email FROM pelanggan_id");
	      while (resultSet.next()) {
	          String user = resultSet.getString("email");
	          list.add(user);
	        }
	  }
	  catch (Exception e) {
		  
	  }
	  return list;
  }
  
  
  public void readDataBase() throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
              + "user=root&password=");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("SELECT nama_pengguna FROM __user_login");
      writeResultSet(resultSet);
      
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user = resultSet.getString("nama_pengguna");
      System.out.println("User: " + user);
    }
  }

  // You need to close the resultSet
  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

public int daftarUser(String username, String password, String fullname,
		String email) {
	// TODO Auto-generated method stub
	// This will load the MySQL driver, each DB has its own driver
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("SELECT (user_id + 1) AS num FROM pelanggan_id GROUP BY user_id DESC LIMIT 0, 1");
	    resultSet.next();
	    int id = resultSet.getInt("num");
	    String str = "INSERT INTO pelanggan_id VALUES (" + id + ", '" + fullname + "', '" + email + "');";
	    int res = statement.executeUpdate(str);
	    str = "INSERT INTO __user_login VALUES (" + id + ", '" + username + "', '" + password + "', '" + "u" + "');";
	    res = statement.executeUpdate(str);
	    return id;
	} catch (Exception e){
	}
	return 999;
}

public User getUser(int user_id) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
		        .executeQuery("SELECT * FROM __user_login natural join pelanggan_id WHERE user_id = " + user_id + " ;");
	    boolean valid = false;
	    
	    resultSet.next();
    	int userid = resultSet.getInt("user_id");
    	String uname = resultSet.getString("nama_pengguna");
    	String pass = resultSet.getString("kata_sandi");
    	String email = resultSet.getString("email");
    	String fullname = resultSet.getString("nama_lengkap");
    	User user = new User(userid,uname,pass,email,fullname);
    	user.role = resultSet.getString("ic");
    	System.out.println("tes1");
    	try {
    		resultSet = statement
    		        .executeQuery("SELECT * FROM pelanggan_addr WHERE user_id = " + userid + " ;");
    		 resultSet.next();
	    	user.provinsi = resultSet.getString("provinsi");
	    	user.alamat = resultSet.getString("jalan");
	    	user.kabupaten = resultSet.getString("kabupaten");
	    	user.kodepos = resultSet.getString("kodepos");
	    	user.handphone = resultSet.getString("user_phone");
	    	
	    	System.out.println("tes2");
    	}
    	catch (Exception e){
    		
    	}
	    return user;
	} catch (Exception e){
		return null;
	}
	// TODO Auto-generated method stub
}
public boolean userExist(String username, String password) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("SELECT kata_sandi FROM __user_login WHERE nama_pengguna = '" + username + "';");
	    resultSet.next();
	    String pass = resultSet.getString("kata_sandi");
	    System.out.println(password + " " + pass);
	    if (pass.equals(password))
	    	return true;
	    else
	    	return false;
	} catch (Exception e){
		return false;
	}
}
public void registerCreditCard(int userId, String nomorKartu,
		String namaPemilik, String date) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
		        .executeQuery("SELECT user_id FROM pelanggan_card WHERE user_id = " + userId + ";");
	    resultSet.last();
	    int nrow = resultSet.getRow();
	    resultSet.beforeFirst();
	    // Update
	    if (nrow == 0){
	    	String str = "INSERT INTO pelanggan_card VALUES (" + userId + ", '" + nomorKartu + "', '" + namaPemilik + "', '" + date + "');";
	    	int res = statement.executeUpdate(str);
	    }
	    else {
	    	String str = "UPDATE pelanggan_card SET card_number = '" + nomorKartu + "', card_name = '" + namaPemilik + "', card_expiry = '" + date + "' WHERE user_id = " + userId + ";";
	    	int res = statement.executeUpdate(str);
	    }
	}
	catch (Exception e){
		
	}
}
public User getUser(String username) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("SELECT * FROM __user_login natural join pelanggan_id WHERE nama_pengguna = '" + username + "' ;");
	    boolean valid = false;
	    
	    resultSet.next();
    	int userid = resultSet.getInt("user_id");
    	String uname = resultSet.getString("nama_pengguna");
    	String pass = resultSet.getString("kata_sandi");
    	String email = resultSet.getString("email");
    	String fullname = resultSet.getString("nama_lengkap");
    	
    	User user = new User(userid,uname,pass,email,fullname);
    	user.role = resultSet.getString("ic");
    	System.out.println("tes1");
    	try {
    		resultSet = statement
    		        .executeQuery("SELECT * FROM pelanggan_addr WHERE user_id = " + userid + " ;");
    		 resultSet.next();
	    	user.provinsi = resultSet.getString("provinsi");
	    	user.alamat = resultSet.getString("jalan");
	    	user.kabupaten = resultSet.getString("kabupaten");
	    	user.kodepos = resultSet.getString("kodepos");
	    	user.handphone = resultSet.getString("user_phone");
	    	
	    	System.out.println("tes2");
    	}
    	catch (Exception e){
    		System.out.println("tes3");
    	}
	    return user;
	} catch (Exception e){
		return null;
	}
}
public void changeIdentity(int user_id, String alamat, String provinsi,
		String kabupaten, String kodepos, String no_hp) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
		        .executeQuery("SELECT user_id FROM pelanggan_addr WHERE user_id = " + user_id + ";");
	    resultSet.last();
	    int nrow = resultSet.getRow();
	    resultSet.beforeFirst();
	    // Update
	    if (nrow == 0){
	    	String str = "INSERT INTO pelanggan_addr VALUES (" + user_id + ", '" + alamat + "', '" + provinsi + "', '" + kabupaten  + "', '" + kodepos  + "', '" + no_hp + "');";
	    	int res = statement.executeUpdate(str);
	    	System.out.println(str);
	    }
	    else {
	    	String str = "UPDATE pelanggan_addr SET jalan = '" + alamat + "', provinsi = '" + provinsi + "', kabupaten = '" +kabupaten + "', kodepos = '" +kodepos + "', user_phone = '" +no_hp + "' WHERE user_id = " + user_id + ";";
	    	int res = statement.executeUpdate(str);
	    }
	}
	catch (Exception e){
		
	}
}
public void changePassword(int user_id, String kata_sandi) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    String str = "UPDATE __user_login SET kata_sandi = '" + kata_sandi + "' WHERE user_id = " + user_id + ";";  
	    int res = statement
		        .executeUpdate(str);
	}
	catch (Exception e){
		
	}
}
public void tambahBarang(String nama, String deskripsi, double harga, int kat,
		String img, int stok) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("SELECT (barang_id + 1) AS num FROM barang_data GROUP BY barang_id DESC LIMIT 0, 1");
	    resultSet.next();
	    int id = resultSet.getInt("num");
	    String str = "INSERT INTO barang_data VALUES (" + id + ", '" + nama + "', " + kat + ", " + harga + ", '" + img + "', '" + deskripsi + "');";
	    int res = statement.executeUpdate(str);
	    str = "INSERT INTO barang_stok VALUES (" + id + ", " + stok + ", " + 1 + ");";
	    res = statement.executeUpdate(str);
	} catch (Exception e){
	}
}

public void updateBarang(int barang_id, String nama, String deskripsi, double harga, int kat,
		String img, int stok) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    String str = "UPDATE barang_data SET nama = '" + nama + "', kategori_id = " + kat + ", harga = " +harga + ", image_url = '" + img + "', deskripsi = '" + deskripsi + "' WHERE barang_id = " + barang_id + ";";
	    int res = statement.executeUpdate(str);
	    str = "UPDATE barang_stok SET stok = " + stok + ", satuan = " + 1 + " WHERE barang_id = " + barang_id + ";";
	    res = statement.executeUpdate(str);
	} catch (Exception e){
	}
}
public Barang getBarang(int id) {
	// TODO Auto-generated method stub
	Barang barang = new Barang();
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    resultSet = statement
	        .executeQuery("SELECT * FROM barang_data natural join barang_stok WHERE barang_id = " + id + " ;");
	    boolean valid = false;
	    
	    resultSet.next();
		int barangid = resultSet.getInt("barang_id");
		String nama = resultSet.getString("nama");
		int kategori_id = resultSet.getInt("kategori_id");
		Double harga = resultSet.getDouble("harga");
		String image_url = resultSet.getString("image_url");
		String deskripsi = resultSet.getString("deskripsi");
		int stock = resultSet.getInt("stok");
		barang.id = barangid;
		barang.nama = nama;
		barang.kategori_id = kategori_id;
		barang.harga = harga;
		barang.image_url = image_url;
		barang.deskripsi = deskripsi;
		barang.stock = stock;
		return barang;
	}catch (Exception e){
		return null;
	}
}
public void hapusBarang(int id) {
	// TODO Auto-generated method stub
	try {
		Class.forName("com.mysql.jdbc.Driver");
	    // Setup the connection with the DB
	    connect = DriverManager
	        .getConnection("jdbc:mysql://localhost/tugas_wbd1?"
	            + "user=root&password=");
	    // Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	    // Result set get the result of the SQL query
	    int res = statement
		        .executeUpdate("DELETE FROM barang_stok WHERE barang_id = " + id + " ;");
	    res = statement
	        .executeUpdate("DELETE FROM barang_data WHERE barang_id = " + id + " ;");
	    
	   
	}catch (Exception e){
	}
} 


} 