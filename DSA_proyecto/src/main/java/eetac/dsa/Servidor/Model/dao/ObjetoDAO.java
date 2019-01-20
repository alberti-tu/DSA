package eetac.dsa.Servidor.Model.dao;

import eetac.dsa.Servidor.Model.jsonpojo.ObjetoJSON;

public class ObjetoDAO extends DAO{

    @OrderFields(indice = 0)
    String id;
    @OrderFields(indice = 1)
    String tipo;
    @OrderFields(indice = 2)
    String extra;
    @OrderFields(indice = 4)
    String nombreUsuario;
    @OrderFields(indice = 3)
    int numArgs;

    public ObjetoDAO() { }

    @OrderGetMethods(indice = 0)
    public String getId() {
        return id;
    }

    @OrderSetMethods(indice = 0)
    public void setId(String id) {
        this.id = id;
    }

    @OrderGetMethods(indice = 1)
    public String getTipo() {
        return tipo;
    }

    @OrderSetMethods(indice = 1)
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @OrderGetMethods(indice = 2)
    public String getExtra() {
        return extra;
    }

    @OrderSetMethods(indice = 2)
    public void setExtra(String extra) {
        this.extra = extra;
    }

    @OrderGetMethods(indice = 4)
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @OrderSetMethods(indice = 4)
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @OrderGetMethods(indice = 3)
    public int getNumArgs() {
        return numArgs;
    }

    @OrderSetMethods(indice = 3)
    public void setNumArgs(int numArgs) {
        this.numArgs = numArgs;
    }

    public void parseToObjectDB(ObjetoJSON objeto, String usuario){

        this.setId(objeto.getId());
        this.setTipo(objeto.getTipo());
        this.setExtra(objeto.getArgs());
        this.setNombreUsuario(usuario);
        this.setNumArgs(objeto.getNumArgs());

    }

    @Override
    public String toString(){

        return "tipo: " + this.tipo + ", args: " + this.extra + ", nombreUsuario: " +
                this.nombreUsuario + ", numArgs: " + this.numArgs;

    }

}
