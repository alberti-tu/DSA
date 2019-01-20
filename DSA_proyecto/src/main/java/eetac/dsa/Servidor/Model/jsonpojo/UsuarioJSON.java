package eetac.dsa.Servidor.Model.jsonpojo;

import eetac.dsa.Servidor.Model.dao.UsuarioDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UsuarioJSON
{
    private ArrayList<ObjetoJSON> inventario;
    private ArrayList<MonstruoJSON> monstruos;
    private String nombre;
    private String password;
    private String email;
    private int x;
    private int y;
    private int key;
    private boolean genero;
    private String escenario;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGenero() {
        return genero;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public String getEscenario() {
        return escenario;
    }

    public void setEscenario(String escenario) {
        this.escenario = escenario;
    }

    //

    public ArrayList<ObjetoJSON> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<ObjetoJSON> inventario) {
        this.inventario = inventario;
    }

    public ArrayList<MonstruoJSON> getMonstruos() {
        return monstruos;
    }

    public void setMonstruos(ArrayList<MonstruoJSON> monstruos) {
        this.monstruos = monstruos;
    }

    public UsuarioJSON(ArrayList<ObjetoJSON> inventario, ArrayList<MonstruoJSON> monstruos, String nombre,
                       String password, String email, boolean genero, String escenario) {
        this.inventario = inventario;
        this.monstruos = monstruos;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.genero = genero;
        this.escenario = escenario;
    }

//


    public UsuarioJSON() { }

    public UsuarioJSON(String nombre, String password, String email, int x, int y, boolean genero) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.x = x;
        this.y = y;
        this.genero = genero;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public static Comparator<UsuarioJSON> Productoventascomparator
            = new Comparator<UsuarioJSON>() {

        public int compare(UsuarioJSON u1, UsuarioJSON u2) {



            return u1.getMonstruos().size()-u2.getMonstruos().size();

        }

    };

    public void parseFromDB(UsuarioDAO usuario){

        this.setNombre(usuario.getId());
        this.setGenero(usuario.getGenero());
        this.setPassword(usuario.getPassword());
        this.setX(usuario.getX());
        this.setY(usuario.getY());
        this.setEmail(usuario.getEmail());
        this.setEscenario(usuario.getNombreMapa());

    }
}
