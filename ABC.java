import java.lang.Math;
import java.util.Random;

class ABC extends Thread{
	private int[][][] problem;
	private int[][] emptyCell;
	private int maxCycle, cycle;
	private int employedSize, numCell, onlookerSize, scoutSize, maxEmptyCell, fitestBee=-1;
	private double maxFit=0;
	private Bee[] bee;
	private Bee bestBee;
	private Subgrid[] subgrid;
	private Random rand=new Random();
	private Fitness fit=new Fitness();
	private String information="";
	private GreedySelection greedy=new GreedySelection();
	private PrintResult printer;
	private PenaltyType penaltyType;

	ABC(PrintResult printer, int[][][] problem,int employedSize, int onlookerSize, int maxCycle, PenaltyType penaltyType) {
		//Setting of parameters
		this.problem = problem;
		this.maxCycle = maxCycle;
		this.employedSize = employedSize;
		this.onlookerSize = onlookerSize;
		this.printer = printer;
		this.penaltyType = penaltyType;
		numCell=problem.length*problem.length;
		scoutSize=(int)(0.1*employedSize);
		initialization();
	}

	public void run() {
		Bee v;
		double sumFitness = 0, beeFitness = 0;

		//actual
		for (cycle = 0; cycle < maxCycle && maxFit != 1; cycle++) {
			sumFitness = 0;

			//employed bee phase
			for (int i = 0; i < bee.length && maxFit != 1; i++) {
				v = neighborhoodSearch(i); // neighborhood search
				bee[i] = greedy.greedySearch(bee[i], v); //greedy
				beeFitness = bee[i].getFitness();
				maxFit = getMaxFit(maxFit, beeFitness, i); //storing of bestbee
				sumFitness = sumFitness + beeFitness;
			}

			//onlooker bee phase
			for (int i = 0; i < bee.length && maxFit != 1; i++) {
				double probability = bee[i].getFitness() / sumFitness;
				int maxOnlooker = (int) ((probability) * onlookerSize);

				for (int count = 0; count < maxOnlooker; count++) {
					v = neighborhoodSearch(i); // neighborhood search
					bee[i] = greedy.greedySearch(bee[i], v); //greedy
					maxFit = getMaxFit(maxFit, bee[i].getFitness(), i); //storing of best bee
				}
			}

			if (scoutSize > 0 && maxFit != 1) {
				double maxMin = 1;
				int minSet[] = new int[scoutSize]; //a set of indices containing the minimum fitness of scoutSize bees
				for (int i = 0; i < scoutSize; i++) {
					minSet[i] = i;
					if (maxMin > bee[i].getFitness())
						maxMin = bee[i].getFitness();
				}
				for (int i = scoutSize; i < bee.length; i++) {
					if (maxMin >= bee[i].getFitness()) {
						boolean hasBeenPopped = false;
						double temp = bee[i].getFitness();
						for (int ctr = 0; ctr < scoutSize; ctr++) {
							double curFitness = bee[minSet[ctr]].getFitness();
							if (temp < curFitness)
								temp = curFitness;
							if (!hasBeenPopped && curFitness == maxMin) {
								hasBeenPopped = true;
								minSet[ctr] = i;
							}
						}
						maxMin = temp;
					}
				}

				for (int i = 0; i < scoutSize && maxFit != 1; i++) {
					v = new Bee(getProblemCopy(), subgrid, this.penaltyType); //generating of new Solution
					bee[minSet[i]] = greedy.greedySearch(bee[minSet[i]], v); //greedy
					maxFit = getMaxFit(maxFit, bee[minSet[i]].getFitness(), minSet[i]); //storing of best bee
				}
			}
			System.out.printf("Cycle %d | Best Fitness: %.6f%n", (cycle + 1), bestBee.getFitness());
			printer.print((cycle + 1) + "\t" + bestBee.getFitness());
			v = null;
		}
	}
	
	protected boolean isDone() {
		if (cycle >= maxCycle || maxFit == 1)
			return true;
		return false;
	}
	
