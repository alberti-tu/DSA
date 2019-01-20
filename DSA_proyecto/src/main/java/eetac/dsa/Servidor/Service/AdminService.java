package eetac.dsa.Servidor.Service;

import eetac.dsa.Servidor.Model.ConsultaDB;
import eetac.dsa.Servidor.Model.dao.UsuarioDAO;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

/*@Path("/admin")*/
public class AdminService {

    @GET
    @Path("/profile/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioJSON getUsuario(@PathParam("nombre") String nombre)
    {
        //Buscar Usuario por nombre y devolverlo al cliente

        UsuarioJSON user = new UsuarioJSON();
        ConsultaDB.getInstance().getUsuarioBasic(nombre);
        user.setPassword(null);                 //La contraseña no se envia al cliente

        return user;


    }
}
