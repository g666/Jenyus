package it.jenyus.environment.tsp;

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


public class TSPGA extends GeneticAlgorithm{

	private Character ori, dest;

	public TSPGA(IGenerator generator, int numOfIterations, Problem problem, GAConfiguration conf,
			Character ori, Character dest) {
		super(generator, numOfIterations, problem,"TSP", conf);
		this.ori = ori;
		this.dest = dest;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TSP";
	}


	@Override
	public FitnessFunction setupFitnessFunction() {
		if(problem instanceof TSPProblem){
			TSPProblem p = (TSPProblem)problem;
			return new TSPFitnessFunction(p.getArchCost());
		}
		else return null;
	}

	@Override
	public Filter setupFilter() {
		if(problem instanceof TSPProblem){
			TSPProblem p = (TSPProblem)problem;
			TSPFilter t = new TSPFilter(ori, dest);
			t.setOfAllele(p.getSetOfAllele());
			return t;
		}
		else return null;

	}

	@Override
	public boolean applyElitism() {
		return false;
	}
	public static void main(String[] args) throws FileNotFoundException {

		File f= new File(Costants.getPath()+"it/jenyus/environment/tsp/tspconf.txt");
		TSPDataSetGenerator d= new TSPDataSetGenerator(new FileInputStream(f));
		GAConfiguration g = new GAConfiguration();
		g.setCHROMOSOME_LENGTH(6);
		g.setELITISM_THRESHOLD(8);
		TSPGA ga = new TSPGA(new TSPRandomGenerator(g.getCHROMOSOME_LENGTH()), 8, new TSPProblem(g, new FileInputStream(f)), g,
				'B','A');
		ga.init();

		TSPChromosome best = (TSPChromosome) ga.run();
		System.out.println("found: "+best.toString()+" --> "+ga.isWithElitism());


	}
}
