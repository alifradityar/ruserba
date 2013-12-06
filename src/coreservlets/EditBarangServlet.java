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
@WebServlet("/edit_barang")
@MultipartConfig
public class EditBarangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBarangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		MySQLAccess db = new MySQLAccess();
		Barang barang = db.getBarang(id);
		request.setAttribute("id",barang.id);
		request.setAttribute("nama",barang.nama);
		request.setAttribute("kategori_id",barang.kategori_id);
		request.setAttribute("harga",barang.harga);
		request.setAttribute("image_url",barang.image_url);
		request.setAttribute("deskripsi",barang.deskripsi);
		request.setAttribute("stock",barang.stock);
		String address = "/WEB-INF/edit_barang.jsp";
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
			int id = Integer.parseInt(request.getParameter("id"));
			String nama = request.getParameter("nama");
			String text = request.getParameter("deskripsi");
			System.out.println("tes2");
			double harga = Double.parseDouble(request.getParameter("harga"));
			int kat = Integer.parseInt(request.getParameter("kategori"));
			int stok = Integer.parseInt(request.getParameter("stock"));
			System.out.println("tes3");
			String img = "assets/image/headset.png";
			MySQLAccess db = new MySQLAccess();;
			db.updateBarang(id,nama,text,harga,kat,img, stok);
			System.out.println("tes4");
			request.setAttribute("msg", "Pengubahan barang " + nama + " berhasil");
			String address = "/WEB-INF/tambah_sukses.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		catch (Exception e){
			System.out.println("tes6");
			request.setAttribute("msg", "Pengubahan barang gagal");
			String address = "/WEB-INF/tambah_sukses.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

}
