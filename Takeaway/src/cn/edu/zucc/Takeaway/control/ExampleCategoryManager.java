package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.Takeaway.model.BeanCategory;
import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleCategoryManager {

	public List<BeanCategory> loadAllUsers() {
		// TODO Auto-generated method stub
		List<BeanCategory> result = new ArrayList<BeanCategory>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from 商品类别 ";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanCategory bc = new BeanCategory();
				bc.setId(rs.getInt(1));
				bc.setName(rs.getString(2));
				bc.setNumber(rs.getInt(3));
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

	public void addCategory(BeanCategory category) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con = DBUtil.getConnection();
			String sql = "insert into 商品类别(分类栏目名,商品数量) values(?,0)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, category.getName());
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

	public void reomveCategory(BeanCategory category) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "delete from 商品类别 where 分类编号 = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, category.getId());
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
