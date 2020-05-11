package net.xyz.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.xyz.user.domain.User;
import net.xyz.user.service.UserException;
import net.xyz.user.service.UserService;

/**
 *��װ������
 *����service��login�����������õ����ص�user����
 *����׳��쳣����ȡ�쳣��Ϣ�����浽reqeust��ת����login.jsp
 *���û���쳣�����û���Ϣ���浽session�У��ض���weclome.jsp
 *
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//������뷽ʽ
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("text/html;charset=utf-8");
	    UserService userService=new UserService();
	    String username=request.getParameter("username");
	    String password=request.getParameter("password");
	    User user=new User();
	    user.setUsername(username);
	    user.setPassword(password);
	    System.out.println(user.toString());
		try {
			 System.out.println(1);
			 User user1 = userService.login(user);
			 System.out.println(2);
			 request.getSession().setAttribute("user", user1);
			 System.out.println(3);
			 response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
			 System.out.println(4);
		} catch (UserException e) {
			System.out.println("������");
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		}
	 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
