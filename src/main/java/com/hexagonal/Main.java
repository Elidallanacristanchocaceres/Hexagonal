package com.hexagonal;

import java.util.Scanner;

import com.hexagonal.application.usecase.client.ClientUseCase;
import com.hexagonal.domain.repository.ClientRespository;
import com.hexagonal.infrastructure.database.ConnectionFactory;
import com.hexagonal.domain.entity.Client; // Update this to the correct package

import com.hexagonal.infrastructure.persistence.client.ClientRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        ClientRespository repositorio = new ClientRepositoryImpl(ConnectionFactory.crearConexion());
        ClientUseCase clienteCasoUso = new ClientUseCase(repositorio);
        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            do {
                System.out.println("\n--- Menú de Clientes ---");
                System.out.println("1. Registrar Cliente");
                System.out.println("2. Buscar Cliente por ID");
                System.out.println("3. Listar Todos los Clientes");
                System.out.println("4. Actualizar Cliente");
                System.out.println("5. Eliminar Cliente");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese ID del Cliente: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consumir la nueva línea
                        System.out.print("Ingrese Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese Email: ");
                        String email = sc.nextLine();
                        clienteCasoUso.registrarCliente(id, nombre, email);
                        System.out.println("✅ Cliente registrado exitosamente.");
                        break;
                    case 2:
                        System.out.print("Ingrese ID del Cliente a buscar: ");
                        int idBuscar = sc.nextInt();
                        sc.nextLine(); // Consumir la nueva línea
                        Client cliente = clienteCasoUso.obtenerCliente(idBuscar);
                        if (cliente != null) {
                            System.out.println("Cliente encontrado: " + cliente.getName() + " - " + cliente.getEmail());
                        } else {
                            System.out.println("❌ Cliente no encontrado.");
                        }
                        break;
                    case 3:
                        System.out.println("Listado de Clientes:");
                        clienteCasoUso.listarClientes().forEach(c -> 
                            System.out.println(c.getId() + " - " + c.getName() + " - " + c.getEmail()));
                        break;
                    case 4:
                        System.out.print("Ingrese ID del Cliente a actualizar: ");
                        int idActualizar = sc.nextInt();
                        sc.nextLine(); // Consumir la nueva línea
                        System.out.print("Ingrese nuevo Nombre: ");
                        String nuevoNombre = sc.nextLine();
                        System.out.print("Ingrese nuevo Email: ");
                        String nuevoEmail = sc.nextLine();
                        clienteCasoUso.actualizarCliente(idActualizar, nuevoNombre, nuevoEmail);
                        System.out.println("✅ Cliente actualizado exitosamente.");
                        break;
                    case 5:
                        System.out.print("Ingrese ID del Cliente a eliminar: ");
                        int idEliminar = sc.nextInt();
                        sc.nextLine(); // Consumir la nueva línea
                        clienteCasoUso.eliminarCliente(idEliminar);
                        System.out.println("✅ Cliente eliminado exitosamente.");
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (opcion != 0);
        }
    }
}