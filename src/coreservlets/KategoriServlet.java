package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Servlet implementation class KategoriServlet
 */

//Kategori, parameter, kat = kategori_id, page = page number, by = sort by, sort = ascor desc
@WebServlet("/kategori/")
public class KategoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KategoriServlet() {
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
			//Connection conn = null;
			//Statement stmt = null;
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

		    	//ResultSet kategori = Process.showKategoriNonFilter(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("page"))).get(0);
		    	ResultSet kategori = Process.showKategoriWithFilter(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("page")), request.getParameter("by"), request.getParameter("sort")).get(0);
				kategori.next();
		    	out.println("<div class=\"kategori\">");
					out.println("<h1>" + kategori.getString("kategori_nama") + "</h1>");
					out.println("<p>");
						out.println("<a class=\"sorting\" href=\"" + request.getContextPath() + "/kategori/?id=" + Integer.parseInt(request.getParameter("id")) + "&page=1&by=nama&sort=asc\">Sorting By Name Ascending</a>");
						out.println("<a class=\"sorting\" href=\"" + request.getContextPath() + "/kategori/?id=" + Integer.parseInt(request.getParameter("id")) + "&page=1&by=nama&sort=desc\">Sorting By Name Descending</a>");
						out.println("<a class=\"sorting\" href=\"" + request.getContextPath() + "/kategori/?id=" + Integer.parseInt(request.getParameter("id")) + "&page=1&by=harga&sort=asc\">Sorting By Price Ascending</a>");
						out.println("<a class=\"sorting\" href=\"" + request.getContextPath() + "/kategori/?id=" + Integer.parseInt(request.getParameter("id")) + "&page=1&by=harga&sort=desc\">Sorting By Price Descending</a>");
					out.println("</p>");
					out.println("<p>");
						out.println("<?php $size= $page_no?>");
						int size; 
						ResultSet page_size = Process.showKategoriWithFilter(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("page")), request.getParameter("by"), request.getParameter("sort")).get(1);
						page_size.next();
						int sisa = page_size.getInt("total")%10;
						size = ((page_size.getInt("total")-sisa)/10) + 1;
						String uri = "";
						boolean filter = request.getParameter("by") != null && request.getParameter("sort") != null;
						for (int i = 1; i <= size; i++){
							uri = request.getContextPath() + "/kategori/?id=" + Integer.parseInt(request.getParameter("id")) + "&page=" + i;
							if (filter) uri += "&by=" + request.getParameter("by") +"&sort=" + request.getParameter("sort");
							//out.println("<h3>"+ uri +"</h3>");
					    	
							out.println("<a class=\"sorting\" href=\"" + uri +"\">" + i + "</a>");
						}
						
					out.println("</p>");
					
					
					
					//ResultSet barang = Process.showKategoriNonFilter(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("page"))).get(1);
					ResultSet barang = Process.showKategoriWithFilter(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("page")), request.getParameter("by"), request.getParameter("sort")).get(2);
					while (barang.next()){
						out.println("<div class=\"box_barang\">");
						String path="";
						if (barang.getString("image_url").equals("")){
							path = request.getContextPath() + "/assets/image/default.png";
						} else {
							path = request.getContextPath() + "/" + barang.getString("image_url");
						}
						out.println("<img class=\"gambar_barang\" src=\"" + path + "\" alt=\""+ barang.getString("nama") +"\" height=\"100\" width=\"100\">");
						out.println("<a href=\""+ request.getContextPath() +"/barang/?id=" + barang.getInt("barang_id") + "\"> " + barang.getString("nama") + "</a>");
						out.println("<span class=\"harga\">Rp." + barang.getDouble("harga")  + "</span>");
						out.println("<form onSubmit=\"return addToShoppingChart(this)\"><input type=\"hidden\" name=\"id_barang\" value=\"" + barang.getInt("barang_id") + "\"><input type=\"text\" name=\"qty\" onKeyUp=\"validateQty(this)\"><input type=\"submit\" value=\"+\" disabled=\"disabled\"></form>");
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
