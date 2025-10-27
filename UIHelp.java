import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UIHelp extends generalPanel{
	private JPanel pane;
	private JLabel bg, help;
	protected int num=0, panelNum;
	protected JButton next, back, cancel;

	UIHelp(JPanel pane, int panelNum){
		this.pane=pane;
		pane.setOpaque(true);
		num=1;
		this.panelNum=panelNum;
		back=addButton(pane, "img/help/left.png", "img/help/h_left.png",586,296);
		next=addButton(pane, "img/help/right.png", "img/help/h_right.png",677,296);
		cancel=addButton(pane, "img/exit/cancel.png", "img/exit/h_cancel.png",607,387);
		help=addLabel(pane,"img/help/1.png",5,26);
		bg=addLabel(pane,"img/help/bg.png",0,0);
		pane.setVisible(true);
		}
	protected void increase(){
		num++;
		if(num==6)
			num=1;
		changePicture(help, "img/help/"+num+".png");
		}
	protected void decrease(){
		num--;
		if(num==0)
			num=5;
		changePicture(help, "img/help/"+num+".png");
		}
	protected void decompose(){
		pane.removeAll();
		pane.setVisible(false);
		back=cancel=next=null;
		bg=help=null;
		}
	}