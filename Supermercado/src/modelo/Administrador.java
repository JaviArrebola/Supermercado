
package modelo;

public class Administrador {
	private int id;

	public Administrador(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Administrador [id=" + id + "]";
	}
	
}
