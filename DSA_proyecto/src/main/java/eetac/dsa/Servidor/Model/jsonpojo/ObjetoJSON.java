package eetac.dsa.Servidor.Model.jsonpojo;

import eetac.dsa.Servidor.Model.dao.ObjetoDAO;

public class ObjetoJSON {

    String id;
    String tipo;
    int numArgs;
    String args;

    public ObjetoJSON() { }

    public ObjetoJSON(String id,String tipo, String args,int numArgs) {
        this.tipo = tipo;
        this.args = args;
        this.numArgs = numArgs;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumArgs() {
        return numArgs;
    }

    public void setNumArgs(int numArgs) {
        this.numArgs = numArgs;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public void parseFromObjectDB(ObjetoDAO objetoD){

        this.setId(objetoD.getId());
        this.setTipo(objetoD.getTipo());
        this.setArgs(objetoD.getExtra());
        this.setNumArgs(objetoD.getNumArgs());

    }

}
