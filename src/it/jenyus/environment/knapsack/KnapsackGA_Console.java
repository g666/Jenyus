package it.jenyus.environment.knapsack;

import it.jenyus.model.GAConfiguration;
import it.jenyus.model.GeneticAlgorithm;
import it.jenyus.model.IGenerator;
import it.jenyus.ui.console.Command;
import it.jenyus.ui.console.Console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KnapsackGA_Console extends Console{

	private KnapsackProblem problem;
	private IGenerator generator;
	private boolean isRandomGenerator;
	private File file;
	private boolean isManualMode;

	@Override
	public List<Command> getCommands() {
		// TODO Auto-generated method stub
		List<Command> c = new ArrayList<Command>();
		c.add(new Command("file", "Choose knapsack configuration file", "-f", "setPar"));
		c.add(new Command("gen", "Choose population generator method (r=random generator, otherwise will be a dataset generator with source specified by -f option)", "-g", "setGen"));
		c.add(new Command("syntax", "Show file syntax", "-sy", "syntax"));
		c.add(new Command("manual", "Choose manual input mode (y/n)", "-m", "mode"));
		return c;
	}
	
	public void mode(Command cmd, String s){
		if(s.equals("y"))
			isManualMode = true;
		else
			isManualMode = false;
		cmd.setDefaultValue(String.valueOf(isManualMode));
	}
	
	public void syntax(Command cmd, String s){
		BufferedReader buf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/it/jenyus/environment/knapsack/knapsackSyntax.txt")));
		try {
			String line = buf.readLine();
			while(line!=null)
			{
				System.out.println(line);
				line = buf.readLine();
			}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setPar(Command cmd, String p){
		try {
			file = new File(p);
			problem = new KnapsackProblem(conf, isManualMode?System.in:new FileInputStream(file));
			cmd.setDefaultValue(file.getPath());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public void setGen(Command cmd, String s){
		if(s.equals("r"))
			isRandomGenerator=true;
		else isRandomGenerator=false;
		cmd.setDefaultValue(String.valueOf(isRandomGenerator));
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Problema dello zaino";
	}

	@Override
	public GeneticAlgorithm algorithm() {
		// TODO Auto-generated method stub
		if(isRandomGenerator)
			generator = new KnapsackRandomGenerator(conf.getCHROMOSOME_LENGTH());
		else
			try {
				generator = new KnapsackDataSetGenerator(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new KnapsackGA(generator, conf.getNUM_OF_ITERATIONS(), problem, conf);
	}

	public static void main(String[] args) {
		GAConfiguration g = new GAConfiguration();
		g.setCHROMOSOME_LENGTH(5);
		g.setNUM_OF_ITERATIONS(5);
		KnapsackProblem k = new KnapsackProblem(g, System.in);
		KnapsackGA kg  =new KnapsackGA(new KnapsackRandomGenerator(g.getCHROMOSOME_LENGTH()), g.getNUM_OF_ITERATIONS(), k, g);
		kg.init();
		System.out.println(kg.run());
	}
}
