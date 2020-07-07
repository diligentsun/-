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
import cn.edu.zucc.Takeaway.model.BeanStore;
public class FrmAddStore extends JDialog implements ActionListener{
	private BeanStore Store =new BeanStore();
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelName = new JLabel("商店名称：");
	private JLabel labelRating = new JLabel("商店星级：");
	private JLabel labelPCC = new JLabel("人均销量：");
	private JTextField edtName = new JTextField(20);
	private JTextField edtRating = new JTextField(20);
	private JTextField edtPCC = new JTextField(20);
	public FrmAddStore(Dialog d, String s, boolean b){
		super(d,s,b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelRating);
		workPane.add(edtRating);
		workPane.add(labelPCC);
		workPane.add(edtPCC);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 160);
		// 屏幕居中显示
				double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
				double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
				this.setLocation((int) (width - this.getWidth()) / 2,
						(int) (height - this.getHeight()) / 2);

				this.validate();
				this.btnOk.addActionListener(this);
				this.btnCancel.addActionListener(this);
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						FrmAddStore.this.Store=null;
					}
				});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.Store=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			int n=0;
			try{
				n=Integer.parseInt(this.edtRating.getText());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, this.edtRating.getText()+"不是一个合法的整数","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(n<0 || n>5){
				JOptionPane.showMessageDialog(null, "商店星级必须在0-5之间","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				this.Store.setPCC(Double.parseDouble(this.edtPCC.getText()));
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, this.edtPCC.getText()+"不是一个合法的整数","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.Store.setName(name);
			this.Store.setRating(n);
			try {
				(new ExampleStoreManager()).addStore(this.Store);
				this.setVisible(false);
			} catch (Exception e1) {
				this.Store=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanStore getStore() {
		return this.Store;
	}

}
