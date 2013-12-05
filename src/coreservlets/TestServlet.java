package coreservlets;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.ws.Response;

/** Reads firstName and lastName request parameters and forwards
 *  to JSP page to display them. Uses session-based bean sharing
 *  to remember previous values.
 *  <P>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF 2.0, Ajax, GWT, and Java</a>.
 */

@WebServlet("/test")
public class TestServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession();
    PrintWriter out = response.getWriter();
    out.println("hi");
    MySQLAccess db = new MySQLAccess();
    
    synchronized(session) {
    	List<String> emails = db.getAllEmail();
    	for (int i=0;i<emails.size();i++){
    		out.println(emails.get(i));
    	}
    	db.daftarUser("alifradityar", "12345678", "Alif Raditya Rochman", "alifradityar@gmail.com");
//      NameBean nameBean = 
//        (NameBean)session.getAttribute("name");
//      if (nameBean == null) {
//        nameBean = new NameBean();
//        session.setAttribute("name", nameBean);
//      }
//      nameBean.setFirstName(request.getParameter("firstName"));
//      nameBean.setLastName(request.getParameter("lastName"));
//      String address = "/WEB-INF/pendaftaran.jsp";
//      RequestDispatcher dispatcher =
//        request.getRequestDispatcher(address);
//      dispatcher.forward(request, response);
    
    }
  }
}
