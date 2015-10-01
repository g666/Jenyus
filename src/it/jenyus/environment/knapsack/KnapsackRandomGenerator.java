package it.jenyus.environment.knapsack;

import it.jenyus.model.GAConfiguration;
import it.jenyus.model.RandomGenerator;

public class KnapsackRandomGenerator extends RandomGenerator<KnapsackChromosome>{

	public KnapsackRandomGenerator(int chromosomeLength) {
		super(chromosomeLength);
		// TODO Auto-generated constructor stub
	}

	@Override
	public KnapsackChromosome generateChromosome() {
		// TODO Auto-generated method stub
		Boolean[] g  =new Boolean[chromosomeLength];
		for(int i=0; i<chromosomeLength; i++){
			g[i] = random.nextBoolean();
		}
		KnapsackChromosome k = new KnapsackChromosome(g);
		return k;
	}

}
