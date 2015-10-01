package it.jenyus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelSelector implements ISelector {

	private int populationSize;

	/**
	 * @param populationSize
	 */
	public RouletteWheelSelector(int populationSize) {
		super();
		this.populationSize = populationSize;
	}

	@Override
	public Population select(Population actualPopulation) throws Exception {
		// TODO Auto-generated method stub
		List<Chromosome<?>> newChr = new ArrayList<Chromosome<?>>();
		Random rnd = new Random();
		actualPopulation.normalizeFitnessValues();
		List<Chromosome<?>> chr = actualPopulation.getIndividuals();
		while(populationSize>0){
			double prob = ((double)(rnd.nextInt(100)+1))/100;
			Chromosome<?> g = getFirstOverThreshold(prob, chr);
			chr.remove(g);
			newChr.add(g);
			populationSize--;
		}
		actualPopulation.setIndividuals(chr);
		return new Population(newChr);
	}

	private Chromosome<?> getFirstOverThreshold(double threshold,
			List<Chromosome<?>> l) {
		for(int i=0; i<l.size()-1; i++){
			Chromosome<?> g = l.get(i);
			if(threshold>g.getFitnessNormalizedValue() && threshold<l.get(i+1).getFitnessNormalizedValue())
			{
				return g;
			}
		}
		return l.get(l.size()-1);
	}

}
