package eetac.dsa.Servidor.Model.jsonpojo.resultsserver;

import eetac.dsa.Servidor.Model.jsonpojo.EscenarioJSON;
import eetac.dsa.Servidor.Model.jsonpojo.MonstruoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;
import eetac.dsa.Servidor.Model.jsonpojo.UsuarioJSON;

import java.util.Vector;

public class ResultLoginArgs {
    boolean permitido;
    UsuarioJSON usuarioJSON;
    EscenarioJSON escenarioJSON;

    MonstruoJSON[][] monstruosEncontrables;
    ObjetoJSON[][] objetosEncontrables;

    public MonstruoJSON[][] getMonstruosEncontrables() {
        return monstruosEncontrables;
    }

    public void setMonstruosEncontrables(MonstruoJSON[][] monstruosEncontrables) {
        this.monstruosEncontrables = monstruosEncontrables;
    }

    public ObjetoJSON[][] getObjetosEncontrables() {
        return objetosEncontrables;
    }

    public void setObjetosEncontrables(ObjetoJSON[][] objetosEncontrables) {
        this.objetosEncontrables = objetosEncontrables;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public UsuarioJSON getUsuarioJSON() {
        return usuarioJSON;
    }

    public void setUsuarioJSON(UsuarioJSON usuarioJSON) {
        this.usuarioJSON = usuarioJSON;
    }

    public EscenarioJSON getEscenarioJSON() {
        return escenarioJSON;
    }

    public void setEscenarioJSON(EscenarioJSON escenarioJSON) {
        this.escenarioJSON = escenarioJSON;
    }
}
