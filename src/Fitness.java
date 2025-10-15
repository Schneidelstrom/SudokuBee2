class Fitness{
	Fitness(){}
	protected double calculateFitness(int penaltyValue){
		return 1/(1+Double.parseDouble(penaltyValue+""));
		}
	}