package it.jenyus.ui.console;

import it.jenyus.model.Chromosome;
import it.jenyus.model.Costants;
import it.jenyus.model.GAConfiguration;
import it.jenyus.model.GeneticAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class Console{

	protected GAConfiguration conf = new GAConfiguration();
	private Scanner sc = new Scanner(System.in);
	private List<Command> cmds;
	private GeneticAlgorithm alg;
	private boolean withElitism;

	public Console(){
		cmds = new ArrayList<Command>();
		cmds.add(new Command("help", "Show help", "-h", "help"));
		cmds.add(new Command("quit", "Exit from Jenyus", "-q", "quit"));
		cmds.add(new Command("chrolen", "Chromosome length", "-chlen", "chrolen"));
		cmds.add(new Command("popdim", "Population length", "-pdim", "popdim"));
		cmds.add(new Command("elitethr", "Set elitism threshold", "-elthr", "elitethr"));
		cmds.add(new Command("withElitism", "Set to aplly elitism selection (y/n) which threshold choosed by -elthr option", "-we", "we"));
		cmds.add(new Command("niter", "Set number of iterations that ", "-nit", "niter"));
		cmds.add(new Command("conf", "Show genetic algorithm configurations", "-cf", "showConf"));
		cmds.add(new Command("use", "Use the selected problem", "-u", "use"));
		cmds.add(new Command("showp", "Show the problems that the genetic algorithm can be resolve", "-sp", "showp"));
		cmds.add(new Command("run", "Execute the genetic algorithm to resolve the specific problem", "-r", "runGA"));
		try {
			cmds.addAll(getCommands());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void printClass(){
		System.out.print("\n"+getClass().getSimpleName().replaceAll("_Console", "")+" --> ");
	}

	public void we(Command cmd, String s){
		if(s.equals("y"))
			withElitism=true;
		else
			withElitism = false;
		cmd.setDefaultValue(String.valueOf(withElitism));
	}

	public abstract List<Command> getCommands();

	public abstract GeneticAlgorithm algorithm();

	public final void runGA(Command cmd, String s){
		alg = algorithm();
		if(withElitism)
			alg.setWithElitism(true);
		else
			alg.setWithElitism(false);
		alg.init();
		long start = System.currentTimeMillis();
		Chromosome<?> best = alg.run();
		long end = System.currentTimeMillis();
		if(best!=null)
		{
			System.out.println("Solution found:\n\t\tChromosome: "+best.toString()+"\t\tWith elitism: "+alg.isWithElitism());
			System.out.println("Time: "+((double)(end-start)/1000)+" s.");
		}else{
			System.out.println("No solution found.");
		}
	}

	public final void showp (Command cmd, String s){
		try {
			Vector<Class<?>> v = getClassBySpuerClassName(new File(Costants.getPath()+"Jenyus.jar"), "it.jenyus.model.GeneticAlgorithm");
			for(int i=0; i<v.size(); i++)
				System.out.println(v.get(i).getName());
		} catch (FileNotFoundException e) {
			Vector<Class<?>> v;
			try {
				v = Utility.getClassesBySuperClassName(new File(Costants.getPath()), "it.jenyus.model.GeneticAlgorithm");
				for(int i=0; i<v.size(); i++)
					System.out.println(v.get(i).getName());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println(e1.getMessage());
				printClass();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			printClass();
		}
	}
	
	private static Vector<Class<?>> getClassBySpuerClassName(File fl, String superclassName) throws IOException{
		Vector<Class<?>> v = new Vector<Class<?>>();
		JarFile jf = new JarFile(fl);
		Enumeration<JarEntry> en = jf.entries();
		while(en.hasMoreElements())
		{
			JarEntry e = en.nextElement();
			if(e.isDirectory()){
			}else{
				if(e.getName().endsWith(".class")){
					try {
						String cst = new String(e.getName().replace("/", ".").replace(".class", ""));
						Class<?> c = Utility.class.getClassLoader().loadClass(cst);
						Class<?> sup = c.getSuperclass();
						while(sup!=null && !sup.getSimpleName().equals("Object")){
							if(sup.getName().equals(superclassName))
							{
								v.add(c);
								break;
							}
							sup = sup.getSuperclass();
						}
					} catch (ClassNotFoundException ee) {
						// TODO Auto-generated catch block
						//ee.printStackTrace();
						//JOptionPane.showMessageDialog(null, ee.getMessage());
						continue;
					}
				}
			}
		}
		return v;
	}

	public final void use (Command cmd, String nameProblem){
		try {
			Class<?> c= Class.forName(nameProblem+"_Console");
			System.gc();
			((Console)c.newInstance()).init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			printClass();
		}
	}

	public final void init(){
		System.out.println();
		printClass();
		System.out.println("\n"+getDescription()+"\n");
		help(null, "");
		printClass();
		dispCmd(sc.nextLine());
	}

	public abstract String getDescription();

	private Command getByName(String name){
		for(int i=0; i<cmds.size(); i++){
			Command c = cmds.get(i);
			if(c.getName().equals(name) || c.getShortName().equals(name))
				return c;
		}
		return null;
	}

	private void dispCmd(String string){
		Command c = getByName(getToken(string, true));
		if(c!=null){
			String par = getToken(string, false);
			try {
				getClass().getMethod(c.getMethod(), Command.class, String.class).invoke(this, c, par);
				printClass();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}else
		{
			System.out.println("Wrong syntax. Show help.");
			printClass();
		}
		dispCmd(sc.nextLine());
	}

	private static String getToken(String s, boolean first){
		try {
			StringTokenizer t = new StringTokenizer(s, " ");
			if(first)
				return t.nextToken();
			else{
				t.nextToken();
				return t.nextToken();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	public final void help(Command cmd, String s){
		System.out.println("Options:");
		for(int i=0; i<cmds.size(); i++){
			Command c = cmds.get(i);
			System.out.println(c.getName()+"\t\t"+c.getShortName()+"\t\tValue: "+c.getDefaultValue()+"\t\t"+c.getDescription());
		}
		System.out.println();
	}

	public final void quit(Command cmd, String s){
		System.exit(0);
	}

	public final void showConf(Command cmd, String par){
		System.out.println("Population size: "+conf.getPOPULATION_SIZE());
		System.out.println("Elitism threshold: "+conf.getELITISM_THRESHOLD());
		System.out.println("Number of iterations: "+conf.getNUM_OF_ITERATIONS());
		System.out.println("Chromosome length: "+conf.getCHROMOSOME_LENGTH());
		System.out.println();
	}

	public final void chrolen (Command cmd, String lenChromosome){
		try {
			int chlen=Integer.parseInt(lenChromosome);
			if(chlen<=0)
				throw new IllegalArgumentException("The chromosome length must be positive.\n");
			else{
				conf.setCHROMOSOME_LENGTH(chlen);
				cmd.setDefaultValue(String.valueOf(chlen));
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException(lenChromosome+" cannot formatted.\n");
		}
	}

	public final void popdim (Command cmd, String popSize){
		try {
			int pop=Integer.parseInt(popSize);
			if(pop<=3)
				throw new IllegalArgumentException("The population size must be grater than 3.\n");
			else
			{
				conf.setPOPULATION_SIZE(pop);
				cmd.setDefaultValue(String.valueOf(pop));
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException(popSize+" cannot formatted.\n");
		}
	}

	public final void elitethr (Command cmd, String eliteThr){
		try {
			int pop=Integer.parseInt(eliteThr);
			if(pop<=3)
				throw new IllegalArgumentException("The elitism threshold must be grater than 3.\n");
			else
			{
				conf.setELITISM_THRESHOLD(pop);
				cmd.setDefaultValue(String.valueOf(pop));
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException(eliteThr+" cannot formatted.\n");
		}
	}

	public final void niter (Command cmd, String nit){
		try {
			int pop=Integer.parseInt(nit);
			if(pop<=3)
				throw new IllegalArgumentException("The number of iterations must be positive.\n");
			else
			{
				conf.setNUM_OF_ITERATIONS(pop);
				cmd.setDefaultValue(String.valueOf(pop));
			}
			System.out.println();
		} catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException(nit+" cannot formatted.\n");
		}
	}
}