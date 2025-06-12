import java.util.ArrayList;

import java.util.Scanner;

public class GerenciadorVeiculos {
    private ArrayList<Veiculo> veiculos;
    private Scanner scanner;

    public GerenciadorVeiculos() {
        veiculos = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Gerenciamento de Veículos ---");
            System.out.println("1 - Cadastrar novo veículo");
            System.out.println("2 - Listar veículos");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                opcao = 0;
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarVeiculo();
                    break;
                case 2:
                    listarVeiculos();
                    break;
                case 3:
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 3);
        scanner.close();
    }

    private void cadastrarVeiculo() {
        System.out.println("\n--- Cadastrar Novo Veículo ---");
        System.out.print("Tipo (1-Carro, 2-Moto, 3-Caminhão): ");
        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Tipo inválido. Cadastro cancelado.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Ano: ");
        int ano;
        try {
            ano = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Cadastro cancelado.");
            return;
        }

        Veiculo novoVeiculo = null;

        switch (tipo) {
            case 1:
                System.out.print("Quantidade de Portas: ");
                int qtdPortas;
                try {
                    qtdPortas = Integer.parseInt(scanner.nextLine());
                    novoVeiculo = new Carro(placa, modelo, ano, qtdPortas);
                } catch (NumberFormatException e) {
                    System.out.println("Quantidade de portas inválida. Cadastro cancelado.");
                }
                break;
            case 2:
                System.out.print("Cilindrada (cc): ");
                int cilindrada;
                try {
                    cilindrada = Integer.parseInt(scanner.nextLine());
                    novoVeiculo = new Moto(placa, modelo, ano, cilindrada);
                } catch (NumberFormatException e) {
                    System.out.println("Cilindrada inválida. Cadastro cancelado.");
                }
                break;
            case 3:
                System.out.print("Capacidade de Carga (toneladas): ");
                double capacidadeCarga;
                try {
                    capacidadeCarga = Double.parseDouble(scanner.nextLine().replace(",", ".")); // Aceita vírgula como
                                                                                                // separador decimal
                    novoVeiculo = new Caminhao(placa, modelo, ano, capacidadeCarga);
                } catch (NumberFormatException e) {
                    System.out.println("Capacidade de carga inválida. Cadastro cancelado.");
                }
                break;
            default:
                System.out.println("Tipo de veículo inválido.");
                break;
        }

        if (novoVeiculo != null) {
            veiculos.add(novoVeiculo);
            System.out.println("Veículo cadastrado com sucesso!");
        } else {
            System.out.println("Falha ao cadastrar veículo.");
        }
    }

    private void listarVeiculos() {
        System.out.println("\n--- Veículos Cadastrados ---");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (Veiculo v : veiculos) {
            v.exibirDados();
        }
    }

    public static void main(String[] args) {
        GerenciadorVeiculos app = new GerenciadorVeiculos();
        app.exibirMenu();
    }
}
