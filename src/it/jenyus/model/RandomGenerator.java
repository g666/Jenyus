package it.jenyus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RandomGenerator<T extends Chromosome<?>> implements IGenerator {

	protected Random random = new Random();
	protected int chromosomeLength;
	
	/**
	 * @param chromosomeLength
	 */
	public RandomGenerator(int chromosomeLength) {
		super();
		this.chromosomeLength = chromosomeLength;
	}

	@Override
	public Population generatePopulation(int populationSize,
			int chromosomeLength) {
		Population p = new Population(populationSize);
		List<Chromosome<?>> li = new ArrayList<Chromosome<?>>();
		for(int i=0; i<populationSize; i++)
			li.add(generateChromosome());
		p.setIndividuals(li);
		return p;
	}
	
	public abstract T generateChromosome();

}
