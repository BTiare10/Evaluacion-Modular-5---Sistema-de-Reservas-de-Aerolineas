package cl.example.dtos;

import java.time.LocalTime;
import java.util.Date;

public class DetalleReservaDTO {
    private Integer idReserva;
    private String nombre;
    private String apellido;
    private String rut;
    private String ciudadDespegue;
    private String ciudadAterrizaje;
    private Date fechaSalida;
    private Date fechaLlegada;
    private LocalTime horaAbordaje;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private int numeroAsiento;
    private String estadoVuelo;
    private String seccionCliente;

    public DetalleReservaDTO() {
    }

    public DetalleReservaDTO(Integer idReserva, String nombre, String apellido, String rut, String ciudadDespegue, String ciudadAterrizaje, Date fechaSalida, Date fechaLlegada, LocalTime horaAbordaje, LocalTime horaSalida, LocalTime horaLlegada, int numeroAsiento, String estadoVuelo, String seccionCliente) {
        this.idReserva = idReserva;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.ciudadDespegue = ciudadDespegue;
        this.ciudadAterrizaje = ciudadAterrizaje;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaAbordaje = horaAbordaje;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCiudadDespegue() {
        return ciudadDespegue;
    }

    public void setCiudadDespegue(String ciudadDespegue) {
        this.ciudadDespegue = ciudadDespegue;
    }

    public String getCiudadAterrizaje() {
        return ciudadAterrizaje;
    }

    public void setCiudadAterrizaje(String ciudadAterrizaje) {
        this.ciudadAterrizaje = ciudadAterrizaje;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalTime getHoraAbordaje() {
        return horaAbordaje;
    }

    public void setHoraAbordaje(LocalTime horaAbordaje) {
        this.horaAbordaje = horaAbordaje;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
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
        return "DetalleReservaDTO{" +
            "idReserva=" + idReserva +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", rut='" + rut + '\'' +
            ", ciudadDespegue='" + ciudadDespegue + '\'' +
            ", ciudadAterrizaje='" + ciudadAterrizaje + '\'' +
            ", fechaSalida=" + fechaSalida +
            ", fechaLlegada=" + fechaLlegada +
            ", horaAbordaje=" + horaAbordaje +
            ", horaSalida=" + horaSalida +
            ", horaLlegada=" + horaLlegada +
            ", numeroAsiento=" + numeroAsiento +
            ", estadoVuelo='" + estadoVuelo + '\'' +
            ", seccionCliente='" + seccionCliente + '\'' +
            '}';
    }
}
