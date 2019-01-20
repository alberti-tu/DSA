package eetac.dsa.Servidor.Model.jsonpojo;

public class CeldaJSON {
    String tipo;
    int numArgs;
    String args;
    String image;

    public CeldaJSON() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
