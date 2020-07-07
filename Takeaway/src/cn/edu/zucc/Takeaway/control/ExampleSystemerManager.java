package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.model.BeanUser;
import cn.edu.zucc.Takeaway.util.BusinessException;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleSystemerManager {

	public BeanUser reg(String useran,String username,String pwd1, String pwd2) throws BusinessException {
		Connection con = null;
		if(!pwd1.equals(pwd2))throw new BusinessException("两次密码不相等");
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "select * from 管理员 where 员工账号=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, useran);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("账号已存在");
			}
			sql = "insert into 管理员(员工账号,员工姓名,登陆密码) values(?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, useran);
			pst.setString(2, username);
			pst.setString(3,pwd1);
			pst.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<BeanStore> loadAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
