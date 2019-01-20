package eetac.dsa.Servidor.Model.jsonpojo.resultsserver;

import eetac.dsa.Servidor.Model.jsonpojo.EscenarioJSON;

public class ResultCambiarEscenario {
    boolean permitido;
    EscenarioJSON escenarioJSON;
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

    public EscenarioJSON getEscenarioJSON() {
        return escenarioJSON;
    }

    public void setEscenarioJSON(EscenarioJSON escenarioJSON) {
        this.escenarioJSON = escenarioJSON;
    }
}
