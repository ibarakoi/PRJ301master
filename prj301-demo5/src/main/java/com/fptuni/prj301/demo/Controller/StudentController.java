/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fptuni.prj301.demo.Controller;

import com.fptuni.prj301.demo.Student.StudentDAO;
import com.fptuni.prj301.demo.Student.StudentDTO;
import com.fptuni.prj301.demo.User.UserDTO;
import com.fptuni.prj301.demo.utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DUNGHUYNH
 */
public class StudentController extends HttpServlet {

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

            String action = request.getParameter("action");
            String keyword = request.getParameter("keyword");
            String city = request.getParameter("city");
           
            // Check security
            HttpSession session = request.getSession(false);
            UserDTO currentUser = null;
                    
            if (session != null){       
                currentUser = (UserDTO) session.getAttribute("usersession");
            }
            
            log("Debug: " + currentUser);
            if (currentUser == null){
                log("Debug: Redirect to login page" + currentUser);            
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
          
            StudentDAO studentDAO = new StudentDAO();
            
            if (action == null || action.equals("list")){
                
                List<StudentDTO> list = studentDAO.list(keyword, city);
                
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("studentlist.jsp");
                rd.forward(request, response);
            }
            
            else if ( action.equals("details")){
                
                Long id = null;
                try{
                    id = Long.parseLong(request.getParameter("id"));      
                }catch (NumberFormatException ex){
                    
                }
                  
                StudentDTO student = null;
                if (id != null){
                    student =  studentDAO.load(id);
                }

                request.setAttribute("object", student);
                RequestDispatcher rd = request.getRequestDispatcher("studentdetails.jsp");
                rd.forward(request, response);
            }
            
            else if ( action.equals("edit")){
                
               
            }
            
            else if ( action.equals("create")){
                
                
            }
            
            
            else if (action.equals("update")){
                 
            }
            
            else if (action.equals("insert")){
                 
                  
                 
            }
            else if (action.equals("delete")){
                
                Long id = null;
                try{
                    id = Long.parseLong(request.getParameter("id"));      
                }catch (NumberFormatException ex){
                    
                }
                  
                if (id != null){
                    studentDAO.delete(id);
                }

                List<StudentDTO> list = studentDAO.list(keyword, city);
                
                request.setAttribute("list", list);
                RequestDispatcher rd = request.getRequestDispatcher("studentlist.jsp");
                rd.forward(request, response);
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
