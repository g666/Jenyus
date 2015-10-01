package it.jenyus.environment.knapsack;

import java.util.Random;
import java.util.Set;

import it.jenyus.model.Filter;

public class KnapsackFilter implements Filter<KnapsackChromosome,Boolean> {

	private int capacity;
	private int[] constraints;

	/**
	 * @param capacity
	 * @param constraints
	 */
	public KnapsackFilter(int capacity, int[] constraints) {
		super();
		this.capacity = capacity;
		this.constraints = constraints;
	}

	@Override
	public boolean isAdmissible(KnapsackChromosome c) {
		// TODO Auto-generated method stub
		int sum=0;
		Boolean[] g = c.getGenes();
		for(int i=0; i<g.length; i++){
			if(g[i])
				sum+=constraints[i];
		}
		c.setCapacity(sum);
		boolean b =sum<=capacity;
		return b;
	}

	@Override
	public void makeAdmissible(KnapsackChromosome c) {
		// TODO Auto-generated method stub
		int i=0;
		do{
			c.getGenes()[i]=c.getGenes()[i]?false:false;
			i++;
		}
		while(!isAdmissible(c) || i<100);
	}

	@Override
	public void setOfAllele(Set<Boolean> set) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Boolean> setOfAllele() {
		// TODO Auto-generated method stub
		return null;
	}

}
