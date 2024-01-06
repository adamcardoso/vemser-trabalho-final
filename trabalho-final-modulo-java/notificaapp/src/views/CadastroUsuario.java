package views;

import entities.Usuario;
import entities.enums.ClasseSocial;
import entities.enums.Etnia;
import entities.enums.Genero;
import entities.enums.TipoUsuario;
import exceptions.MaxAttemptsExceededException;
import interfaces.IUsuarioCadastro;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class CadastroUsuario implements IUsuarioCadastro{
    Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final String LIMITE_TENTATIVAS = "Você ultrapassou o número de tentativas";

    private String digitarNomeUsuario(){
        String nomeUsuario = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            nomeUsuario = scanner.nextLine();

            if (nomeUsuario.isEmpty()) {
                System.out.println("Nome do usuário não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return nomeUsuario;
    }

    private String digitarCampoNumeroCelular(){
        String numeroCelular = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            numeroCelular = scanner.nextLine();

            if (!numeroCelular.matches("\\d{10}")) {
                System.out.println("Número de celular inválido. Deve conter 10 dígitos numéricos.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return numeroCelular;
    }

    private String digitarCampoSenha(){
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;

            String senhaUsuario = scanner.nextLine();

            if (!this.validaSenha(senhaUsuario)) {
                System.out.println("Padrão de senha inválido.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
                System.out.println("""
                    entre 6 e 18 caracteres
                    pelo menos 1 número
                    pelo menos 1 letra minúscula
                    pelo menos 1 letra maiúscula
                    pelo menos 1 caracter especial (@ # $ % &)
                    """);
            }else{
                return senhaUsuario;
            }
            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return null;
    }

    private boolean validaSenha(String senhaUsuario){
        return senhaUsuario.matches("(?=.*[@#$%&])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,18}");
    }

    private Etnia digitarCampoEtnia(){
        System.out.println("""
                0 - PRETO
                1 - PARDO
                2 - BRANCO
                3 - INDIGENA
                4 - AMARELO
                """);

        int tentativas = 0;

        while (tentativas < 3){
            tentativas++;
            String etnia = scanner.nextLine();

            if (etnia.isEmpty()) {
                System.out.println("O campo etnia não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if(!this.validaEtnia(etnia)){
                System.out.printf("""
                        Digite algum dos valores informados.\nVocê possui 3 tentativas, restam: %s
                        """, (3 - tentativas));
            } else{
                return switch (etnia) {
                    case "0" -> Etnia.PRETO;
                    case "1" -> Etnia.PARDO;
                    case "2" -> Etnia.BRANCO;
                    case "3" -> Etnia.INDIGENA;
                    case "4" -> Etnia.AMARELO;
                    default -> null;
                };
            }

            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return null;
    }

    private boolean validaEtnia(String etnia){
        for(Etnia e: Etnia.values())
            if(Objects.equals(e.getValor(), etnia))
                return true;
        return false;
    }

    private LocalDate digitarCampoData(){
        int tentativas = 0;

        while(tentativas < 3){
            tentativas++;

            String dataString = scanner.nextLine();

            if(dataString.isEmpty()) {
                System.out.println("O campo faixa salarial não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3 - tentativas));
            } else if(!this.validaFormatoDataNascimento(dataString)){
                System.out.printf("""
                        A data precisa ser inserida no seguinte formato - dd-MM-yyyy.
                        Você possui 3 tentativas, restam: %s
                        """, (3 - tentativas));
            } else{
                return LocalDate.parse(dataString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            }

            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return null;
    }

    private boolean validaFormatoDataNascimento(String dataNascimento){
        return dataNascimento.matches("(?=.*[0-9]).{2}-(?=.*[0-9]).{2}-(?=.*[0-9]).{4}");
    }

    private ClasseSocial digitarCampoClasseSocial(){
        System.out.println("""
                0 - Até 2
                1 - De 2 a 4
                2 - De 4 a 10
                3 - De 10 a 20
                4 - Acima de 20
                """);
        int tentativas = 0;

        while(tentativas < 3){
            tentativas++;

            String faixaSalarial = scanner.nextLine();

            if(faixaSalarial.isEmpty()){
                System.out.println("O campo faixa salarial não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if(!this.validaFaixaSalarial(faixaSalarial)){
                System.out.printf("""
                        Digite algum dos valores informados.\nVocê possui 3 tentativas, restam: %s
                        """, (3 - tentativas));
            } else{
                return switch (faixaSalarial){
                    case "0" -> ClasseSocial.E;
                    case "1" -> ClasseSocial.D;
                    case "2" -> ClasseSocial.C;
                    case "3" -> ClasseSocial.B;
                    case "4" -> ClasseSocial.A;
                    default -> null;
                };
            }

            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return null;
    }

    private boolean validaFaixaSalarial(String faixaSalarial){
        for(ClasseSocial c: ClasseSocial.values())
            if(Objects.equals(c.getValor(), faixaSalarial))
                return true;
        return false;
    }

    private Genero digitarCampoGenero(){
        System.out.println("""
                0 - MASCULINO
                1 - FEMININO
                2 - OUTRO
                """);

        int tentativas = 0;

        while(tentativas < 3){
            tentativas++;
            String genero = scanner.nextLine();

            if(genero.isEmpty()){
                System.out.println("O campo genero não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            } else if(!this.validaGenero(genero)){
                System.out.printf("""
                        Digite algum dos valores informados.\nVocê possui 3 tentativas, restam: %s
                        """, (3 - tentativas));
            } else{
                return switch (genero){
                    case "0" -> Genero.MASCULINO;
                    case "1" -> Genero.FEMININO;
                    case "2" -> Genero.OUTRO;
                    default -> null;
                };
            }

            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }
        return null;
    }

    private boolean validaGenero(String genero){
        for(Genero g: Genero.values())
            if(Objects.equals(g.getValor(), genero))
                return true;
        return false;
    }

    private TipoUsuario digitarCampoTipoDeUsuario(){
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            System.out.println(tipoUsuario.ordinal() + " - " + tipoUsuario);
        }

        int opcaoTipo = 0;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            opcaoTipo = scanner.nextInt();

            if (opcaoTipo < 0 || opcaoTipo >= TipoUsuario.values().length) {
                System.out.println("Opção de tipo de usuário inválida.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new MaxAttemptsExceededException(LIMITE_TENTATIVAS);
            }
        }

        return TipoUsuario.values()[opcaoTipo];
    }
    @Override
    public Usuario cadastrarUsuario() {
        Usuario usuario = new Usuario();
        try {
            int idUsuario = random.nextInt(1000);
            usuario.setIdUsuario(idUsuario);

            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = this.digitarNomeUsuario();
            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = this.digitarCampoNumeroCelular();
            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            System.out.println("""
                    mínimo 6 e máximo 18 caracteres
                    pelo menos 1 número
                    pelo menos 1 letra minúscula
                    pelo menos 1 letra maiúscula
                    pelo menos 1 caracter especial (@ # $ % &)
                    """);
            String senhaUsuario = this.digitarCampoSenha();
            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            Etnia etnia = this.digitarCampoEtnia();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            LocalDate dataNascimento = this.digitarCampoData();
            usuario.setDataNascimento(dataNascimento);

            System.out.println("Digite sua faixa salárial:");
            ClasseSocial classesocial = this.digitarCampoClasseSocial();
            usuario.setClasseSocial(classesocial);

            System.out.println("Digite o Genero:");
            Genero genero = this.digitarCampoGenero();
            usuario.setGeneroUsuario(genero);

            System.out.println("Selecione o TipoUsuario:");
            TipoUsuario tipo = this.digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (MaxAttemptsExceededException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public Usuario editarUsuario(int idUsuario, Usuario usuario) {
        try {
            usuario.setIdUsuario(idUsuario);
            System.out.println("Digite o nome do usuário:");
            String nomeUsuario = this.digitarNomeUsuario();
            usuario.setNomeUsuario(nomeUsuario);

            System.out.println("Digite o número do celular:");
            String numeroCelular = this.digitarCampoNumeroCelular();
            usuario.setNumeroCelular(numeroCelular);

            System.out.println("Digite a senha do usuário:");
            System.out.println("""
                    mínimo 6 e máximo 18 caracteres
                    pelo menos 1 número
                    pelo menos 1 letra minúscula
                    pelo menos 1 letra maiúscula
                    pelo menos 1 caracter especial (@ # $ % &)
                    """);
            String senhaUsuario = this.digitarCampoSenha();
            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            Etnia etnia = this.digitarCampoEtnia();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            LocalDate dataNascimento = this.digitarCampoData();
            usuario.setDataNascimento(dataNascimento);

            System.out.println("Digite sua faixa salárial:");
            ClasseSocial classesocial = this.digitarCampoClasseSocial();
            usuario.setClasseSocial(classesocial);

            System.out.println("Digite o Genero:");
            Genero genero = this.digitarCampoGenero();
            usuario.setGeneroUsuario(genero);

            System.out.println("Selecione o TipoUsuario:");
            TipoUsuario tipo = this.digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (MaxAttemptsExceededException e) {
            System.out.println("Tipo de entrada inválida: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public void excluirUsuario(int idUsuario, HashMap<Integer, Usuario> usuario) {
        if (usuario.containsKey(idUsuario)){
            usuario.remove(idUsuario);
        } else
            System.out.println("Usuario não encontrado");
    }

    @Override
    public void visualizarUsuario(Usuario usuario) {
        System.out.println("ID do Usuário: " + usuario.getIdUsuario());
        System.out.println("Nome do Usuário: " + usuario.getNomeUsuario());
        System.out.println("Número do Celular: " + usuario.getNumeroCelular());
        System.out.println("Senha do Usuário: " + usuario.getSenhaUsuario());
        System.out.println("Etnia do Usuário: " + usuario.getEtniaUsuario());
        System.out.println("Data Nascimento: " + usuario.getDataNascimento());
        System.out.println("Classe Social: " + usuario.getClasseSocial());
        System.out.println("Genero: " + usuario.getGeneroUsuario());
        System.out.println("Tipo do Usuário: " + usuario.getTipoUsuario());
    }
}
