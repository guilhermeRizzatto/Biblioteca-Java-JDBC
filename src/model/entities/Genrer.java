package model.entities;

import java.util.Objects;

public class Genrer {
	
	private String name_genrer;
	
	public Genrer() {
	}

	public Genrer(String name_genrer) {
		this.name_genrer = name_genrer;
	}

	public String getName_genrer() {
		return name_genrer;
	}

	public void setName_genrer(String name_genrer) {
		this.name_genrer = name_genrer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name_genrer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genrer other = (Genrer) obj;
		return Objects.equals(name_genrer, other.name_genrer);
	}

	@Override
	public String toString() {
		return "Genrer [name_genrer=" + name_genrer + "]";
	}
	
	
	
	

}
