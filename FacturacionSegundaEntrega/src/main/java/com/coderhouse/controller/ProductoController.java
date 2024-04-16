package com.coderhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.modelos.Producto;
import com.coderhouse.repository.ProductoRepository;
import com.coderhouse.services.ProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@GetMapping(value = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<Producto>> listarProductos() {
		
		try {
			
			List<Producto> productos = productoService.listarProductos(); //invoco el metodo del service
			return new ResponseEntity<>(productos, HttpStatus.OK); //devuelve la lista + un OK, cod 200
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //arroja error de servidor 500
		}
	}

	
	@GetMapping(value= "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Producto> mostrarProductoPorId(@PathVariable("id") Integer id) {
		
		try {
			
			Producto producto = productoService.mostrarProductoPorID(id); 
			if (producto != null) {
				return new ResponseEntity<>(producto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(producto, HttpStatus.NOT_FOUND); //error 404
			}
			
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	
	}
	
	
	@PostMapping ( value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		
		try {
			
			productoService.agregarProducto(producto);
			return new ResponseEntity<> (producto, HttpStatus.CREATED); //creado codigo 201
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	}
	
	
	@GetMapping (value = "/{id}/eliminar", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable ("id") Integer id){
		
		try {
			productoService.eliminarProductoPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
			
		} catch (EmptyResultDataAccessException e) { //cuando no lo pongo un valor correcto
			return new ResponseEntity<> (HttpStatus.INTERNAL_SERVER_ERROR); //error 500
		}
		
	}
	
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> editarProducto(@PathVariable ("id") Integer id, @RequestBody Producto producto) {
		
		Producto productoEditado = productoService.editarProducto(id, producto);
		if( productoEditado != null) {
			return new ResponseEntity<>(HttpStatus.OK); //codigo 200
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //codigo 404
		}
		
	}
	
	@DeleteMapping (value = "{id}/eliminar") //no devuelve nada asi que va void
	public ResponseEntity<Void> eliminarProducto(@PathVariable ("id") Integer id) {
		boolean eliminado = productoService.eliminarProductoPorId(id);
		if ( eliminado) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT); //todo ok, codigo 204
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND); //error 404
		}
	}
}
