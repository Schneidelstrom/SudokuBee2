import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import javax.swing.ListCellRenderer;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.util.Calendar;

public class generalPanel{
	private JLabel bg;
	protected JPanel solve,special;
	protected JPanel panel[]=new JPanel[8];
	protected JButton play, create, options, exit, open, help;
	public generalPanel(){}
	public generalPanel(Container container){

		special=addPanel(container, 0,0,800,625);
		solve=addPanel(container, 0,0,800,625);
		for(int ctr=0; ctr<4; ctr++){
			panel[ctr]=addPanel(container, 0,0,800,625);
			}
		panel[4]=addPanel(container, 0,0,513,81);
		panel[5]=addPanel(container, 0,0,513,625);
		for(int ctr=6; ctr<8; ctr++){
			panel[ctr]=addPanel(container, 0,0,800,625);
			}
		play=addButton(panel[7], "img/main/play.png", "img/main/h_play.png",325,110);
		open=addButton(panel[7], "img/main/open.png", "img/main/h_open.png",480,190);
		create=addButton(panel[7], "img/main/create.png", "img/main/h_create.png",190,190);
		options=addButton(panel[7], "img/main/options.png", "img/main/h_options.png",177,350);
		help=addButton(panel[7], "img/main/help.png", "img/main/h_help.png", 475,345);
		exit=addButton(panel[7], "img/main/exit.png", "img/main/h_exit.png", 330,425);
		bg=addLabel(panel[7],"img/bg/main.png",0,0);
		setVisible(7);
		}
	protected void setVisibleButton(boolean isVisible){
		play.setVisible(isVisible);
		open.setVisible(isVisible);
		create.setVisible(isVisible);
		options.setVisible(isVisible);
		help.setVisible(isVisible);
		exit.setVisible(isVisible);
		}
	protected void setVisible(int num){
		solve.setVisible(num==-1);
		special.setVisible(num==-2);
		for(int ctr=0;ctr<8; ctr++)
			panel[ctr].setVisible(false);
		if(num==5){
			panel[6].setVisible(true);
			panel[4].setVisible(true);
			}
		if(num!=-1 && num!=-2)
			panel[num].setVisible(true);
		}
//JPanel
	protected JPanel addPanel(Container container, int x, int y, int width, int height){
		JPanel pane=new JPanel();
		pane.setBounds(x, y, width, height);
		pane.setLayout(null);
		pane.setVisible(true);
		pane.setBackground(new Color(255,255,150,100));
		pane.setOpaque(false);
		container.add(pane);
		return pane;
		}
//JLabel
	protected JLabel addLabel(Container pane, String pix, int x, int y){
		Icon img=new ImageIcon(pix);
		JLabel label= new JLabel();
		label.setIcon(img);
		pane.add(label);
		label.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		img=null;
		return label;
		}
	protected JLabel addLabel(Container pane, ImageIcon pix, int x, int y){
		JLabel label= new JLabel();
		label.setIcon(pix);
		pane.add(label);
		label.setBounds(x, y, pix.getIconWidth(), pix.getIconHeight());
		pix=null;
		return label;
		}
	protected JLabel addLabel(Container pane, String string,int x, int y, int width, int height){
		JLabel label= new JLabel(string);
		pane.add(label);
		label.setBounds(x, y,width, height);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setForeground(Color.black);
		return label;
		}
	protected JLabel addLabel(Container pane, String string,Color color,int x, int y, int width, int height, int alignment){
		JLabel label= new JLabel(string, alignment);
		pane.add(label);
		label.setBounds(x, y,width, height);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		label.setForeground(color);
		return label;
		}
	protected JLabel addLabel(Container pane, String string,int x, int y, int width, int height, int alignment){
		JLabel label= new JLabel(string, alignment);
		pane.add(label);
		label.setBounds(x, y,width, height);
		label.setFont(new Font("Arial", Font.PLAIN, 12));
		label.setForeground(Color.black);
		return label;
		}
	protected JLabel addInvisibleLabel(Container pane, String string, int x, int y){
		Icon img=new ImageIcon(string);
		JLabel label= new JLabel();
		label.setVisible(false);
		label.setIcon(img);
		pane.add(label);
		label.setBounds(x, y, img.getIconWidth(), img.getIconHeight());
		label.setVisible(false);
		return label;
		}
//JButton
	protected JButton addButton(Container pane, String string, int x, int y, int width, int height){
		JButton button=new JButton(string);
		pane.add(button);
		button.setBounds(x,y,width,height);
		button.setCursor(new Cursor(12));
		return button;
		}
	protected JButton addButton(Container pane, String string, String hover,int x, int y){
		JButton button=new JButton();
		Icon img=new ImageIcon(string);
		button.setIcon(img);
		img=new ImageIcon(hover);
		button.setRolloverIcon(img);
		button.setPressedIcon(img);
		pane.add(button);
		setButton(button);
		button.setBounds(x,y,img.getIconWidth(),img.getIconHeight());
		return button;
		}
	protected JButton gameButton(Container pane, String string,int x, int y){
		Icon img=new ImageIcon(string);
		JButton button=new JButton(img);
		pane.add(button);
		button.setBounds(x,y,img.getIconWidth(),img.getIconHeight());
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setOpaque(false);
		setButton(button);
		return button;
		}
	protected JButton addButton(Container pane, String string, int x, int y){
		JButton button=new JButton();
		Icon img=new ImageIcon(string);
		button.setIcon(img);
		pane.add(button);
		setButton(button);
		button.setBounds(x,y,img.getIconWidth(),img.getIconHeight());
		return button;
		}
	private void setButton(JButton button){
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setOpaque(false);
		button.setCursor(new Cursor(12));
		}
//JRadioButton
	protected JRadioButton addRadioButton(Container pane, String string, int x, int y, int width, int height, int font){
		JRadioButton button=new JRadioButton(string);
		pane.add(button);
		button.setBounds(x,y,width,height);
		button.setBorder(BorderFactory.createLoweredBevelBorder());
		button.setContentAreaFilled(false);
		button.setFont(new Font("Arial", Font.PLAIN, font));
		return button;
		}
//JTextField
	protected JTextField addTextField(Container pane, String string, int x, int y, int width, int height){
		JTextField area= new JTextField(string);
		area.setBounds(x, y, width, height);
		area.setOpaque(false);
		area.setBorder(null);
		pane.add(area);
		area.setFont(new Font("Arial", Font.BOLD, 28));
		area.setForeground(Color.BLACK);
		area.setHorizontalAlignment(JTextField.CENTER);
		return area;
		}
//JPasswordField
	protected JPasswordField addPasswordField(JPanel pane, int x, int y, int width, int height){
		JPasswordField area= new JPasswordField("",10);
		area.setBounds(x, y, width, height);
		pane.add(area);
		area.setFont(new Font("Arial", Font.PLAIN, 12));
		return area;
		}
//JTable
	protected JTable addTable(Container pane, Object[] column, Object[][] data, int[] columnWidth){
		DefaultTableModel listModel = new DefaultTableModel(data, column);
	    JTable table= new JTable(listModel){
	      	public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
	      		}
			};
		table.setBorder(null);
		table.setSelectionMode(0);
		table.setAutoResizeMode(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		table.setDragEnabled(false);
		for(int ctr=0; ctr<columnWidth.length; ctr++){
			TableColumn columnNum =table.getColumnModel().getColumn(ctr);
			columnNum.setPreferredWidth(columnWidth[ctr]);
			columnNum.setMinWidth(columnWidth[ctr]);
			columnNum.setMaxWidth(columnWidth[ctr]);
			}
		pane.add(table);
		return table;
		}
	protected void tableProperties(JTable table){
		table.setBorder(null);
		table.setSelectionMode(0);
		table.setAutoResizeMode(0);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setFont(new Font("Arial", Font.PLAIN, 11));
		table.setDragEnabled(false);
		}
	protected void columnWidth(JTable table, int[] columnWidth, int max){
		for(int ctr=0; ctr<max; ctr++){
			TableColumn columnNum =table.getColumnModel().getColumn(ctr);
			columnNum.setPreferredWidth(columnWidth[ctr]);
			columnNum.setMinWidth(columnWidth[ctr]);
			columnNum.setMaxWidth(columnWidth[ctr]);
			}
		}
//JScrollPane
	protected JScrollPane addScrollPane(Container pane, JComponent component, int x, int y, int width, int height){
		JScrollPane TScroll=new JScrollPane(component);
		TScroll.setViewportView(component);
		TScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		TScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		TScroll.setBounds(x-1,y-1, width+3, height+16);
		javax.swing.JViewport view=TScroll.getViewport();
		view.setOpaque(false);
		TScroll.setOpaque(false);
		view.setBorder(null);
		TScroll.setBorder(null);
		pane.add(TScroll);
		return TScroll;
		}
//JCheckBox
	protected JCheckBox addCheckBox(Container pane, String string, int x, int y, int width, int height){
		JCheckBox box=new JCheckBox(string);
		pane.add(box);
		box.setFont(new Font("Arial", Font.PLAIN, 12));
		box.setBounds(x,y,width,height);
		box.setBorder(BorderFactory.createLoweredBevelBorder());
		box.setContentAreaFilled(false);
		return box;
		}
//JComboBox
	protected JComboBox addJCombo(Container pane, String string[], int x, int y, int width, int height){
		JComboBox comboBox = new JComboBox();
		for(int ctr=0; ctr<string.length; ctr++)
			comboBox.addItem(string[ctr]);
		comboBox.setBounds(x, y, width, height);
		pane.add(comboBox);
		return comboBox;
		}
	protected JComboBox addJCombo(Container pane, String title,Object string[], int x, int y, int width, int height, int length){
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(title);
		for(int ctr=0; ctr<length; ctr++)
			comboBox.addItem(string[ctr]);
		comboBox.setBounds(x, y, width, height);
		pane.add(comboBox);
		return comboBox;
		}
	protected JComboBox addJCombo(Container pane, String title, Object string[], int x, int y, int width, int height){
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(title);
		for(int ctr=0; ctr<string.length; ctr++)
			comboBox.addItem(string[ctr]+"");
		comboBox.setBounds(x, y, width, height);
		pane.add(comboBox);
		return comboBox;
		}

