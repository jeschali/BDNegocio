
import java.util.Scanner;

public class MenuProductos {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("**********************************");
            System.out.println("          Menu principal          ");
            System.out.println("**********************************");
            System.out.println("1.....Ingresar producto");
            System.out.println("2.....Mostrar productos");
            System.out.println("3.....Buscar producto");
            System.out.println("4.....Modificar producto");
            System.out.println("5.....Eliminar producto");
            System.out.println("6.....Salir del menu principal");
            System.out.print("Seleccione una opcion del menu: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese el codigo del producto: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el precio del producto: ");
                    double precio = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la fecha de vencimiento (YYYY-MM-DD): ");
                    String fecha = scanner.nextLine();

                    ConexionBD.insertarProducto(codigo, nombre, precio, cantidad, fecha);
                    break;

                case 2:
                    ConexionBD.listarProductos();
                    break;

                case 3:
                    System.out.print("\nIngrese el codigo del producto a buscar: ");
                    String codigoBuscar = scanner.nextLine();

                    ConexionBD.buscarProducto(codigoBuscar);
                    break;

                case 4:
                    System.out.print("Ingrese el codigo del producto a modificar: ");
                    String codigoMod = scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del producto: ");
                    String nombreMod = scanner.nextLine();
                    System.out.print("Ingrese el nuevo precio del producto: ");
                    double precioMod = scanner.nextDouble();
                    System.out.print("Ingrese la nueva cantidad del producto: ");
                    int cantidadMod = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la nueva fecha de vencimiento (YYYY-MM-DD): ");
                    String fechaMod = scanner.nextLine();

                    ConexionBD.actualizarProducto(codigoMod, nombreMod, precioMod, cantidadMod, fechaMod);
                    break;

                case 5:
                    System.out.print("Ingrese el codigo del producto a eliminar: ");
                    String codigoEliminar = scanner.nextLine();
                    ConexionBD.eliminarProducto(codigoEliminar);
                    break;

                case 6:
                    System.out.println("Saliendo del menu principal...");
                    break;

                default:
                    System.out.println("Opción no valida. Intente de nuevo.");
            }
        } while (opcion != 6);

        scanner.close();
    }
}
