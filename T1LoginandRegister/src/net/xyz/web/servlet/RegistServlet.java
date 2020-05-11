package net.xyz.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xyz.user.domain.User;
import net.xyz.user.service.UserException;
import net.xyz.user.service.UserService;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		UserService userService=new UserService();
		//��װ�����ݣ�����UserService�㣬���������ʾ�쳣��Ϣ�����浽request����
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String verifyCode=request.getParameter("verifyCode");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setVerifyCode(verifyCode);
		//У���û����Ƿ���Ϲ淶����map��ת������Ϣ
		Map<String,String> errors=new HashMap<String,String>();
		//У���û����Ƿ���Ϲ淶
		if(username==null||username.trim().isEmpty()) {
			errors.put("username", "�û�������Ϊ��");
		}else if(username.length()<3||username.length()>15) {
			errors.put("username","�û������ȱ�����3-15֮�䣡");
		}
		//У�������Ƿ���Ϲ淶
		if(password==null||password.trim().isEmpty()) {
			errors.put("password", "���벻��Ϊ��");
		}else if(password.length()<3||password.length()>15) {
			errors.put("password","���볤�ȱ�����3-15֮�䣡");
		}
		/*
		 * �ж�map�Ƿ�Ϊ�գ���Ϊ�գ�˵�����ڴ���
		 * 
		 */
		System.out.println(errors);
		if(errors!=null&&errors.size()>0) {
			/*
			 * 1.����errors��request��
			 * 2.����form��request���У�Ϊ�˻���
			 * 3.ת����regist.jsp
			 */
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		//У���û���֤���Ƿ���ȷ���������ȷ���������Ϣ
		String sessionVerifyCode=(String) request.getSession().getAttribute("session");
		if(!sessionVerifyCode.equals(verifyCode)) {
			request.setAttribute("msg", "��֤����д����");
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);;
		}
			try {
				userService.registUser(user);
				response.getWriter().write("<h1>ע��ɹ�<h1>"+"<a href='"+request.getContextPath()+"/user/login.jsp'>��������¼</a>");
			} catch (UserException e) {
				
				request.setAttribute("msg", e.getMessage());
				request.getRequestDispatcher("/user/regist.jsp").forward(request, response);;
				System.out.println("������");
			}
			
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
