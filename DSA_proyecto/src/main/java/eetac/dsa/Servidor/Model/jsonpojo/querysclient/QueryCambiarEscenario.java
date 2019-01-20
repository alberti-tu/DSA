package eetac.dsa.Servidor.Model.jsonpojo.querysclient;


public class QueryCambiarEscenario {
    int x;
    int y;
    int key;
    String nombre;

    public QueryCambiarEscenario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
