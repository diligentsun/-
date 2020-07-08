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
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
public class FrmModifyRider extends JDialog implements ActionListener{
	java.text.SimpleDateFormat SDF = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	private BeanRider Rider =new BeanRider();
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelName = new JLabel("骑手姓名：");
	private JLabel labelcreateTime = new JLabel("入职时间：");
	private JLabel labelSign = new JLabel("骑手身份：");
	private JTextField edtName = new JTextField(20);
	private JTextField edtcreateTime = new JTextField(20);
	private JTextField edtSign = new JTextField(20);
	public FrmModifyRider(Dialog d, String s, boolean b,BeanRider BR){
		super(d,s,b);
		Rider = BR;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		this.edtName.setText(BR.getName());
		workPane.add(edtName);
		
		workPane.add(labelSign);
		this.edtSign.setText(BR.getSign()+"");
		workPane.add(edtSign);
		
		workPane.add(labelcreateTime);
		this.edtcreateTime.setEditable(false);
		this.edtcreateTime.setText(SDF.format(Rider.getCreateTime()));
		workPane.add(edtcreateTime);
		
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
						FrmModifyRider.this.Rider=null;
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
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			String sign = this.edtSign.getText();
			int n=0;
			if(n<0 || n>5){
				JOptionPane.showMessageDialog(null, "商店星级必须在0-5之间","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(name.equals("")||sign.equals("")){
				JOptionPane.showMessageDialog(null, "骑手姓名和骑手身份不得为空","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(!(sign.equals("新人")||sign.equals("正式员工")||sign.equals("单王"))){
				JOptionPane.showMessageDialog(null, "骑手身份必须为新人，正式员工，单王中的一个","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.Rider.setName(name);
			this.Rider.setSign(sign);
			
			try {
				TakeawayUtil.RiderManager.ModifyRider(this.Rider);
				this.setVisible(false);
			} catch (Exception e1) {
				this.Rider=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public BeanRider getRider() {
		return this.Rider;
	}

}
