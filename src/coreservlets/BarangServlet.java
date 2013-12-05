package coreservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Servlet implementation class BarangServlet
 */

//Barang, parameter. id = barang_id to be shown
@WebServlet("/barang/")
public class BarangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BarangServlet() {
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
		    	int id = Integer.parseInt(request.getParameter("id"));
		    	ResultSet barang = Process.showBarang(id);
		    	barang.next();
				out.println("<h1>Detail Barang</h1>");
				out.println("<form class=\"table barang\" method=\"post\" onSubmit=\"return addToShoppingChartBarang(this)\">");
					out.println("<div class=\"row\">");
						String path="";
						if (barang.getString("image_url").equals("")){
	    					path = request.getContextPath() + "/assets/image/default.big.png";
	    				} else {
	    					path = request.getContextPath() + "/" + barang.getString("image_url");
	    				}
						out.println("<span class=\"column\"><img src="+ path +" alt=\"Default\" width=\"250\" height=\"250\"></span>");
						out.println("<span class=\"column\" style=\"vertical-align: top\">");
							out.println("<h2>" + barang.getString("nama") + "</h2>");
							out.println("<p>Rp." + barang.getDouble("harga")  + "</p>");
							out.println("<p>" + barang.getString("deskripsi") + "</p>");
							out.println("<p><textarea name=\"pesan\"></textarea></p>");
							out.println("<p><span>Kuantitas: </span><input type=\"hidden\" name=\"id_barang\" value=\"" + barang.getInt("barang_id") + "\"><input type=\"text\" name=\"qty\" onKeyUp=\"validateQtyBarang(this)\"> <input type=\"submit\" value=\"+\" disabled=\"disabled\"></p>");
						out.println("</span>");
					out.println("</div>");
				out.println("</form>");
		    	
		    	
		    	
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
