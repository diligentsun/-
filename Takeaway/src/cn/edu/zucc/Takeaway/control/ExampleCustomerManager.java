package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleCustomerManager {

	public List<BeanCustomer> loadAllUsers() {
		// TODO Auto-generated method stub
		List<BeanCustomer> result = new ArrayList<BeanCustomer>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from 用户 where 移除时间 is null";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanCustomer bc = new BeanCustomer();
				bc.setId(rs.getInt(1));
				bc.setName(rs.getString(2));
				bc.setSex(rs.getString(3));
				bc.setPwd(rs.getString(4));
				bc.setPhonenum(rs.getString(5));
				bc.setEmail(rs.getString(6));
				bc.setCity(rs.getString(7));
				bc.setCreateTime(rs.getDate(8));
				bc.setIsMember(rs.getString(9));
				bc.setMemberEndTime(rs.getDate(10));
				bc.setRemoveTime(rs.getDate(11));
				result.add(bc);
			}
			return result;
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


	public void modifyCustomer(BeanCustomer customer) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update 用户 set 姓名 = ? ,性别=?,密码=?,邮箱=?,所在城市=?,是否会员=?,会员截止日期=? wehre 用户编号=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getSex());
			pst.setString(3, customer.getPwd());
			pst.setString(4, customer.getEmail());
			pst.setString(5, customer.getCity());
			pst.setString(6, customer.getIsMember());
			if(customer.getMemberEndTime()!=null)pst.setDate(7, new java.sql.Date(customer.getMemberEndTime().getTime()));
			else pst.setNull(7, java.sql.Types.DATE);
			pst.setInt(8, customer.getId());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
