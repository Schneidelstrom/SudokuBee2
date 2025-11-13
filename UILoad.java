import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;
import java.io.File;
import java.io.FilenameFilter;

public class UILoad extends generalPanel{
	private JPanel pane;
	private JLabel bg, error;
	protected JButton load, cancel;
	protected int num;
	protected JScrollPane scroll;
	protected JList lists;

	UILoad(JPanel pane){
		this.pane=pane;
		pane.setOpaque(true);
		try{
			FilenameFilter f = new EndsWithFilter( ".sav" );
			String[] filenames = new File( "save/" ).list(f);
			load=addButton(pane, "img/exit/okay.png", "img/exit/h_okay.png",290,445);
			cancel=addButton(pane, "img/exit/cancel.png", "img/exit/h_cancel.png",430,445);
			if(filenames==null || filenames.length==0)
				throw new Exception();
			else{
				for(int ctr=0; ctr<filenames.length; ctr++)
					filenames[ctr]=filenames[ctr].substring(0, filenames[ctr].length()-4);
				lists=addList(pane, filenames, 250,195,300,200);
				lists.setSelectedIndex(0);
				scroll=addScrollPane(pane, lists,  250,195,300,200);
				error=new JLabel();
				}
			}
		catch(Exception e){
			lists=new JList();
			scroll=new JScrollPane();
			error=addLabel(pane, "No Saved Sudoku", 280,250,300,30);
			error.setFont(new Font("Arial", Font.BOLD, 30));
			load.setEnabled(false);
			}
		bg=addLabel(pane,"img/bg/load.png",100,54);
		pane.setVisible(true);
		}
	protected void decompose(){
		pane.removeAll();
		lists=null;
		load=cancel=null;
		bg=error=null;
		}
	}