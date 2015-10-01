package it.jenyus.ui.console;

public class Command {

	private String name, description, shortName, method, parameter,defaultValue;

	/**
	 * @param name
	 * @param description
	 * @param shortName
	 * @param method
	 * @param parameter
	 */
	public Command(String name, String description, String shortName,
			String method) {
		super();
		this.name = name;
		this.description = description;
		this.shortName = shortName;
		this.method = method;
	}

	/**
	 * @param name
	 * @param description
	 * @param shortName
	 * @param method
	 * @param parameter
	 * @param defaultValue
	 */
	public Command(String name, String description, String shortName,
			String method, String parameter, String defaultValue) {
		super();
		this.name = name;
		this.description = description;
		this.shortName = shortName;
		this.method = method;
		this.parameter = parameter;
		this.defaultValue = defaultValue;
	}



	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public String getParameter() {
		return parameter;
	}
	
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(arg0 instanceof Command){
			return name.equals(((Command)arg0).getName());
		}else return false;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public final void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the method
	 */
	public final String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public final void setMethod(String method) {
		this.method = method;
	}
	
	
}
