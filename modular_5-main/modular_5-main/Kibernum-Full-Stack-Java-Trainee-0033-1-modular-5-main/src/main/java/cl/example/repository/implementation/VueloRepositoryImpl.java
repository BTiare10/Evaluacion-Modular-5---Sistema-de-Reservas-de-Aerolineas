package cl.example.repository.implementation;

import cl.example.configuration.DatabaseConnection;
import cl.example.model.Vuelo;
import cl.example.repository.VueloRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VueloRepositoryImpl implements VueloRepository {

    private final DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

    @Override
    public List<Vuelo> obtenerVuelos() {

        String query = "SELECT * FROM vuelo";
        List<Vuelo> vuelos = new ArrayList<>();

        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(resultSet.getInt("id_vuelo"));
                vuelo.setCiudadDespegue(resultSet.getString("ciudad_despegue"));
                vuelo.setCiudadAterrizaje(resultSet.getString("ciudad_aterrizaje"));
                vuelo.setFechaSalida(resultSet.getDate("fecha_salida"));
                vuelo.setFechaLlegada(resultSet.getDate("fecha_llegada"));
                vuelo.setHoraAbordaje(resultSet.getTime("hora_abordaje").toLocalTime());
                vuelo.setHoraSalida(resultSet.getTime("hora_salida").toLocalTime());
                vuelo.setHoraLlegada(resultSet.getTime("hora_llegada").toLocalTime());
                vuelos.add(vuelo);
            }
            return vuelos;

        } catch (SQLException e ) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Vuelo obtenerVueloPorId(Integer idVuelo) {
        String query = "SELECT * FROM vuelo WHERE id_vuelo = ?";

        try (
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ) {
            preparedStatement.setInt(1, idVuelo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Vuelo vuelo = new Vuelo();
                vuelo.setIdVuelo(resultSet.getInt("id_vuelo"));
                vuelo.setCiudadDespegue(resultSet.getString("ciudad_despegue"));
                vuelo.setCiudadAterrizaje(resultSet.getString("ciudad_aterrizaje"));
                vuelo.setFechaSalida(resultSet.getDate("fecha_salida"));
                vuelo.setFechaLlegada(resultSet.getDate("fecha_llegada"));
                vuelo.setHoraAbordaje(resultSet.getTime("hora_abordaje").toLocalTime());
                vuelo.setHoraSalida(resultSet.getTime("hora_salida").toLocalTime());
                vuelo.setHoraLlegada(resultSet.getTime("hora_llegada").toLocalTime());
                return vuelo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
