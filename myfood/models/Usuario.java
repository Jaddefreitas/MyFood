package myfood.models;

import myfood.framework.database.Model;

public class Usuario extends Model<Usuario> {
    @Override
    public String table() {
        return "usuarios";
    }

    public boolean isDono() {
        String nome = this.getProperty("nome");
        return nome != null && nome.endsWith("Dono");
    }
}
