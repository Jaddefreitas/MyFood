package myfood.services;

import java.util.UUID;

import myfood.framework.Cpf;
import myfood.framework.Email;
import myfood.models.Usuario;

public class CriarUsuario {
    public static void run(String nome, String email, String senha, String endereco) {
        // Valida os dados fornecidos
        validaNome(nome);
        validaEmail(email);
        validaSenha(senha);
        validaEndereco(endereco);

        // Verifica se o email ja esta cadastrado
        verificaCadastro(email);

        // Cria o usuario sem CPF
        criarUsuario(nome, email, senha, endereco, null);
    }

    public static void run(String nome, String email, String senha, String endereco, String cpf) {
        // Valida os dados fornecidos
        validaNome(nome);
        validaEmail(email);
        validaSenha(senha);
        validaEndereco(endereco);
        validaCpf(cpf);

        // Verifica se o email ja esta cadastrado
        verificaCadastro(email);

        // Cria o usuario com CPF
        criarUsuario(nome, email, senha, endereco, cpf);
    }

    private static void validaNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("Nome invalido");
        }
    }

    private static void validaEmail(String email) {
        if (email == null || email.isEmpty() || !Email.isValid(email)) {
            throw new RuntimeException("Email invalido");
        }
    }

    private static void validaSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new RuntimeException("Senha invalido");
        }
    }

    private static void validaEndereco(String endereco) {
        if (endereco == null || endereco.isEmpty()) {
            throw new RuntimeException("Endereco invalido");
        }
    }

    private static void validaCpf(String cpf) {
        if (cpf == null || cpf.isEmpty() || !Cpf.isValid(cpf)) {
            throw new RuntimeException("CPF invalido");
        }
    }

    private static void verificaCadastro(String email) {
        Usuario existente = new Usuario().findBy("email", email);
        if (existente != null) {
            throw new RuntimeException("Conta com esse email ja existe");
        }
    }

    private static void criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        Usuario usuario = new Usuario();
        usuario.setProperty("id", UUID.randomUUID().toString());
        usuario.setProperty("nome", nome);
        usuario.setProperty("email", email);
        usuario.setProperty("senha", senha);
        usuario.setProperty("endereco", endereco);
        if (cpf != null) {
            usuario.setProperty("cpf", cpf);
        }
        usuario.save();
    }
}
