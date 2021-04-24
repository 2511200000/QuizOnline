/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khanhhq.daos.TblLoginDAO;
 import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class CreateAccountServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(CreateAccountServlet.class.getName());
    private final String CREATE = "createAccount.jsp";
    private final String LOGINPAGE = "login.jsp";

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
        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

        String url = CREATE;
        try {
            if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {
                String msgFill = "Emai or name or password must be filled";
                request.setAttribute("CREATEERR", msgFill);
            } else if (!password.equals(confirm)) {
                String msgFill = "Password and confirm not match";
                request.setAttribute("CREATEERR", msgFill);
            } else {
                TblLoginDAO daoLogin = new TblLoginDAO();
                boolean result = daoLogin.createAccount(email, name, sha256hex, "312312", true);
                if (result) {
                    url = LOGINPAGE;
                }
            }
            /* TODO output your page here. You may use following sample code. */
        } catch (SQLException e) {
            String errMSG = e.getMessage();
            log("CreateAccountServlet_SQL " + errMSG);
            if (errMSG.contains("duplicate")) {
                String msg = "Email is exist";
                request.setAttribute("CREATEERR", msg);
            }
            BasicConfigurator.configure();
            log.error("SQLException");
        } catch (NamingException e) {
            BasicConfigurator.configure();
            log.error("NamingException");
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
