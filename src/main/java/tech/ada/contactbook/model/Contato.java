package tech.ada.contactbook.model;

import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(String nome, String sobreNome, List<Telefone> telefones) {
        setTelefones(telefones);
        setNome(nome);
        setSobreNome(sobreNome);
    }

    public Contato() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID inválido");
        }

        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        if (sobreNome == null || sobreNome.isBlank()) {
            throw new IllegalArgumentException("Sobrenome inválido");
        }

        this.sobreNome = sobreNome;
    }

    public void setTelefones(List<Telefone> telefones) {
        if (telefones == null) {
            throw new IllegalArgumentException("Lista de telefones inválido");
        }

        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return id + " | " + nome + " " + sobreNome;
    }
}