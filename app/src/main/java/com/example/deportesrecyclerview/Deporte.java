package com.example.deportesrecyclerview;

public class Deporte {
    long id;
    int imagen;
    String texto;
    boolean checkbox;

    public Deporte(long id, int imagen, String texto, boolean checkbox) {
        this.id=id;
        this.imagen = imagen;
        this.texto = texto;
        this.checkbox = checkbox;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }
}
