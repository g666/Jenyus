package it.jenyus.environment.tsp;

import it.jenyus.model.FitnessFunction;

public class TSPFitnessFunction implements FitnessFunction<TSPChromosome>{

	private double[][] archCost;
	
	public TSPFitnessFunction(double[][] archCost){
		this.archCost = archCost;
	}
	
	@Override
	public double evaluate(TSPChromosome c) {
		
		Character[] gene = c.getGenes();
		double sum = 0.0;
		for(int i=1; i<gene.length; i++)
		{
			Character prec = gene[i-1];
			Character now = gene[i];
			int indexPrec = ((int)prec.charValue())-65;
			int indexNow = ((int)now.charValue())-65;
			sum += archCost[indexPrec][indexNow];
		}
		return 1/sum;
	}

	

}
