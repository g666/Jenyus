package it.jenyus.environment.tsp;

import it.jenyus.model.GAConfiguration;
import it.jenyus.model.Problem;

import java.awt.Checkbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class TSPProblem extends Problem{

	private double[][] archCost;
	private InputStream in;
	private String eof="eof";
	private Set<Character> setOfAllele;

	/**
	 * @param archCost
	 */
	public TSPProblem(GAConfiguration conf, InputStream in) {
		super(conf);
		this.in = in;
	}

	/**
	 * @return the archCost
	 */
	public final double[][] getArchCost() {
		return archCost;
	}
	
	public Set<Character> getSetOfAllele() {
		return setOfAllele;
	}

	/**
	 * @param archCost the archCost to set
	 */
	public final void setArchCost(double[][] archCost) {
		this.archCost = archCost;
	}

	@Override
	public void init() {
		String dim=null,graph=null;
		boolean done=false;
		Scanner sc=null;
		try {
			int numrow=0;
			sc= new Scanner(in);
			while(sc.hasNextLine()){
				String line=sc.nextLine();
				numrow++;
				if(line.equals("eof"))
					break;
				if(!line.startsWith("#")){
					if(line.equals("name")){
						String l = sc.nextLine();
						StringTokenizer t = new StringTokenizer(l, ",");
						setOfAllele = new HashSet<Character>();
						while(t.hasMoreElements()){
							try {
								setOfAllele.add(Character.valueOf(t.nextToken().charAt(0)));
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
					}
					if(line.contains("dim")){
						try{
							dim=sc.nextLine();
							StringTokenizer d= new StringTokenizer(dim,",");
							int i= Integer.parseInt(d.nextToken().toString());
							int j= Integer.parseInt(d.nextToken().toString());
							archCost= new double[i][j];
							numrow++;
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
					else if(line.contains("Graph")){
						graph=sc.nextLine();
						if(graph.startsWith("**")){
							while(!done){
								graph=sc.nextLine();
								if(!graph.equalsIgnoreCase("**")){
									StringTokenizer dd= new StringTokenizer(graph,",");
									while(dd.hasMoreTokens()){
										String c= dd.nextToken();
										Character c1= c.charAt(0);
										String d= dd.nextToken();
										Character c2= d.charAt(0);
										int i= ((int)c1.charValue())-65;
										int j= ((int)c2.charValue())-65;
										double cost=Double.parseDouble(dd.nextToken().toString());
										archCost[i][j]=cost;
									}
									numrow++;
								}else
									done=true;
							}
						}
						else
							throw new DataSetGeneratorException("Error line ->", numrow);
					}
					else
						continue;
				}
				else{
					sc.nextLine();
					continue;
				}
			}
			
			for(int i=0; i<archCost.length; i++)
				for(int j=0; j<archCost.length; j++){
					if(i==j)
						archCost[i][j]=Double.MAX_VALUE;
				}
					
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			sc.close();
		}
	}
	public static void main(String[] args) throws FileNotFoundException{
//		File f=new File("C:/Users/pc/Dropbox/MapMe/Ricerca Operativa/Progetto AG/Jenyus/src/it/jenyus/environment/tsp/tspconf.txt");
//		TSPProblem p= new TSPProblem(f);
//		p.init();
//
//
//		double mTsp[][]= p.getArchCost();
//		for(int i=0; i<mTsp.length; i++)
//			for(int j=0; j<mTsp.length; j++){
//				if(i==j)
//					mTsp[i][j]=Double.MAX_VALUE;
//				System.out.println("Pos["+i+"]["+j+"] -> -> value"+mTsp[i][j]+"");
//			}
		
	}
}
