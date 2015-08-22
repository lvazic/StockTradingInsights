package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Feature {
	
	private int redniBroj;
	private List<Entry<String, Double>> akcije;
	private List<Entry<String, Double>> datumi;
	
	
	
	
	public Feature() {
		akcije = new ArrayList<Entry<String, Double>>();
		datumi = new ArrayList<Entry<String, Double>>();
	}


	public Feature(int redniBroj, List<Entry<String, Double>> akcije, List<Entry<String, Double>> datumi) {
		super();
		this.redniBroj = redniBroj;
		this.akcije = akcije;
		this.datumi = datumi;
	}


	public int getRedniBroj() {
		return redniBroj;
	}


	public void setRedniBroj(int redniBroj) {
		this.redniBroj = redniBroj;
	}


	public List<Entry<String, Double>> getAkcije() {
		return akcije;
	}


	public void setAkcije(List<Entry<String, Double>> akcije) {
		this.akcije = akcije;
		
	}


	public List<Entry<String, Double>> getDatumi() {
		return datumi;
	}


	public void setDatumi(List<Entry<String, Double>> datumi) {
		this.datumi = datumi;
	}
	
	
	public void sortiraj(String tip) {
		
		if (tip.equalsIgnoreCase("akcije")) sortiraj(akcije);
		if (tip.equalsIgnoreCase("datumi")) sortiraj(datumi);
	}
	
	private void sortiraj(List<Entry<String, Double>> lista) {
	
		
		Collections.sort(lista, new Comparator<Entry<String, Double>>() {
		    public int compare(Entry<String, Double> e1, Entry<String, Double> e2) {
		        return e2.getValue().compareTo(e1.getValue());
		    }
		});

		Map<String, Double> orderedMap = new LinkedHashMap<String, Double>();
		for (Entry<String, Double> entry : lista) {
		    orderedMap.put(entry.getKey(), entry.getValue());
		    
		}
		
		lista = new ArrayList<Entry<String, Double>>(orderedMap.entrySet());
	}
	
	
	

}
