package it.jenyus.model;

public abstract class GeneticAlgorithm {

	private Population inititialPopulation;
	private Population bestPopulation;
	private IGenerator generator;
	private ElitismSelector elitismSelector;
	private GeneticOperator crossover, mutation;
	private int numOfIterations;
	private FitnessFunction<Chromosome<?>> fitnessFunction;
	private Filter<Chromosome<?>,?> filter;
	protected Problem problem;
	private boolean withElitism, isInit;
	private String descP;
	private GAConfiguration conf;
	
	public GeneticAlgorithm(IGenerator generator, int numOfIterations, Problem problem, String desc, GAConfiguration conf){
		this.generator = generator;
		this.problem = problem;
		this.numOfIterations = numOfIterations;
		this.descP=desc;
		bestPopulation = new Population();
		elitismSelector = new ElitismSelector(0);
		crossover = new Crossover();
		mutation = new Mutation();
		
		if(conf==null)
			throw new IllegalStateException("Set up the algorithm configuration.\n");
		else
			this.conf = conf;
	}
	
	public abstract FitnessFunction setupFitnessFunction();

	public abstract Filter setupFilter();
	
	private Population retrieveInitialPopulation() throws Exception{
		return generator.generatePopulation(conf.getPOPULATION_SIZE(), conf.getCHROMOSOME_LENGTH());
	}
	
	private void checkConfiguration(){
			int popSize = conf.getPOPULATION_SIZE();
			int chromSize = conf.getCHROMOSOME_LENGTH();
			int el = conf.getELITISM_THRESHOLD();
			if(popSize<=3)
				throw new IllegalStateException("The population size must be grater than 3.");
			if(chromSize<=0)
				throw new IllegalStateException("The chromosome length must be positive.");
			if(numOfIterations<=0)
				throw new IllegalStateException("The number of iterations must be positive.");
			if(problem == null)
				throw new IllegalStateException("The problem cannot be null.");
			if(el<=3)
				throw new IllegalStateException("The elitism threshold must be grater than 3.\n.");
			if(chromSize>=conf.getEND_POINT_CROSSOVER() &&
					chromSize>conf.getSTRAT_POINT_CROSSOVER());
			else
				throw new IllegalStateException("Incorrect value of start point crossover/end point crossover.\n.");
		
			this.fitnessFunction = setupFitnessFunction();
			this.filter = setupFilter();
			
			if(generator == null)
				throw new IllegalStateException("The generator cannot be null.");
			if(fitnessFunction == null)
				throw new IllegalStateException("The fitness function cannot be null.");
			if(filter == null)
				throw new IllegalStateException("The filter cannot be null.");
	}
	
	private void evaluatePopulation(Population p, FitnessFunction<Chromosome<?>> fitnessFunction){
		p.evaluatePopulation(fitnessFunction);
	}
	
	private void selectIndividuals(Population to, Population from, ISelector selector) throws Exception{
		from.getIndividuals().addAll(selector.select(to).getIndividuals());
	}
	
	private Population applyGeneticOperator(Population population, GeneticOperator operator) throws Exception{
		int size = population.getSize();
		int type= size>50?GAConfiguration.POINT_CROSSOVER:GAConfiguration.UNIFORM_CROSSOVER;
		return operator.execute(population,type,filter);
	}
	
	public abstract boolean applyElitism();
	
	public final void init(){
		try {
			problem.init();
			checkConfiguration();
			withElitism = applyElitism();
			isInit = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isInit = false;
		}
	}
	
	public void setWithElitism(boolean withElitism) {
		this.withElitism = withElitism;
	}
	
	public boolean isWithElitism() {
		return withElitism;
	}
	
	public final Chromosome<?> run(){
		try {
			if(isInit){
				int numIt = numOfIterations;
				inititialPopulation = retrieveInitialPopulation();
				evaluatePopulation(inititialPopulation, fitnessFunction);
				while(numIt>0){
					if(withElitism){
						elitismSelector.setThreshold(conf.getELITISM_THRESHOLD());
						selectIndividuals(inititialPopulation, bestPopulation, elitismSelector);
						if(bestPopulation.getSize()<4){
							while(bestPopulation.getSize()<4)
							{
							Chromosome<?> b = inititialPopulation.getBestIndividual();
							bestPopulation.getIndividuals().add(b);
							inititialPopulation.getIndividuals().remove(b);
							}
						}
					}
					int matingPoolSize = withElitism?bestPopulation.getSize():inititialPopulation.getSize()/2;
					if(matingPoolSize<=0)
						matingPoolSize=1;
					Population matingPool = new Population(matingPoolSize);
					if(withElitism)
						selectIndividuals(bestPopulation, matingPool, new RouletteWheelSelector(matingPoolSize));
					else
						selectIndividuals(inititialPopulation, matingPool, new RouletteWheelSelector(matingPoolSize));
					
					Population p2 = applyGeneticOperator(matingPool, crossover);
					Population p3 = applyGeneticOperator(p2, mutation);

					Population newpReplace = new Population();
					newpReplace.getIndividuals().addAll(p3.getIndividuals());
					newpReplace.getIndividuals().addAll(bestPopulation.getIndividuals());
					newpReplace.getIndividuals().addAll(inititialPopulation.getIndividuals());				
					inititialPopulation.setIndividuals(newpReplace.getIndividuals());
					
					evaluatePopulation(inititialPopulation, fitnessFunction);
					numIt--;
				}
				return inititialPopulation.getBestIndividual();
			}else
				throw new IllegalStateException("Genetic algorithm "+getClass().getName()+" not initialized. Invoke init() method before starting algorithm.");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getDescP() {
		return descP;
	}
}
