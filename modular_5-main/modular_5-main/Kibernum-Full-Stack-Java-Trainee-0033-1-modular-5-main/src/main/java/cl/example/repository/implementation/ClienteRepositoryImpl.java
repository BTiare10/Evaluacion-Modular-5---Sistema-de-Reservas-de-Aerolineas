package cl.example.repository.implementation;

import cl.example.configuration.DatabaseConnection;
import cl.example.model.Cliente;
import cl.example.repository.ClienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    @Override
    public Integer crearCliente(Cliente cliente) {
        String query = "INSERT INTO cliente (nombre, apellido, rut) VALUES (?, ?, ?)";

        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getRut());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Cliente> obtenerClientes() {
        String query = "SELECT id_cliente, nombre, apellido, rut FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setidCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setRut(resultSet.getString("rut"));
                clientes.add(cliente);
            }
            return clientes;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cliente obtenerClientePorId(Integer idCliente) {
        String query = "SELECT id_cliente, nombre, apellido, rut FROM cliente WHERE id_cliente = ?";

        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
            preparedStatement.setInt(1, idCliente);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setidCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setRut(resultSet.getString("rut"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Cliente obtenerClientePorRut(String rutCliente) {
        String query = "SELECT id_cliente, nombre, apellido, rut FROM cliente WHERE rut = ?";
        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {

            preparedStatement.setString(1, rutCliente);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setidCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setRut(resultSet.getString("rut"));
                return cliente;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
