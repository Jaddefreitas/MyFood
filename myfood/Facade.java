package myfood;

import myfood.framework.database.Storage;
import myfood.services.CriarUsuario;
import myfood.services.GetAtributoUsuario;
import myfood.services.Login;

public class Facade {
    public void zerarSistema() {
        Storage database = new Storage(Configuration.STORAGE_NAME, Configuration.TABLES);
        database.delete();
        database.create();
    }

    public String getAtributoUsuario(String id, String atributo) {
        return GetAtributoUsuario.run(id, atributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) {
        CriarUsuario.run(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        CriarUsuario.run(nome, email, senha, endereco, cpf);
    }

    public String login(String email, String senha) {
        return Login.run(email, senha);
    }
}

