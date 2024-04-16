package com.coderhouse.modelos;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@Column (unique = true, nullable = false)
	private int dni;
	
	@Column ( name = "nombre")
	private String nombre;
	
	@Column ( name = "apellido")
	private String apellido;
	
	@Column (name = "telefono")
	private int telefono;
	
	@Column (name= "email")
	private String email;
	
	@OneToMany(mappedBy = "cliente")
	
	private List<Venta> ventas;
	
	
	
	public Cliente() {
		
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, email, nombre, telefono, ventas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellido, other.apellido) && dni == other.dni && Objects.equals(email, other.email)
				&& Objects.equals(nombre, other.nombre) && telefono == other.telefono
				&& Objects.equals(ventas, other.ventas);
	}
	
	
}
