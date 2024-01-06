package views;

import entities.Denuncia;
import entities.Localizacao;
import entities.Usuario;
import entities.enums.Categoria;
import entities.enums.Situacao;
import exceptions.MaxAttemptsExceededException;
import helpers.CadastroDenunciaHelper;
import interfaces.IDenunciaCadastro;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CadastroDenuncia extends CadastroDenunciaHelper implements IDenunciaCadastro {

    @Override
    public Denuncia cadastrarDenuncia() {
        try {
            int idDenuncia = gerarNumeroAleatorio();

            System.out.println("Nome: ");
            String nomeUsuario = this.digitarNomeUsuario();
            Usuario usuario = new Usuario(nomeUsuario);

            System.out.println("Descrição: ");
            String descricao = this.digitarDescricao();

            System.out.println("longitute: ");
            double longitude = this.digitarLongitude();
            System.out.println("latitude: ");
            double latitude = this.digitarLatitude();
            Localizacao localizacao = new Localizacao(latitude, longitude);

            System.out.println("Selecione o Status da Denúncia:");
            for (Situacao situacao : Situacao.values()) {
                System.out.println(situacao.ordinal() + " - " + situacao);
            }
            int opcaoStatus = this.digitarStatus();
            Situacao statusDenuncia = Situacao.values()[opcaoStatus];

            System.out.println("Selecione a Categoria da Denúncia:");
            for (Categoria categoria : Categoria.values()) {
                System.out.println(categoria.ordinal() + " - " + categoria);
            }
            int opcaoCategoria = this.digitarCategoria();
            Categoria categoriaSelecionada = Categoria.values()[opcaoCategoria];

            LocalDateTime dataHora = LocalDateTime.now();

            return new Denuncia(idDenuncia, descricao, localizacao, usuario, dataHora, statusDenuncia, categoriaSelecionada);
        } catch (MaxAttemptsExceededException e) {
            System.err.println("Tipo de entrada inválida: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Denuncia editarDenuncia(int idDenuncia, Denuncia denuncia) {
        try {
            denuncia.setIdDenuncia(idDenuncia);

            denuncia.setUsuario(new Usuario(this.digitarNomeUsuario()));
            denuncia.setDescricao(this.digitarDescricao());

            double longitude = this.digitarLongitude();
            double latitude = this.digitarLatitude();
            denuncia.setLocal(new Localizacao(latitude, longitude));

            exibirOpcoesEnum("Selecione o Status da Denúncia:", Situacao.values(), Situacao::toString, denuncia::setStatusDenuncia);
            exibirOpcoesEnum("Selecione a Categoria da Denúncia:", Categoria.values(), Categoria::toString, denuncia::setCategoria);

            denuncia.setDataHora(LocalDateTime.now());
        } catch (MaxAttemptsExceededException e) {
            System.err.println("Tipo de entrada inválida: " + e.getMessage());
            return null;
        }

        return denuncia;
    }

    private <T> void exibirOpcoesEnum(String mensagem, T[] opcoes, java.util.function.Function<T, String> formatador, java.util.function.Consumer<T> definidor) {
        System.out.println(mensagem);
        for (T opcao : opcoes) {
            System.out.println(formatador.apply(opcao));
        }

        int escolha = this.digitarStatus();
        if (escolha >= 0 && escolha < opcoes.length) {
            definidor.accept(opcoes[escolha]);
        } else {
            System.err.println("Escolha inválida.");
        }
    }

    @Override
    public void excluirDenuncia(int idDenuncia, HashMap<Integer, Denuncia> denuncias) {
        denuncias.remove(idDenuncia);
    }

    @Override
    public void visualizarDenuncia(Denuncia denuncia) {
        System.out.println("ID da Denúncia: " + denuncia.getIdDenuncia());
        System.out.println("Nome do Usuário: " + denuncia.getUsuario().getNomeUsuario());
        System.out.println("Descrição: " + denuncia.getDescricao());
        System.out.println("Localização (Longitude): " + denuncia.getLocal().longitude());
        System.out.println("Localização (Latitude): " + denuncia.getLocal().latitude());
        System.out.println("Status da Denúncia: " + denuncia.getStatusDenuncia());
        System.out.println("Categoria: " + denuncia.getCategoria());
        System.out.println("Data e Hora: " + denuncia.getDataHora());
        System.out.println("--------------------------------------");
    }
}
