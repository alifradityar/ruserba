package coreservlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/** Reads firstName and lastName request parameters and forwards
 *  to JSP page to display them. Uses session-based bean sharing
 *  to remember previous values.
 *  <P>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF 2.0, Ajax, GWT, and Java</a>.
 */

@WebServlet("/pendaftaran/kartu")
public class DaftarKartuKreditServlet extends HttpServlet {
	
	
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    synchronized(session) {
//      NameBean nameBean = 
//        (NameBean)session.getAttribute("name");
//      if (nameBean == null) {
//        nameBean = new NameBean();
//        session.setAttribute("name", nameBean);
//      }
//      nameBean.setFirstName(request.getParameter("firstName"));
//      nameBean.setLastName(request.getParameter("lastName"));
    	List<String> months = new ArrayList<String>();
    	months.add("Januari");
    	months.add("Februari");
    	months.add("Maret");
    	months.add("April");
    	months.add("Mei");
    	months.add("Juni");
    	months.add("Juli");
    	months.add("Agustus");
    	months.add("September");
    	months.add("Oktober");
    	months.add("November");
    	months.add("Desember");
    	List<Integer> tahun = new ArrayList<Integer>();
    	for (int i=2050;i>=2014;i--){
    		tahun.add(i);
    	}
    	String address = "/WEB-INF/daftar_kartukredit.jsp";
    	request.setAttribute("months", months);
    	request.setAttribute("tahun", tahun);
    	RequestDispatcher dispatcher = request.getRequestDispatcher(address);
    	dispatcher.forward(request, response);
    
    }
  }
}
