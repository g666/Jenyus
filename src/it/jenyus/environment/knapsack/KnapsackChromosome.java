package it.jenyus.environment.knapsack;

import java.util.Random;

import it.jenyus.model.Chromosome;

public class KnapsackChromosome extends Chromosome<Boolean>{

	private Random rnd = new Random();
	private int capacity;
	
	/**
	 * 
	 */
	public KnapsackChromosome() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param genes
	 */
	public KnapsackChromosome(Boolean[] genes) {
		super(genes);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param length
	 */
	public KnapsackChromosome(int length) {
		super(length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Boolean randomGene() {
		// TODO Auto-generated method stub
		return rnd.nextBoolean();
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		Boolean[] b = getGenes();
		for(int i=0; i<b.length; i++)
			s.append(b[i]+"|");
		return s.toString()+" --> "+("Fitness value: "+getFitnessValue()+" --> "+"Capacity: "+capacity);
	}
}
