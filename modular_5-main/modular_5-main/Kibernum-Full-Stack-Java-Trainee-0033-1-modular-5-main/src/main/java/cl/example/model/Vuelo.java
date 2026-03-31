package cl.example.model;

import java.time.LocalTime;
import java.util.Date;

public class Vuelo {

    private Integer idVuelo;
    private String ciudadDespegue;
    private String ciudadAterrizaje;
    private Date fechaSalida;
    private Date fechaLlegada;
    private LocalTime horaAbordaje;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;

    public Vuelo() {
    }

    public Vuelo(Integer idVuelo, String ciudadDespegue, String ciudadAterrizaje, Date fechaSalida, Date fechaLlegada, LocalTime horaAbordaje, LocalTime horaSalida, LocalTime horaLlegada) {
        this.idVuelo = idVuelo;
        this.ciudadDespegue = ciudadDespegue;
        this.ciudadAterrizaje = ciudadAterrizaje;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaAbordaje = horaAbordaje;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
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

    @Override
    public String toString() {
        return "Vuelo{" +
            "idVuelo=" + idVuelo +
            ", ciudadDespegue='" + ciudadDespegue + '\'' +
            ", ciudadAterrizaje='" + ciudadAterrizaje + '\'' +
            ", fechaSalida=" + fechaSalida +
            ", fechaLlegada=" + fechaLlegada +
            ", horaAbordaje=" + horaAbordaje +
            ", horaSalida=" + horaSalida +
            ", horaLlegada=" + horaLlegada +
            '}';
    }
}