	private void initialization() {
		//Creating subgrids
		subgrid=new Subgrid[problem.length];
		int subDimY=(int)Math.sqrt(problem.length);
		int subDimX=problem.length/subDimY;
		for(int ctr=0, xCount=0; ctr<problem.length; ctr++, xCount++){
			subgrid[ctr]=new Subgrid(xCount*subDimX, ((ctr/subDimY)*subDimY), subDimX, subDimY);
			if((ctr+1)%subDimY==0 && ctr>0)
				xCount=-1;
			}

		//Initialization of population
		bee=new Bee[employedSize];
		bestBee=new Bee(subgrid, this.penaltyType);
		for(int ctr=0; ctr<employedSize; ctr++){
			bee[ctr]=new Bee(getProblemCopy(), subgrid, this.penaltyType);
			bee[ctr].setFitness(fit.calculateFitness(bee[ctr].getPenaltyValue()));
			}
		bestBee.copyProblem(bee[0].getCopy());
		bestBee.setFitness(bee[0].getFitness());
		//array of empty cells
		emptyCell=new int[numCell][3];
		maxEmptyCell=0;
		for(int ctr=0; ctr<problem.length; ctr++){
			for(int count=0; count<problem.length; count++){
				if(problem[ctr][count][1]==1){
					emptyCell[maxEmptyCell][0]=ctr;
					emptyCell[maxEmptyCell][1]=count;
					for(int ctr2=0; ctr2<subgrid.length; ctr2++){
						if(subgrid[ctr2].isBelong(emptyCell[maxEmptyCell][1], emptyCell[maxEmptyCell][0])){
							emptyCell[maxEmptyCell][2]=ctr2;
							break;
							}
						}
					maxEmptyCell++;
					}
				}
			}
		for(int ctr=0; ctr<problem.length; ctr++)
			subgrid[ctr].setNeededNum(bestBee.neededNumbers(subgrid[ctr]));
		}
	protected int[][][] getBestSolution(){
		return bestBee.getSolution();
		}
	protected String getInfo(){
		return bestBee.getFitness()+" "+cycle+" ";
		}
	protected String getCycle(){
		return "cycles:\t"+cycle;
		}

	protected String getCycles(){
		return cycle+"";
		}
	protected double getFitness(){
		return bestBee.getFitness();
		}
	protected int[][][] getProblemCopy(){
		int[][][] copy=new int[problem.length][problem.length][2];
		for(int ctr=0; ctr<copy.length; ctr++){
			for(int ct=0; ct<copy.length; ct++){
				copy[ctr][ct][0]=problem[ctr][ct][0];
				copy[ct][ctr][0]=problem[ct][ctr][0];
				copy[ctr][ct][1]=problem[ctr][ct][1];
				copy[ct][ctr][1]=problem[ct][ctr][1];
				}
			}
		return copy;
		}
	private double getMaxFit(double maxFit, double beeFitness, int i){
		if(maxFit<=beeFitness){
			maxFit=beeFitness;
			bestBee.copyProblem(bee[i].getCopy());
			bestBee.setFitness(beeFitness);
			}
		return maxFit;
		}
	private Bee neighborhoodSearch(int i) {
		int j=rand.nextInt(maxEmptyCell), k=rand.nextInt(employedSize);
		while(k==i)	k=rand.nextInt(employedSize);

		int xij=bee[i].getSolution()[emptyCell[j][0]][emptyCell[j][1]][0], xkj=bee[k].getSolution()[emptyCell[j][0]][emptyCell[j][1]][0];
		int neededNum[]=subgrid[emptyCell[j][2]].getNeededNum();
		int vij=neededNum[(int)Math.ceil(xij+Math.abs(rand.nextDouble()*(xij-xkj)))%neededNum.length];

        Bee newBee=new Bee(subgrid, bee[i].getPenaltyType());
		newBee.swap(bee[i].getCopy(), emptyCell[j][2], emptyCell[j][0], emptyCell[j][1], xij, vij);

        return newBee;
    }

	protected void decompose(){
		for(int ctr=0; ctr<employedSize; ctr++)
			bee[ctr]=null;
		bestBee=null;
		}
	}