package com.hexagonal;

import java.util.Scanner;

import com.hexagonal.application.usecase.client.ClientUseCase;
import com.hexagonal.application.usecase.product.ProductUseCase;
import com.hexagonal.domain.repository.ClientRespository;
import com.hexagonal.domain.repository.ProductRepository;
import com.hexagonal.infrastructure.database.ConnectionFactory;
import com.hexagonal.domain.entity.Client;
import com.hexagonal.domain.entity.Product;
import com.hexagonal.infrastructure.persistence.client.ClientRepositoryImpl;
import com.hexagonal.infrastructure.persistence.product.ProductRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        ClientRespository clienteRepositorio = new ClientRepositoryImpl(ConnectionFactory.crearConexion());
        ProductRepository productoRepositorio = new ProductRepositoryImpl(ConnectionFactory.crearConexion());
        
        ClientUseCase clienteCasoUso = new ClientUseCase(clienteRepositorio);
        ProductUseCase productoCasoUso = new ProductUseCase(productoRepositorio);

        try (Scanner sc = new Scanner(System.in)) {
            int opcionPrincipal;
            do {
                System.out.println("\n--- Menú Principal ---");
                System.out.println("1. Clientes");
                System.out.println("2. Productos");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                opcionPrincipal = sc.nextInt();
                sc.nextLine(); 

                switch (opcionPrincipal) {
                    case 1:
                        menuClientes(sc, clienteCasoUso);
                        break;
                    case 2:
                        menuProductos(sc, productoCasoUso);
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("❌ Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (opcionPrincipal != 0);
        } catch(Exception e){System.out.println("Error al poner signos");}
    }

    private static void menuClientes(Scanner sc, ClientUseCase clienteCasoUso) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Clientes ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Buscar Cliente por ID");
            System.out.println("3. Listar Todos los Clientes");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Eliminar Cliente");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del Cliente: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Ingrese Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese Email: ");
                    String email = sc.nextLine();
                    if (email.contains("@gmail.com")) {
                        clienteCasoUso.registrarCliente(id, nombre, email);
                        System.out.println("✅ Cliente registrado exitosamente.");
                    }else {
                        System.out.println("El correo debe llevar un '@gmail.com' ");
                    }
                   
                    break;

                case 2:
                    System.out.print("Ingrese ID del Cliente a buscar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine(); 
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
                    System.out.print("Ingrese el ID del cliente que deseas actualizar: ");
                    int idNuevo = sc.nextInt();
                    sc.nextLine();

                    if (clienteCasoUso.obtenerCliente(idNuevo) != null) {
                        System.out.print("Ingrese el nuevo nombre para el cliente: ");
                        String NombreNuevo = sc.nextLine();

                        String EmailNuevo;
                        do {
                            System.out.print("Ingrese el nuevo email del cliente: ");
                            EmailNuevo = sc.nextLine();
                            if (!EmailNuevo.contains("@gmail.com")) {
                                System.out.println(
                                        "El correo debe llevar un '@gmail.com' ");
                            }
                        } while (!EmailNuevo.contains("@"));

                        System.out.println("");

                        clienteCasoUso.actualizarCliente(idNuevo, NombreNuevo, EmailNuevo);
                        System.out.println("Cliente actualizado correctamente:");
                        clienteCasoUso.obtenerCliente(idNuevo);
                    } else {
                        System.out.println("El cliente con ID " + idNuevo + " no existe.");
                    }
                    break;
                        
                case 5:
                    System.out.print("Ingrese ID del Cliente a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine(); 
                    // Verificar si el cliente existe antes de eliminar
                    Client clienteAEliminar = clienteCasoUso.obtenerCliente(idEliminar);
                    if (clienteAEliminar != null) {
                        clienteCasoUso.eliminarCliente(idEliminar);
                        System.out.println("✅ Cliente eliminado exitosamente.");
                    } else {
                        System.out.println("❌ Cliente no encontrado. No se puede eliminar.");
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void menuProductos(Scanner sc, ProductUseCase productoCasoUso) {
        int opcion;
        do {
            System.out.println("\n--- Menú de Productos ---");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Buscar Producto por ID");
            System.out.println("3. Listar Todos los Productos");
            System.out.println("4. Actualizar Producto");
            System.out.println("5. Eliminar Producto");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ID del Producto: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Ingrese Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese Stock: ");
                    int Stock = sc.nextInt();
                    sc.nextLine(); 
                    productoCasoUso.registrarproducto(id, nombre, Stock);
                    System.out.println("✅ Producto registrado exitosamente.");
                    break;

                case 2:
                    System.out.print("Ingrese ID del Producto a buscar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine(); 
                    Product producto = productoCasoUso.obtenerproducto(idBuscar);
                    if (producto != null) {
                        System.out.println("Producto encontrado: " + producto.getName() + " - " + producto.getStock());
                    } else {
                        System.out.println("❌ Producto no encontrado.");
                    }
                    break;

                case 3:
                    System.out.println("Listado de Productos:");
                    productoCasoUso.listarproductos().forEach(p -> 
                        System.out.println(p.getId() + " - " + p.getName() + " - " + p.getStock()));
                    break;

                    case 4:
                    System.out.print("Ingrese el ID del producto que deseas actualizar: ");
                    int idNuevoProducto = sc.nextInt();
                    sc.nextLine();

                    if (productoCasoUso.obtenerproducto(idNuevoProducto) != null) {
                        System.out.print("Ingresa el nuevo nombre del producto: ");
                        String NombreProducto = sc.nextLine();
                        System.out.print("Ingresa el nuevo stock del producto: ");
                        int StockNuevo = sc.nextInt();
                        sc.nextLine();
                        System.out.println("");

                        System.out.println("Información anterior: ");
                        productoCasoUso.obtenerproducto(idNuevoProducto);

                        productoCasoUso.actualizarproducto(idNuevoProducto, NombreProducto, StockNuevo);
                        System.out.println("");

                        System.out.println("Información actualizada: ");
                        productoCasoUso.obtenerproducto(idNuevoProducto);
                        System.out.println("");
                    } else {
                        System.out.println("El producto con ID " + idNuevoProducto + " no existe.");
                    }
                    
                    break;

                case 5:
                    System.out.print("Ingrese ID del Producto a eliminar: ");
                    int idEliminar = sc.nextInt();
                    sc.nextLine(); 
                    // Verificar si el producto existe antes de eliminar
                    Product productoAEliminar = productoCasoUso.obtenerproducto(idEliminar);
                    if (productoAEliminar != null) {
                        productoCasoUso.eliminarproducto(idEliminar);
                        System.out.println("✅ Producto eliminado exitosamente.");
                    } else {
                        System.out.println("❌ Producto no encontrado. No se puede eliminar.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }
}