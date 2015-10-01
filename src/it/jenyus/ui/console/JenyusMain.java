package it.jenyus.ui.console;

import it.jenyus.model.Costants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JenyusMain {

	private Scanner sc = new Scanner(System.in);


	public void start(String[] args){
		System.out.println();
		System.out.println("****************** JENYUS *******************");
		System.out.println();
		System.out.println("***Tool for solving problem with Genetic Alghoritms***");
		System.out.println("******************************************************");
		System.out.println("Written by Giuseppe Fusco and Giovanni Di Blasio");
		System.out.println();
		help();
		dispCmd(sc.nextLine());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new JenyusMain().start(args);
	}

	private void dispCmd(String string) {
		// TODO Auto-generated method stub
		if(string.equalsIgnoreCase("help") || string.equalsIgnoreCase("-h")){
			help();
		}
		else if(string.equalsIgnoreCase("quit") || string.equalsIgnoreCase("-q")){
			System.exit(0);
		}

		else if(string.equalsIgnoreCase("showp") || string.equalsIgnoreCase("-sp")){
			showp();
		}
		else if(string.startsWith("use") || string.startsWith("-u")){
			use(getSecondToken(string));
		}
		else
			help();
		dispCmd(sc.nextLine());
	}

	private String getSecondToken(String s){
		try {
			StringTokenizer t = new StringTokenizer(s, " ");
			t.nextToken();
			return t.nextToken();
		} catch (Exception e) {
			return "";
		}
	}

	public void help(){
		System.out.println("help       -h        Visualizza Help");
		System.out.println("quit       -q        Exit Jenyus");
		System.out.println("showp      -sp       Visualizza problemi");
		System.out.println("use        -u        Usa problema");
		System.out.println();
		System.out.print("jenyus --> ");
	}

	public static void showp (){
		try {
			Vector<Class<?>> v = getClassBySpuerClassName(new File(Costants.getPath()+"Jenyus.jar"), "it.jenyus.model.GeneticAlgorithm");
			for(int i=0; i<v.size(); i++)
				System.out.println(v.get(i).getName());
			System.out.print("\njenyus --> ");
		} catch (FileNotFoundException e) {
			Vector<Class<?>> v;
			try {
				v = Utility.getClassesBySuperClassName(new File(Costants.getPath()), "it.jenyus.model.GeneticAlgorithm");
				for(int i=0; i<v.size(); i++)
					System.out.println(v.get(i).getName());
				System.out.print("\njenyus --> ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.print("\njenyus --> ");
			}
		} catch (IOException e) {
			System.out.print("\njenyus --> ");
		}
	}

	public void use (String nameProblem){
		try {
			Class<?> c= Class.forName(nameProblem+"_Console");
			((Console)c.newInstance()).init();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.print("\njenyus --> ");
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
}
