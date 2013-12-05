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
	    str = "INSERT INTO __user_login VALUES (" + id + ", '" + username + "', '" + password + "', '" + "ab" + "');";
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
	        .executeQuery("SELECT * FROM __user_login natural join pelanggan_id natural join pelanggan_addr WHERE user_id =" + user_id + ";");
	    boolean valid = false;
	    resultSet.next();
    	int userid = resultSet.getInt("user_id");
    	String uname = resultSet.getString("nama_pengguna");
    	String pass = resultSet.getString("kata_sandi");
    	String email = resultSet.getString("email");
    	String fullname = resultSet.getString("nama_lengkap");
    	User user = new User(userid,uname,pass,email,fullname);
	    return user;
	} catch (Exception e){
	}
	// TODO Auto-generated method stub
	return null;
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
	        .executeQuery("SELECT nama_pengguna, kata_sandi FROM __user_login;");
	    boolean valid = false;
	    while (resultSet.next()){
	    	String uname = resultSet.getString("nama_pengguna");
	    	String pass = resultSet.getString("kata_sandi");
	    	if (uname == username ){
	    		if (pass == password){
	    			valid = true;
	    		}
	    		else {
	    			valid = false;
	    		}
	    	}
	    	else {
	    		continue;
	    	}
	    }
	    
	    return valid;
	} catch (Exception e){
		return false;
	}
}
public User getUser(String username) {
	// TODO Auto-generated method stub
	
	return null;
}

} 