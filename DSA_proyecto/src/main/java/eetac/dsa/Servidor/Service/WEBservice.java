package eetac.dsa.Servidor.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import eetac.dsa.Servidor.CargadorJSON;
import eetac.dsa.Servidor.Model.ConsultaDB;
import eetac.dsa.Servidor.Model.jsonpojo.EscenarioJSON;
import eetac.dsa.Servidor.Model.jsonpojo.KeyUser;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@Path("/web")
public class WEBservice {

    private static final Logger logger = LogManager.getLogger(WEBservice.class.getName());

    @POST
    @Path("/guardarMapa")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public KeyUser guardarMapa(EscenarioJSON newEscenario){

        KeyUser key = new KeyUser();

        try {
            String filePath = new File("").getAbsolutePath();
            FileWriter file = new FileWriter(filePath + "/src/main/resources/Escenarios/" + newEscenario.getNombre() + ".json");
            Gson gson = new Gson();
            Type type = new TypeToken<EscenarioJSON>(){}.getType();
            String json = gson.toJson(newEscenario,type);
            file.write(json);
            file.flush();
            file.close();
            key.setKey(1);
            return key;
        }catch(Exception e){

            logger.error("Error: " + e.getMessage());
            key.setKey(0);
            return key;

        }

    }

    @GET
    @Path("/usuarioExsistente/{nickname}")
    @Produces(MediaType.APPLICATION_JSON)
    public KeyUser usuariosExsistentes(@PathParam("nickname") String nickName) {

        KeyUser key = new KeyUser();

        if(ConsultaDB.getInstance().ExistUser(nickName)){
            key.setKey(1);
            logger.error("Id: " + nickName + " ya existente");
            return key;

        }

        key.setKey(0);
        return key;
    }

    @GET
    @Path("/registro/")
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewRegistro()
    {

        try {

            File f = new File("src/Web/Registro/Registro.html");
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/registro/assets/{asset}")
    @Produces("text/css")
    public InputStream viewRegistroAssets(@PathParam("asset") String asset)
    {

        try {

            File f = null;

            if(asset.equals("js")) {
                f = new File("src/Web/Registro/Registro.js");
            }else if(asset.equals("css")) {
                f = new File("src/Web/Registro/Registro.css");
            }
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/images/{rsc}")
    @Produces("image/png")
    public byte[] getResourceImage(@PathParam("rsc") String resource){

        try {

            BufferedImage image = ImageIO.read(new File("src/Web/AssetsGenerales/" + resource + ".png"));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageData = baos.toByteArray();

            return  imageData;

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewPrincipal()
    {

        try {

            File f = new File("src/Web/Pagina Principal/Principal.html");
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/assets/{asset}")
    @Produces("text/css")
    public InputStream viewPrincipalAssets(@PathParam("asset") String asset)
    {

        try {

            File f = null;

            if(asset.equals("js")) {
                f = new File("src/Web/Pagina Principal/Principal.js");
            }else if(asset.equals("css")) {
                f = new File("src/Web/Pagina Principal/Principal.css");
            }
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/about")
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewAbout()
    {

        try {

            File f = new File("src/Web/About/About.html");
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/about/assets/{asset}")
    @Produces("text/css")
    public InputStream viewAboutAssets(@PathParam("asset") String asset)
    {

        try {

            File f = null;

            if(asset.equals("js")) {
                f = new File("src/Web/About/About.js");
            }else if(asset.equals("css")) {
                f = new File("src/Web/About/About.css");
            }
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/creadorMapas")
    @Produces(MediaType.TEXT_HTML)
    public InputStream viewCreador()
    {

        try {
            File f = new File("src/Web/Creadormapas/Creadordemapas.html");
            return new FileInputStream(f);

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }

    @GET
    @Path("/creadorMapas/assets/{asset}")
    @Produces("text/css")
    public InputStream viewCreadorAssets(@PathParam("asset") String asset)
    {

        try {

            File f = null;

            if(asset.equals("js")) {
                f = new File("src/Web/Creadormapas/Creadordemapas.js");
            }else if(asset.equals("css")) {
                f = new File("src/Web/Creadormapas/Creadordemapas.css");
            }
            return new FileInputStream(f);

        }catch (Exception e){
            return null;
        }

    }

    @GET
    @Path("/creadorMapas/ultimoMapa")
    @Produces(MediaType.APPLICATION_JSON)
    public EscenarioJSON ultimoMapa(){

        EscenarioJSON ultimoEscenario = new EscenarioJSON();

        File folder = new File("src/main/resources/Escenarios");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].getName().startsWith("Escenario")) {

                try {
                    ultimoEscenario = CargadorJSON.fileToEscenario(listOfFiles[i].getAbsolutePath());
                }catch (Exception e){

                    logger.error("Cargar escenario");

                }

            }
        }

        return ultimoEscenario;
    }

}
