package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
