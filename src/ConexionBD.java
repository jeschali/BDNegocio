
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/DBArteMarcos";
    private static final String USER = "root";
    private static final String PASSWORD = "12345jes";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("\nConexion exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("\nError al conectar: " + e.getMessage());
        }
        return conexion;
    }// fin conectar()

    public static void insertarProducto(String codigo, String nombre, double precio, int cantidad, String fecha) {
        String query = "INSERT INTO producto (codigoProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?,?, ?, ?, ?)";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setDouble(3, precio);
            pst.setInt(4, cantidad);
            pst.setDate(5, java.sql.Date.valueOf(fecha));
            pst.executeUpdate();
            System.out.println("\nProducto insertado correctamente");
        } catch (SQLException e) {
        }
    }// fin insertarProducto()

    public static void listarProductos() {
        String query = "select * from producto;";
        try (Connection con = ConexionBD.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                System.out.println("Codigo: " + rs.getString("codigoProducto"));
                System.out.println("Nombre: " + rs.getString("nombreProducto"));
                System.out.println("Precio: " + rs.getDouble("precioUnitario"));
                System.out.println("Cantidad: " + rs.getInt("cantidadProducto"));
                System.out.println("Fecha de Vencimiento: " + rs.getDate("fechaVencimiento"));
                System.out.println("");
            }
            if (!hayResultados) {
                System.out.println("\nNo hay productos disponibles.");
            }//fin if

        } catch (SQLException e) {

        }//fin catch
    }// fin listarProductos()

    public static void buscarProducto(String codigoProducto) {
        String query = "SELECT * FROM producto WHERE codigoProducto = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Si se encuentra el producto, mostramos su información
                System.out.println("\nProducto encontrado:");
                System.out.println("Codigo: " + rs.getString("codigoProducto"));
                System.out.println("Nombre: " + rs.getString("nombreProducto"));
                System.out.println("Precio: " + rs.getDouble("precioUnitario"));
                System.out.println("Cantidad: " + rs.getInt("cantidadProducto"));
                System.out.println("Fecha de Vencimiento: " + rs.getDate("fechaVencimiento"));
                System.out.println("");
            } else {
                // Si no se encuentra el producto, mostramos un mensaje
                System.out.println("\nNo se encontró ningún producto con el código: " + codigoProducto);
            }

        } catch (SQLException e) {
            System.out.println("\nError al buscar el producto: " + e.getMessage());
        }
    }

    public static void actualizarProducto(String codigoProducto, String nombre, double precio, int cantidadProducto, String fechaVencimiento) {
        String query = "UPDATE producto SET nombreProducto = ?, precioUnitario = ?, cantidadProducto = ?, fechaVencimiento = ? WHERE codigoProducto = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, nombre);
            pst.setDouble(2, precio);
            pst.setInt(3, cantidadProducto);
            pst.setDate(4, java.sql.Date.valueOf(fechaVencimiento));
            pst.setString(5, codigoProducto);
            pst.executeUpdate();
            System.out.println("\nProducto actualizado correctamente");
        } catch (SQLException e) {
            System.out.println("\nError al actualizar el producto: " + e.getMessage());
        }
    }

    public static void eliminarProducto(String codigoProducto) {
        String query = "DELETE FROM producto WHERE codigoProducto = ?";
        try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, codigoProducto);
            pst.executeUpdate();
            System.out.println("\nProducto eliminado correctamente");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }// fin eliminarProducto

    public static void main(String[] args) {
        //conectar();
        //insertarProducto("VRG100", "Virogrip", 50, 150, "2024-10-31");
        listarProductos();
        //actualizarProducto("VRG100","Virogrip funcional",150.99);
        //eliminarProducto("VRG100");
        //listarProductos();     
    }
}// fin main

