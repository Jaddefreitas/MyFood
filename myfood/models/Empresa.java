package myfood.models;

import myfood.framework.database.Model;

public class Empresa extends Model<Empresa> {
    @Override
    public String table() {
        return "empresas";
    }
}
