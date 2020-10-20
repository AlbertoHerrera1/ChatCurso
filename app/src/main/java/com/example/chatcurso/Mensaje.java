package com.example.chatcurso;

public class Mensaje {
    String nombre;
    String mensaje;
    String fecha;
    String UID;

    public Mensaje() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public Mensaje(String nombre, String mensaje, String fecha, String UID) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.UID = UID;
    }
}
