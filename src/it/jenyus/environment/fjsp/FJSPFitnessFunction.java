package it.jenyus.environment.fjsp;

import it.jenyus.model.FitnessFunction;

public class FJSPFitnessFunction implements FitnessFunction<FJSPChromosome> {

	private int [][][] matrix;
	
	/**
	 * @param matrix
	 */
	public FJSPFitnessFunction(int[][][] matrix) {
		super();
		this.matrix = matrix;
	}


	@Override
	public double evaluate(FJSPChromosome c) {
		
		double sum = 0.0;
		Operation[] gene = c.getGenes();
		for(int i=0; i<gene.length; i++){
			Operation o = gene[i];
		//	sum += matrix[o.getJob()][o.getTask()][o.getMachine()];
		}
		c.setMakespan(sum);
		return (double)sum;
	}
}
