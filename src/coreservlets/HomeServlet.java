package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	    synchronized(session) {
	    	response.setContentType("text/html");
			// Get a output writer to write the response message into the network socket
			PrintWriter out = response.getWriter();
			Connection conn = null;
			Statement stmt = null;
			try {
				conn = Process.getConnection();
				stmt = conn.createStatement();
								
				
		    	
		    	// Print an HTML page as the output of the query
		    	out.println("<html><head>" +
						"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">" +
						"<title>Ruserba</title>" +
						"<link rel=\"icon\" type=\"image/png\" href=\"" + request.getContextPath() + "/favicon.png\">" +
						"<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/home.css\" type=\"text/css\" /> " +
						"<link rel=\"stylesheet\" href=\"" + request.getContextPath() + "/css/loginpopup.css\" type=\"text/css\" />" +
						"<script src=\"" + request.getContextPath() + "/ajax_generic.js\"></script>	" +
						"</head><body>");
		    	
		    	
		    	
		    	
		    	//HEADER
		    	out.println("<header>");
		    	out.println("<nav><div class=\"container\">");
		    	out.println("<span id=\"login\"><a class=\"menu_cell hyperlink\" href=\"#loginbox\">Masuk</a></span>");
		    	out.println("<form id=\"wbd_search\" class=\"menu_cell\" onSubmit=\"return testA()\">");
		    	out.println("<input type=\"text\" name=\"search_input\" placeholder=\"Cari disini\">");
		    	out.println("<input type=\"submit\" name=\"submit\" value=\"Cari\">");
		    	out.println("</form>");
		    	out.println("</div>");
		    	out.println("</nav>");
		    	out.println("<div class=\"container\">");
		    	out.println("<a href=\"" + request.getContextPath() + "/home\"><img id=\"logo\" src=\"" + request.getContextPath() + "/logo.png\" height=\"72\" alt=\"Ruko Serba Ada\"></a>");
		    	out.println("</div>");
		    	out.println("<div id=\"background_cat\">");
		    	out.println("<img class=\"background\" id='kat1' src=\"" + request.getContextPath() + "/img_style/kat1.gif\" alt=\"Kategori 1\"/>");
		    	out.println("<img class=\"background\" id='kat2' src=\"" + request.getContextPath() + "/img_style/kat2.gif\" alt=\"Kategori 1\"/>");
		    	out.println("<img class=\"background\" id='kat3' src=\"" + request.getContextPath() + "/img_style/kat3.gif\" alt=\"Kategori 1\"/>");
		    	out.println("<img class=\"background\" id='kat4' src=\"" + request.getContextPath() + "/img_style/kat4.gif\" alt=\"Kategori 1\"/>");
		    	out.println("<img class=\"background\" id='kat5' src=\"" + request.getContextPath() + "/img_style/kat5.gif\" alt=\"Kategori 1\"/>");
		    	out.println("</div>");
		    	out.println("<div class=\"kategori_group\">");
		    	out.println("<a href=\"" + request.getContextPath() + "/kategori/?id=1&page=1\"><img src=\"" + request.getContextPath() + "/img_style/klik.gif\" alt=\"Klik\"/></a>");
		    	out.println("<a href=\"" + request.getContextPath() + "/kategori/?id=2&page=1\"><img src=\"" + request.getContextPath() + "/img_style/klik.gif\" alt=\"Klik\"/></a>");
		    	out.println("<a href=\"" + request.getContextPath() + "/kategori/?id=3&page=1\"><img src=\"" + request.getContextPath() + "/img_style/klik.gif\" alt=\"Klik\"/></a>");
		    	out.println("<a href=\"" + request.getContextPath() + "/kategori/?id=4&page=1\"><img src=\"" + request.getContextPath() + "/img_style/klik.gif\" alt=\"Klik\"/></a>");
		    	out.println("<a href=\"" + request.getContextPath() + "/kategori/?id=5&page=1\"><img src=\"" + request.getContextPath() + "/img_style/klik.gif\" alt=\"Klik\"/></a>");
		    	out.println("</div>");
		    	out.println("</header>");
				//END OF HEADER
		    	
		    	
		    	//ARTICLE
		    	out.println("<article class=\"container\">");
		    	out.println("<div class=\"box_kategori\">");
				 
		    	
		    	String sqlStr = "select nama, image_url, deskripsi from barang_data";
				
				String num_kategori_query = "SELECT COUNT(kategori_id) FROM barang_kategori";
		    	ResultSet num_kategori = stmt.executeQuery(num_kategori_query);
		    	num_kategori.next();
		    	int kategori = num_kategori.getInt(1); //get count of category
				
		    	
		    	int count = 0;
		    	String nama_kategori_query;
		    	ResultSet nama_kategori_tabel;
		    	Statement nama_kategori_stmt = conn.createStatement();
		    	
		    	String num_barang_query;
		    	ResultSet num_barang_tabel;
		    	Statement num_barang_stmt = conn.createStatement();
		    	
		    	ArrayList<String> nama;
		    	ArrayList<String> image_url;
		    	ArrayList<Double> harga;
		    	ArrayList<Integer> id;
		    	
		    	for (int i = 1; i < kategori; i++){
		    		nama_kategori_query = "SELECT kategori_nama FROM barang_kategori WHERE kategori_id =" + i + ";";
		    		nama_kategori_tabel = nama_kategori_stmt.executeQuery(nama_kategori_query);
		    		nama_kategori_tabel.next();
		    		
		    		int numItem = 3;
		    		int productId = 0;
		    		
		    		num_barang_query = "select barang_data.barang_id, nama, image_url, harga from barang_kategori join barang_data join transaksi on barang_kategori.kategori_id = barang_data.kategori_id and barang_data.barang_id = transaksi.barang_id where barang_data.kategori_id = "+i+" group by barang_data.barang_id order by count(nama) desc;";
		    	    num_barang_tabel = num_barang_stmt.executeQuery(num_barang_query);
		    	    while (num_barang_tabel.next()){
		    	    	count++;
		    	    }
		    	    num_barang_tabel.beforeFirst();
		    	    if (count > 0){
		    	    	out.println("<div class=\"box_nama\"><h2>" + nama_kategori_tabel.getString("kategori_nama") + "</h2></div>");
		    	    	
		    	    	int nama_size = 0;
		    	    	nama = new ArrayList<String>();
		    	    	image_url = new ArrayList<String>();
		    	    	harga = new ArrayList<Double>();
		    	    	id = new ArrayList<Integer>();
		    			while(num_barang_tabel.next()) {
		    				nama.add(num_barang_tabel.getString("nama"));
		    				image_url.add(num_barang_tabel.getString("image_url"));
		    				harga.add(num_barang_tabel.getDouble("harga"));
		    				id.add(num_barang_tabel.getInt("barang_id"));
		    				nama_size++;
		    			}
		    			
		    			for (int j = 1; j <= numItem; j++) {
		    				//TODO up the link for each picture
		    				out.println("<a class=\"box_barang\" href=\"barang\\?id=" + id.get(j-1) + "\">");
		    				String path="";
		    				if (image_url.get(j-1).equals("")){
		    					path = request.getContextPath() + "/assets/image/default.png";
		    				} else {
		    					path = request.getContextPath() + "/" + image_url.get(j-1);
		    				}
		    				out.println("<img class=\"gambar_barang\" src=\"" + path + "\" alt=\"Default\" height=\"100\" width=\"100\"/>");
		    				out.println("<h3>"+ nama.get(j-1) +"</h3>");
		    				out.print("<span class=\"harga\">Rp. " + harga.get(j-1) + "</span>");
		    				out.println("</a>");
		    				if (j >= nama_size) break;
		    				
		    			}
		    	    }
		    	    
		    	}
		    	out.println("</article>");
				//END OF ARTICLE
		    	
		    	//FOOTER
		    	out.println("<footer class=\"container\"> " +
								" <div>Ruserba &copy; 2013</div>" +
							"</footer>" +
							"<script src=\"" + request.getContextPath() + "/use.js\"></script>	" +
							"<script src=\"" + request.getContextPath() + "/validator.js\"></script>"
							);
				//END OF FOOTER
		    	
		    	
		    	out.println("</body></html>");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				out.close(); // Close the output writer
				try {
					// Step 5: Close the resources
					if (stmt != null) stmt.close();
					//if (conn != null) conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
	
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
