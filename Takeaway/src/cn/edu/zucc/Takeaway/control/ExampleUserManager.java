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
		if(userid.equals("")||pwd.equals("")||userClass.equals("--请选择--"))throw new BusinessException("账号，密码，用户类型不能为空");
		if(userid == null || pwd == null || userClass ==null)throw new BusinessException("账号，密码，用户类型不能为空");
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql =null;
			if(userClass.equals("管理员")) {
				sql="select 员工姓名,员工编号,登陆密码,移除时间 from 管理员 where 员工账号 = ?	";
			}else if(userClass.equals("客户")){
				sql="select 姓名,用户编号,密码,移除时间from 用户 where 手机号码 = ?";
			}
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, userid);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("账号不存在");
			
			}
			if(!rs.getString(3).equals(pwd)) {
				rs.close();
				pst.close();
				throw new BusinessException("密码错误");
			}
			if(rs.getDate(4)!=null) {
				rs.close();
				pst.close();
				throw new BusinessException("账号已移除");
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
		if(oldPwd.equals("")||newPwd.equals("")||newPwd2.equals(""))throw new BusinessException("输入不能为空");
		if(oldPwd==null||newPwd==null||newPwd2==null)throw new BusinessException("输入不能为空");
		if(!user.getPwd().equals(oldPwd))throw new BusinessException("旧密码输入错误");
		if(!newPwd.equals(newPwd2))throw new BusinessException("新密码两次输入不一致");
		if(newPwd.length()<=8||newPwd.length()>=20)throw new BusinessException("密码应该再8-15个字符之间");
		Connection con = null;
		try {
			con=DBUtil.getConnection();
			String sql = null;
			if(user.getUserClass().equals("管理员")) {
				sql = "update 管理员 set 登陆密码 = ? where 员工编号= ?";
			}else if(user.getUserClass().equals("客户")) {
				sql = "update 用户 set 密码 = ? where 用户编号= ?";
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
