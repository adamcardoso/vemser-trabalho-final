package helpers;

import models.enums.ClasseSocial;
import models.enums.Etnia;
import models.enums.Genero;
import models.enums.TipoUsuario;
import exceptions.MaxAttemptsExceededException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class CadastroUsuarioHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LIMITE_TENTATIVAS = "Você ultrapassou o número de tentativas";
    private static final String MENSAGEM_VALORES_INFORMADOS = "Digite algum dos valores informados.";
    private static final String MENSAGEM_TENTATIVAS_RESTANTES = "Você possui 3 tentativas, restam: ";
    private static final int LIMITE_SUPERIOR = 1000;

    protected String digitarCampo(String regex) throws MaxAttemptsExceededException {
        String campo;
        System.out.println("Digite o valor: ");
        int tentativas = 0;

        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.out.println("Campo não pode ser vazio.\n "+ MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else if (!campo.matches(regex)) {
                System.out.println("Formato inválido.\n "+ MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return campo;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public LocalDate digitarCampoData() throws MaxAttemptsExceededException {
        int tentativas = 0;
        System.out.println("Digite o campo data: ");

        while (tentativas < 3) {
            tentativas++;

            String dataString = scanner.nextLine();

            if (dataString.isEmpty()) {
                System.out.println("O campo data de nascimento não pode ser vazio.\n "+ MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else if (!validaFormatoDataNascimento(dataString)) {
                System.out.printf("A data precisa ser inserida no seguinte formato - dd-MM-yyyy.%n "+ MENSAGEM_TENTATIVAS_RESTANTES + "%s%n", (3 - tentativas));
            } else {
                return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public boolean validaFormatoDataNascimento(String dataNascimento) {
        return dataNascimento.matches("(?=.*\\d).{2}-(?=.*\\d).{2}-(?=.*\\d).{4}");
    }

    public Etnia digitarCampoEtnia() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - PRETO
                1 - PARDO
                2 - BRANCO
                3 - INDIGENA
                4 - AMARELO
                """);

        String regex = "[0-4]";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String etnia = digitarCampo(regex);

            if (!validaEtnia(etnia)) {
                System.out.println(MENSAGEM_VALORES_INFORMADOS + "\n"+ MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return Etnia.values()[Integer.parseInt(etnia)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public boolean validaEtnia(String etnia) {
        return etnia.matches("[0-4]");
    }

    public String digitarNomeUsuario() throws MaxAttemptsExceededException {
        return digitarCampo("[A-Za-zÀ-ÖØ-öø-ÿ ]+");
    }

    public String digitarCampoNumeroCelular() throws MaxAttemptsExceededException {
        return digitarCampo("\\d{10,11}");
    }

    public String digitarCampoSenha() throws MaxAttemptsExceededException {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,18}$";
        String mensagem = """
        Mínimo 6 e máximo 18 caracteres
        Pelo menos 1 número
        Pelo menos 1 letra minúscula
        Pelo menos 1 letra maiúscula
        Pelo menos 1 caracter especial (@ # $ % &)
        """;

        return digitarCampoValidado(regex, mensagem);
    }

    public String digitarCampoValidado(String regex, String mensagem) throws MaxAttemptsExceededException {
        String campo;
        int tentativas = 0;
        System.out.println("Digite o valor: ");

        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.out.println("Campo não pode ser vazio.\n" + MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else if (!campo.matches(regex)) {
                System.out.println(mensagem + "\n" + MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return campo;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public ClasseSocial digitarCampoClasseSocial() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - CLASSE A
                1 - CLASSE B
                2 - CLASSE C
                3 - CLASSE D
                4 - CLASSE E
                """);

        String regex = "[0-2]";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String classeSocial = digitarCampo(regex);

            if (!classeSocial.matches(regex)) {
                System.out.println(MENSAGEM_VALORES_INFORMADOS + MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return ClasseSocial.values()[Integer.parseInt(classeSocial)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public Genero digitarCampoGenero() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - MASCULINO
                1 - FEMININO
                2 - OUTRO
                """);

        String regex = "[0-2]";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String genero = digitarCampo(regex);

            if (!genero.matches(regex)) {
                System.out.println(MENSAGEM_VALORES_INFORMADOS + MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return Genero.values()[Integer.parseInt(genero)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    public TipoUsuario digitarCampoTipoDeUsuario() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - ADMINISTRADOR
                1 - USUÁRIO COMUM
                """);

        String regex = "[0-1]";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String tipoUsuario = digitarCampo(regex);

            if (!tipoUsuario.matches(regex)) {
                System.out.println(MENSAGEM_VALORES_INFORMADOS + MENSAGEM_TENTATIVAS_RESTANTES + (3 - tentativas));
            } else {
                return TipoUsuario.values()[Integer.parseInt(tipoUsuario)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }
}