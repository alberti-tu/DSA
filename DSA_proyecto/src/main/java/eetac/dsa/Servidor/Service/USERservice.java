package eetac.dsa.Servidor.Service;

import eetac.dsa.Servidor.CargadorJSON;

import eetac.dsa.Servidor.Model.ConsultaDB;

import eetac.dsa.Servidor.Model.jsonpojo.*;
import eetac.dsa.Servidor.Model.jsonpojo.querysclient.*;
import eetac.dsa.Servidor.Model.jsonpojo.resultsserver.ResultCambiarEscenario;
import eetac.dsa.Servidor.Model.jsonpojo.resultsserver.ResultLoginArgs;
import eetac.dsa.Servidor.Model.jsonpojo.resultsserver.ResultadoAceptar;
import eetac.dsa.Servidor.MundoControlador;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;

@Path("/user")
public class USERservice
{
    private static final Logger logger = LogManager.getLogger(USERservice.class.getName());

    @GET
    @Path("/getLoginArgs/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultLoginArgs getLoggingArgs(@PathParam("id") int key)
    {
        try
        {
            return MundoControlador.getInstance().getSesion(key).resultLoginArgs();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @GET
    @Path("/profile/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioJSON getUsuario(@PathParam("nombre") String nombre)
    {
        //Buscar Usuario por nombre y devolverlo al cliente
        UsuarioJSON user = ConsultaDB.getInstance().getUsuarioBasic(nombre);
        //user.setPassword(null);                 //La contraseña no se envia al cliente
        return user;
    }

    @POST
    @Path("/profile/fields")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioJSON actualizarPerfil(UsuarioJSON usuario)
    {
        if(ConsultaDB.getInstance().updateUserDB(usuario))
        {
            logger.error("Usuario " + usuario.getNombre() + " actualizado");
            usuario.setKey(0);
        }

        else
        {
            logger.error("Usuario " + usuario.getNombre() + " no se ha podido actualizar");
            usuario.setKey(1);
        }

        return usuario;
    }

    @POST
    @Path("/profile/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResultadoAceptar updateUsuario(QueryUpdateUsuario qUpUsuario)
    {
        try
        {
            UsuarioJSON userUpdate = qUpUsuario.getUsuarioJson();
            userUpdate.setEscenario(qUpUsuario.getNomEscenari());
            MundoControlador.getInstance().getSesion(qUpUsuario.getKey()).setProtagonista(userUpdate);
            ResultadoAceptar resultadoAceptar = new ResultadoAceptar();
            ConsultaDB.getInstance().deleteMonstruosAndObjetosUser(userUpdate);
            ConsultaDB.getInstance().insertMonstruos(userUpdate);
            ConsultaDB.getInstance().insertInventario(userUpdate);
            resultadoAceptar.setPermitido(ConsultaDB.getInstance().updateUserDB(userUpdate));
            return resultadoAceptar;
        }

        catch (Exception e)
        {
            ResultadoAceptar resultadoAceptar = new ResultadoAceptar();
            resultadoAceptar.setPermitido(false);
            return resultadoAceptar;
        }
    }

    @GET
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UsuarioJSON> ranking()
    {
        ArrayList<UsuarioJSON> list;

        list = ConsultaDB.getInstance().getAllUsers(true);

        Collections.sort(list,UsuarioJSON.Productoventascomparator);
        return list;
    }

    @GET
    @Path("/listaMonstruo/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MonstruoJSON> getMonstruos(@PathParam("nombre") String nombre)
    {

        if(ConsultaDB.getInstance().ExistUser(nombre)){
            ArrayList<MonstruoJSON> list;
            list = ConsultaDB.getInstance().getMonstruosUsuario(nombre);
            return list;
        }

        return null;

    }

    @GET
    @Path("/listaInventario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<ObjetoJSON> getInventario(@PathParam("nombre") String nombre)
    {
        ArrayList<ObjetoJSON> list;
        list = ConsultaDB.getInstance().getObjtosUsuario(nombre);
        return list;
    }

    @POST
    @Path("/cambiarEscenario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResultCambiarEscenario getCambiarEscenario(QueryCambiarEscenario qCamEsc)
    {
        try
        {
            return MundoControlador.getInstance().getSesion(qCamEsc.getKey()).cambiarEscenario(qCamEsc.getNombre(),qCamEsc.getX(),qCamEsc.getY());
        }

        catch (Exception e)
        {
            return null;
        }
    }

    @GET
    @Path("/getCeldasEncontrables")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCeldasEncontrables()
    {
        try
        {
            return Response.ok().entity(CargadorJSON.celdasEncontrables()).header("Access-Control-Allow-Origin", "*").build();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @GET
    @Path("/getMonstruosEncontrables")
    @Produces(MediaType.APPLICATION_JSON)
    public MonstruoJSON[][] getMonstruosEncontrables()
    {
        try
        {
            return CargadorJSON.monstruosEncontrables();
        }

        catch (Exception e)
        {
            return null;
        }
    }

    @GET
    @Path("/getObjetosEncontrables")
    @Produces(MediaType.APPLICATION_JSON)
    public ObjetoJSON[][] getObjetosEncontrables()
    {
        try
        {
            return CargadorJSON.objetosEncontrables();
        }

        catch (Exception e)
        {
            return null;
        }
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public KeyUser deleteUser(UsuarioJSON user)
    {
        KeyUser key = new KeyUser();

        if(ConsultaDB.getInstance().deleteUserRowDB(user))
        {
            key.setKey(1);
            logger.info("Usuario " + user.getNombre() + " ha sido eliminado");
        }

        else
        {
            key.setKey(0);
            logger.error("Usuario " + user.getNombre() + " no encontrado");
        }
        return key;
    }
}