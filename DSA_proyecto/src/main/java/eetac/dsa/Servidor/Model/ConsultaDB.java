package eetac.dsa.Servidor.Model;

import eetac.dsa.Servidor.Model.dao.*;

import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaDB extends DAO {

    private static ConsultaDB singleConsulta = null;
    private static final Logger logger = LogManager.getLogger(ConsultaDB.class.getName());

    private ConsultaDB(){


    }

    public static ConsultaDB getInstance(){

        if(singleConsulta == null){

            singleConsulta = new ConsultaDB();

        }

        return singleConsulta;

    }

    public UsuarioJSON getUsuarioBasic(String name) {

        UsuarioDAO usuarioD = new UsuarioDAO();
        UsuarioJSON usuario = new UsuarioJSON();

        try{

            usuarioD.selectDB(name);
            usuario.parseFromDB(usuarioD);

        }catch(SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }

        return usuario;

    }

    public ArrayList<ObjetoJSON> getObjtosUsuario(String name) {

        UsuarioDAO usuarioD = new UsuarioDAO();
        ArrayList<ObjetoDAO> listObj;
        ArrayList<ObjetoJSON> objetoJSONArrayList = new ArrayList<>();

        try {

            usuarioD.selectDB(name);
            listObj = usuarioD.selectListObjetoDB();

            for (ObjetoDAO obj : listObj) {

                ObjetoJSON objetoJSON = new ObjetoJSON();
                objetoJSON.parseFromObjectDB(obj);
                objetoJSONArrayList.add(objetoJSON);

            }
        }catch (SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }

        return objetoJSONArrayList;

    }

    public ArrayList<MonstruoJSON> getMonstruosUsuario(String name) {

        UsuarioDAO usuarioD = new UsuarioDAO();
        ArrayList<MonstruoDAO> monstruoDAOArrayList;
        ArrayList<MonstruoJSON> monstruoJSONArrayList = new ArrayList<>();

        try {
            usuarioD.selectDB(name);
            monstruoDAOArrayList = usuarioD.selectListMonstruoDB();

            for (MonstruoDAO mon : monstruoDAOArrayList) {

                MonstruoJSON monstruoJSON = new MonstruoJSON();
                monstruoJSON.parseFromMonstruoDB(mon);
                monstruoJSONArrayList.add(monstruoJSON);

            }
        }catch (SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }

        return monstruoJSONArrayList;

    }

    public UsuarioJSON getUsuarioEntero(String name){

        UsuarioJSON usuarioJSON;
        usuarioJSON = ConsultaDB.getInstance().getUsuarioBasic(name);
        usuarioJSON.setInventario(ConsultaDB.getInstance().getObjtosUsuario(usuarioJSON.getNombre()));
        usuarioJSON.setMonstruos(ConsultaDB.getInstance().getMonstruosUsuario(usuarioJSON.getNombre()));
        return usuarioJSON;

    }

    public UsuarioJSON getUsuarioEnteroSeguro(String name){

        UsuarioJSON usuarioJSON = new UsuarioJSON();
        usuarioJSON.setNombre(name);
        usuarioJSON.setInventario(ConsultaDB.getInstance().getObjtosUsuario(usuarioJSON.getNombre()));
        usuarioJSON.setMonstruos(ConsultaDB.getInstance().getMonstruosUsuario(usuarioJSON.getNombre()));
        return usuarioJSON;

    }

    public  ArrayList<UsuarioJSON> getAllUsers(boolean isRanking) {

        ArrayList<UsuarioDAO> usuarioDAOArrayList;
        ArrayList<UsuarioJSON> usuarioJSONArrayList = new ArrayList<>();

        try {

            usuarioDAOArrayList = selectAllUsers();

            for (UsuarioDAO usuarioDAO : usuarioDAOArrayList) {

                UsuarioJSON usuarioJSON;
                if(isRanking){
                    usuarioJSON = getUsuarioEnteroSeguro(usuarioDAO.getId());
                }else{
                    usuarioJSON = ConsultaDB.getInstance().getUsuarioEntero(usuarioDAO.getId());
                }

                usuarioJSONArrayList.add(usuarioJSON);

            }
        }catch (SQLException e){
            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
        }

        return usuarioJSONArrayList;

    }

    public Boolean insertUser(UsuarioJSON usuarioJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToNewUsuario(usuarioJSON);

        try{
            usuarioDAO.insertDB();
            return true;

        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }

    }

    public Boolean deleteUserRowDB(UsuarioJSON usuarioJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToUsuario(usuarioJSON);

        try{
            usuarioDAO.deleteDB();
            usuarioDAO.deleteMonstruosAndObjetosUsuario();
            return true;

        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }

    }

    public Boolean updateUserDB(UsuarioJSON usuarioJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToUsuario(usuarioJSON);


        try{
            usuarioDAO.updateDB();
            return true;

        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }

    }

    public ArrayList<String> getAllUsersDB(){

        try{

            return selectUsersID();

        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return null;

        }

    }

    public Boolean ExistUser(String userName){

        ArrayList<String> usuariosExistentes = new ArrayList<>();

        usuariosExistentes = getAllUsersDB();

        if(usuariosExistentes.contains(userName)){

            return true;

        }else{

            return false;

        }

    }

    public Boolean deleteMonstruosAndObjetosUser(UsuarioJSON userJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToUsuario(userJSON);

        try{
            usuarioDAO.deleteMonstruosAndObjetosUsuario();

            logger.info("Monstruos i Objetos de " + userJSON.getNombre() + " eliminados");
            return true;
        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }




    }

    public Boolean insertMonstruos(UsuarioJSON userJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToUsuario(userJSON);

        try{
            for(MonstruoJSON monstruoJSON: userJSON.getMonstruos()){

                MonstruoDAO monstruoDAO = new MonstruoDAO();
                monstruoDAO.parseToMonstruo(monstruoJSON, userJSON.getNombre());
                monstruoDAO.insertDB();

            }
            logger.info("Monstruos de " + userJSON.getNombre() + " añadidos");
            return true;
        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }

    }

    public  Boolean insertInventario(UsuarioJSON userJSON){

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.parseToUsuario(userJSON);

        try{
            for(ObjetoJSON objetoJSON: userJSON.getInventario()){

                ObjetoDAO objetoDAO = new ObjetoDAO();
                objetoDAO.parseToObjectDB(objetoJSON, userJSON.getNombre());
                objetoDAO.insertDB();

            }

            logger.info("Objetos de " + userJSON.getNombre() + " añadidos");

            return true;
        }catch (SQLException e){

            logger.error(e.getErrorCode() + "-" + e.getSQLState()+ ": " + e.getMessage());
            return false;

        }

    }

}
