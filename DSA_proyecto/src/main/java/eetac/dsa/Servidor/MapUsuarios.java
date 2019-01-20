package eetac.dsa.Servidor;

import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;


/*public class MapUsuarios {

    private static final Logger logger = LogManager.getLogger(MapUsuarios.class.getName()

    static MapUsuarios mapUsuarios = null;

    HashMap<String,UsuarioJSON> usuarios;


    static public MapUsuarios getInstance() {
        if(mapUsuarios==null)
            mapUsuarios= new MapUsuarios();
        return mapUsuarios;
    }

    public HashMap<String, UsuarioJSON> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, UsuarioJSON> usuarios) {
        this.usuarios = usuarios;
    }

    private MapUsuarios() {


        this.usuarios = new HashMap<String,UsuarioJSON>();

        ArrayList<MonstruoJSON> monstruos= new ArrayList<MonstruoJSON>();
        monstruos.add(new MonstruoJSON("tierra",12,100,12, "aaa"));
        monstruos.add(new MonstruoJSON("agua",4,1,4, "aaa"));

        usuarios.put("juan",new UsuarioJSON(null,monstruos,"juan","123","juan123",true));

        ArrayList<MonstruoJSON> monstruos2= new ArrayList<MonstruoJSON>();
        monstruos2.add(new MonstruoJSON("viento",12,100,12,"aaa"));
        monstruos2.add(new MonstruoJSON("agua",11,10,12,"aaa"));
        usuarios.put("pepe",new UsuarioJSON(null,monstruos2,"pepe","1234","pepe3",true));

        ArrayList<MonstruoJSON> monstruos3= new ArrayList<MonstruoJSON>();
        monstruos3.add(new MonstruoJSON("tierra",12,100,12,"aaa"));
        monstruos3.add(new MonstruoJSON("agua",12,100,12,"aaa"));
        monstruos3.add(new MonstruoJSON("agua",11,10,12,"aaa"));
        monstruos3.add(new MonstruoJSON("agua",11,10,12,"aaa"));
        usuarios.put("oscar",new UsuarioJSON(null,monstruos3,"oscar","123","oscar123",true));


    }
    public boolean loggin(String nombre, String password)
    {


        if (usuarios.containsKey(nombre))
        {

            if(usuarios.get(nombre).getPassword().equals(password))
            {

                return true;
            }
        }
       return false;
    }

    public boolean Registrarse(UsuarioJSON user){

        if (usuarios.containsKey(user.getNombre()))
            return false;
        ArrayList<MonstruoJSON> monstruos= new ArrayList<MonstruoJSON>();
        monstruos.add(new MonstruoJSON("tierra",12,100,12,"aaa"));
        monstruos.add(new MonstruoJSON("agua",123,1000,1232,"aaa"));
        monstruos.add(new MonstruoJSON("viento",1,10,12,"aaa"));


        //ArrayList<ObjetoJSON> inventario= new ArrayList<ObjetoJSON>();
        //inventario.add(new ObjetoJSON())
        UsuarioJSON u = new UsuarioJSON(null,monstruos,user.getNombre(),user.getPassword(),user.getEmail(),user.isGenero());
        usuarios.put(user.getNombre(),u);


        return true;
    }


}*/
