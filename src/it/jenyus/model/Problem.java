package it.jenyus.model;

public abstract class Problem {

	protected GAConfiguration conf;
	
	/**
	 * @param conf
	 */
	public Problem(GAConfiguration conf) {
		super();
		this.conf = conf;
	}

	public abstract void init();
}
