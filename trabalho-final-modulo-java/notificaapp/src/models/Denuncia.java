package models;

import models.enums.Categoria;
import models.enums.StatusDenuncia;
import models.enums.TipoDenuncia;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Denuncia{
    private int idDenuncia;
    private String descricao;
    private String titulo;
    private Localizacao local;
    private Usuario usuario;
    private LocalDateTime dataHora;
    private StatusDenuncia statusDenuncia;
    private Categoria categoria;
    private int curtidas;
    private final List<String> comentarios = new ArrayList<>();
    private TipoDenuncia tipoDenuncia;
    private int validarDenuncia;

    private Long idUsuario;

    public Denuncia(int idDenuncia, String titulo, String descricao, StatusDenuncia statusDenuncia){
        this.idDenuncia = idDenuncia;
        this.titulo = titulo;
        this.descricao = descricao;
        this.statusDenuncia = statusDenuncia;
    }
    public Denuncia(String titulo, String descricao, Categoria categoria, TipoDenuncia tipoDenuncia, Long idUsuario){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        this.statusDenuncia = StatusDenuncia.getEnum("1");
        this.categoria = categoria;
        this.curtidas = 0;
        this.validarDenuncia = 0;
        this.tipoDenuncia = tipoDenuncia;
        this.idUsuario = idUsuario;
    }


    public Denuncia(int idDenuncia, String titulo, String descricao, Usuario usuario, Categoria categoria, TipoDenuncia tipoDenuncia, Long idUsuario) {
        this.idDenuncia = idDenuncia;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.dataHora = LocalDateTime.now();
        this.statusDenuncia = StatusDenuncia.getEnum("1");
        this.categoria = categoria;
        this.curtidas = 0;
        this.validarDenuncia = 0;
        this.tipoDenuncia = tipoDenuncia;
        this.idUsuario = idUsuario;
    }

    public void curtirDenuncia(){
        this.curtidas++;
    }

    public void validarDenuncia(){
        this.validarDenuncia++;
    }

    public void comentar(String comentario){
        comentarios.add(comentario);
    }

    public void imprimirDenunciaFeed(){
        System.out.println(this.usuario.getNomeUsuario());
        System.out.println("Titulo: " + this.descricao);
        System.out.println("Quantiadde de validações: " + this.validarDenuncia);
    }

    public void imprimirDetalhesDenunciaFeed(){
        this.imprimirDenunciaFeed();
        System.out.println("Total de curtidas: " + this.curtidas);
        System.out.println("Lista de comentários: ");
        try {
            for (int i = 0; i < comentarios.size(); i ++){
                String comentario = comentarios.get(i);
                System.out.println(" >" + i + ":" + comentario);

            }
        } catch ( Exception e){
            System.out.println("Nenhum comentário");
        }

    }

    public int getIdDenuncia() {
        return idDenuncia;
    }

    public void setIdDenuncia(int idDenuncia) {
        this.idDenuncia = idDenuncia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Localizacao getLocal() {
        return local;
    }

    public void setLocal(Localizacao local) {
        this.local = local;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusDenuncia getStatusDenuncia() {
        return statusDenuncia;
    }

    public void setStatusDenuncia(StatusDenuncia statusDenuncia) {
        this.statusDenuncia = statusDenuncia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getCurtidas() {
        return curtidas;
    }


    public int getValidarDenuncia() {
        return validarDenuncia;
    }

    public TipoDenuncia getTipoDenuncia() {
        return tipoDenuncia;
    }

    public void setIdUsuario(Long idUsuario){
        this.idUsuario = idUsuario;
    }
    public Long getIdUsuario(){
        return idUsuario;
    }

    public void setTipoDenuncia(TipoDenuncia tipoDenuncia) {
        this.tipoDenuncia = tipoDenuncia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
