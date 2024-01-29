package tech.ada.contactbook.model;

public class Telefone {
    private Long id;

    private Long idContato;
    private String ddd;
    private Long numero;

    public Telefone(Long id, String ddd, String numero) {
        this.id = id;
        setDdd(ddd);
        setNumero(numero);
    }

    public Telefone(String ddd, String numero) {
        setDdd(ddd);
        setNumero(numero);
    }

    public Telefone() {

    }

    public Long getId() {
        return id;
    }

    public Long getIdContato() {
        return idContato;
    }

    public void setIdContato(Long idContato) {
        this.idContato = idContato;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public Long getNumero() {
        return numero;
    }

    public void setDdd(String ddd) {
        String REGEX_DDD = "[0-9]{2}";
        if (!ddd.matches(REGEX_DDD)) {
            throw new IllegalArgumentException("DDD inválido. DDD deve ter 2 dígitos.");
        }

        this.ddd = ddd;
    }

    public void setNumero(String numero) {
        String REGEX_NUMERO = "[0-9]{9}|[0-9]{8}";
        if (!numero.matches(REGEX_NUMERO)) {
            throw new IllegalArgumentException("Número inválido. Número deve ter apenas 8 dígitos numéricos.");
        }

        this.numero = Long.parseLong(numero);
    }

    @Override
    public String toString() {
        String telefone = null;

        if (numero.toString().length() == 8) {
            telefone = String.format("+55 %s %s-%s", ddd, numero.toString().substring(0, 4), numero.toString().substring(4));
        }

        return telefone;
    }
}
