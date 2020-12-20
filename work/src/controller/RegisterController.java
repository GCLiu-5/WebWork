package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SaveToSql;
import vo.User;

@WebServlet(urlPatterns = "/register.do")
public class RegisterController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String phone = request.getParameter("phone");
		String password = request.getParameter("password1");
		String checkCode = request.getParameter("checkCode");
		String forwardPath = "";
		
		HttpSession session = request.getSession();
		
		String saveCheckCode = (String)session.getAttribute("checkCode");
		if (!checkCode.equals(saveCheckCode)) {
			forwardPath = "/error.jsp";
			request.setAttribute("info", "验证码不正确！");
		} else {
			User user = new User(phone, password);
			SaveToSql saveToSql = new SaveToSql();
			if (saveToSql.save(user)) {
				forwardPath = "/main.jsp";
				request.setAttribute("info", "该手机号已注册！");
			}else {
				forwardPath = "/error.jsp";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forwardPath);
		rd.forward(request, response);
	}

}
