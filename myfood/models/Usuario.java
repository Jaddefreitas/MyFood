package myfood.models;

import myfood.framework.database.Model;

public class Usuario extends Model<Usuario> {
    @Override
    public String table() {
        return "usuarios";
    }
}
