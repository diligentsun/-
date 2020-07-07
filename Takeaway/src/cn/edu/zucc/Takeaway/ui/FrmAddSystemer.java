package cn.edu.zucc.Takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.Takeaway.model.BeanUser;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
import cn.edu.zucc.Takeaway.util.BaseException;

public class FrmAddSystemer extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("×¢²á");
	private Button btnCancel = new Button("È¡Ïû");
	
	private JLabel labelUseran = new JLabel("ÕËºÅ£º");
	private JLabel labelUsername = new JLabel("ÐÕÃû£º");
	private JLabel labelPwd = new JLabel("ÃÜÂë£º");
	private JLabel labelPwd2 = new JLabel("ÃÜÂë£º");
	private JTextField edtUserAn = new JTextField(20);
	private JTextField edtUserName = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmAddSystemer(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUseran);
		workPane.add(edtUserAn);
		workPane.add(labelUsername);
		workPane.add(edtUserName);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){	
			String useran=this.edtUserAn.getText();
			String username=this.edtUserName.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				BeanUser user=TakeawayUtil.SystmerManager.reg(useran,username,pwd1,pwd2);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}