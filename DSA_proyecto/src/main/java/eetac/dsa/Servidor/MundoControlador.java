package eetac.dsa.Servidor;

import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;

import java.util.*;

public class MundoControlador {
    HashMap<Integer,Sesion> sesiones;

    static MundoControlador mundoControlador;

    public HashMap<Integer, Sesion> getSesiones() {
        return sesiones;
    }

    static public MundoControlador getInstance() {
        if(mundoControlador==null)
            mundoControlador = new MundoControlador();
        return mundoControlador;
    }

    private MundoControlador() {
        sesiones = new HashMap<>();

    }

    public Sesion getSesion(int key)
    {
        return sesiones.get(key);
    }

    public void addSesion(int key,Sesion sesion)
    {
        sesiones.put(key,sesion);
    }

    public boolean closeSession(int key){
        if(sesiones.containsKey(key)){
            sesiones.remove(key);
            return true;
        }else{
            return false;
        }
    }

    public Vector<Vector<MonstruoJSON>> monstruosEncontrables;

    public boolean usuarioYaLoggeado(UsuarioJSON user){

        for(Map.Entry<Integer, Sesion> entry : sesiones.entrySet()) {
            if (entry.getValue().protagonista.getNombre().equals(user.getNombre())) {
                return true;
            }
        }

        return false;

    }

    public void usuarioReplace(UsuarioJSON user, int key){

        for(Map.Entry<Integer, Sesion> entry : sesiones.entrySet()) {
            if (entry.getValue().protagonista.getNombre().equals(user.getNombre())) {

                sesiones.remove(entry.getKey());
                sesiones.put(key,new Sesion(user));

            }
        }

    }
}
