package cn.edu.zucc.Takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.Takeaway.control.ExampleStoreManager;
import cn.edu.zucc.Takeaway.model.BeanCategory;
import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanGoods;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
import cn.edu.zucc.Takeaway.util.BaseException;

public class FrmGoodsInformation extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("�����Ʒ");
	private Button btnWatchStore = new Button("�鿴������Ϣ");
	private Button btnModify = new Button("�޸���Ϣ");
	private Button btnRemove = new Button("�Ƴ���Ʒ");
	private Object tblTitle[]={"��Ʒ���","��Ʒ��","�������","��������","�۸�","�ۺ�۸�"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	List<BeanGoods> users=null;
	BeanCategory BC =null;
	BeanStore BS =null;
	BeanGoods Goods=new BeanGoods(); 

	private void reloadUserTable(BeanCategory BC){
		try {
			users=TakeawayUtil.GoodsManager.loadAllUsers(BC);
			tblData =new Object[users.size()][6];
			System.out.print(users.size());
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getGoods_id();
				tblData[i][1]=""+users.get(i).getGoods_name();
				tblData[i][2]=""+users.get(i).getStore_name();
				tblData[i][3]=""+users.get(i).getGategory_name();
				tblData[i][4]=""+users.get(i).getPrice();
				tblData[i][5]=""+users.get(i).getSale_price();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void reloadUserTable(BeanStore BS){
		try {
			users=TakeawayUtil.GoodsManager.loadAllUsers(BS);
			tblData =new Object[users.size()][6];
			System.out.print(users.size());
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getGoods_id();
				tblData[i][1]=""+users.get(i).getGoods_name();
				tblData[i][2]=""+users.get(i).getStore_name();
				tblData[i][3]=""+users.get(i).getGategory_name();
				//System.out.print(users.get(i).getGategory_name());
				tblData[i][4]=""+users.get(i).getPrice();
				tblData[i][5]=""+users.get(i).getSale_price();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmGoodsInformation(JDialog d, String s, boolean b,BeanCategory Category) {
		super(d,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnWatchStore);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		BC=Category;
		this.reloadUserTable(Category);
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.btnWatchStore.addActionListener(this);
		this.validate();
	
	}
	public FrmGoodsInformation(JDialog d, String s, boolean b ,BeanStore Store) {
		super(d,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		//toolBar.add(btnWatch);
		toolBar.add(btnModify);
		toolBar.add(btnRemove);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		BS=Store;
		this.reloadUserTable(Store);
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnAdd.addActionListener(this);
		//this.btnWatch.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnRemove.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAdd){
			FrmAddGoods dlg = new FrmAddGoods(this,"�����Ʒ",true,BS);
			dlg.setVisible(true);
			if(dlg.getGoods()!=null){//ˢ�±��
				this.reloadUserTable(BS);
			}
		}
		
		if(e.getSource()==this.btnModify){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ��ͻ�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanGoods GoodsS = this.users.get(i);
			FrmModifyGoods dlg = new FrmModifyGoods(this,"�޸���Ʒ",true,BS,GoodsS);
			dlg.setVisible(true);
			if(dlg.getGoods()!=null){//ˢ�±��
				this.reloadUserTable(BS);
			}
	}
		else if(e.getSource()==this.btnRemove){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ʒ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanGoods Goods = this.users.get(i);
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ������Ʒ��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					TakeawayUtil.GoodsManager.removeGoods(Goods);
					this.reloadUserTable(BS);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		} 
		
	}
	public BeanGoods getGoods() {
		// TODO Auto-generated method stub
		return this.Goods;
	}

}


