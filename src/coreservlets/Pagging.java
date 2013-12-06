package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;



/**
 * Servlet implementation class Pagging
 */
@WebServlet("/proses/json/")
public class Pagging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pagging() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		 String data = request.getParameter("data");
		 System.out.println(data);
		 JsonObject jsonObject = JsonObject.readFrom(data);
		 String todo = jsonObject.get("todo").asString();
		 if (todo.equals("pendaftaran")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String check_field = jsonObject.get("check_field").asString();
			 String value = jsonObject.get("value").asString();
			 //out.println(check_field);
			 //out.println(value);
			 //out.println(check_field);
			 if (check_field.equals("username")){
				 MySQLAccess db = new MySQLAccess();
				 try {
					 //out.println("test1");
					 List<String> names = db.getAllName();
					 //out.println("test2");
					 boolean found = false;
					 for(String name : names){
						 if (name.equals(value)){
							 found = true;
						 }
					 }
					 if (found){
						 
						 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "Ada dalam basis data." );
					 }
					 else {
						 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
					 }
					 jsonObject.writeTo(out);
				 }
				 catch (Exception e) {
					 out.println("Exception");
				 }
			 }
			 else if (check_field.equals("email")){
					MySQLAccess db = new MySQLAccess();
					try {
						 //out.println("test0");
						 List<String> emails = db.getAllEmail();
						 //out.println(names.size());
						 boolean found = false;
						 for(String email : emails){
							 if (email.equals(value)){
								 found = true;
							 }
						 }
						 if (found){
							 
							 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "Ada dalam basis data." );
						 }
						 else {
							 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
						 }
						 jsonObject.writeTo(out);
					 }
					 catch (Exception e) {
						 out.println("Exception");
					 }
			}
		 }
		 else if (todo.equals("submitPendaftaran")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String username = jsonObject.get("nama_pengguna").asString();
			 String password = jsonObject.get("kata_sandi").asString();
			 String fullname = jsonObject.get("nama_lengkap").asString();
			 String email = jsonObject.get("email").asString();
			 MySQLAccess db = new MySQLAccess();
				try {
					int userid = db.daftarUser(username,password,fullname,email);
					jsonObject = new JsonObject().add("status", "success").add("data",new JsonObject().add("nama_lengkap", fullname).add("email",email).add("user_id", userid));
					jsonObject.writeTo(out);
				 }
				 catch (Exception e) {
					 out.println("Exception");
				 }
		 }
		 else if (todo.equals("checkCreditCardNumber")){
			 String value = jsonObject.get("data").asString();
			 String delims = "[-]+";
			 String[] tokens = value.split(delims);
			 boolean valid = true;
			 for (int i=0;i<tokens.length;i++){
				 if (tokens[i].length() == 4 && isNumeric(tokens[i]))
					 continue;
				 valid = false;
			 }
			 if (valid){
				 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
			 }
			 else {
				 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "" );
			 }
			 jsonObject.writeTo(out);
		 }
		 else if (todo.equals("getIdentity")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 int user_id = jsonObject.get("user_id").asInt();
			 MySQLAccess db = new MySQLAccess();
			 User user = db.getUser(user_id);
			 jsonObject = new JsonObject().add("status","success").add("data",new JsonArray().add(new JsonObject().add("user_id", user.userid).add("alamat", user.alamat).add("provinsi", user.provinsi).add("kabupaten", user.kabupaten).add("kodepos", user.kodepos).add("user_phone", user.handphone)));
			 jsonObject.writeTo(out);
		 }
		 else if (todo.equals("login")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String username = jsonObject.get("nama_pengguna").asString();
			 String password = jsonObject.get("kata_sandi").asString();
			 MySQLAccess db = new MySQLAccess();
			 if (db.userExist(username,password)){
				 User user = db.getUser(username);
				 String fullname = user.fullname;
				 String email = user.email;
				 int userid = user.userid;
				 String role = user.role;
				 jsonObject = new JsonObject().add("status","success").add("data", new JsonArray().add(new JsonObject().add("nama_lengkap", fullname).add("email", email).add("user_id",userid).add("role", role)));
				 jsonObject.writeTo(out);
			 }
			 else {
				 jsonObject = new JsonObject().add("status","failed");
				 jsonObject.writeTo(out);
			 }
		 }
		 else if (todo.equals("searching")){
			 String value = jsonObject.get("data").asString();
			 jsonObject = new JsonObject().add("data","ih nyampe");
			 jsonObject.writeTo(out);
		 }
		
	}

	public boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // This will load the MySQL driver, each DB has its own driver
		System.out.println("hello");
		PrintWriter out = response.getWriter();
		 String data = request.getParameter("data");
		 System.out.println(data);
		 //out.println(data);
		 response.setContentType("text/html");
		 JsonObject jsonObject = JsonObject.readFrom(data);
		 String todo = jsonObject.get("todo").asString();
		 System.out.println(todo); 
		 if (todo.equals("pendaftaran")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String check_field = jsonObject.get("check_field").asString();
			 String value = jsonObject.get("value").asString();
			 //out.println(check_field);
			 //out.println(value);
			 //out.println(check_field);
			 if (check_field.equals("username")){
				 MySQLAccess db = new MySQLAccess();
				 try {
					 //out.println("test1");
					 List<String> names = db.getAllName();
					 //out.println("test2");
					 boolean found = false;
					 for(String name : names){
						 if (name.equals(value)){
							 found = true;
						 }
					 }
					 if (found){
						 
						 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "Ada dalam basis data." );
					 }
					 else {
						 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
					 }
					 jsonObject.writeTo(out);
				 }
				 catch (Exception e) {
					 out.println("Exception");
				 }
			 }
			 else if (check_field.equals("email")){
					MySQLAccess db = new MySQLAccess();
					try {
						 //out.println("test0");
						 List<String> emails = db.getAllEmail();
						 //out.println(names.size());
						 boolean found = false;
						 for(String email : emails){
							 if (email.equals(value)){
								 found = true;
							 }
						 }
						 if (found){
							 
							 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "Ada dalam basis data." );
						 }
						 else {
							 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
						 }
						 jsonObject.writeTo(out);
					 }
					 catch (Exception e) {
						 out.println("Exception");
					 }
			}
		 }
		 else if (todo.equals("submitPendaftaran")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String username = jsonObject.get("nama_pengguna").asString();
			 String password = jsonObject.get("kata_sandi").asString();
			 String fullname = jsonObject.get("nama_lengkap").asString();
			 String email = jsonObject.get("email").asString();
			 MySQLAccess db = new MySQLAccess();
				try {
					int userid = db.daftarUser(username,password,fullname,email);
					String role = "u";
					jsonObject = new JsonObject().add("status", "success").add("data",new JsonObject().add("nama_lengkap", fullname).add("email",email).add("user_id", userid).add("role", role));
					System.out.println(jsonObject.toString());
					jsonObject.writeTo(out);
				 }
				 catch (Exception e) {
					 out.println("Exception");
				 }
		 }
		 else if (todo.equals("checkCreditCardNumber")){
			 String value = jsonObject.get("data").asString();
			 String delims = "[-]+";
			 String[] tokens = value.split(delims);
			 boolean valid = true;
			 for (int i=0;i<tokens.length;i++){
				 if (tokens[i].length() == 4 && isNumeric(tokens[i]))
					 continue;
				 valid = false;
			 }
			 if (valid){
				 jsonObject = new JsonObject().add( "status", "valid" ).add( "data", "" );
			 }
			 else {
				 jsonObject = new JsonObject().add( "status", "invalid" ).add( "data", "" );
			 }
			 jsonObject.writeTo(out);
		 }
		 else if (todo.equals("daftarCreditCard")){
			 jsonObject = jsonObject.get("data").asObject();
			 int userId = jsonObject.get("user_id").asInt();
			 String nomorKartu = jsonObject.get("nomor_kartu").asString();
			 String namaPemilik = jsonObject.get("nama_pemilik").asString();
			 String strbulan = jsonObject.get("bulan").asString();
			 int bulan = 1;
			 if (strbulan.equals("Januari"))
				 bulan = 1;
			 else if (strbulan.equals("Februari"))
				 bulan = 2;
			 else if (strbulan.equals("Maret"))
				 bulan = 3;
			 else if (strbulan.equals("April"))
				 bulan = 4;
			 else if (strbulan.equals("Mei"))
				 bulan = 5;
			 else if (strbulan.equals("Juni"))
				 bulan = 6;
			 else if (strbulan.equals("Juli"))
				 bulan = 7;
			 else if (strbulan.equals("Agustus"))
				 bulan = 8;
			 else if (strbulan.equals("September"))
				 bulan = 9;
			 else if (strbulan.equals("Oktober"))
				 bulan = 10;
			 else if (strbulan.equals("November"))
				 bulan = 11;
			 else if (strbulan.equals("Desember"))
				 bulan = 12;
			 String strTahun = jsonObject.get("tahun").asString();
			 int tahun = Integer.parseInt(strTahun);
			 Calendar now = Calendar.getInstance();
			 Calendar exp = Calendar.getInstance();
			 exp.set(tahun, bulan, 1);
			 if (exp.after(now)){
				 MySQLAccess db = new MySQLAccess();
				 String date = tahun + "-" + bulan + "-01";
				 db.registerCreditCard(userId,nomorKartu,namaPemilik,date);
				 jsonObject = new JsonObject().add("status","success");
				 jsonObject.writeTo(out);
			 }
			 else {
				 jsonObject = new JsonObject().add("status","failed").add("data","Date is not valid.");
				 jsonObject.writeTo(out);
			 }
		 }
		 else if (todo.equals("getIdentity")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 int user_id = jsonObject.get("user_id").asInt();
			 MySQLAccess db = new MySQLAccess();
			 User user = db.getUser(user_id);
			 if (user != null){
				 jsonObject = new JsonObject().add("status","success").add("data",new JsonObject().add("user_id", user.userid).add("alamat", user.alamat).add("provinsi", user.provinsi).add("kabupaten", user.kabupaten).add("kodepos", user.kodepos).add("user_phone", user.handphone));
				 jsonObject.writeTo(out);
			 }
			 else {
				 jsonObject = new JsonObject().add("status","failed").add("data","Data tidak ditemukan");
				 jsonObject.writeTo(out);
			 }
		 }
		 else if (todo.equals("changeIdentity")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 int user_id = jsonObject.get("user_id").asInt();
			 String kata_sandi = jsonObject.get("kata_sandi").asString();
			 String alamat = jsonObject.get("alamat").asString();
			 String provinsi = jsonObject.get("provinsi").asString();
			 String kabupaten = jsonObject.get("kabupaten").asString();
			 String kodepos = jsonObject.get("kodepos").asString();
			 String no_hp = jsonObject.get("no_hp").asString();
			 MySQLAccess db = new MySQLAccess();
			 db.changeIdentity(user_id,alamat,provinsi,kabupaten,kodepos,no_hp);
			 if (kata_sandi != ""){
				 db.changePassword(user_id, kata_sandi);
			 }
			 jsonObject = new JsonObject().add("status","success").add("data",new JsonObject().add("user_id", user_id).add("alamat", alamat).add("provinsi", provinsi).add("kabupaten", kabupaten).add("kodepos", kodepos).add("user_phone", no_hp));
			 jsonObject.writeTo(out);
		 }
		 else if (todo.equals("login")){
			 JsonArray jsonArray = jsonObject.get("data").asArray();
			 jsonObject = jsonArray.get(0).asObject();
			 String username = jsonObject.get("nama_pengguna").asString();
			 String password = jsonObject.get("kata_sandi").asString();
			 MySQLAccess db = new MySQLAccess();
			 if (db.userExist(username,password)){
				 User user = db.getUser(username);
				 String fullname = user.fullname;
				 String email = user.email;
				 int userid = user.userid;
				 String role = user.role;
				 jsonObject = new JsonObject().add("status","success").add("data", new JsonObject().add("nama_lengkap", fullname).add("email", email).add("user_id",userid).add("role", role));
				 jsonObject.writeTo(out);
			 }
			 else {
				 jsonObject = new JsonObject().add("status","failed");
				 jsonObject.writeTo(out);
			 }
		 }
		 else if (todo.equals("searching")){
			 String value = jsonObject.get("data").asString();
			 jsonObject = new JsonObject().add("data","ih nyampe");
			 jsonObject.writeTo(out);
		 }
		 else if (todo.equals("hapusBarang")){
			 String value = jsonObject.get("data").asString();
			 int id = Integer.parseInt(value);
			 MySQLAccess db = new MySQLAccess();
			 db.hapusBarang(id);
			 jsonObject = new JsonObject().add("status","success");
			 jsonObject.writeTo(out);
		 }
		
	}

}
