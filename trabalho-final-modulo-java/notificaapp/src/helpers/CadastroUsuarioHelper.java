package helpers;

import entities.enums.ClasseSocial;
import entities.enums.Etnia;
import entities.enums.Genero;
import entities.enums.TipoUsuario;
import exceptions.MaxAttemptsExceededException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class CadastroUsuarioHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LIMITE_TENTATIVAS = "Você ultrapassou o número de tentativas";
    private static final Random random = new Random();

    protected int gerarNumeroAleatorio(int limiteSuperior) {
        return random.nextInt(limiteSuperior);
    }

    protected String digitarCampo(String regex) throws MaxAttemptsExceededException {
        String campo;
        int tentativas = 0;

        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.out.println("Campo não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (!campo.matches(regex)) {
                System.out.println("Formato inválido.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                return campo;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    protected LocalDate digitarCampoData() throws MaxAttemptsExceededException {
        int tentativas = 0;

        while (tentativas < 3) {
            tentativas++;

            String dataString = scanner.nextLine();

            if (dataString.isEmpty()) {
                System.out.println("O campo data de nascimento não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (!validaFormatoDataNascimento(dataString)) {
                System.out.printf("A data precisa ser inserida no seguinte formato - dd-MM-yyyy.\nVocê possui 3 tentativas, restam: %s%n", (3 - tentativas));
            } else {
                return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    private boolean validaFormatoDataNascimento(String dataNascimento) {
        return dataNascimento.matches("(?=.*[0-9]).{2}-(?=.*[0-9]).{2}-(?=.*[0-9]).{4}");
    }

    protected Etnia digitarCampoEtnia() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - PRETO
                1 - PARDO
                2 - BRANCO
                3 - INDIGENA
                4 - AMARELO
                """);

        String regex = "[0-4]";
        String mensagem = "Digite algum dos valores informados.";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String etnia = digitarCampo(regex);

            if (!validaEtnia(etnia)) {
                System.out.println(mensagem + "\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                return Etnia.values()[Integer.parseInt(etnia)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    protected boolean validaEtnia(String etnia) {
        return etnia.matches("[0-4]");
    }

    protected String digitarNomeUsuario() throws MaxAttemptsExceededException {
        return digitarCampo("[A-Za-zÀ-ÖØ-öø-ÿ ]+");
    }

    protected String digitarCampoNumeroCelular() throws MaxAttemptsExceededException {
        return digitarCampo("\\d{10,11}");
    }

    protected String digitarCampoSenha() throws MaxAttemptsExceededException {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,18}$";
        String mensagem = """
            Mínimo 6 e máximo 18 caracteres
            Pelo menos 1 número
            Pelo menos 1 letra minúscula
            Pelo menos 1 letra maiúscula
            Pelo menos 1 caracter especial (@ # $ % &)
            """;

        return digitarCampo(regex, mensagem);
    }

    protected String digitarCampo(String regex, String mensagem) throws MaxAttemptsExceededException {
        String campo;
        int tentativas = 0;

        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.out.println("Campo não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (!campo.matches(regex)) {
                System.out.println(mensagem + "\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                return campo;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    protected ClasseSocial digitarCampoClasseSocial() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - BAIXA
                1 - MÉDIA
                2 - ALTA
                """);

        String regex = "[0-2]";
        String mensagem = "Digite algum dos valores informados.";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String classeSocial = digitarCampo(regex);

            if (!classeSocial.matches(regex)) {
                System.out.println(mensagem + "\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                return ClasseSocial.values()[Integer.parseInt(classeSocial)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    protected Genero digitarCampoGenero() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - MASCULINO
                1 - FEMININO
                2 - NÃO BINÁRIO
                """);

        String regex = "[0-2]";
        String mensagem = "Digite algum dos valores informados.";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String genero = digitarCampo(regex);

            if (!genero.matches(regex)) {
                System.out.println(mensagem + "\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                return Genero.values()[Integer.parseInt(genero)];
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    protected TipoUsuario digitarCampoTipoDeUsuario() throws MaxAttemptsExceededException {
        System.out.println("""
                0 - ADMINISTRADOR
                1 - USUÁRIO COMUM
                """);

        String regex = "[0-1]";
        String mensagem = "Digite algum dos valores informados.";

        int tentativas = 0;
        while (tentativas < 3) {
            tentativas++;
            String tipoUsuario = digitarCampo(regex);

            if (!tipoUsuario.matches(regex)) {
                System.out.println(mensagem + "\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
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
