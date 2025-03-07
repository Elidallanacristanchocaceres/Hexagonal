package com.hexagonal.application.usecase.client;

import java.util.List;

import com.hexagonal.domain.entity.Client;
import com.hexagonal.domain.repository.ClientRespository;

public class ClientUseCase {
    private final ClientRespository repository;

    public ClientUseCase(ClientRespository repository) {
        this.repository = repository;
    }

    public void registrarCliente(int id, String nombre, String email) {
        Client cliente = new Client(id, nombre, email);
        repository.guardar(cliente);
    }

    public Client obtenerCliente(int id) {
        return repository.buscarPorId(id);
    }

    public List<Client> listarClientes() {
        return repository.listarTodos();
    }

    public void actualizarCliente(int id, String nombre, String email) {
        Client cliente = new Client(id, nombre, email);
        repository.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        repository.eliminar(id);
    }
}