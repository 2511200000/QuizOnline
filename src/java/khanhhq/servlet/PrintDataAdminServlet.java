/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khanhhq.daos.TblQuestionDAO;
import khanhhq.daos.tblSubjectDAO;
import khanhhq.dtos.TblQuestionDTO;
import khanhhq.dtos.tblSubjectDTO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class PrintDataAdminServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(PrintDataAdminServlet.class.getName());
    private final String DATAADMIN = "displayAdmin.jsp";
    private final String INVALID = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String txtIndex = request.getParameter("txtIndex");
        String url = INVALID;

        try {
            HttpSession session = request.getSession();
            String fullname = (String) session.getAttribute("FULLNAMEADMIN");
            tblSubjectDAO daoSubject = new tblSubjectDAO();

            TblQuestionDAO daoQuestion = new TblQuestionDAO();

            int count = daoQuestion.countAllQuesionAdmin();
            if (txtIndex == null) {
                txtIndex = "1";
            }

            int index = Integer.parseInt(txtIndex);
            int pageSize = 10;
            int endPage = 0;
            endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }

            if (fullname != null) {
                daoQuestion.printData(index);
                daoQuestion.statusBoolean();
                daoQuestion.Answer();
                Map<Boolean, String> status = daoQuestion.getStatus();
                Map<String, String> answer = daoQuestion.getAnswerCorrect();
                List<TblQuestionDTO> result = daoQuestion.getDataAdmin();
                if (result != null) {
                    url = DATAADMIN;
                    session.setAttribute("DISPLAYADMIN", result);
                    session.setAttribute("STATUS", status);
                    session.setAttribute("ANSWER", answer);
                    request.setAttribute("ENDPAGE", endPage);
                    request.setAttribute("INDEX", index);
                }
            }
            /* TODO output your page here. You may use following sample code. */
            List<tblSubjectDTO> subject = daoSubject.getAllSubject();
            if (subject != null) {
                url = DATAADMIN;
                session.setAttribute("CBONAME", subject);
            }
        } catch (SQLException e) {
            BasicConfigurator.configure();
            log.error("SQLException");
         } catch (NamingException e) {
            BasicConfigurator.configure();
            log.error("NamingException");
e.printStackTrace();
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
