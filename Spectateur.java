package packnp.cinema;

public class Spectateur {

	private String nom; 
	private int age;
	
	public Spectateur(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
	}

	public boolean estMajeur() {
		if (this.age >= 18) {
			return true;
		}
		else {return false;}
	}

	//equals:
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spectateur other = (Spectateur) obj;
		if (age != other.age)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Spectateur [nom=" + nom + ", age=" + age + "]";
	}

}
