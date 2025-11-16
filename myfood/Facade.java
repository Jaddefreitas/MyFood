package myfood;

import myfood.framework.database.Storage;
import myfood.services.AdicionarProduto;
import myfood.services.CriarEmpresa;
import myfood.services.CriarPedido;
import myfood.services.CriarProduto;
import myfood.services.CriarUsuario;
import myfood.services.EditarProduto;
import myfood.services.FecharPedido;
import myfood.services.GetAtributoEmpresa;
import myfood.services.GetAtributoUsuario;
import myfood.services.GetEmpresasDoUsuario;
import myfood.services.GetIdEmpresa;
import myfood.services.GetNumeroPedido;
import myfood.services.GetPedidos;
import myfood.services.GetProduto;
import myfood.services.ListarProdutos;
import myfood.services.Login;
import myfood.services.RemoverProduto;

public class Facade {
    public void zerarSistema() {
        Storage database = new Storage(Configuration.STORAGE_NAME, Configuration.TABLES);
        database.delete();
        database.create();
    }

    public void encerrarSistema() {}

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

    public String criarEmpresa(String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha) {
        return CriarEmpresa.run(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }

    public String getEmpresasDoUsuario(String idUsuario) {
        return GetEmpresasDoUsuario.run(idUsuario);
    }

    public String getAtributoEmpresa(String id, String atributo) {
        return GetAtributoEmpresa.run(id, atributo);
    }

    public String getIdEmpresa(String idDono, String nomeEmpresa, String indice) {
        return GetIdEmpresa.run(idDono, nomeEmpresa, indice);
    }

    public String criarProduto(String empresa, String nome, String valor, String categoria) {
        return CriarProduto.run(empresa, nome, valor, categoria);
    }

    public void editarProduto(String produto, String nome, String valor, String categoria) {
        EditarProduto.run(produto, nome, valor, categoria);
    }

    public String getProduto(String nome, String empresa, String atributo) {
        return GetProduto.run(nome, empresa, atributo);
    }

    public String listarProdutos(String empresa) {
        return ListarProdutos.run(empresa);
    }

    public String criarPedido(String cliente, String empresa) {
        return CriarPedido.run(cliente, empresa);
    }

    public void adicionarProduto(String numero, String produto) {
        AdicionarProduto.run(numero, produto);
    }

    public String getPedidos(String pedido, String atributo) {
        return GetPedidos.run(pedido, atributo);
    }

    public void fecharPedido(String numero) {
        FecharPedido.run(numero);
    }

    public void removerProduto(String numero, String produto) {
        RemoverProduto.run(numero, produto);
    }

    public String getNumeroPedido(String cliente, String empresa, String indice) {
        return GetNumeroPedido.run(cliente, empresa, indice);
    }
}

