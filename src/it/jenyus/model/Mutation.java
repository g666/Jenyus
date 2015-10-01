package it.jenyus.model;

import java.util.List;
import java.util.Random;


public class Mutation implements GeneticOperator {

	public Mutation() {

	}

	@Override
	public Population execute(Population initialPopulation, int type,
			Filter<Chromosome<?>,?> filter) throws Exception {
		Random rnd= new Random();
		double pm = ((double)(rnd.nextInt(100)+1)/10000);


		List<Chromosome<?>> list=initialPopulation.getIndividuals();

		for(int i=0; i<list.size(); i++){

			Chromosome<?> t= list.get(i);
			Object[] g= t.getGenes();
			for(int k=0; k<g.length; k++){
				double q = ((double)(rnd.nextInt(1000)+1))/10000;
				if(q<pm){
					//mutation
					Object all = g[k];
					boolean done = false;
					while(!done){
						Object newAll = t.randomGene();
						if(!newAll.equals(all)){
							g[k]=newAll;
							break;
						}
					}
				}else
					continue;
			}
			t.setGenes(g);

			if(filter!=null && !filter.isAdmissible(t))
				filter.makeAdmissible(t);
		}
		return new Population(list);
	}

	@Override
	public GAConfiguration getConfiguration() {
		return null;
	}

	@Override
	public void setConfiguration(GAConfiguration conf) {

	}
	
	public static void main(String[] args) {

		Random rnd= new Random();
		//
		//		double prob = ((double)(rnd.nextInt(100)+1)/10000);
		//		System.out.println(prob);

		double q = ((double)(rnd.nextInt(1000)+1))/10000;
		System.out.println(q);
		//		System.out.println(q);
		//
		//		if(q<prob)
		//			System.out.println("oke");
	}


}