import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

public class UIOptions extends generalPanel{
	private JPanel panel[];
	private JLabel bg;

	private String size[]={"img/exit/size/9 x 9.png","img/exit/size/16 x 16.png","img/exit/size/25 x 25.png"};
	private String sound[]={"img/exit/sound/on.png","img/exit/sound/off.png"};

	protected JLabel sizeLabel, levelLabel, soundLabel;
	protected JButton exit, no;
	protected JButton left[]=new JButton[2];
	protected JButton right[]=new JButton[2];

	protected int sz, lvl, snd, num;

    UIOptions(JPanel panel[]){
		this.panel=panel;
		panel[1].setOpaque(true);
		exit=addButton(panel[1], "img/exit/okay.png", "img/exit/h_okay.png",385,401);

		for(int ctr=0; ctr<2; ctr++){
			left[ctr]=addButton(panel[1], "img/exit/left.png", "img/exit/h_left.png",356,235+70*ctr);
			right[ctr]=addButton(panel[1], "img/exit/right.png", "img/exit/h_right.png",568,235+70*ctr);
			}
		sizeLabel=addLabel(panel[1], size[0], 389,237);
		soundLabel=addLabel(panel[1], sound[0], 389,308);
		bg=addLabel(panel[1],"img/bg/options.png",100,99);

		sz=0;
		num=lvl=snd=0;
		}
	protected void setSize(boolean isRight){
		if(isRight){
			sz++;
			if (sz==3) sz=0;
        }
		else{
			sz--;
			if(sz==-1)  sz=2;
        }
		changePicture(sizeLabel, size[sz]);
    }

	protected void setSound(boolean isRight){
		if(isRight){
			snd++;
			if(snd==2)
				snd=0;
			}
		else{
			snd--;
			if(snd==-1)
				snd=1;
			}
		changePicture(soundLabel, sound[snd]);
		}
	protected void setVisible(boolean isVisible, int num){
		this.num=num;
		panel[1].setVisible(isVisible);
		}
	protected void decompose(){
		panel[1].removeAll();
		bg=sizeLabel=levelLabel=soundLabel=null;
		exit=no=null;
		left[0]=right[0]=left[1]=right[1]=null;
		}
	}