package cl.example.model;

public class Reserva {

    private Integer idReserva;
    private Integer idCliente;
    private Integer idVuelo;
    private int numeroAsiento;
    private EstadoVuelo estadoVuelo;
    private SeccionCliente seccionCliente;

    public Reserva() {
    }

    public Reserva(Integer idReserva, Integer idCliente, Integer idVuelo, int numeroAsiento, EstadoVuelo estadoVuelo, SeccionCliente seccionCliente) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idVuelo = idVuelo;
        this.numeroAsiento = numeroAsiento;
        this.estadoVuelo = estadoVuelo;
        this.seccionCliente = seccionCliente;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public EstadoVuelo getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(EstadoVuelo estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }

    public SeccionCliente getSeccionCliente() {
        return seccionCliente;
    }

    public void setSeccionCliente(SeccionCliente seccionCliente) {
        this.seccionCliente = seccionCliente;
    }

    @Override
    public String toString() {
        return "Reserva{" +
            "idReserva=" + idReserva +
            ", idCliente=" + idCliente +
            ", idVuelo=" + idVuelo +
            ", numeroAsiento=" + numeroAsiento +
            ", estadoVuelo=" + estadoVuelo +
            ", seccionCliente=" + seccionCliente +
            '}';
    }
}
