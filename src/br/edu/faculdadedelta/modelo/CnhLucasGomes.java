package br.edu.faculdadedelta.modelo;

public class CnhLucasGomes {

	private long id;
	private String descCat;

	public CnhLucasGomes() {
		super();
	}

	public CnhLucasGomes(long id, String descCat) {
		super();
		this.id = id;
		this.descCat = descCat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescCat() {
		return descCat;
	}

	public void setDescCat(String descCat) {
		this.descCat = descCat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CnhLucasGomes other = (CnhLucasGomes) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
