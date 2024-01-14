package helpers;

import exceptions.MaxAttemptsExceededException;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CadastroDenunciaHelper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("^-?\\d*\\.\\d+$" + "|" + "^-?\\d+$");
    private static final String LIMITE_TENTATIVAS = "Você ultrapassou o número de tentativas";
    private static final Random random = new Random();
    private static final int LIMITE_SUPERIOR = 1000;

    protected int gerarNumeroAleatorio() {
        return random.nextInt(LIMITE_SUPERIOR);
    }

    public String digitarNomeUsuario() throws MaxAttemptsExceededException {
        return digitarCampo("Digite o nome do usuário:");
    }

    public String digitarDescricao() throws MaxAttemptsExceededException {
        return digitarCampo("Digite a descrição:");
    }

    public String digitarTitulo() throws MaxAttemptsExceededException {
        return digitarCampo("Digite o titulo:");
    }

    public double digitarLongitude() throws MaxAttemptsExceededException {
        return Double.parseDouble(digitarCampoNumerico("Digite a Localização (Longitude):"));
    }

    public double digitarLatitude() throws MaxAttemptsExceededException {
        return Double.parseDouble(digitarCampoNumerico("Digite a Localização (Latitude):"));
    }

    public int digitarStatus() throws MaxAttemptsExceededException {
        return Integer.parseInt(digitarCampoNumerico("Selecione o Status da Denúncia:"));
    }

    public int digitarCategoria() throws MaxAttemptsExceededException {
        return Integer.parseInt(digitarCampoNumerico("Selecione a Categoria da Denúncia:"));
    }

    private String digitarCampo(String mensagem) throws MaxAttemptsExceededException {
        String campo = null;
        int tentativas = 0;
        System.out.println(mensagem);

        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.err.println(mensagem + " não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                break;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return campo;
    }

    private String digitarCampoNumerico(String mensagem) throws MaxAttemptsExceededException {
        String campo = null;
        int tentativas = 0;
        System.out.println(mensagem);
        while (tentativas < 3) {
            tentativas++;
            campo = scanner.nextLine();

            if (campo.isEmpty()) {
                System.err.println(mensagem + " não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if (!DOUBLE_PATTERN.matcher(campo).find()) {
                System.err.println(mensagem + " deve ser um valor numérico.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else {
                break;
            }

            if (tentativas == 3) {
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return campo;
    }
}
