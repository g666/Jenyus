package it.jenyus.environment.knapsack;

import it.jenyus.environment.tsp.DataSetGeneratorException;
import it.jenyus.model.GAConfiguration;
import it.jenyus.model.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KnapsackProblem extends Problem{

	private int[] function, constraints, weigths;
	private int capacity;
	private InputStream in;
	private String eof="eof";
	
	public KnapsackProblem(GAConfiguration conf, InputStream in) {
		super(conf);
		this.in = in;
	}
	
	/**
	 * @return the function
	 */
	public final int[] getFunction() {
		return function;
	}


	/**
	 * @return the constraints
	 */
	public final int[] getConstraints() {
		return constraints;
	}


	/**
	 * @return the weigths
	 */
	public final int[] getWeigths() {
		return weigths;
	}


	/**
	 * @return the capacity
	 */
	public final int getCapacity() {
		return capacity;
	}


	@Override
	public void init() {
		function = new int[conf.getCHROMOSOME_LENGTH()];
		constraints = new int[conf.getCHROMOSOME_LENGTH()];
		weigths = new int[conf.getCHROMOSOME_LENGTH()];
		BufferedReader buf;
		try {
			buf = new BufferedReader(new InputStreamReader(in));
			int num = 0;
			String line = buf.readLine();
			while(line!=null){
				num++;
				if(line.equals("eof"))
					break;
				if(!line.startsWith("#") && line.length()>0){

					StringTokenizer tok = new StringTokenizer(line, ":");
					if(tok.countTokens()==2){
						String start = tok.nextToken();
						String end = tok.nextToken().replaceAll(" ", "");
						try {
							if(start.equals("f")){
								filleArray(function, end, "-");
							}else if(start.equals("v")){
								filleArray(constraints, end, "-");
							}else if(start.equals("w")){
								filleArray(weigths, end, "-");
							}else if(start.equals("b")){
								capacity = Integer.parseInt(end);
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new DataSetGeneratorException("Incorrect syntax", num);
						}
					}
					//						else
					//							throw new DataSetGeneratorException("Incorrect syntax", num);
				}
				line = buf.readLine();
			}
			buf.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void filleArray(int[] a, String line, String delimiter) throws Exception{
		StringTokenizer tok = new StringTokenizer(line, delimiter);
		int i=0;
		while(tok.hasMoreElements()){
			String token = tok.nextToken();
			try {
				int n = Integer.parseInt(token);
				a[i]=n;
				i++;
			} catch (NumberFormatException e) {
				// TODO: handle exception
				throw new NumberFormatException(token+" cannot be formatted.");
			}
		}
	}
	
}
