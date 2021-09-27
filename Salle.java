package packnp.cinema;

import java.util.LinkedList;

public class Salle {

	private LinkedList<Spectateur> spectateurs;
	private String nom;
	private int capacite;
	
	public Salle(String nom, int capacite) {
		super();
	
		this.nom = nom;
		this.capacite = capacite;
	}

	public boolean estPleine() {
		if (spectateurs.size()==this.capacite) {
			return true;
		}
		else {return false;}
	}

	public boolean placer(Spectateur s1) {
		if (estPleine()==true) {return false;
		}
		else {
			spectateurs.add(s1);
			return true;
		}
	}
	
	public boolean estPresent (Spectateur s1) {
		return spectateurs.contains(s1);
	}

	@Override
	public String toString() {
		return "Salle [spectateurs=" + this.nom + "composée de"+ spectateurs.size()+"spectateurs]";
	}
	
}
