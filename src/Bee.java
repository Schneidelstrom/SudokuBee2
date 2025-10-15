import java.util.Random;

class Bee{
	private int[][][] solution;
	private double fitness;
	private Subgrid[] subgrid;
	private Random rand=new Random();
	Bee(Subgrid[] subgrid){
		this.subgrid=subgrid;
		}
	Bee(int[][][] prob, Subgrid[] subgrid){
		solution=prob;
		this.subgrid=subgrid;
		for(int ctr=0; ctr<subgrid.length; ctr++){
			int[] needed=neededNumbers(subgrid[ctr]);
			for(int y=subgrid[ctr].getStartY(), indexRand=needed.length, limY=y+subgrid[ctr].getDimY(); y<limY; y++){
				for(int x=subgrid[ctr].getStartX(), limX=x+subgrid[ctr].getDimX(); x<limX; x++){
					if(solution[y][x][1]==1){
						int tmp=rand.nextInt(indexRand);
						solution[y][x][0]=needed[tmp];
						needed[tmp]=needed[indexRand-1];
						needed[indexRand-1]=solution[y][x][0];
						indexRand=indexRand-1;
						}
					}
				}
			}
		}
	protected void copyProblem(int[][][] prob){
		solution=prob;
		}
	protected void printResult(){
		for(int ctr=0; ctr<solution.length; ctr++){
			for(int ctr1=0; ctr1<solution[ctr].length; ctr1++){
				System.out.print(solution[ctr][ctr1][0]+"");
				}
			System.out.println("");
			}
		}
	protected int getPenaltyValue(){
		int penalty=0;
		customSet hor=new customSet();
		customSet ver=new customSet();
		for(int ctr=0; ctr<solution.length; ctr++){
			hor.clear();
			ver.clear();
			for(int ct=0; ct<solution.length; ct++){
				if(hor.contains(solution[ctr][ct][0]))
					penalty++;
				else
					hor.add(Integer.valueOf(solution[ctr][ct][0]));
				if(ver.contains(solution[ct][ctr][0]))
					penalty++;
				else
					ver.add(solution[ct][ctr][0]);
				}
			}
		return penalty;
		}
	protected int[][][] getSolution(){
		return solution;
		}
	protected void setFitness(double fit){
		fitness=fit;
		}
	protected double getFitness(){
		return fitness;
		}
	protected int getElement(int j){
		int row=j/solution.length, column=j%solution.length;
		if(solution[row][column][1]==0)
			return 0;
		return solution[row][column][0];
		}
	protected int[] neededNumbers(Subgrid grid){
		int[] needed=new int[solution.length];
		int removed=0;
		for(int ctr=1; ctr<=solution.length; ctr++)
			needed[ctr-1]=ctr;
		for(int y=grid.getStartY(), limY=y+grid.getDimY(); y<limY; y++){
			for(int x=grid.getStartX(), limX=x+grid.getDimX(); x<limX; x++){
				if(solution[y][x][1]==0){
					needed[solution[y][x][0]-1]=0;
					removed=removed+1;
					}
				}
			}
		int[] neededNum=new int[solution.length-removed];
		for(int ctr=0, ctr2=0; ctr<solution.length; ctr++){
			if(needed[ctr]>0){
				neededNum[ctr2]=needed[ctr];
				ctr2=ctr2+1;
				}
			}
		return neededNum;
		}
	protected int[][][] getCopy(){
		int[][][] copy=new int[solution.length][solution.length][2];
		for(int ctr=0; ctr<copy.length; ctr++){
			for(int ct=0; ct<copy.length; ct++){
				copy[ctr][ct][0]=solution[ctr][ct][0];
				copy[ct][ctr][0]=solution[ct][ctr][0];
				copy[ctr][ct][1]=solution[ctr][ct][1];
				copy[ct][ctr][1]=solution[ct][ctr][1];
				}
			}
		return copy;
		}
	protected int[][][] swap(int[][][] solution, int subgridNum, int row, int column, int xij, int vij){
		this.solution=solution;
		for(int y=subgrid[subgridNum].getStartY(), limY=y+subgrid[subgridNum].getDimY(); y<limY; y++){
			for(int x=subgrid[subgridNum].getStartX(), limX=x+subgrid[subgridNum].getDimX(); x<limX; x++){
				if(solution[y][x][0]==vij){
					solution[y][x][0]=xij;
					solution[row][column][0]=vij;
					return solution;
					}
				}
			}
		return null;
		}
	}