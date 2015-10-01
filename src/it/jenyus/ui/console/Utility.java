package it.jenyus.ui.console;

import it.jenyus.model.Costants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Utility {

	public static Vector<Class<?>> getClassesBySuperClassName(File fl, String superClassName) throws IOException{
		Vector<Class<?>> v = new Vector<Class<?>>();
		List<File> en = getAllFile(fl);
		for(int i=0; i<en.size(); i++){
			File e = en.get(i);
			if(e.isDirectory()){
			}else{
				if(e.getName().endsWith(".java")){
					try {
						String cst = new String(e.getPath().replace(System.getProperty("user.dir"), "").replace("\\", ".").replace(".java", ""));
						String cs = cst.substring(1);
						Class<?> c = Utility.class.getClassLoader().loadClass(cs);
						Class<?> sup = c.getSuperclass();
						while(sup!=null && !sup.getSimpleName().equals("Object")){
							if(sup.getName().equals(superClassName))
							{
								v.add(c);
								break;
							}
							sup = sup.getSuperclass();
						}
					} catch (ClassNotFoundException ee) {
						// TODO Auto-generated catch block
//						ee.printStackTrace();
						//JOptionPane.showMessageDialog(null, ee.getMessage());
						continue;
					}
				}
			}	
		}
		return v;
	}
	
	public static Vector<Class<?>> getClassesByInterfaceName(File fl, String interfaceName) throws IOException{
		Vector<Class<?>> v = new Vector<Class<?>>();
		List<File> en = getAllFile(fl);
		for(int i=0; i<en.size(); i++){
			File e = en.get(i);
			if(e.isDirectory()){
			}else{
				if(e.getName().endsWith(".java")){
					try {
						String cst = new String(e.getPath().replace(System.getProperty("user.dir"), "").replace("\\", ".").replace(".java", ""));
						String cs = cst.substring(1);
						Class<?> c = Utility.class.getClassLoader().loadClass(cs);
						Class<?>[] interfaceArray = c.getInterfaces();
						for(int k=0; k<interfaceArray.length; k++){
							Class<?> ifc = interfaceArray[k];
							if(ifc.getName().equals(interfaceName))
							{
								v.add(c);
								break;
							}
						}
					} catch (ClassNotFoundException ee) {
						// TODO Auto-generated catch block
//						ee.printStackTrace();
						//JOptionPane.showMessageDialog(null, ee.getMessage());
						continue;
					}
				}
			}	
		}
		return v;
	}

	private static List<File> getAllFile(File dir){
		List<File> l = new ArrayList<File>();
		File[] list = dir.listFiles();
		for(int i=0; i<list.length; i++)
		{
			File f = list[i];
			if(!f.isDirectory())
				l.add(f);
			else
				l.addAll(getAllFile(f));
		}
		return l;
	}

	public static void main(String[] args) throws IOException {
		Vector<Class<?>> v = getClassesBySuperClassName(new File(Costants.getPath()), "it.jenyus.model.GeneticAlgorithm");
		for(Class<?> c: v)
			System.out.println(c.getName());
	}
}
