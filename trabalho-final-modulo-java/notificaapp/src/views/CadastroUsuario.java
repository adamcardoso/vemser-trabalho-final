package views;

import entities.Denuncia;
import entities.Usuario;
import entities.enums.Etnia;
import entities.enums.TipoUsuario;
import exceptions.InvalidInputException;
import interfaces.IUsuarioCadastro;

import java.text.SimpleDateFormat;
import java.util.*;

public class CadastroUsuario implements IUsuarioCadastro{
    Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

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
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
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
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return numeroCelular;
    }

    private String digitarCampoSenha(){
        String senhaUsuario = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            senhaUsuario = scanner.nextLine();

            if (senhaUsuario.length() < 6) {
                System.out.println("Senha deve conter pelo menos 6 caracteres.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return senhaUsuario;
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
            } else if(this.etniaValida(etnia)){
                System.out.printf("""
                        Digite algum dos valores informados.\nVocê possui 3 tentativas, restam: %s
                        """, (3 - tentativas));
            }else{
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
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return null;
    }

    private boolean etniaValida(String etnia){
        for(Etnia e: Etnia.values())
            if(Objects.equals(e.getValor(), etnia))
                return true;
        return false;
    }

    private Date digitarCampoData(){
        Date dataNascimento = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            String data = scanner.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);

                dataNascimento = dateFormat.parse(data);
                return dataNascimento;
            } catch (Exception e) {
                System.out.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }
            if(tentativas == 3){
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return null;
    }

    private String digitarCampoClasseSocial(){
        String classeSocial = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            classeSocial = scanner.nextLine();

            if (classeSocial.isEmpty()) {
                System.out.println("O campo classe social não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return classeSocial;
    }

    private String digitarCampoGenero(){
        String faixaSalarial = null;
        int tentativas = 0;
        while (tentativas < 3){
            tentativas++;
            faixaSalarial = scanner.nextLine();

            if (faixaSalarial.isEmpty()) {
                System.out.println("O campo genero não pode ser vazio.\nVocê possui 3 tentativas, restam: " + (3-tentativas));
            }else{
                break;
            }
            if(tentativas == 3){
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        return faixaSalarial;
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
                throw new InvalidInputException("Você ultrapassou o número de tentativas");
            }
        }
        TipoUsuario tipo = TipoUsuario.values()[opcaoTipo];

        return tipo;
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
            String senhaUsuario = this.digitarCampoSenha();
            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
            Etnia etnia = this.digitarCampoEtnia();
            usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            Date dataNascimento = this.digitarCampoData();
            usuario.setDataNascimento(dataNascimento);


            System.out.println("Digite sua faixa salárial:");
            String classesocial = this.digitarCampoClasseSocial();
            //usuario.setEtniaUsuario( classesocial);

            System.out.println("Digite o Genero:");
            String genero = this.digitarCampoGenero();
            //usuario.setEtniaUsuario(genero);


            System.out.println("Selecione o TipoUsuario:");
            TipoUsuario tipo = this.digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (InvalidInputException e) {
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
            String senhaUsuario = this.digitarCampoSenha();

            usuario.setSenhaUsuario(senhaUsuario);

            System.out.println("Digite a Etnia:");
//            String etnia = this.digitarCampoEtnia();
            //usuario.setEtniaUsuario(etnia);

            System.out.println("Digite sua data de nascimento (dd/MM/yyyy): ");
            Date dataNascimento = this.digitarCampoData();
            usuario.setDataNascimento(dataNascimento);

            System.out.println("Digite sua faixa salárial:");
            String classesocial = this.digitarCampoClasseSocial();
            //usuario.setEtniaUsuario(classesocial);

            System.out.println("Digite o Genero:");
            String genero = this.digitarCampoGenero();
            //usuario.setEtniaUsuario(genero);

            System.out.println("Selecione o TipoUsuario:");
            TipoUsuario tipo = this.digitarCampoTipoDeUsuario();
            usuario.setTipoUsuario(tipo);

        } catch (InvalidInputException e) {
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
