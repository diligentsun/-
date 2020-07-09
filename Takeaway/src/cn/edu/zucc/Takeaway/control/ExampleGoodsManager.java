package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import cn.edu.zucc.Takeaway.model.BeanCategory;
import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanGoods;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.util.BusinessException;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleGoodsManager {

	public List<BeanGoods> loadAllUsers(BeanCategory BC) {
		// TODO Auto-generated method stub
		
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "select 商品.* , 商家.商家名 from 商品 ,商家 where 商品.商家编号 = 商家.商家编号 and 商品.分类编号 = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,BC.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods bc = new BeanGoods();
				bc.setGoods_id(rs.getInt(1));
				bc.setStore_id(rs.getInt(2));
				bc.setGategory_id(rs.getInt(3));
				bc.setGoods_name(rs.getString(4));
				bc.setPrice(rs.getDouble(5));
				bc.setSale_price(rs.getDouble(6));
				bc.setStore_name(rs.getString(8));
				System.out.print(rs.getString(8)+"zzzz");
				bc.setGategory_name(BC.getName());

				result.add(bc);
			}
			con.commit();
			return result;
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
		return null;
	}

	public List<BeanGoods> loadAllUsers(BeanStore BS) {
		// TODO Auto-generated method stub
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "select 商品.* , 商品类别.分类栏目名 from 商品 ,商品类别 where 商品.分类编号 = 商品类别.分类编号 and 商家编号 = ?  and 商品.移除时间 is null";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,BS.getId());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods bc = new BeanGoods();
				bc.setGoods_id(rs.getInt(1));
				bc.setStore_id(rs.getInt(2));
				bc.setGategory_id(rs.getInt(3));
				bc.setGoods_name(rs.getString(4));
				bc.setPrice(rs.getDouble(5));
				bc.setSale_price(rs.getDouble(6));
				bc.setGategory_name(rs.getString(8));
				bc.setStore_name(BS.getName());
				result.add(bc);
			}
			con.commit();
			return result;
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
		return null;
	}

	public void addGoods(BeanGoods goods) throws BusinessException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=DBUtil.getConnection();
			con.setAutoCommit(false);
			String sql = "select 分类编号 from 商品类别 where 分类栏目名 = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,goods.getGategory_name());
			ResultSet rs = pst.executeQuery(); 
			if(rs.next())goods.setGategory_id(rs.getInt(1));
			else {
				rs.close();
				pst.close();
				throw new BusinessException("该分类不存在");	
			}
			rs.close();
			pst.close();
			
			sql = "update 商品类别 set 商品数量=商品数量+1 where 分类栏目名 = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1,goods.getGategory_name());
			if(pst.executeUpdate()!=1) {
				pst.close();
				throw new BusinessException("添加出错");
			}
			
			sql ="insert into 商品(商家编号,分类编号,商品名,商品价格,优惠价格)  values(?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, goods.getStore_id());
			pst.setInt(2, goods.getGategory_id());
			pst.setString(3,goods.getGoods_name());
			pst.setDouble(4, goods.getPrice());
			pst.setDouble(5, goods.getSale_price());
			pst.execute();
			con.commit();
			pst.close();
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
	}

	public void removeGoods(BeanGoods goods) throws BusinessException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=DBUtil.getConnection();
			con.setAutoCommit(false);

			String sql = "update 商品类别 set 商品数量=商品数量-1 where 分类栏目名 = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,goods.getGategory_name());
			if(pst.executeUpdate()!=1) {
				pst.close();
				throw new BusinessException("删除出错");
			}
			
			sql ="update 商品 set 移除时间 = ? where 商品编号=?";
			pst = con.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(2, goods.getStore_id());
			pst.execute();
			con.commit();
			pst.close();
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
	}

	public void ModifyGoods(BeanGoods goods) throws BusinessException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con=DBUtil.getConnection();
			con.setAutoCommit(false);

			String sql = "update 商品 set 商品价格=?,优惠价格=?  where 商品编号 = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, goods.getPrice());
			pst.setDouble(2, goods.getSale_price());
			pst.setInt(3, goods.getGoods_id());
			pst.execute();
			con.commit();
			pst.close();
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
		
	}

}
