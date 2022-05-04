package org.uma.jmetal.problem.toptrumps.entities;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Card {
	
	
	private Map<String,Double> values;
	
	private String description;
	
	private String image;
	
	private String name;
	
	
	
	public Card(List<String> names,List<Double> values) {
		this(buildMap(names,values));
	}
	
	private static Map<String, Double> buildMap(List<String> names, List<Double> values2) {
		Map<String,Double> result=new HashMap<String,Double>();
		for(int i=0;i<names.size();i++) {
			result.put(names.get(i), values2.get(i));
		}
		return result;
	}

	public Card(Map<String,Double> values){
		this(values,null,null,null);
	}
	
	

	public Card(Map<String, Double> values, String description, String image, String name) {
		super();
		this.values = values;
		this.description = description;
		this.image = image;
		this.name = name;
	}


	public Map<String,Double> getValues() {
		return values;
	}
	public void setValues(Map<String,Double> values) {
		this.values = values;
	}	
	public Double getValue(String category) {
		return values.get(category);
	}
	
	public boolean isCategory(String category) {
		return values.containsKey(category);
	}
	
	public Set<String> getCategories(){
		return values.keySet();
	}
	
	public int getNCategories() {
		return values.size();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
