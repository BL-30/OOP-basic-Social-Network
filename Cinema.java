package packnp.cinema;

import java.util.LinkedList;

public class Cinema {

	private String nom;
	private LinkedList<Salle> salles;
	
	public Cinema(String nom, LinkedList<Salle> salles) {
		super();
		this.nom = nom;
		this.salles = salles;
	}

	public boolean placer(Spectateur s1) {
		for (int i=0; i<salles.size();i++) {
			if (salles.get(i).placer(s1)) {
				return true;}}
	
		return false;
			
		}
	
	public boolean estPresent(Spectateur s1) {
		for (int i=0; i<salles.size() ; i++) {
			if(salles.get(i).estPresent(s1)) {
				return true;
			}}
		
		return false;}

public String toString() {
		
		String s = "";
		for (int i=0; i<salles.size() ; i++) {
			s+= salles.get(i).toString();
		}
		
		return "nom=" + nom + "infos cinémas" + s;
			
	}

}
