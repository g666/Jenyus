package it.jenyus.environment.fjsp;

import it.jenyus.model.Filter;
import it.jenyus.model.FitnessFunction;
import it.jenyus.model.GAConfiguration;
import it.jenyus.model.GeneticAlgorithm;
import it.jenyus.model.IGenerator;
import it.jenyus.model.Problem;

public class FJSPGA extends GeneticAlgorithm{

	public FJSPGA(IGenerator generator, int numOfIterations, Problem problem, GAConfiguration conf) {
		super(generator, numOfIterations, problem, "Scheduling", conf);
		// TODO Auto-generated constructor stub
	}

	@Override
	public FitnessFunction setupFitnessFunction() {
		// TODO Auto-generated method stub
		return new FJSPFitnessFunction(((FJSProblem)problem).getMatrix());
	}

	@Override
	public Filter setupFilter() {
		// TODO Auto-generated method stub
		return new FJSPFilter(((FJSProblem)problem).getMatrix());
	}

	@Override
	public boolean applyElitism() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		GAConfiguration conf = new GAConfiguration();
		conf.setCHROMOSOME_LENGTH(3);
		FJSPGA f = new FJSPGA(new FJSPRandomGenerator(conf.getCHROMOSOME_LENGTH(), 1), 3, new FJSProblem(conf), conf);
		f.init();
		FJSPChromosome c = (FJSPChromosome) f.run();
		System.out.println(c);
	}
}
