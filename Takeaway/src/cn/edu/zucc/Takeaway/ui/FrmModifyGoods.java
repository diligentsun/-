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
import cn.edu.zucc.Takeaway.model.BeanGoods;
import cn.edu.zucc.Takeaway.model.BeanRider;
import cn.edu.zucc.Takeaway.model.BeanStore;
import cn.edu.zucc.Takeaway.start.TakeawayUtil;
public class FrmModifyGoods extends JDialog implements ActionListener{
	private BeanGoods Goods =new BeanGoods();
	private BeanStore BS =new BeanStore();
	private BeanGoods BG =new BeanGoods();
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("商品名称：");
	private JLabel labelCategory = new JLabel("商品类别：");
	private JLabel labelStore = new JLabel("所属商家：");
	private JLabel labelPrice = new JLabel("商品价格：");
	private JLabel labelSPrice = new JLabel("优惠价格：");
	private JTextField edtName = new JTextField(20);
	private JTextField edtCategory = new JTextField(20);
	private JTextField edtStore = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtSPrice = new JTextField(20);
	public FrmModifyGoods(Dialog d, String s, boolean b ,BeanStore Store,BeanGoods GS){
		super(d,s,b);
		BS=Store;
		BG=GS;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		workPane.add(labelName);
		edtName.setText(BG.getGoods_name());
		this.edtName.setEditable(false);
		workPane.add(edtName);
		
		workPane.add(labelCategory);
		edtCategory.setText(BG.getGategory_name());
		this.edtCategory.setEditable(false);
		workPane.add(edtCategory);
		
		workPane.add(labelStore);
		edtStore.setText(Store.getName());
		this.edtStore.setEditable(false);
		workPane.add(edtStore);
		
		workPane.add(labelPrice);
		edtPrice.setText(BG.getPrice()+"");
		workPane.add(edtPrice);
		
		workPane.add(labelSPrice);
		edtSPrice.setText(BG.getSale_price()+"");
		workPane.add(edtSPrice);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 260);
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
						FrmModifyGoods.this.Goods=null;
					}
				});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.Goods=null;
			return;
		}else if(e.getSource()==this.btnOk){
			String name=this.edtName.getText();
			String category = this.edtCategory.getText();
			double price,sprice;
			
			try{
				price = Double.parseDouble(this.edtPrice.getText());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, this.edtPrice.getText()+"不是一个合法的数","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try{
				sprice = Double.parseDouble(this.edtSPrice.getText());
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, this.edtSPrice.getText()+"不是一个合法的数","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			Goods.setGoods_name(name);
			Goods.setGategory_name(this.edtCategory.getText());
			Goods.setStore_name(this.edtStore.getText());
			Goods.setPrice(price);
			Goods.setSale_price(sprice);
			Goods.setStore_id(BS.getId());
			try {
				TakeawayUtil.GoodsManager.ModifyGoods(this.Goods);
				this.setVisible(false);
			} catch (Exception e1) {
				this.Goods=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public Object getGoods() {
		// TODO Auto-generated method stub
		return this.Goods;
	}

}
