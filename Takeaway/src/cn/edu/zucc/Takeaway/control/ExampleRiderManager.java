package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanRider;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleRiderManager {

	public List<BeanRider> loadAllUsers() {
		// TODO Auto-generated method stub
		List<BeanRider> result = new ArrayList<BeanRider>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from 骑手信息 where 移除时间 is null";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanRider br = new BeanRider();
				br.setId(rs.getInt(1));
				br.setName(rs.getString(2));
			
				br.setCreateTime(rs.getDate(3));
				br.setSign(rs.getString(4));
				result.add(br);
			}
			pst.close();
			rs.close();
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

	public void ModifyRider(BeanRider rider) {
		// TODO Auto-generated method stub
		List<BeanRider> result = new ArrayList<BeanRider>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update 骑手信息 set 骑手姓名=? , 骑手身份 = ? where 骑手编号 = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, rider.getName());
			pst.setString(2,rider.getSign());
			pst.setInt(3, rider.getId());
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

	public void removeRider(BeanRider rider) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update 骑手信息 set 移除时间 = ? where 骑手编号= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(2, rider.getId());
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

	public void addRider(BeanRider rider) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into 骑手信息(骑手姓名,入职日期,骑手身份) values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, rider.getName());
			pst.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			pst.setString(3, rider.getSign());
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