	protected int question(Container container, String first, String second){
		Icon icon=new ImageIcon("img/question.png");
		JOptionPane optionPane=new JOptionPane();
		int selectedValue=optionPane.showConfirmDialog(container, first, second, JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon);
		icon=null;
		return selectedValue;
		}
	protected void error(Container container, String first, String second){
		Icon icon=new ImageIcon("img/stop.png");
		JOptionPane.showMessageDialog(container,first, second, JOptionPane.ERROR_MESSAGE,icon);
		icon=null;
		}
	protected int saveChanges(Container container){
		Icon icon=new ImageIcon("img/save.png");
		JOptionPane optionPane=new JOptionPane();
		int selectedValue=optionPane.showConfirmDialog(container,"Save changes?", "Save", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icon);
		icon=null;
		return selectedValue;
		}
	protected void information(Container container, String first, String second){
		Icon icon=new ImageIcon("img/info.png");
		JOptionPane.showMessageDialog(container,first, second, JOptionPane.ERROR_MESSAGE,icon);
		icon=null;
		}
	protected JDialog addDialog(JFrame frame, JOptionPane pane, String string, int x, int y){
		JDialog dialog=new JDialog(frame, string, true);
		dialog.setContentPane(pane);
		dialog.setSize(x,y-10);
		dialog.setLocationRelativeTo(frame);
		dialog.setResizable(false);
		return dialog;
		}
	protected JOptionPane addOPane(){
		JOptionPane pane=new JOptionPane();
		pane.setBackground(new Color(220,220,220));
		pane.setLayout(null);
		return pane;
		}
	protected FileDialog addFileDialog(JFrame frame, String string, String ft){
		FileDialog FD=new FileDialog(frame, string);
		FD.setFile(ft);
		return FD;
		}
	protected JSpinner setTimeSpinner(Container pane, String date, int x, int y, int width, int height){
		SpinnerDateModel sm = new SpinnerDateModel(java.sql.Date.valueOf(date), null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinner = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(de);
    	pane.add(spinner);
    	spinner.setBounds(x,y,width, height);
    	return spinner;
		}
	protected JList addList(Container pane, Object obj[]){
		JList list=new JList();
		if(obj!=null)
			list.setListData(obj);
		pane.add(list);
		return list;
		}
	protected boolean checkCaptiaSize(String file){
		Icon icon=new ImageIcon(file);
		if(icon.getIconWidth()!=200 || icon.getIconHeight()!=75)
			return false;
		icon=null;
		return true;
		}
	protected JTabbedPane addTab(Container pane, int x, int y, int width, int height){
		JTabbedPane tab=new JTabbedPane(JTabbedPane.TOP, 0);
		pane.add(tab);
		tab.setBounds(x,y,width,height);
		tab.setFont(new Font("Arial", Font.BOLD, 13));
		pane.setBackground(Color.black);
		return tab;
		}
	protected JList addList(Container pane, Object str[], int x, int y, int width, int height){
		JList list=new JList(str);
		pane.add(list);
		list.setBounds(x,y,width,height);
		list.setFont(new Font("Arial", Font.BOLD, 25));
		list.setVisibleRowCount(5);
		list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		list.setSelectionBackground(Color.orange);
		list.setCellRenderer(new ListCellRenderer (){
			public java.awt.Component getListCellRendererComponent(JList list,Object obj, int index, boolean isSelected, boolean hasFocus){
				JLabel area=new JLabel();
				area.setFont(new Font("Arial", Font.BOLD, 25));
				area.setText((String)obj);
				area.setBorder(null);
				if (isSelected) {
					area.setBackground(list.getSelectionBackground());
					area.setForeground(list.getSelectionForeground());
					area.setOpaque(true);
					}
				else {
					area.setOpaque(false);
					area.setBackground(list.getBackground());
					area.setForeground(list.getForeground());
					 }
				area.setBorder(null);
				area.setEnabled(true);
				return area;
				}
			});
		list.setOpaque(false);
		return list;
		}
	protected JPanel addSpecialPanel(JTabbedPane container, String title){
		JPanel pane=new JPanel();
		pane.setLayout(null);
		pane.setBackground(new Color(233,136,89));
		pane.setOpaque(true);
		container.addTab(title, pane);
		return pane;
		}
	protected void changePicture(JButton label, String photo){
		Icon img=new ImageIcon(photo);
		label.setIcon(img);
		img=new ImageIcon(photo);
		label.setRolloverIcon(img);
		img=null;
		}
	protected void changePicture(JLabel label, String photo){
		Icon img=new ImageIcon(photo);
		label.setIcon(img);
		img=null;
		}
	protected void sop(Object obj){
		System.out.println(obj+"");
		}
	}