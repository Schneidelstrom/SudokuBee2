import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UIGame extends generalPanel{
	private JPanel pane;
	private JLabel bg, solving;
	private JPanel panel[]=new JPanel[3];
	protected JButton newGame, solve, options, help, exit, hint;
	UIGame(JPanel pane){
		this.pane=pane;
		gameButtons();
		}
	private void gameButtons(){
		for(int ctr=0; ctr<3; ctr++)
			panel[ctr]=addPanel(pane,0,0,800,625);
		newGame=addButton(panel[0], "img/game/new.png", "img/game/h_new.png",565,230);
		solve=addButton(panel[0], "img/game/solve.png", "img/game/h_solve.png",610,280);
		options=addButton(panel[0], "img/game/options.png", "img/game/h_options.png",600,330);
		help=addButton(panel[0], "img/game/help.png", "img/game/h_help.png",630,380);
		exit=addButton(panel[0], "img/game/exit.png", "img/game/h_exit.png",625,430);
		solving=addLabel(panel[1], "img/misc/solving.gif", 586,235);
		bg=addLabel(panel[2],"img/bg/game.png",0,0);
		setVisible(0);
		}
	protected void setVisible(boolean isVisible){
		panel[0].setVisible(isVisible);
		}
	protected void setVisible(int num){
		panel[0].setVisible(num==0);
		panel[1].setVisible(num==1);
		}
	protected void decompose(){
		for(int ctr=0; ctr<panel.length && panel[ctr]!=null; ctr++){
			panel[ctr].setVisible(false);
			panel[ctr].removeAll();
			panel[ctr]=null;
			}
		bg=solving=null;
		newGame= solve= options= help= exit= null;
		}
	}