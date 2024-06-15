package ufv._4.examenprueba.yls;

public class Productos {

    public String nombre;
    public String categoria;
    public String precio;
    public String EAN13;


    public Productos() {
    }

    public Productos(String nombre, String categoria, String precio, String EAN13) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.EAN13 = EAN13;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getEAN13() {
        return EAN13;
    }

    public void setEAN13(String EAN13) {
        this.EAN13 = EAN13;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                ", precio='" + precio + '\'' +
                ", EAN13='" + EAN13 + '\'';
    }
}
