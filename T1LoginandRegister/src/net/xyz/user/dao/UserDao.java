package net.xyz.user.dao;

import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import net.xyz.user.domain.User;

/*
 * User��ҵ���߼���
 */
public class UserDao {
    String path="E:\\Test\\user.xml";
	//String path="D:\\ѧϰ\\javaEE\\workspace\\T1LoginandRegister\\WebContent\\WebContent\\user.xml";
    //�����û�����ѯ�û��Ƿ����
    public User selectUserByUsername(String userName) {
    	/*
    	 * 1.�õ�document����
    	 * 2.xpath��ѯ
    	 * 3.��ѯ����Ƿ���ڣ���������ڷ���null
    	 * 4.������ڣ��Ѳ�ѯ�������ݷ�װΪUser���󲢷���
    	 *
    	 */
    	
    	SAXReader saxReader=new SAXReader();
    	try {
			Document document=saxReader.read(path);
			Element ele=(Element) document.selectSingleNode("//user[@username='"+userName+"']");
			if(ele==null) return null;
			String username=ele.attributeValue("username");
			String password=ele.attributeValue("password");
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			return user;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
    }
    //����û�
    public void addUser(User user) {
    	/*
    	 * 1.�õ�document����
    	 * 2.�ҵ��ĵ��ĸ�Ԫ��
    	 * 3.�ڸ�Ԫ�������user
    	 * 4.��дxml�ļ�
    	 */
    	SAXReader saxReader=new SAXReader();
    	try {
			Document document=saxReader.read(path);
			Element root=document.getRootElement();
			Element us=root.addElement("user");
			us.addAttribute("username", user.getUsername());
			us.addAttribute("password", user.getPassword());
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter xmlWriter=new XMLWriter(new FileOutputStream(path),format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
