import java.util.Random;
class Subgrid{
	private int dimX;
	private int dimY;
	private int startX;
	private int startY;
	private int[] neededNum;
	private Random rand;
	Subgrid(int startX, int startY, int dimX, int dimY){
		this.dimX=dimX;
		this.dimY=dimY;
		this.startX=startX;
		this.startY=startY;
		rand=new Random();
		}
	protected int getDimX(){
		return dimX;
		}
	protected int getDimY(){
		return dimY;
		}
	protected int getStartX(){
		return startX;
		}
	protected int getStartY(){
		return startY;
		}
	protected void setNeededNum(int[] neededNum){
		this.neededNum=neededNum;
		}
	protected int[] getNeededNum(){
		for(int ctr=0, ptr=neededNum.length-1; ctr<neededNum.length; ctr++, ptr--){
			int index=rand.nextInt(ptr+1);
			int tmp=neededNum[index];
			neededNum[index]=neededNum[ptr];
			neededNum[ptr]=tmp;
			}
		return neededNum;
		}
	protected boolean isBelong(int x, int y){
		if(x>=startX && x<startX+dimX && y>=startY && y<startY+dimY)
			return true;
		return false;
		}
	}