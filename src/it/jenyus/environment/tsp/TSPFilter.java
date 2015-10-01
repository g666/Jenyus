package it.jenyus.environment.tsp;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import it.jenyus.model.Filter;


public class TSPFilter implements Filter<TSPChromosome, Character> {

	private Character ori, dest;
	private Set<Character> allele;

	/**
	 * @param ori
	 * @param dest
	 */
	public TSPFilter(Character ori, Character dest) {
		super();
		this.ori = ori;
		this.dest = dest;
	}

	@Override
	public boolean isAdmissible(TSPChromosome c) {
	
		for(int i=1; i<c.getGenes().length-1; i++)
			if(contains(c.getGenes()[i], c.getGenes())>1)
				return false;
//		if(c.getGenes()[0].equals(c.getGenes()[c.getGenes().length-1]))
//			return true;
		if(c.getGenes()[0].equals(ori) && c.getGenes()[c.getGenes().length-1].equals(dest))
			return true;
		else
			return false;
	}

	@Override
	public void makeAdmissible(TSPChromosome c) {
		// TODO Auto-generated method stub
		if(!c.getGenes()[0].equals(ori)){
			c.getGenes()[0] = ori;
		}
		if(!c.getGenes()[c.getGenes().length-1].equals(dest)){
			c.getGenes()[c.getGenes().length-1] = dest;
		}
		Character[] g = c.getGenes();
		for(int i=1; i<g.length-1; i++)
			if(contains(g[i], g)>1){
				Iterator<Character> it = allele.iterator();
				while(it.hasNext()){
					Character o = it.next();
					if(!g[i].equals(o) && contains(o, g)<1){
						g[i]=o;
						break;
					}
				}
			}
		c.setGenes(g);
	}

	private int contains(Character c, Character[] cc){
		int n=0;
		for(int i=0; i<cc.length; i++)
			if(cc[i]==c)
				n++;
		return n;
	}

	@Override
	public void setOfAllele(Set<Character> set) {
		// TODO Auto-generated method stub
		this.allele = set;
	}

	@Override
	public Set<Character> setOfAllele() {
		// TODO Auto-generated method stub
		return allele;
	}

}
