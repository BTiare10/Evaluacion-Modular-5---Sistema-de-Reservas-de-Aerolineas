package cl.example.dtos;

public class ListarReservaDTO {
    private Integer idReserva;
    private String ciudadDespegue;
    private String ciudadAterrizaje;

    public ListarReservaDTO() {
    }

    public ListarReservaDTO(Integer idReserva, String ciudadDespegue, String ciudadAterrizaje) {
        this.idReserva = idReserva;
        this.ciudadDespegue = ciudadDespegue;
        this.ciudadAterrizaje = ciudadAterrizaje;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
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

    @Override
    public String toString() {
        return "ListarReservaDTO{" +
            "idReserva=" + idReserva +
            ", ciudadDespegue='" + ciudadDespegue + '\'' +
            ", ciudadAterrizaje='" + ciudadAterrizaje + '\'' +
            '}';
    }
}
