package it.jenyus.environment.knapsack;

import it.jenyus.model.Costants;
import it.jenyus.model.Filter;
import it.jenyus.model.FitnessFunction;
import it.jenyus.model.GAConfiguration;
import it.jenyus.model.GeneticAlgorithm;
import it.jenyus.model.IGenerator;
import it.jenyus.model.Problem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class KnapsackGA extends GeneticAlgorithm{

	public KnapsackGA(IGenerator generator, int numOfIterations, Problem problem, GAConfiguration conf) {
		super(generator, numOfIterations, problem,"Zaino", conf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FitnessFunction setupFitnessFunction() {
		// TODO Auto-generated method stub
		if(problem instanceof KnapsackProblem){
			KnapsackProblem p = (KnapsackProblem)problem;
			return new KnapsackFitnessFunction(p.getFunction(), p.getWeigths());
		}
		else return null;
	}

	@Override
	public Filter setupFilter() {
		// TODO Auto-generated method stub
		if(problem instanceof KnapsackProblem){
			KnapsackProblem p = (KnapsackProblem)problem;
			return new KnapsackFilter(p.getCapacity(), p.getConstraints());
		}
		else return null;
	}


	public static void main(String[] args) throws FileNotFoundException {
		File f = new File(Costants.getPath()+"src/it/jenyus/environment/knapsack/test.txt");

		//		File ff = new File("C:/Users/pc/Dropbox/MapMe/Ricerca Operativa/Progetto AG/Jenyus/src/it/jenyus/environment/knapsack/knapsackconf.txt");
		GAConfiguration g = new GAConfiguration();
		g.setCHROMOSOME_LENGTH(100);
//		g.setELITISM_THRESHOLD(40);
		KnapsackProblem p = new KnapsackProblem(g,new FileInputStream(f));
		
		KnapsackGA k = new KnapsackGA(
				new KnapsackRandomGenerator(g.getCHROMOSOME_LENGTH()), 
				2, 
				p, g);
		k.init();
		int[] w = p.getConstraints();
		System.out.println("size: "+w.length);
		int sum=0;
		for(int i=0; i<w.length; i++)
			sum+=w[i];
		System.out.println("length: "+sum);
		System.out.println(p.getCapacity());
		KnapsackChromosome best = (KnapsackChromosome) k.run();
		System.out.println(best.getFitnessValue());
		System.out.println(best.getCapacity());
		System.out.println(best+"\t\telitism: "+k.isWithElitism());
		
	}


	@Override
	public boolean applyElitism() {
		// TODO Auto-generated method stub
		return false;
	}
}
