package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Producto;
import com.coderhouse.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> listarProductos() {
		return productoRepository.findAll(); //no hay que hacer ninguna logica porque es solo trae a todos
	}
	
	public Producto mostrarProductoPorID(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}
	
	public Producto agregarProducto(Producto producto) {
		return productoRepository.save(producto);
	}
	
	public Producto editarProducto(Integer id, Producto producto) {
		try {
			if(productoRepository.existsById(id)) {
				producto.setId(id);
				return productoRepository.save(producto);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;

		}
		return null;
	}
	
	
	public boolean eliminarProductoPorId(Integer id) { //los delete devuelven false o true
		try {
			
			productoRepository.deleteById(id);
			return true;
			
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		
	}
}
