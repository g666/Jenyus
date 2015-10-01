package it.jenyus.environment.knapsack;

import it.jenyus.model.FitnessFunction;

public class KnapsackFitnessFunction implements FitnessFunction<KnapsackChromosome>{

	private int[] function, weigths;
	
	/**
	 * @param function
	 * @param constraints
	 * @param weigths
	 * @param capacity
	 */
	public KnapsackFitnessFunction(int[] function,
			int[] weigths) {
		super();
		this.function = function;
		this.weigths = weigths;
//		for(int i=0; i<this.weigths.length; i++){
//			int t = this.weigths[i];
//			int n=this.function[i]/t;
//			this.weigths[i]=n;
//		}
	}

	@Override
	public double evaluate(KnapsackChromosome c) {
		// TODO Auto-generated method stub
		double fit = 0.0;
		Boolean[] g = c.getGenes();
		for(int i=0; i<g.length; i++){
			if(g[i])
				fit+=function[i];
		}
		return fit;
	}

//	public static void main(String[] args) throws Exception{
//		KnapsackRandomGenerator r = new KnapsackRandomGenerator();
//		KnapsackChromosome k = r.generateChromosome();
//		FileInputStream ff = new FileInputStream(new File("C:/Users/Mentalist/Documents/Dropbox_666/Dropbox/MapYou/Ricerca Operativa/" +
//				"Progetto AG/Jenyus/src/it/jenyus/environment/knapsack/knapsackconf.txt"));
//		KnapsackDataSetGenerator kk = new KnapsackDataSetGenerator(ff);
//		kk.generatePopulation(0, 5);
//		KnapsackFitnessFunction f = new KnapsackFitnessFunction(kk.getFunction(), kk.getConstraints(), kk.getWeigths(), kk.getCapacity());
//		System.out.println(k);
//		System.out.println(k.isAdmissible());
//		System.out.println(f.evaluate(k));
//	}
}
