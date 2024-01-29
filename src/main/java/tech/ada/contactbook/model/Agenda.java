package tech.ada.contactbook.model;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

    private final List<Contato> contatos;
    private final List<Telefone> telefones;

    // Distribuição de ids
    private long idsContato = 1;
    private long idsTelefone = 1;

    public Agenda() {
        this.contatos = new ArrayList<>();
        this.telefones = new ArrayList<>();
    }

    // Métodos de manipulação de Contato

    public void adicionarContato(Contato contato) {
        long id = this.idsContato++;
        contato.setId(id);
        contatos.add(contato);
    }

    public void removerContato(Long id) {
        contatos.removeIf(c -> c.getId().equals(id));
        telefones.removeIf(t -> t.getId().equals(id));
    }

    public void editarContato(Long idContato, String nome, String sobreNome) {
        contatos.forEach(c -> {
            if (c.getId().equals(idContato)) {
                c.setNome(nome);
                c.setSobreNome(sobreNome);
            }
        });
    }

    public List<Contato> getContatos() {
        return new ArrayList<>(contatos);
    }

    public boolean contatoExiste(Long idContato) {
        return contatos.stream().anyMatch(c -> c.getId().equals(idContato));
    }

    // Métodos de manipulação de Telefone

    public void adicionarTelefone(Contato contato, Telefone telefone) {
        if (telefones.stream().anyMatch(t -> t.toString().equals(telefone.toString()))) {
            throw new IllegalArgumentException("Telefone já existe");
        }

        telefone.setId(idsTelefone++);
        telefones.add(telefone);

        contatos.forEach(c -> {
            if (c.getId().equals(contato.getId())) {
                c.getTelefones().add(telefone);
            }
        });
    }

    public List<Telefone> getTelefones(Long idContato) {
        return contatos.stream()
                .filter(c -> c.getId().equals(idContato))
                .findFirst()
                .map(Contato::getTelefones)
                .orElse(null);
    }

    public void editarTelefone(Long idContato, Long idTelefone, String ddd, String numero) {
        for (Contato c : contatos) {
            if (c.getId().equals(idContato)) {
                for (Telefone t : c.getTelefones()) {
                    if (t.getId().equals(idTelefone)) {
                        Telefone telefone = new Telefone();
                        t.setDdd(ddd);
                        t.setNumero(numero);

                        if (!c.getTelefones().contains(telefone) && !telefones.contains(telefone)) {
                            c.getTelefones().add(telefone);
                            throw new IllegalArgumentException("Telefone já existe");
                        }

                        telefones.forEach(principalTelefone -> {
                            if (principalTelefone.getId().equals(idTelefone)) {
                                principalTelefone.setDdd(ddd);
                                principalTelefone.setNumero(numero);
                            }
                        });
                    }
                }
            }
        }
    }

    public void removerTelefone(Long idContato, Long idTelefone) {
        telefones.removeIf(t -> t.getId().equals(idTelefone));

        for (Contato c : contatos) {
            if (c.getId().equals(idContato)) {
                c.getTelefones().removeIf(t -> t.getId().equals(idTelefone));
            }
        }
    }
}
