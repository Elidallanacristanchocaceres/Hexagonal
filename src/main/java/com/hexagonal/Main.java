package com.hexagonal;

import java.util.Scanner;

import com.hexagonal.application.usecase.client.ClientUseCase;
import com.hexagonal.domain.repository.ClientRespository;
import com.hexagonal.infrastructure.database.ConnectionFactory;
import com.hexagonal.infrastructure.persistence.client.ClientRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        ClientRespository repositorio = new ClientRepositoryImpl(ConnectionFactory.crearConexion());
        ClientUseCase clienteCasoUso = new ClientUseCase(repositorio);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrese ID del Cliente: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea
            System.out.print("Ingrese Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Ingrese Email: ");
            String email = sc.nextLine();
            clienteCasoUso.registrarCliente(id, nombre, email);
            System.out.println("✅ Cliente registrado exitosamente.");        
        }
    }
}