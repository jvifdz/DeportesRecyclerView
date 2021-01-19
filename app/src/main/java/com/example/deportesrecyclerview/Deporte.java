package com.example.deportesrecyclerview;

public class Deporte {
    int imagen;
    String texto;
    boolean checkbox;

    public Deporte(int imagen, String texto, boolean checkbox) {
        this.imagen = imagen;
        this.texto = texto;
        this.checkbox = checkbox;
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
