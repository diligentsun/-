package cn.edu.zucc.Takeaway.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import cn.edu.zucc.Takeaway.model.BeanUser;

public class FrmMain extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FrmLogin dlgLogin=null;
	
	public FrmMain(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		dlgLogin=new FrmLogin(this,"��½",true);
		dlgLogin.setVisible(true);
		if(BeanUser.currentLoginUser.getUserClass().equals("����Ա")){
			FrmSystemerMain dig =new FrmSystemerMain();
			dig.setVisible(true);
		}else if(BeanUser.currentLoginUser.getUserClass().equals("�̼�")){
			FrmCustomerMain dig =new FrmCustomerMain();
			dig.setVisible(true);
		}else if(BeanUser.currentLoginUser.getUserClass().equals("�û�")){
			;
		}else if(BeanUser.currentLoginUser.getUserClass().equals("����")){
			;
		}else {
			this.setVisible(false);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
