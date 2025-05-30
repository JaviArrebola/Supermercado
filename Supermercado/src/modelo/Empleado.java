package modelo;

public class Empleado {
	private int id;
    private String nombre;
    private String password;
    private String email;
    private int telefono;

    public Empleado(int id, String nombre, String password, String email, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", password=" + password + ", email=" + email
				+ ", telefono=" + telefono + "]";
	}
    
}