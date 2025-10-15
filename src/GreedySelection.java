class GreedySelection{
	GreedySelection(){}
	protected Bee greedySearch(Bee x, Bee v){
		double newFitness=new Fitness().calculateFitness(v.getPenaltyValue());
		if(x.getFitness()<=newFitness){
			x.copyProblem(v.getCopy());
			x.setFitness(newFitness);
			}
		return x;
		}
	}