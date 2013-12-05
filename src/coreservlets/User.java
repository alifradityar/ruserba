package coreservlets;

public class User {
	public int userid;
	public String username;
	public String password;
	public String email;
	public String fullname;
	public String provinsi;
	public String alamat;
	public String kabupaten;
	public String kodepos;
	public String handphone;
	public KartuKredit creditCard;
	
	public User(int userid,String username,String password,String email,String fullname){
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}
}
