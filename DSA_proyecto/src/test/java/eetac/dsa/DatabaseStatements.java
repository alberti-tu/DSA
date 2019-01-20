package eetac.dsa;

import eetac.dsa.Servidor.Model.ConsultaDB;
import eetac.dsa.Servidor.Model.dao.MonstruoDAO;
import eetac.dsa.Servidor.Model.dao.ObjetoDAO;
import eetac.dsa.Servidor.Model.dao.UsuarioDAO;
import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.dao.DAO;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseStatements {

    MonstruoDAO monstruo;
    MonstruoDAO monstruo1;
    MonstruoDAO monstruo3;
    MonstruoJSON monstruo2;
    ObjetoJSON objeto;
    ObjetoJSON objeto2;
    UsuarioJSON usuario1;
    ArrayList<UsuarioDAO> usuarioDAOArrayList;
    ArrayList<ObjetoDAO> listObj;
    ArrayList<MonstruoDAO> listMon;
    UsuarioDAO usuarioD;
    UsuarioDAO usuarioD2;
    ObjetoDAO objetoD;
    ArrayList<ObjetoJSON> listObjetos;
    ArrayList<MonstruoJSON> listMonstruos;
    private static final Logger logger = LogManager.getLogger(DatabaseStatements.class.getName());

    @Before
    public void prepareClass(){

        monstruo2 = new MonstruoJSON("Hierba",0,1,2, "bbb");
        usuarioD = new UsuarioDAO();
        usuarioD2 = new UsuarioDAO();
        usuarioDAOArrayList = new ArrayList<>();
        objeto = new ObjetoJSON(UUID.randomUUID().toString(),"Pocion","{\"Str0\":\"pocion pequeña\",\"int1\":100",2);
        objeto2 = new ObjetoJSON(UUID.randomUUID().toString(),"PocionExperiencia","{\"Str0\":\"pocion pequeña experiencia\",\"int1\":100",2);
        objetoD =new ObjetoDAO();
        listMonstruos = new ArrayList<>();
        listMonstruos.add(monstruo2);
        listObjetos = new ArrayList<>();
        listObjetos.add(objeto);
        listObjetos.add(objeto2);
        listObj = new ArrayList<>();
        usuario1 = new UsuarioJSON(listObjetos,listMonstruos,"pepito","aaa","bbb",true,null);
        monstruo = new MonstruoDAO("Primero","Hierba",100,1,1000,"jaume");
        monstruo1 = new MonstruoDAO("Primero","Fuego",100,1,1000,"jaume");
        monstruo3 = new MonstruoDAO();


    }

    @Test
    public void addTestM(){

        try{
            monstruo.insertDB();
        }catch (SQLException e){
            logger.error(e.getSQLState());
            logger.error(e.getErrorCode());
        }

    }

    @Test
    public void deleteTestM(){

        try{
            monstruo.deleteDB();
        }catch (SQLException e){
            e.getSQLState();
        }

    }

    @Test
    public void selectTestM(){

        try{
            monstruo3.selectDB("Primero");
        }catch (SQLException e){
            e.getSQLState();
        }

        logger.info(monstruo3.toString());

        assertEquals("Primero",monstruo3.getId());

    }

    @Test
    public  void updateTestM(){

        try{
            monstruo1.updateDB();
        }catch (SQLException e){
            e.getSQLState();
        }

    }

    @Test
    public void insertTestU(){

        try{
            usuarioD.parseToUsuario(usuario1);

            logger.info(usuarioD.getId() + " " + usuarioD.getPassword());

            usuarioD.insertDB();
            usuarioD2.selectDB("jaume");
            usuario1 = new UsuarioJSON();
            usuario1.parseFromDB(usuarioD2);

            logger.info(usuario1.getNombre() + " " + usuario1.getPassword());

        }catch (SQLException e){
            logger.error(e.getSQLState());
            logger.error(e.getErrorCode());
        }

    }

    @Test
    public void insertTestO(){

        try{
            objetoD.parseToObjectDB(objeto,usuario1.getNombre());

            logger.info(objetoD.getId());

            objetoD.insertDB();
            objetoD = new ObjetoDAO();
            objetoD.selectDB("Pvida");
            ObjetoJSON objeto3 = new ObjetoJSON();
            objeto3.parseFromObjectDB(objetoD);

            logger.info(objeto3.toString());

        }catch (SQLException e){
            logger.error(e.getSQLState());
            logger.error(e.getErrorCode());
        }

    }

    @Test
    public void retrieveListObj(){

        try{
        usuarioD.parseToUsuario(usuario1);
        listObj = usuarioD.selectListObjetoDB();

        for(ObjetoDAO obj: listObj){
            logger.info(obj.toString());
        }

        }catch (SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }

    }

    @Test
    public  void getAllUsersTest(){

        try{

            usuarioDAOArrayList = ConsultaDB.getInstance().selectAllUsers();

            for(UsuarioDAO usr: usuarioDAOArrayList){
                logger.info(usr.getId());
            }

        }catch (SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }
    }

    @Test
    public  void deleteAllTest(){

        if(ConsultaDB.getInstance().deleteUserRowDB(usuario1)){
            logger.info("Hecho");
        }else{
            logger.error("Error algo no ha funcionado");
        }

    }

}
