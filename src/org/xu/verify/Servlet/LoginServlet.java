package org.xu.verify.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		if (!code.equalsIgnoreCase((String)request.getSession().getAttribute("text"))){
			request.setAttribute("msg", "验证码输入错误");
			request.getRequestDispatcher("/java_practice/login.jsp").forward(request, response);
			return;
		}
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.equalsIgnoreCase("xuzuohong")){
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(3600);
			response.addCookie(cookie);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("/java_practice/succ1.jsp");
		} else {
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/java_practice/login.jsp").forward(request, response);
		}
	}

}
