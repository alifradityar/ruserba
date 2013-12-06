package coreservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TambahBarangServlet
 */
@WebServlet("/tambah_barang")
@MultipartConfig
public class TambahBarangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TambahBarangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int kat = Integer.parseInt(request.getParameter("kat"));
		String address = "/WEB-INF/tambah_barang.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("tes1");
		try {
			String nama = request.getParameter("nama");
			String text = request.getParameter("deskripsi");
			System.out.println("tes2");
			double harga = Double.parseDouble(request.getParameter("harga"));
			int kat = Integer.parseInt(request.getParameter("kategori"));
			int stok = Integer.parseInt(request.getParameter("stock"));
			System.out.println("tes3");
			String img = "assets/image/headset.png";
			MySQLAccess db = new MySQLAccess();;
			db.tambahBarang(nama,text,harga,kat,img, stok);
			System.out.println("tes4");
			request.setAttribute("msg", "Penambahan barang " + nama + " berhasil");
			String address = "/WEB-INF/tambah_sukses.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch (Exception e){
			System.out.println("tes6");
			request.setAttribute("msg", "Penambahan barang gagal");
			String address = "/WEB-INF/tambah_sukses.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

}
