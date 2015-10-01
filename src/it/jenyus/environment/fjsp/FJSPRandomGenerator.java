package it.jenyus.environment.fjsp;

import it.jenyus.model.GAConfiguration;
import it.jenyus.model.RandomGenerator;

public class FJSPRandomGenerator extends RandomGenerator<FJSPChromosome>{

	private int job;
	
	
	/**
	 * @param job
	 */
	public FJSPRandomGenerator(int chromosomeLength, int job) {
		super(chromosomeLength);
		this.job = job;
	}


	@Override
	public FJSPChromosome generateChromosome() {
		// TODO Auto-generated method stub
		FJSPChromosome f = new FJSPChromosome(chromosomeLength, job);
		Operation[] o = new Operation[chromosomeLength];
		for(int i=0; i<o.length; i++){
			o[i] = f.randomGene();
		}
		f.setGenes(o);
		return f;
	}

	public static void main(String[] args) {
		FJSPRandomGenerator f = new FJSPRandomGenerator(3, 1);
		FJSPChromosome c = f.generateChromosome();
		System.out.println(c.toString());
		FJSPFilter filter = new FJSPFilter(null);
		System.out.println(filter.isAdmissible(c));
		filter.makeAdmissible(c);
		System.out.println(c);
		System.out.println(filter.isAdmissible(c));
	}
}
