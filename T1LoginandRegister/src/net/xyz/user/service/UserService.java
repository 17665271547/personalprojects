package net.xyz.user.service;

import net.xyz.user.dao.UserDao;
import net.xyz.user.domain.User;
/*
 * �û��߼���
 */
public class UserService {
    private UserDao userDao= new UserDao();
    /*
     * �û�ע�Ṧ�ܣ�����û��Ƿ���ڣ����ڵĻ��׳��쳣�������������ݿ�����û�
     */
    public void registUser(User user) throws UserException{
    	
    	User _user=userDao.selectUserByUsername(user.getUsername());
    	if(_user!=null) throw new UserException("�û���"+_user.getUsername()+"�ѱ�ע��");
    	userDao.addUser(user);
    }
    /*
     * �û���¼���ܣ�ʹ�ñ��е����ݽ��в�ѯ������û��������ڣ��׳�������Ϣ�������������׳�������Ϣ
     */
	public User login(User user) throws UserException{
		User _user=userDao.selectUserByUsername(user.getUsername());
		if(_user==null) throw new UserException("�û���������");
		if(!_user.getPassword().equals(user.getPassword())) {
			throw new UserException("�������");
		}
		/*
		 * �������ݿ��в�ѯ������user��������form����Ϊform��ֻ���û���������,��user���û���������Ϣ
		 * 
		 */
		return _user;
	}
}
