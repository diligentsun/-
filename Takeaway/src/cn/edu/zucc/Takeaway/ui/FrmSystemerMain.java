package cn.edu.zucc.Takeaway.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cn.edu.zucc.Takeaway.model.BeanUser;
public class FrmSystemerMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
	private JMenu menu_System=new JMenu("管理员权限");
	private JMenu menu_user=new JMenu("用户管理");
    private JMenuItem  menuItem_AddSystemer=new JMenuItem("新增管理员");
    private JMenuItem  menuItem_ChangePwd=new JMenuItem("密码修改");
    private JMenuItem  menuItem_DeleteSystemer=new JMenuItem("注销账号");
    private JMenuItem  menuItem_StoreInformation=new JMenuItem("商家信息");
    private JMenuItem  menuItem_CustomerInformation=new JMenuItem("客户信息");
    private JMenuItem  menuItem_RiderInformation=new JMenuItem("骑手信息");
    private JPanel statusBar = new JPanel();
	public static BeanUser currentLoginUser = null;
	public FrmSystemerMain(){
		menu_user.add(menuItem_StoreInformation);
    	menuItem_StoreInformation.addActionListener(this);
    	menu_user.add(menuItem_CustomerInformation);
    	menuItem_CustomerInformation.addActionListener(this);
    	menu_user.add(menuItem_RiderInformation);
    	menuItem_RiderInformation.addActionListener(this);
    	menubar.add(this.menu_user);
    	
    	menu_System.add(menuItem_AddSystemer);
    	menuItem_AddSystemer.addActionListener(this);
    	menu_System.add(menuItem_ChangePwd);
    	menuItem_ChangePwd.addActionListener(this);
    	menu_System.add(menuItem_DeleteSystemer);
    	menuItem_DeleteSystemer.addActionListener(this);
    	
    	menubar.add(this.menu_System);
	    this.setJMenuBar(menubar);
	    
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	   // JLabel label=new JLabel("您好!"+BeanUser.currentLoginUser.getUsername());
	//    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_AddSystemer){
			FrmAddSystemer dlg=new FrmAddSystemer(this,"新增管理员",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.menuItem_ChangePwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"修改密码",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.menuItem_StoreInformation){
			FrmStoreInformation dlg=new FrmStoreInformation(this,"商家信息",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.menuItem_CustomerInformation){
			FrmCustomerInformation dlg=new FrmCustomerInformation(this,"客户信息",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.menuItem_RiderInformation){
			FrmRiderInformation dlg=new FrmRiderInformation(this,"骑手信息",true);
			dlg.setVisible(true);
		}
	}
	
	
}

