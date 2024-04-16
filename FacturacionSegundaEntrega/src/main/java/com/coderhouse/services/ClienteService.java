package com.coderhouse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Cliente;
import com.coderhouse.modelos.Producto;
import com.coderhouse.repository.ClienteRepository;
import com.coderhouse.repository.ProductoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll(); //no hay que hacer ninguna logica porque es solo trae a todos
	}
	
	public Cliente mostrarClientePorId(Integer dni) {
		return clienteRepository.findById(dni).orElse(null);
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarCliente(Integer dni, Cliente cliente) {
		try {
			if(clienteRepository.existsById(dni)) {
				cliente.setDni(dni);
				return clienteRepository.save(cliente);
			}
		} catch (EmptyResultDataAccessException e) {
			return null;

		}
		return null;
	}
	
	
	public boolean eliminarClientePorDni(Integer dni) { 
		try {
			
			clienteRepository.deleteById(dni);;
			return true;
			
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
		
	}
	
}
