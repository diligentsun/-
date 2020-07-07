package cn.edu.zucc.Takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.Takeaway.model.BeanCustomer;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;

public class FrmModifyCustomer extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private BeanCustomer Customer = new BeanCustomer();
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("�޸��û���Ϣ");
	private Button btnCancel = new Button("ȷ��");
	
	private JLabel labelName = new JLabel("�û�������");
	private JLabel labelSex = new JLabel("�û��Ա�");
	private JLabel labelPwd = new JLabel("�û����룺");
	private JLabel labelPhonenum = new JLabel("�ֻ����룺");
	private JLabel labelEmail = new JLabel("�û����䣺");
	private JLabel labelCity = new JLabel("���ڳ��У�");
	private JLabel labelcreateTime = new JLabel("ע��ʱ�䣺");
	private JLabel labelisMember = new JLabel("�Ƿ��Ա��");
	private JLabel labelMemberEndTime = new JLabel("��ֹʱ�䣺");
	private JTextField edtName = new JTextField(20);
	private JTextField edtSex = new JTextField(20);
	private JTextField edtPwd = new JTextField(20);
	private JTextField edtPhonenum = new JTextField(20);
	private JTextField edtEmail = new JTextField(20);
	private JTextField edtCity = new JTextField(20);
	private JTextField edtcreateTime = new JTextField(20);
	private JTextField edtisMember = new JTextField(20);
	private JTextField edtMemberEndTime = new JTextField(20);
	public FrmModifyCustomer(Dialog d, String s, boolean b,BeanCustomer BC){
	super(d,s,b);
	Customer = BC;
	toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
	toolBar.add(btnOk);
	toolBar.add(btnCancel);
	this.getContentPane().add(toolBar, BorderLayout.SOUTH);

	workPane.add(labelName);
	this.edtName.setText(Customer.getName());
	workPane.add(edtName);
	
	workPane.add(labelSex);
	this.edtSex.setText(Customer.getSex());
	workPane.add(edtSex);
	
	workPane.add(labelPwd);
	this.edtPwd.setText(Customer.getPwd());
	workPane.add(edtPwd);
	
	workPane.add(labelPhonenum);
	this.edtPhonenum.setEditable(false);
	this.edtPhonenum.setText(Customer.getPhonenum());
	workPane.add(edtPhonenum);
	
	workPane.add(labelEmail);
	this.edtEmail.setText(Customer.getEmail());
	workPane.add(edtEmail);
	
	workPane.add(labelCity);
	this.edtCity.setText(Customer.getCity());
	workPane.add(edtCity);
	
	workPane.add(labelcreateTime);
	this.edtcreateTime.setEditable(false);
	this.edtcreateTime.setText(SDF.format(Customer.getCreateTime()));
	workPane.add(edtcreateTime);
	
	workPane.add(labelisMember);
	//if(!BeanUser.currentLoginUser.getUserClass().equals("����Ա"))this.edtisMember.setEditable(false);
	this.edtisMember.setText(Customer.getIsMember());
	workPane.add(edtisMember);
	
	workPane.add(labelMemberEndTime);
	//if(!BeanUser.currentLoginUser.getUserClass().equals("����Ա"))this.edtMemberEndTime.setEditable(false);
	if(Customer.getMemberEndTime()!=null)this.edtMemberEndTime.setText(SDF.format(Customer.getMemberEndTime()));
	workPane.add(edtMemberEndTime);
	
	this.getContentPane().add(workPane, BorderLayout.CENTER);
	this.setSize(300, 400);
	// ��Ļ������ʾ
			double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			this.setLocation((int) (width - this.getWidth()) / 2,
					(int) (height - this.getHeight()) / 2);

			this.validate();
			this.btnOk.addActionListener(this);
			this.btnCancel.addActionListener(this);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					FrmModifyCustomer.this.Customer=null;
				}
			});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.Customer=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			if(this.edtName.getText().length()==0||this.edtName.getText().length()>18){
				JOptionPane.showMessageDialog(null, "��������ֻ����1-20֮��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer.setName(this.edtName.getText());
			if(!(this.edtSex.getText().equals("��")||this.edtSex.getText().equals("Ů"))){
				JOptionPane.showMessageDialog(null, "�Ա�ֻ��Ϊ�л�Ů","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer.setSex(this.edtName.getText());
			if(this.edtPwd.getText().length()==0||this.edtPwd.getText().length()>18){
				JOptionPane.showMessageDialog(null, "���볤��ֻ����1-18֮��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer.setPwd(this.edtPwd.getText());
			if(this.edtEmail.getText().length()==0||this.edtEmail.getText().length()>50){
				JOptionPane.showMessageDialog(null, "���䳤��ֻ����1-50֮��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer.setEmail(this.edtEmail.getText());
			if(!(this.edtisMember.getText().equals("��")||this.edtisMember.getText().equals("��"))){
				JOptionPane.showMessageDialog(null, "��Աֻ��Ϊ�ǻ��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Customer.setIsMember(this.edtisMember.getText());
			if(this.edtisMember.getText().equals("��")&&!this.edtMemberEndTime.getText().equals("")){
				JOptionPane.showMessageDialog(null, "û�л�Ա�������û�Ա��ֹʱ��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(this.edtMemberEndTime.getText().equals("")&&this.edtisMember.getText().equals("��")) {
				try {
					Customer.setMemberEndTime(SDF.parse(this.edtMemberEndTime.getText()));
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "ʱ���ʽ��׼ȷ","����",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if(JOptionPane.showConfirmDialog(this,"ȷ��Ҫ�޸��û�ô��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					TakeawayUtil.CustomerManager.modifyCustomer(this.Customer);
					this.setVisible(false);
				} catch (Exception e1) {
					this.Customer=null;
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		
	}
	public BeanCustomer getCustomer() {
		return this.Customer;
	}
}
