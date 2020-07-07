package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.zucc.Takeaway.model.BeanUser;
import cn.edu.zucc.Takeaway.util.BaseException;
import cn.edu.zucc.Takeaway.util.BusinessException;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleUserManager {
	public BeanUser reg(String userid, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		return null;
		
	}

	
	public BeanUser login(String userid, String pwd,String userClass) throws BaseException {
		// TODO Auto-generated method stub
		if(userid.equals("")||pwd.equals("")||userClass.equals("--��ѡ��--"))throw new BusinessException("�˺ţ����룬�û����Ͳ���Ϊ��");
		if(userid == null || pwd == null || userClass ==null)throw new BusinessException("�˺ţ����룬�û����Ͳ���Ϊ��");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql =null;
			if(userClass.equals("����Ա")) {
				sql="select Ա������,Ա�����,��½����,�Ƴ�ʱ�� from ����Ա where Ա���˺� = ?	";
			}else if(userClass.equals("�ͻ�")){
				sql="select ����,�û����,����,�Ƴ�ʱ��from �û� where �ֻ����� = ?";
			}
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userid);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("�˺Ų�����");
			
			}
			if(!rs.getString(3).equals(pwd)) {
				rs.close();
				pst.close();
				throw new BusinessException("�������");
			}
			if(rs.getDate(4)!=null) {
				rs.close();
				pst.close();
				throw new BusinessException("�˺����Ƴ�");
			}
			BeanUser U = new BeanUser();
			U.setUsername(rs.getString(1));
			U.setUserid(rs.getInt(2));
			U.setPwd(rs.getString(3));
			U.setUserClass(userClass);
			return U;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {		
		// TODO Auto-generated method stub
		if(oldPwd.equals("")||newPwd.equals("")||newPwd2.equals(""))throw new BusinessException("���벻��Ϊ��");
		if(oldPwd==null||newPwd==null||newPwd2==null)throw new BusinessException("���벻��Ϊ��");
		if(!user.getPwd().equals(oldPwd))throw new BusinessException("�������������");
		if(!newPwd.equals(newPwd2))throw new BusinessException("�������������벻һ��");
		if(newPwd.length()<=8||newPwd.length()>=20)throw new BusinessException("����Ӧ����8-15���ַ�֮��");
		Connection con = null;
		try {
			con=DBUtil.getConnection();
			String sql = null;
			if(user.getUserClass().equals("����Ա")) {
				sql = "update ����Ա set ��½���� = ? where Ա�����= ?";
			}else if(user.getUserClass().equals("�ͻ�")) {
				sql = "update �û� set ���� = ? where �û����= ?";
			}
			PreparedStatement pst =con.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2,user.getUserid());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}



}
