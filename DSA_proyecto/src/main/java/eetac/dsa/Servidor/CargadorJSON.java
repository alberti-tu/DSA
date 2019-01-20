package eetac.dsa.Servidor;

import eetac.dsa.Servidor.Model.jsonpojo.CeldaJSON;
import eetac.dsa.Servidor.Model.jsonpojo.EscenarioJSON;
import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

public class CargadorJSON {
    private static final Logger logger = LogManager.getLogger(EscenarioJSON.class.getName());

    public static EscenarioJSON fileToEscenario(String direccion)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        EscenarioJSON escenarioJSON =mapper.readValue(new File(direccion), EscenarioJSON.class);
        return escenarioJSON;
    }

    public static void EscenariotoFile(String direccion,EscenarioJSON escenarioJSON)throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(direccion), escenarioJSON);
    }

    public static MonstruoJSON[][] monstruosEncontrables() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        MonstruoJSON[][] monstruoJSONS =mapper.readValue(new File("src/main/resources/monstruosAleatorios.json"), MonstruoJSON[][].class);
        return monstruoJSONS;
    }

    public static ObjetoJSON[][] objetosEncontrables() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjetoJSON[][] objetoJSONS =mapper.readValue(new File("src/main/resources/objetosAleatorios.json"), ObjetoJSON[][].class);
        return objetoJSONS;
    }

    public static CeldaJSON[] celdasEncontrables() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        CeldaJSON[] celdaJSONS = mapper.readValue(new File("src/main/resources/Celdas.json"), CeldaJSON[].class);
        return celdaJSONS;
    }
}
