package it.jenyus.environment.tsp;

public class DataSetGeneratorException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3925829369605317571L;

	/**
	 * @param arg0
	 */
	public DataSetGeneratorException(String arg0, int textRow) {
		super(DataSetGeneratorException.class.getName()+": Error at line "+String.valueOf(textRow)+" ("+arg0+")\n");
 
	}
	
}
