package eetac.dsa.Servidor.Model.dao;

import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UsuarioDAO extends DAO{

    @OrderFields(indice = 0)
    private String id;
    @OrderFields(indice = 1)
    private String password;
    @OrderFields(indice = 2)
    private int x;
    @OrderFields(indice = 3)
    private int y;
    @OrderFields(indice = 4)
    private boolean genero;
    @OrderFields(indice = 5)
    private String email;
    @OrderFields(indice = 6)
    private String nombreMapa;


    public UsuarioDAO() {
    }

    @OrderGetMethods(indice = 0)
    public String getId() {
        return id;
    }

    @OrderSetMethods(indice = 0)
    public void setId(String nombre) {
        this.id = nombre;
    }

    @OrderGetMethods(indice = 1)
    public String getPassword() {
        return password;
    }

    @OrderSetMethods(indice = 1)
    public void setPassword(String password) {
        this.password = password;
    }

    @OrderGetMethods(indice = 2)
    public int getX() {
        return x;
    }

    @OrderSetMethods(indice = 2)
    public void setX(int x) {
        this.x = x;
    }

    @OrderGetMethods(indice = 3)
    public int getY() {
        return y;
    }

    @OrderSetMethods(indice = 3)
    public void setY(int y) {
        this.y = y;
    }

    @OrderGetMethods(indice = 4)
    public boolean getGenero() {
        return genero;
    }

    @OrderSetMethods(indice = 4)
    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    @OrderGetMethods(indice = 5)
    public String getEmail() {
        return email;
    }

    @OrderSetMethods(indice = 5)
    public void setEmail(String email) {
        this.email = email;
    }

    @OrderGetMethods(indice = 6)
    public String getNombreMapa() {
        return nombreMapa;
    }

    @OrderSetMethods(indice = 6)
    public void setNombreMapa(String nombreMapa) {
        this.nombreMapa = nombreMapa;
    }

    public void parseToNewUsuario(UsuarioJSON usuario){

        this.setId(usuario.getNombre());
        this.setGenero(usuario.isGenero());
        this.setPassword(usuario.getPassword());
        this.setX(1);
        this.setY(1);
        this.setNombreMapa("Escenario1");
        this.setEmail(usuario.getEmail());

    }

    public void parseToUsuario(UsuarioJSON usuario){

        this.setId(usuario.getNombre());
        this.setGenero(usuario.isGenero());
        this.setPassword(usuario.getPassword());
        this.setX(usuario.getX());
        this.setY(usuario.getY());
        this.setEmail(usuario.getEmail());
        this.setNombreMapa(nombreMapa);
        this.setNombreMapa(usuario.getEscenario());

    }



    public ArrayList<ObjetoDAO> parseInventario(UsuarioJSON usuario){

        ArrayList<ObjetoJSON> listObjetos= usuario.getInventario();
        if(listObjetos.size() >= 0) {

            ArrayList<ObjetoDAO> listObjetoDAO = new ArrayList<>();

            for (ObjetoJSON objeto : listObjetos) {

                ObjetoDAO objetoD = new ObjetoDAO();
                objetoD.parseToObjectDB(objeto, usuario.getNombre());
                listObjetoDAO.add(objetoD);

            }

            return listObjetoDAO;
        }

        return null;

    }

    public ArrayList<MonstruoDAO> parseMonstruos(UsuarioJSON usuario){

        ArrayList<MonstruoJSON> listMonsturos = usuario.getMonstruos();
        if(listMonsturos.size() >= 0) {

            ArrayList<MonstruoDAO> listMonstruoDAO = new ArrayList<>();

            for (MonstruoJSON monstruo : listMonsturos) {

                MonstruoDAO monstruoD = new MonstruoDAO();
                monstruoD.parseToMonstruo(monstruo, usuario.getNombre());
                listMonstruoDAO.add(monstruoD);

            }

            return listMonstruoDAO;
        }

        return null;

    }

}
