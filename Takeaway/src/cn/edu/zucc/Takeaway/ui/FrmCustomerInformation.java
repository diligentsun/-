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

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.Takeaway.control.ExampleStoreManager;
import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
import cn.edu.zucc.Takeaway.util.BaseException;

public class FrmCustomerInformation extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnModify = new Button("查看详细信息");
	private Button btnRemove = new Button("移除用户");
	private Object tblTitle[]={"用户编号","姓名","手机号"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	List<BeanCustomer> users=null;
	private void reloadUserTable(){
		try {
			users=TakeawayUtil.CustomerManager.loadAllUsers();
			tblData =new Object[users.size()][3];
			System.out.print(users.size());
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getId();
				tblData[i][1]=""+users.get(i).getName();
				tblData[i][2]=""+users.get(i).getPhonenum();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmCustomerInformation(Frame f, String s, boolean b) {
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnModify);
		toolBar.add(btnRemove);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
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
		if(e.getSource()==this.btnModify){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择客户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanCustomer Customer = this.users.get(i);
			FrmModifyCustomer dlg = new FrmModifyCustomer(this,"客户信息",true,Customer);
			dlg.setVisible(true);
			if(dlg.getCustomer()!=null){//刷新表格
				this.reloadUserTable();
			}
		}else if(e.getSource()==this.btnRemove){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择客户","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanCustomer Customer = this.users.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定删除该商家吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					TakeawayUtil.CustomerManager.removeCustomer(Customer);
					this.reloadUserTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		} 
		
	}

}


