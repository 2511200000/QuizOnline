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
import khanhhq.daos.TblQuestionDAO;
 import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class CreateQuestionServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(CreateQuestionServlet.class.getName());
    private final String CREATE = "createQuestion.jsp";
    private final String CREATESUCCESS = "PrintDataAdminServlet";

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
        String id = request.getParameter("txtID");
        String questionContent = request.getParameter("txtQuestionContent");
        String answerContent1 = request.getParameter("txtAnswerContent1");
        String answerContent2 = request.getParameter("txtAnswerContent2");
        String answerContent3 = request.getParameter("txtAnswerContent3");
        String answerContent4 = request.getParameter("txtAnswerContent4");
        String answerCorrect = request.getParameter("cboAnswer");
        String subject = request.getParameter("cboSubject");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String url = CREATE;
        try {
            /* TODO output your page here. You may use following sample code. */
            if (id.isEmpty() || questionContent.isEmpty() || answerContent1.isEmpty() || answerContent2.isEmpty() || answerContent3.isEmpty() || answerContent4.isEmpty()) {
                String msg = "Can not bank";
                request.setAttribute("CREATEQUESTIONERR", msg);
            } else {
                TblQuestionDAO daoQuestion = new TblQuestionDAO();
                if (answerCorrect.equals("A")) {
                    boolean result = daoQuestion.createQuestion(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerContent1, date, subject, true);
                    if (result) {
                        url = CREATESUCCESS;
                    }
                } else if (answerCorrect.equals("B")) {
                    boolean result = daoQuestion.createQuestion(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerContent2, date, subject, true);
                    if (result) {
                        url = CREATESUCCESS;
                    }
                } else if (answerCorrect.equals("C")) {
                    boolean result = daoQuestion.createQuestion(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerContent3, date, subject, true);
                    if (result) {
                        url = CREATESUCCESS;
                    }
                } else {
                    boolean result = daoQuestion.createQuestion(id, questionContent, answerContent1, answerContent2, answerContent3, answerContent4, answerContent4, date, subject, true);
                    if (result) {
                        url = CREATESUCCESS;
                    }
                }
            }
        } catch (SQLException e) {
            String errMSG = e.getMessage();
            log("CreateAccountServlet_SQL " + errMSG);
            if (errMSG.contains("duplicate")) {
                String msg = "ID is exist";
                request.setAttribute("CREATEQUESTIONERR", msg);
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
