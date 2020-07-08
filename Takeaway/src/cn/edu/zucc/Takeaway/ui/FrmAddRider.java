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

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.Takeaway.control.ExampleStoreManager;
import cn.edu.zucc.Takeaway.model.BeanRider;
import cn.edu.zucc.Takeaway.model.BeanStore;
public class FrmAddRider extends JDialog implements ActionListener{
	private BeanRider Rider =new BeanRider();
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	
	private JLabel labelName = new JLabel("����������");
	private JLabel labelSign = new JLabel("������ݣ�");
	private JTextField edtName = new JTextField(20);
	private JTextField edtSign = new JTextField(20);
	public FrmAddRider(Dialog d, String s, boolean b){
		super(d,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelSign);
		workPane.add(edtSign);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 160);
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
						FrmAddRider.this.Rider=null;
					}
				});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.Rider=null;
			return;
		}else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			String sign = this.edtSign.getText();
			if(name.equals("")||sign.equals("")){
				JOptionPane.showMessageDialog(null, "����������������ݲ���Ϊ��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!(sign.equals("����")||sign.equals("��ʽԱ��")||sign.equals("����"))){
				JOptionPane.showMessageDialog(null, "������ݱ���Ϊ���ˣ���ʽԱ���������е�һ��","����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.Rider.setName(name);
			this.Rider.setSign(sign);
			try {
				(new ExampleStoreManager()).addRider(this.Rider);
				this.setVisible(false);
			} catch (Exception e1) {
				this.Rider=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public Object getRider() {
		// TODO Auto-generated method stub
		return this.Rider;
	}

}
