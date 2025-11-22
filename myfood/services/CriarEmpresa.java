package myfood.services;

import java.util.List;
import com.github.f4b6a3.uuid.UuidCreator;

import myfood.framework.Data;
import myfood.models.Empresa;
import myfood.models.Usuario;

public class CriarEmpresa {
    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha) {
        validaTipoEmpresa(tipoEmpresa);
        validaNome(nome);
        validaEndereco(endereco);
        validaEmpresaExistente(nome, dono, endereco);
        validaIsDono(dono);

        return salvarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha, null, null, null, null, null);
    }

    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String abre, String fecha, String tipoMercado) {
        validaTipoEmpresa(tipoEmpresa);
        validaNome(nome);
        validaEndereco(endereco);
        validaTipoMercado(tipoMercado);
        Data.isValid(abre, fecha);
        validaEmpresaExistente(nome, dono, endereco);
        validaIsDono(dono);

        return salvarEmpresa(tipoEmpresa, dono, nome, endereco, null, abre, fecha, tipoMercado, null, null);
    }

    public static String run (String tipoEmpresa, String dono, String nome, String endereco, String aberto24Horas, String numeroFuncionarios) {
        validaTipoEmpresa(tipoEmpresa);
        validaNome(nome);
        validaEndereco(endereco);
        validaAberto24Horas(aberto24Horas);
        validaNumeroFuncionarios(numeroFuncionarios);
        validaEmpresaExistente(nome, dono, endereco);
        validaIsDono(dono);

        return salvarEmpresa(tipoEmpresa, dono, nome, endereco, null, null, null, null, aberto24Horas, numeroFuncionarios);
    }

    private static void validaTipoEmpresa(String tipoEmpresa) {
        // Verifica se o tipo de empresa é válido
        if (tipoEmpresa == null || tipoEmpresa.isEmpty()) {
            throw new RuntimeException("Tipo de empresa invalido");
        }
    }

    private static void validaNome(String nome) {
        // Verifica se o nome da empresa é válido
        if (nome == null || nome.isEmpty()) {
            throw new RuntimeException("Nome invalido");
        }
    }

    private static void validaEndereco(String endereco) {
        // Verifica se o endereço da empresa é válido
        if (endereco == null || endereco.isEmpty()) {
            throw new RuntimeException("Endereco da empresa invalido");
        }
    }

    private static void validaTipoMercado(String tipoMercado) {
        // Verifica se o tipo de mercado é válido
        if (tipoMercado == null || tipoMercado.isEmpty()) {
            throw new RuntimeException("Tipo de mercado invalido");
        }
    }

    private static void validaAberto24Horas(String aberto24Horas) {
        // Verifica se o campo aberto24Horas é válido
        if (aberto24Horas == null || aberto24Horas.isEmpty()) {
            throw new RuntimeException("Campo aberto24Horas invalido");
        }

        // Verifica se o valor é "true" ou "false"
        if (!aberto24Horas.equals("true") && !aberto24Horas.equals("false")) {
            throw new RuntimeException("Valor de aberto24Horas invalido");
        }
    }

    private static void validaNumeroFuncionarios(String numeroFuncionarios) {
        // Verifica se o campo numeroFuncionarios é válido
        if (numeroFuncionarios == null || numeroFuncionarios.isEmpty()) {
            throw new RuntimeException("Campo numeroFuncionarios invalido");
        }

        // Verifica se o valor é um número inteiro positivo
        try {
            int num = Integer.parseInt(numeroFuncionarios);
            if (num < 0) {
                throw new RuntimeException("Numero de funcionarios deve ser positivo");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Numero de funcionarios invalido");
        }
    }

    private static void validaEmpresaExistente(String nome, String dono, String endereco) {
        // Busca se já existe uma empresa com o mesmo nome e dono diferente
        List<Empresa> empresasExistentes = new Empresa().getBy("nome", nome);
        for (Empresa empresa : empresasExistentes) {
            if (!empresa.getProperty("dono").equals(dono)) {
                throw new RuntimeException("Empresa com esse nome ja existe");
            }
        }

        // Se for do mesmo dono a empresa de mesmo nome, verifica se o endereço é o mesmo
        for (Empresa empresa : empresasExistentes) {
            if (empresa.getProperty("dono").equals(dono) &&
                empresa.getProperty("endereco").equals(endereco)) {
                throw new RuntimeException("Proibido cadastrar duas empresas com o mesmo nome e local");
            }
        }
    }

    private static void validaIsDono(String dono) {
        // Verifica se o usuário é do tipo dono
        Usuario usuarioDono = new Usuario().find(dono);
        if (usuarioDono != null && !usuarioDono.isDono()) {
            throw new RuntimeException("Usuario nao pode criar uma empresa");
        }
    }

    private static String salvarEmpresa(String tipoEmpresa, String dono, String nome, String endereco, String tipoCozinha, String abre, String fecha, String tipoMercado, String aberto24Horas, String numeroFuncionarios) {
        Empresa empresa = new Empresa();
        empresa.setProperty("id", UuidCreator.getTimeOrdered().toString());
        empresa.setProperty("tipoEmpresa", tipoEmpresa);
        empresa.setProperty("dono", dono);
        empresa.setProperty("nome", nome);
        empresa.setProperty("endereco", endereco);
        if (tipoCozinha != null) {
            empresa.setProperty("tipoCozinha", tipoCozinha);
        }
        if (abre != null) {
            empresa.setProperty("abre", abre);
        }
        if (fecha != null) {
            empresa.setProperty("fecha", fecha);
        }
        if (tipoMercado != null) {
            empresa.setProperty("tipoMercado", tipoMercado);
        }
        if (aberto24Horas != null) {
            empresa.setProperty("aberto24Horas", aberto24Horas);
        }
        if (numeroFuncionarios != null) {
            empresa.setProperty("numeroFuncionarios", numeroFuncionarios);
        }
        empresa.save();
        return empresa.getProperty("id");
    }
}
