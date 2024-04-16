package com.coderhouse.controller;

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
import com.coderhouse.modelos.Venta;
import com.coderhouse.repository.VentaRepository;
import com.coderhouse.services.VentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	
	@Autowired
	VentaService ventaService;
	
	@GetMapping(value = "/{id}", produces= {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<Venta>> listarVentas() {
		
		try {
			
			List<Venta> ventas = ventaService.listaDeVentas(); //invoco el metodo del service
			return new ResponseEntity<>(ventas, HttpStatus.OK); //devuelve la lista + un OK, cod 200
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //arroja error de servidor 500
		}
	}

	
	@GetMapping(value= "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Venta> listarVentaPorCodigo(@PathVariable("numero_operacion") Integer numero_operacion) {
		
		try {
			
			Venta venta = ventaService.listarPorCodigo(numero_operacion); 
			if (venta != null) {
				return new ResponseEntity<>(venta, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(venta, HttpStatus.NOT_FOUND); //error 404
			}
			
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	
	}
	
	
	@PostMapping ( value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Venta> agregarUnaVenta(@RequestBody Venta venta) {
		
		try {
			
			ventaService.agregarUnaVenta(venta);
			return new ResponseEntity<> (venta, HttpStatus.CREATED); //creado codigo 201
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	}
	
	
	
	
	@PutMapping(value = "/{numero_operacion}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Producto> editarVentaPorId(@PathVariable ("numero_operacion") Integer numero_operacion, @RequestBody Venta venta) {
		
		Venta ventaEditada = ventaService.editarVentaPorId(numero_operacion, venta);
		if( ventaEditada != null) {
			return new ResponseEntity<>(HttpStatus.OK); //codigo 200
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //codigo 404
		}
		
	}
	
	@DeleteMapping (value = "{numero_operacion}/eliminar") //no devuelve nada asi que va void
	public ResponseEntity<Void> eliminarVentaPorId(@PathVariable ("numero_operacion") Integer numero_operacion) {
		boolean eliminado = ventaService.eliminarVentaPorId(numero_operacion);
		if ( eliminado) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT); //todo ok, codigo 204
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND); //error 404
		}
	}

}
