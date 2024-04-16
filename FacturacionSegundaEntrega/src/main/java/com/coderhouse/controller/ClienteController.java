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

import com.coderhouse.modelos.Cliente;
import com.coderhouse.modelos.Producto;
import com.coderhouse.services.ClienteService;
import com.coderhouse.services.ProductoService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{dni}", produces= {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<List<Cliente>> listarClientes() {
		
		try {
			
			List<Cliente> cliente = clienteService.listarClientes(); //invoco el metodo del service
			return new ResponseEntity<>(cliente, HttpStatus.OK); //devuelve la lista + un OK, cod 200
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //arroja error de servidor 500
		}
	}

	
	@GetMapping(value= "/{dni}", produces = { MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<Cliente> mostrarCientePorDni(@PathVariable("dni") Integer dni) {
		
		try {
			
			Cliente cliente = clienteService.mostrarClientePorId(dni); 
			if (cliente != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND); //error 404
			}
			
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	
	}
	
	
	@PostMapping ( value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
		
		try {
			
			clienteService.agregarCliente(cliente);
			return new ResponseEntity<> (cliente, HttpStatus.CREATED); //creado codigo 201
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //error de servidor 500
		}
	}
	
	
	
	
	@PutMapping(value = "/{dni}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> editarProducto(@PathVariable ("dni") Integer dni, @RequestBody Cliente cliente) {
		
		Cliente clienteEditado = clienteService.editarCliente(dni, cliente);
		if( clienteEditado != null) {
			return new ResponseEntity<>(HttpStatus.OK); //codigo 200
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); //codigo 404
		}
		
	}
	
	@DeleteMapping (value = "{dni}/eliminar") //no devuelve nada asi que va void
	public ResponseEntity<Void> eliminarCliente(@PathVariable ("dni") Integer dni) {
		boolean eliminado = clienteService.eliminarClientePorDni(dni);
		if ( eliminado) {
			return new ResponseEntity<> (HttpStatus.NO_CONTENT); //todo ok, codigo 204
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND); //error 404
		}
	}
	
}
