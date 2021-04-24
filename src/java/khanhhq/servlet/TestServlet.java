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
import javax.servlet.http.HttpSession;
import khanhhq.cart.CartObject;
import khanhhq.daos.TblMarkUserDAO;
import khanhhq.daos.TblQuestionDAO;
import khanhhq.daos.TblTestDAO;
import khanhhq.dtos.TblQuestionDTO;
 import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(TestServlet.class.getName());
    private final String SUCCESS = "markUser.jsp";
    private final String FAIL = "displayUser.jsp";

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
        String[] answerContent = request.getParameterValues("chkItem");
        String[] idQuestion = request.getParameterValues("txtID");
        String idQues = request.getParameter("txtID");
        int countQuestion = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        String subject = request.getParameter("txtSubject");
        String answerUser = null;
        float diem;
        String url = FAIL;
        try {

            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USERID");

            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            if (cart == null) {
                cart = new CartObject();
            }

            TblQuestionDAO daoQuestion = new TblQuestionDAO();

            for (String id : idQuestion) {
                countQuestion++;
                TblQuestionDTO dto = daoQuestion.findItemByID(id);
                if (answerContent != null) {
                    for (String answer : answerContent) {
                        if (answer.equals(dto.getAnswerCorrect())) {
                            cart.addtemToCart(dto);
                        }

                    }
                }
            }

            diem = cart.TongDiem();
            session.setAttribute("DIEM", diem);
            int corect = cart.Correct();
            session.setAttribute("CORRECT", corect);
            TblTestDAO daoTest = new TblTestDAO();
            int idTest = daoTest.printIdTest();
            if (idTest == 0) {
                idTest++;
            } else {
                idTest++;
            }
            daoTest.createTest(idTest, subject, idQues, countQuestion, userID, date);
            
            TblMarkUserDAO daoMark = new TblMarkUserDAO();
            if (answerContent != null) {
                for (int i = 0; i < answerContent.length; i++) {
                    TblQuestionDTO dto = daoQuestion.findItemByID(idQuestion[i]);
                    if (dto.getAnswerCorrect().equals(answerContent[i])) {
                        daoMark.createMark(userID, subject, idTest, idQuestion[i], answerContent[i], true, corect, diem);
                    } else {
                        daoMark.createMark(userID, subject, idTest, idQuestion[i], answerContent[i], false, corect, diem);
                    }
                }
            } else {
                for (int i = 0; i < idQuestion.length; i++) {
                    daoMark.createMark(userID, subject, idTest, idQuestion[i], "null", false, corect, diem);
                }
            }
            url = SUCCESS;
        } catch (NullPointerException e) {
            BasicConfigurator.configure();
            log.error("NullPointerException");
        } catch (SQLException e) {
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
