package cn.edu.zucc.Takeaway.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanRider;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.util.BusinessException;
import cn.edu.zucc.Takeaway.util.DBUtil;

public class ExampleStoreManager {

	public List<BeanStore> loadAllUsers() throws BusinessException{
		// TODO Auto-generated method stub
		List<BeanStore> result = new ArrayList<BeanStore>();
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "select * from �̼� where �Ƴ�ʱ�� is null";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanStore bs = new BeanStore();
				bs.setId(rs.getInt(1));
				System.out.print(bs.getId());
				bs.setName(rs.getString(2));
				bs.setRating(rs.getInt(3));
				bs.setPCC(rs.getDouble(4));
				bs.setTotal_sales(rs.getDouble(5));
				result.add(bs);
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

	public void modifyStore(BeanStore store) throws BusinessException {
		// TODO Auto-generated method stub
		Connection con = null;
			if(store.getName().length()>20) {
				throw new BusinessException("�̵����Ƴ��ȱ���С��20");
		}
		if(store.getPCC()<=0) {
			throw new BusinessException("�˾����ѱ������0");
		}
		try {
			con=DBUtil.getConnection();
			String sql = "update �̼� set �̼���=? , �̼��Ǽ�=? , �˾�����=? where �̼ұ�� = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, store.getName());
			pst.setInt(2, store.getRating());
			pst.setDouble(3, store.getPCC());
			pst.setInt(4, store.getId());
			if(pst.execute()) {
				pst.close();
				throw new BusinessException("�޸ĳ���");
			}
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

	public void addStore(BeanStore store) throws BusinessException {
		// TODO Auto-generated method stub
		Connection con = null;
		if(store.getName().length()>20) {
			throw new BusinessException("�̵����Ƴ��ȱ���С��20");
		}
		if(store.getPCC()<=0) {
			throw new BusinessException("�˾����ѱ������0");
		}
		try {
			con=DBUtil.getConnection();
			String sql = "insert into �̼�(�̼���,�̼��Ǽ�,�˾�����,������) values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, store.getName());
			pst.setInt(2, store.getRating());
			pst.setDouble(3, store.getPCC());
			pst.setDouble(4,0);
			if(pst.execute()) {
				pst.close();
				throw new BusinessException("��ӳ���");
			}
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

	public void removeStore(BeanStore store) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DBUtil.getConnection();
			String sql = "update �̼� set �Ƴ�ʱ�� = ? where �̼ұ��= ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDate(1, new java.sql.Date(System.currentTimeMillis()));
			pst.setInt(2, store.getId());
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
