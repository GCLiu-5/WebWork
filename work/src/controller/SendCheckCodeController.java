package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.SendCheckCode;
import vo.PhoneCode;
import vo.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



public class SendCheckCodeController extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String phone = null;
        String result= null;
        PhoneCode phoneCode = null;
        
        phone = request.getParameter("phone");
        
		try {
			result = SendCheckCode.sendCode(phone, "19469751");
	        ObjectMapper om = new ObjectMapper();
	        phoneCode = om.readValue(result, PhoneCode.class);
	        response.setContentType("text/plain");
	        if(phoneCode.getCode()==200){
	        	
	        	HttpSession session = request.getSession();
	            session.setAttribute("checkCode",phoneCode.getObj());
	            session.setMaxInactiveInterval(60*3);
	            response.getWriter().write("200");
	            
	        }else {
	        	
				response.getWriter().write(phoneCode.getCode()+"");
				
			}
	           
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
