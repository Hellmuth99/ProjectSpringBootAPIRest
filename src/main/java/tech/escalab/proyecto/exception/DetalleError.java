

package tech.escalab.proyecto.exception;

        import org.springframework.validation.FieldError;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Objects;

public class DetalleError {

    private String objeto;
    private String atributo;
    private String valorRecibido;
    private String mensaje;

    public DetalleError(){

    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(String valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleError that = (DetalleError) o;
        return Objects.equals(objeto, that.objeto) && Objects.equals(atributo, that.atributo) && Objects.equals(valorRecibido, that.valorRecibido) && Objects.equals(mensaje, that.mensaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objeto, atributo, valorRecibido, mensaje);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetalleError{");
        sb.append("objeto='").append(objeto).append('\'');
        sb.append(", atributo='").append(atributo).append('\'');
        sb.append(", valorRecibido='").append(valorRecibido).append('\'');
        sb.append(", mensaje='").append(mensaje).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static List<DetalleError> mapearError(List<FieldError> fieldErrors){
        List<DetalleError> detalleErrores = new ArrayList<>();

        for (FieldError error: fieldErrors){
            DetalleError detalle = new DetalleError();
            detalle.setAtributo(error.getField());
            detalle.setObjeto(error.getObjectName());
            //detalle.setValorRecibido(error.getRejectedValue().toString());
            //detalle.setValorRecibido(error.getRejectedValue() != null ? error.getRejectedValue().toString() : "null");
            //detalle.setValorRecibido(error.getRejectedValue() != null ? error.getRejectedValue().toString() : null);
            detalle.setValorRecibido(error.getRejectedValue() != null ? error.getRejectedValue().toString() : "");

            detalle.setMensaje(error.getDefaultMessage());



            detalleErrores.add(detalle);
        }

        return detalleErrores;
    }
}
