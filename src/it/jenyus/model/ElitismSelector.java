package it.jenyus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ElitismSelector implements ISelector {

	private int threshold;
	
	/**
	 * @param threshold
	 */
	public ElitismSelector(int threshold) {
		super();
		this.threshold = threshold;
	}

	@Override
	public final Population select(Population actualPopulation) throws Exception {
		// TODO Auto-generated method stub
		if(threshold>0){
			int t = threshold;
			List<Chromosome<?>> newChr = new ArrayList<Chromosome<?>>();
			List<Chromosome<?>> chr = actualPopulation.getIndividuals();
			Collections.sort(chr, new Comparator<Chromosome<?>>() {

				@Override
				public int compare(Chromosome<?> o1, Chromosome<?> o2) {
					// TODO Auto-generated method stub
					return (int) ((o1.getFitnessValue()*1000)-(o2.getFitnessValue()*1000));
				}
			});
			Collections.reverse(chr);
			for(int i=0; i<chr.size(); i++){
				Chromosome<?> c  =chr.get(i);
				if(newChr.size()<t)
					{
					newChr.add(c);
					chr.remove(c);
					t--;
					}
			}
			actualPopulation.setIndividuals(chr);
			return new Population(newChr);
		}
		else throw new IllegalStateException("The elitism threshold must be positive.");
	}
	
	public int getThreshold() {
		return threshold;
	}
	
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
}
