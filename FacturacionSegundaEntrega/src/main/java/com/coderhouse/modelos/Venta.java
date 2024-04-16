package com.coderhouse.modelos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "venta")
public class Venta {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int numero_operacion;
	
	@Column (name = "forma_de_pago")
	private String metodoDePago;
	
	@Column (name = "fecha")
	private LocalDate fecha;
	
	
	@OneToMany(mappedBy = "venta")
	
	private List<Producto> productos;
	
	//ya que cliente tiene una relacion de 1 a n con ventas y ventas a productos 1 a n
	
	@ManyToOne
	@JoinColumn(name = "dni")
	private Cliente cliente;
	
	public Venta() {
		
	}

	public int getNumero_operacion() {
		return numero_operacion;
	}

	public void setNumero_operacion(int numero_operacion) {
		this.numero_operacion = numero_operacion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
	
}
