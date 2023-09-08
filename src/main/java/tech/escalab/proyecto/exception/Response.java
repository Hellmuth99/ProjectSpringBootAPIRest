package tech.escalab.proyecto.exception;

public class Response {

    private int status;
    private Object message;
    //private String error;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
        //   this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public Object getMessage() {
        return message;
    }


}
