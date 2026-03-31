package cl.example.dtos;

public class CrearReservaDTO {

    private String nombreCliente;
    private String apellidoCliente;
    private String rutCliente;
    private Integer idVuelo;
    private int numeroAsiento;
    private String estadoVuelo;
    private String seccionCliente;

    public CrearReservaDTO() {
    }

    public CrearReservaDTO(String nombreCliente, String apellidoCliente, String rutCliente, Integer idVuelo, int numeroAsiento, String estadoVuelo, String seccionCliente) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.rutCliente = rutCliente;
        this.idVuelo = idVuelo;
        this.numeroAsiento = numeroAsiento;
        this.estadoVuelo = estadoVuelo;
        this.seccionCliente = seccionCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getRutCliente() {
        return rutCliente;
    }

    public void setRutCliente(String rutCliente) {
        this.rutCliente = rutCliente;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(String estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }

    public String getSeccionCliente() {
        return seccionCliente;
    }

    public void setSeccionCliente(String seccionCliente) {
        this.seccionCliente = seccionCliente;
    }

    @Override
    public String toString() {
        return "CrearReservaDTO{" +
            "nombreCliente='" + nombreCliente + '\'' +
            ", apellidoCliente='" + apellidoCliente + '\'' +
            ", rutCliente='" + rutCliente + '\'' +
            ", idVuelo=" + idVuelo +
            ", numeroAsiento=" + numeroAsiento +
            ", estadoVuelo='" + estadoVuelo + '\'' +
            ", seccionCliente='" + seccionCliente + '\'' +
            '}';
    }
}
