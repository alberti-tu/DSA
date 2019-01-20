package eetac.dsa.Servidor;

import eetac.dsa.Servidor.Model.ConsultaDB;
import eetac.dsa.Servidor.Model.jsonpojo.EscenarioJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;
import eetac.dsa.Servidor.Model.jsonpojo.resultsserver.ResultCambiarEscenario;
import eetac.dsa.Servidor.Model.jsonpojo.resultsserver.ResultLoginArgs;

public class Sesion {
    EscenarioJSON escenario;
    UsuarioJSON protagonista;

    public Sesion(UsuarioJSON protagonista) {
        this.protagonista = protagonista;
        cargarEscenarioFichero("src/main/resources/Escenarios/" + protagonista.getEscenario() + ".json");

    }

    public EscenarioJSON getEscenario() {
        return escenario;
    }

    public void setProtagonista(UsuarioJSON protagonista) {
        this.protagonista = protagonista;
    }

    public UsuarioJSON getProtagonista() {
        return protagonista;
    }


    public ResultLoginArgs resultLoginArgs() throws Exception
    {
        ResultLoginArgs resultLoginArgs = new ResultLoginArgs();
        EscenarioJSON escenarioJSON = escenario;
        resultLoginArgs.setEscenarioJSON(escenarioJSON);
        UsuarioJSON usuarioJSON = protagonista;
        resultLoginArgs.setUsuarioJSON(usuarioJSON);
        resultLoginArgs.setMonstruosEncontrables(CargadorJSON.monstruosEncontrables());
        resultLoginArgs.setObjetosEncontrables(CargadorJSON.objetosEncontrables());
        resultLoginArgs.setPermitido(true);
        return resultLoginArgs;
    }

    public ResultCambiarEscenario cambiarEscenario(String nombreEscenario,int x,int y)
    {
            ResultCambiarEscenario resultCambiarEscenario = new ResultCambiarEscenario();
            //cargarEscenarioFichero(ConsultaDB.getInstance().getMapa(nombreEscenario).getDireccioFile());
            cargarEscenarioFichero("src/main/resources/Escenarios/"+nombreEscenario+".json");
            resultCambiarEscenario.setPermitido(true);
            resultCambiarEscenario.setX(x);
            resultCambiarEscenario.setY(y);
            resultCambiarEscenario.setEscenarioJSON(escenario);
            return resultCambiarEscenario;
    }


    public boolean cargarEscenarioFichero(String direccion)
    {
        try {
            escenario = CargadorJSON.fileToEscenario(direccion);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
