package br.com.grupo9.sistemadereservas.controle.Util;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter{
	 public void destroy() {
          
      }
   
      public void doFilter(ServletRequest request, ServletResponse response,
             FilterChain chain) throws IOException, ServletException {
   
         HttpSession sess = ((HttpServletRequest) request).getSession(true);
   
         String paginaAtual = ((HttpServletRequest) request).getServletPath();
         System.out.println("Admin log acess :"+paginaAtual);
         
         RequestDispatcher dispacher = request.getRequestDispatcher("login.do");
         
         dispacher.forward(request, response);
         
         if (sess.getAttribute("currentPage") == null) {
             sess.setAttribute("lastPage", paginaAtual);
             sess.setAttribute("currentPage", paginaAtual);
         } else {
   
             String oldCurrentPage = sess.getAttribute("currentPage").toString();
             if (!oldCurrentPage.equals(paginaAtual)) {
               sess.setAttribute("lastPage", oldCurrentPage);
               sess.setAttribute("currentPage", paginaAtual);
             }
         }
   
         chain.doFilter(request, response);
   
      }
   
      public void init(FilterConfig arg0) throws ServletException {
         // TODO Auto-generated method stub
   
      }
}

