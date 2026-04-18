package priorityQueueModule;

public class Reporte {

    private String titulo;
    private String descripcion;

    public Reporte(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
