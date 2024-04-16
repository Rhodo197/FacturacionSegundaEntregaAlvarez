package com.coderhouse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Venta;
import com.coderhouse.repository.VentaRepository;

@Service
public class VentaService {

	@Autowired
	VentaRepository ventaRepository;
	
	public List<Venta> listaDeVentas() {
		
		return ventaRepository.findAll();
		
	}
	
	public Venta agregarUnaVenta(Venta venta) {
		return ventaRepository.save(venta);
	}
	
	public Venta listarPorCodigo(Integer numero_operacion) {
		
		return ventaRepository.findById(numero_operacion).orElse(null);
	}
	
	public Venta editarVentaPorId(Integer numero_operacion, Venta venta) {
		
		try {
			if (ventaRepository.existsById(numero_operacion)) {
				venta.setNumero_operacion(numero_operacion);
				return ventaRepository.save(venta);
			} 
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public boolean eliminarVentaPorId(Integer numero_operacion) {
		try {
			ventaRepository.deleteById(numero_operacion);
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
}
