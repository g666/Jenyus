package it.jenyus.model;

public class GAConfiguration {
	
	public static final int DEFAULT_POPULATION_SIZE = 50;
	
	private int POPULATION_SIZE = DEFAULT_POPULATION_SIZE;
	
	public static final int POINT_CROSSOVER=1;
	
	public static final int UNIFORM_CROSSOVER=-1;
	
//	public static int DEFAULT_CROSS=ONE_POINT_CROSSOVER;
	
	public static final int DEFAULT_CHROMOSOME_LENGTH = 8;

	private int CHROMOSOME_LENGTH = DEFAULT_CHROMOSOME_LENGTH;
	
	private int NUM_OF_ITERATIONS = 0;
	private int ELITISM_THRESHOLD = 4;
	private int STRAT_POINT_CROSSOVER = 0;
	private int END_POINT_CROSSOVER = 0;
	
	public int getEND_POINT_CROSSOVER() {
		return getCHROMOSOME_LENGTH();
	}
	
	public int getSTRAT_POINT_CROSSOVER() {
		return getCHROMOSOME_LENGTH()/2;
	}
	
	public void setEND_POINT_CROSSOVER(int eND_POINT_CROSSOVER) {
		END_POINT_CROSSOVER = eND_POINT_CROSSOVER;
	}
	
	public void setSTRAT_POINT_CROSSOVER(int sTRAT_POINT_CROSSOVER) {
		STRAT_POINT_CROSSOVER = sTRAT_POINT_CROSSOVER;
	}

	/**
	 * @return the eLITISM_THRESHOLD
	 */
	public final int getELITISM_THRESHOLD() {
		return ELITISM_THRESHOLD;
	}

	/**
	 * @param eLITISM_THRESHOLD the eLITISM_THRESHOLD to set
	 */
	public final void setELITISM_THRESHOLD(int eLITISM_THRESHOLD) {
		ELITISM_THRESHOLD = eLITISM_THRESHOLD;
	}

	/**
	 * @return the pOPULATION_SIZE
	 */
	public final int getPOPULATION_SIZE() {
		return POPULATION_SIZE;
	}

	/**
	 * @param pOPULATION_SIZE the pOPULATION_SIZE to set
	 */
	public final void setPOPULATION_SIZE(int pOPULATION_SIZE) {
		POPULATION_SIZE = pOPULATION_SIZE;
	}

	/**
	 * @return the cHROMOSOME_LENGTH
	 */
	public final int getCHROMOSOME_LENGTH() {
		return CHROMOSOME_LENGTH;
	}

	/**
	 * @param cHROMOSOME_LENGTH the cHROMOSOME_LENGTH to set
	 */
	public final void setCHROMOSOME_LENGTH(int cHROMOSOME_LENGTH) {
		CHROMOSOME_LENGTH = cHROMOSOME_LENGTH;
	}

	/**
	 * @return the nUM_OF_ITERATIONS
	 */
	public final int getNUM_OF_ITERATIONS() {
		return NUM_OF_ITERATIONS;
	}

	/**
	 * @param nUM_OF_ITERATIONS the nUM_OF_ITERATIONS to set
	 */
	public final void setNUM_OF_ITERATIONS(int nUM_OF_ITERATIONS) {
		NUM_OF_ITERATIONS = nUM_OF_ITERATIONS;
	}
	
	
}
