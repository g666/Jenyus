//package it.jenyus.environment.tsp;
//
//import it.jenyus.controller.Utility;
//import it.jenyus.model.Costants;
//import it.jenyus.model.GAConfiguration;
//import it.jenyus.ui.console.Console;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//import java.util.Vector;
//
//
//public class UtilityTestTSP {
//
//	private static Scanner sc = new Scanner(System.in);
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//
//		System.out.println("********* JENYUS **********");
//		System.out.println("***Tool for solving problem with Genetic Alghoritms***");
//		System.out.println();
//		if(args.length==0){
//			help();
//		}else{
//			for(int i=0; i<args.length; i++){
//				dispCmd(args[i]);
//			}
//		}
//		dispCmd(sc.nextLine());
//
//	}
//
//	private static void dispCmd(String string) {
//		// TODO Auto-generated method stub
//		if(string.equalsIgnoreCase("help") || string.equalsIgnoreCase("-h")){
//			help();
//		}
//		else if(string.equalsIgnoreCase("quit") || string.equalsIgnoreCase("-q")){
//			System.exit(0);
//		}
//
//		else if(string.equalsIgnoreCase("showp") || string.equalsIgnoreCase("-sp")){
//			showp();
//		}
//		else if(string.startsWith("use") || string.startsWith("-u")){
//			use(getSecondToken(string));
//		}
//		else
//			help();
//		dispCmd(sc.nextLine());
//	}
//
//	private static String getSecondToken(String s){
//		try {
//			StringTokenizer t = new StringTokenizer(s, " ");
//			t.nextToken();
//			return t.nextToken();
//		} catch (Exception e) {
//			// TODO: handle exception
//			return "";
//		}
//	}
//
//	public static void help(){
//		System.out.println("-help       -h        Visualizza Help-");
//		System.out.println("-quit       -q        Exit Jenyus-");
//		System.out.println("-showp      -sp       Visualizza problemi-");
//		System.out.println("-use        -u        Usa problema-");
//		System.out.print("jenyus --> ");
//	}
//
//	public static void showp (){
//		Vector<Class<?>> v;
//		try {
//			v = Utility.getClassesBySuperClassName(new File(Costants.getPath()), "it.jenyus.model.GeneticAlgorithm");
//			for(Class<?> c: v)
//				System.out.println(c.getName());
//			System.out.print("jenyus --> ");
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.print("jenyus --> ");
//		}
//	}
//
//	public static void use (String nameProblem){
//		try {
//			Class<?> c= Class.forName(nameProblem+"_Console");
//			Console ga= (Console) c.newInstance();
//			ga.startConsole();
//			System.out.print("jenyus --> ");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			System.out.print("jenyus --> ");
//		}
//	}
//}
//
//
