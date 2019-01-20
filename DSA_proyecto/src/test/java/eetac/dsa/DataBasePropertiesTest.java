package eetac.dsa;

import eetac.dsa.Servidor.Model.dao.DAO;
import eetac.dsa.Servidor.Model.dao.PropertiesDB;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DataBasePropertiesTest {

    @Test
    public void propertyTest(){

        String property = PropertiesDB.getInstance().getText("dbms");

        assertEquals(property, "mysql");

    }

    @Test
    public void connectionPropTest(){

        //DAO.getConnection();

    }

}
