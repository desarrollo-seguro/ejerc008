package es.santander.ascender.ejerc007.config;

public class ErrorInfo {
    private int codigo;
    private String mensaje;

    public ErrorInfo(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }   
}
