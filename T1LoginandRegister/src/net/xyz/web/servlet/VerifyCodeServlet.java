package net.xyz.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class VerifyCodeServlet
 */
@WebServlet("/VerifyCodeServlet")
//��ͻ��˻�ͼƬ��������֤���ı���Ϣ���浽session����
public class VerifyCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public VerifyCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("text/html;charset=utf-8");
		/*
		 * 1.��ͻ��˷���ͼƬ
		 * 2.��ͼƬ�е��ı���Ϣ���浽session����
		 */
		VerifyCode vc=new VerifyCode();
		BufferedImage image=vc.getImage();
		request.getSession().setAttribute("session", vc.getText());
		VerifyCode.output(image, response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
