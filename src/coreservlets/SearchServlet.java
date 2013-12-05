package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/cari")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		    	
		    	String sql = "";
		    	String title ="";
		    	String query = request.getParameter("query");
		    	String result;
		    	//<?php
		    		    // check input
		    		    if(query.contains("less ")) {
		    		        result = query.substring(5);
		    		        title = "Hasil pencarian kurang dari Rp. " + result;
		    		        sql = "select barang_data.barang_id, barang_data.nama, barang_kategori.kategori_nama, barang_data.harga, barang_data.image_url, barang_data.deskripsi from barang_data inner join barang_kategori on barang_data.kategori_id = barang_kategori.kategori_id where barang_data.harga < " + result;
		    		    	
		    		    } else if(query.contains("more")) {
		    		        result = query.substring(5);
		    		        title = "Hasil pencarian lebih dari Rp. " + result;
		    		        sql = "select barang_data.barang_id, barang_data.nama, barang_kategori.kategori_nama, barang_data.harga, barang_data.image_url, barang_data.deskripsi from barang_data inner join barang_kategori on barang_data.kategori_id = barang_kategori.kategori_id where barang_data.harga > " + result;
		    		       
		    		    } else if(query.contains("category ")) {
		    		        result = query.substring(9);
		    		        title = "Hasil pencarian untuk kategori <i>" + result + "</i>";
		    		        sql = "select barang_data.barang_id, barang_data.nama, barang_kategori.kategori_nama, barang_data.harga, barang_data.image_url, barang_data.deskripsi from barang_data inner join barang_kategori on barang_data.kategori_id = barang_kategori.kategori_id where barang_kategori.kategori_nama like '%" + result + "%'";
		    		       
		    		    } else {
		    		        result = query;
		    		        title = "Hasil pencarian untuk <i>" + result + "</i>";
		    		        sql = "select barang_data.barang_id, barang_data.nama, barang_kategori.kategori_nama, barang_data.harga, barang_data.image_url, barang_data.deskripsi from barang_data inner join barang_kategori on barang_data.kategori_id = barang_kategori.kategori_id where barang_data.nama like '%" + result +"%'";
		    		      
		    		    }
		    		    
		    		    
	    		        conn = Process.getConnection();
						stmt = conn.createStatement();
						
							
		    		    
						String exec = sql + ";";
		    		    ResultSet res = stmt.executeQuery(exec);
		    		    int total_row = 0;
		    		    while (res.next()) {
		    		    	total_row++;
		    		    }
		    		    res.beforeFirst();
		    		    
		    		    int start_number = 0;
		    		    int this_page = 1;
		    		    if(request.getParameter("page") != null) {
		    		        this_page = Integer.parseInt(request.getParameter("page"));
		    		        start_number = total_row > (this_page - 1)*10 && this_page > 0 ? (this_page - 1)*10 : 0;
		    		        
		    		    }
		    		    
		    		    
		    		out.println("<h1>" + title + "</h1>");
	    		    if(total_row == 0) {
	    		        out.println("<p><i>Hasil pencarian tidak ada.</i></p>");
	    		    } else { 
	    		        sql += " LIMIT " + start_number + ",10;";
	    		        Statement stmtLimit = conn.createStatement();
	    		        ResultSet barang = stmtLimit.executeQuery(sql);
	    		        while(barang.next()) {
							out.println("<div class=\"table sresult\" onSubmit=\"return addToShoppingChartBarang(this)\">");
							out.println("<div class=\"row\">");
							String path="";
							if (barang.getString("image_url").equals("")){
								path = request.getContextPath() + "/assets/image/default.png";
							} else {
								path = request.getContextPath() + "/" + barang.getString("image_url");
							}
							out.println("<span class=\"column\"><img src=\"" + path + "\" alt=\"Default\" width=\"100\" height=\"100\"></span>");
							out.println("<span class=\"column\" style=\"vertical-align: top\">");
							out.println("<h3><a href=\""+ request.getContextPath() +"/barang/?id=" + barang.getInt("barang_id") + "\"> " + barang.getString("nama") + "</a></h3>");
							out.println("<p>Rp." + barang.getDouble("harga") +"<br>");
							out.println(barang.getString("deskripsi") +"<br>");
							out.println("<form onSubmit=\"return addToShoppingChart(this)\"><span>Kuantitas: </span><input type=\"hidden\" name=\"id_barang\" value=\"" + barang.getInt("barang_id") +"\"><input type=\"text\" name=\"qty\" onKeyUp=\"validateQtyBarang(this)\"> <input type=\"submit\" value=\"+\" disabled=\"disabled\"></form></p>");
							out.println("</span>");
							out.println("</div>");
							out.println("</div>");
	    		        }
	    		        int sisa = total_row % 10;
	    		        int total_page = ((total_row - sisa) / 10) + 1;
	    		        out.println("<div align=\"center\">Page");
	    		        for(int i = 1; i <= total_page; i++) {
	    		            if(i == this_page) out.println(" - <b>" + i + "</b>");
	    		            else out.println(" - <a href=\"?query="+ request.getParameter("query") +"&page="+i+"\">"+i+"</a>");
	    		        }
	    		        out.println("</div>");
	    		    }
	    		
		    	
		    	
				
				out.println("</div>");
		    	
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
	
	    	/*
	      String address = "/WEB-INF/home.jsp";
	      RequestDispatcher dispatcher =
	        request.getRequestDispatcher(address);
	      dispatcher.forward(request, response);
	    */
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
