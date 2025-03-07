package com.hexagonal.domain.repository;

import java.util.List;

import com.hexagonal.domain.entity.Client;

public interface ClientRespository {
    void guardar(Client cliente);
    Client buscarPorId(int id);
    List<Client> listarTodos();
    void actualizar(Client cliente);
    void eliminar(int id);
}