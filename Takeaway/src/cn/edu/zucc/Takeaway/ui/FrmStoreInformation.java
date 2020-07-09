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
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
import cn.edu.zucc.Takeaway.util.BaseException;

public class FrmStoreInformation extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnWatchGoods = new Button("�鿴��Ʒ");
	private Button btnWatchRetabe = new Button("�鿴��������");
	private Button btnAdd = new Button("����̵�");
	private Button btnModify = new Button("�޸���Ϣ");
	private Button btnRemove = new Button("�Ƴ��̵�");
	private Object tblTitle[]={"������","�Ǽ�","�˾�����","������"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable userTable=new JTable(tablmod);
	List<BeanStore> users=null;
	private void reloadUserTable(){
		try {
			users=(new ExampleStoreManager()).loadAllUsers();
			tblData =new Object[users.size()][4];
			System.out.print(users.size());
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getName();
				tblData[i][1]=""+users.get(i).getRating();
				tblData[i][2]=""+users.get(i).getPCC();
				tblData[i][3]=""+users.get(i).getTotal_sales();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmStoreInformation(Frame f, String s, boolean b) {
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnWatchGoods);
		toolBar.add(btnWatchRetabe);
		toolBar.add(btnAdd);
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
		this.btnWatchGoods.addActionListener(this);
		this.btnWatchRetabe.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnAdd.addActionListener(this);
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
				JOptionPane.showMessageDialog(null,  "��ѡ���̵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanStore Store = this.users.get(i);
			FrmModifyStore dlg = new FrmModifyStore(this,"�޸ĵ���",true,Store);
			dlg.setVisible(true);
			if(dlg.getStore()!=null){//ˢ�±��
				this.reloadUserTable();;
			}
		}else if(e.getSource()==this.btnAdd){
			FrmAddStore dlg = new FrmAddStore(this,"��ӵ���",true);
			dlg.setVisible(true);
			if(dlg.getStore()!=null){//ˢ�±��
				this.reloadUserTable();;
			}
		}else if(e.getSource()==this.btnRemove){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���̵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanStore Store = this.users.get(i);
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ�����̼���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new ExampleStoreManager()).removeStore(Store);
					this.reloadUserTable();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}else if(e.getSource()==this.btnWatchRetabe){
			int i=this.userTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ���̵�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			BeanStore Store = this.users.get(i);
			FrmGoodsInformation dlg = new FrmGoodsInformation(this,"�鿴��Ʒ",true,Store);
			dlg.setVisible(true);
			if(dlg.getGoods()!=null){//ˢ�±��
				this.reloadUserTable();;
			}
		} 
		
	}

}


