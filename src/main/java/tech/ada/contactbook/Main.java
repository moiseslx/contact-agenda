package tech.ada.contactbook;

import tech.ada.contactbook.model.Agenda;
import tech.ada.contactbook.model.Contato;
import tech.ada.contactbook.model.Telefone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Agenda agenda = new Agenda();

    public static void main(String[] args) {
        System.out.println("##################\n##### AGENDA #####\n##################\n");

        System.out.println(">>>> Menu <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Remover Contatos");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Sair\n");

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n>>>> Contatos <<<<\nId | Nome ");
            agenda.getContatos().forEach(c -> System.out.println(c.toString()));

            System.out.print("\nEscolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> adicionarContato(sc);
                case 2 -> removerContato(sc);
                case 3 -> editarContato(sc);
                case 4 -> {
                    System.out.println("Saindo...");
                    sc.close();
                    System.exit(0);
                }
            }
        }
    }

    private static void adicionarContato(Scanner sc) {
        Contato contato = new Contato();

        System.out.print("Digite o nome do contato: ");
        contato.setNome(sc.nextLine());

        System.out.print("Digite o sobrenome do contato: ");
        contato.setSobreNome(sc.nextLine());

        contato.setTelefones(new ArrayList<>());

        agenda.adicionarContato(contato);

        while (true) {
            System.out.print("Deseja adicionar um telefone? (s/n) ");
            if (sc.nextLine().equalsIgnoreCase("n")) {
                break;
            }

            try{
                Telefone telefone = new Telefone();
                System.out.print("Digite o ddd: ");
                telefone.setDdd(sc.nextLine());
                System.out.print("Digite o telefone: ");
                telefone.setNumero(sc.nextLine());
                telefone.setIdContato(contato.getId());
                agenda.adicionarTelefone(contato, telefone);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println("Tente novamente...");
            }
        }

        System.out.println("Contato adicionado com sucesso!");
    }

    private static void removerContato(Scanner sc) {
        System.out.print("Digite o id do contato que deseja remover: ");
        agenda.removerContato(sc.nextLong());
        System.out.println("Contato removido com sucesso!");
    }

    private static void editarContato(Scanner sc) {
        System.out.print("Digite o id do contato que deseja editar: ");
        Long id = sc.nextLong();
        sc.nextLine();
        if (agenda.contatoExiste(id)) {

            System.out.print("Digite o novo nome do contato: ");
            String nome = sc.nextLine();

            System.out.print("Digite o novo sobrenome do contato: ");
            String sobreNome = sc.nextLine();

            agenda.editarContato(id, nome, sobreNome);

            // Operando sobre os telefones do contato
            while (true) {
                System.out.print("Deseja editar os telefones? (s/n) ");
                if (sc.nextLine().equalsIgnoreCase("n")) {
                    break;
                }

                System.out.println("Telefones deste contato:\nId | Telefone ");
                agenda.getTelefones(id).forEach(t -> System.out.println(t.getId()+ " - " + t.toString()));

                System.out.println("\nOpções: ");
                System.out.println("1 - Editar Telefone\n2 - Remover Telefone");

                System.out.print("Escolha uma opção: ");

                switch (sc.nextLine()) {
                    case "1" -> editarTelefone(sc, id);
                    case "2" -> removerTelefone(sc, id);
                }
            }

            System.out.println("Contato editado com sucesso!");
        } else {
            System.out.println("Contato não encontrado...");
        }
    }

    private static void editarTelefone(Scanner sc, Long idContato) {
        System.out.print("Digite o id do telefone que deseja editar: ");
        Long idTelefone = sc.nextLong();
        sc.nextLine();

        try{
            Telefone telefone = new Telefone();
            System.out.print("Digite o ddd: ");
            telefone.setDdd(sc.nextLine());
            System.out.print("Digite o telefone: ");
            telefone.setNumero(sc.nextLine());

            agenda.editarTelefone(idContato, idTelefone, telefone.getDdd(), telefone.getNumero().toString());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Tente novamente...");
        }
    }

    private static void removerTelefone(Scanner sc, Long idContato) {
        System.out.print("Digite o id do telefone que deseja remover: ");
        Long idTelefone = sc.nextLong();
        sc.nextLine();

        agenda.removerTelefone(idContato, idTelefone);
    }
}