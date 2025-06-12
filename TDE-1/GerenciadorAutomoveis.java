import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GerenciadorAutomoveis {
    private static final String NOME_ARQUIVO = "automoveis.txt";
    private ArrayList<Automovel> automoveis;
    private Scanner scanner;

    public GerenciadorAutomoveis() {
        automoveis = new ArrayList<>();
        scanner = new Scanner(System.in);
        carregarDados(); // Carrega os dados ao iniciar o programa
    }

    // 1. Inclusão
    public void incluirAutomovel() {
        System.out.println("\n--- Incluir Automóvel ---");
        System.out.print("Digite a placa do automóvel: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        if (buscarAutomovelPorPlaca(placa) != null) {
            System.out.println("Erro: Automóvel com a placa '" + placa + "' já existe.");
            return;
        }

        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o ano: ");
        int ano = 0;
        try {
            ano = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Inclusão cancelada.");
            return;
        }
        System.out.print("Digite o valor: ");
        double valor = 0.0;
        try {
            valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Inclusão cancelada.");
            return;
        }

        Automovel novoAutomovel = new Automovel(placa, modelo, marca, ano, valor);
        automoveis.add(novoAutomovel);
        System.out.println("Automóvel incluído com sucesso!");
    }

    // 2. Exclusão
    public void removerAutomovel() {
        System.out.println("\n--- Remover Automóvel ---");
        System.out.print("Digite a placa do automóvel a ser removido: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Automovel automovelParaRemover = buscarAutomovelPorPlaca(placa);
        if (automovelParaRemover != null) {
            automoveis.remove(automovelParaRemover);
            System.out.println("Automóvel com placa '" + placa + "' removido com sucesso.");
        } else {
            System.out.println("Automóvel com placa '" + placa + "' não encontrado.");
        }
    }

    // 3. Alteração
    public void alterarAutomovel() {
        System.out.println("\n--- Alterar Dados de Automóvel ---");
        System.out.print("Digite a placa do automóvel a ser alterado: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Automovel automovelParaAlterar = buscarAutomovelPorPlaca(placa);
        if (automovelParaAlterar != null) {
            System.out.println("Automóvel encontrado: " + automovelParaAlterar);
            System.out.println("Digite os novos dados (deixe em branco para manter o atual):");

            System.out.print("Novo modelo (" + automovelParaAlterar.getModelo() + "): ");
            String novoModelo = scanner.nextLine();
            if (!novoModelo.isEmpty()) {
                automovelParaAlterar.setModelo(novoModelo);
            }

            System.out.print("Nova marca (" + automovelParaAlterar.getMarca() + "): ");
            String novaMarca = scanner.nextLine();
            if (!novaMarca.isEmpty()) {
                automovelParaAlterar.setMarca(novaMarca);
            }

            System.out.print("Novo ano (" + automovelParaAlterar.getAno() + "): ");
            String novoAnoStr = scanner.nextLine();
            if (!novoAnoStr.isEmpty()) {
                try {
                    automovelParaAlterar.setAno(Integer.parseInt(novoAnoStr));
                } catch (NumberFormatException e) {
                    System.out.println("Ano inválido. Mantendo o ano atual.");
                }
            }

            System.out.print("Novo valor (" + String.format("%.2f", automovelParaAlterar.getValor()) + "): ");
            String novoValorStr = scanner.nextLine();
            if (!novoValorStr.isEmpty()) {
                try {
                    automovelParaAlterar.setValor(Double.parseDouble(novoValorStr.replace(",", ".")));
                } catch (NumberFormatException e) {
                    System.out.println("Valor inválido. Mantendo o valor atual.");
                }
            }
            System.out.println("Dados do automóvel alterados com sucesso!");
        } else {
            System.out.println("Automóvel com placa '" + placa + "' não encontrado.");
        }
    }

    // 4. Consulta
    public void consultarAutomovelPorPlaca() {
        System.out.println("\n--- Consultar Automóvel por Placa ---");
        System.out.print("Digite a placa do automóvel a ser consultado: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        Automovel automovel = buscarAutomovelPorPlaca(placa);
        if (automovel != null) {
            System.out.println("Automóvel encontrado:");
            System.out.println(automovel);
        } else {
            System.out.println("Automóvel com placa '" + placa + "' não encontrado.");
        }
    }

    // Método auxiliar para buscar automóvel por placa
    private Automovel buscarAutomovelPorPlaca(String placa) {
        for (Automovel auto : automoveis) {
            if (auto.getPlaca().equalsIgnoreCase(placa)) {
                return auto;
            }
        }
        return null;
    }

    // 5. Listagem ordenada
    public void listarAutomoveisOrdenados() {
        System.out.println("\n--- Listar Automóveis ---");
        if (automoveis.isEmpty()) {
            System.out.println("Nenhum automóvel cadastrado.");
            return;
        }

        System.out.println("Ordenar por:");
        System.out.println("1 - Placa");
        System.out.println("2 - Modelo");
        System.out.println("3 - Marca");
        System.out.print("Escolha uma opção de ordenação: ");
        String opcao = scanner.nextLine();

        ArrayList<Automovel> listaOrdenada = new ArrayList<>(automoveis); // Cria uma cópia para ordenar

        switch (opcao) {
            case "1":
                Collections.sort(listaOrdenada, Comparator.comparing(Automovel::getPlaca));
                System.out.println("\n--- Automóveis Ordenados por Placa ---");
                break;
            case "2":
                Collections.sort(listaOrdenada, Comparator.comparing(Automovel::getModelo));
                System.out.println("\n--- Automóveis Ordenados por Modelo ---");
                break;
            case "3":
                Collections.sort(listaOrdenada, Comparator.comparing(Automovel::getMarca));
                System.out.println("\n--- Automóveis Ordenados por Marca ---");
                break;
            default:
                System.out.println("Opção de ordenação inválida. Listando sem ordenação específica.");
                break;
        }

        for (Automovel auto : listaOrdenada) {
            System.out.println(auto);
        }
    }

    // 6. Persistência de dados - Salvar
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Automovel auto : automoveis) {
                writer.write(auto.toCSV());
                writer.newLine();
            }
            System.out.println("Dados salvos com sucesso em '" + NOME_ARQUIVO + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    // 6. Persistência de dados - Carregar
    private void carregarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            System.out.println("Arquivo de dados '" + NOME_ARQUIVO + "' não encontrado. Iniciando com lista vazia.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Automovel auto = Automovel.fromCSV(linha);
                if (auto != null) {
                    automoveis.add(auto);
                }
            }
            System.out.println("Dados carregados com sucesso de '" + NOME_ARQUIVO + "'.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
        }
    }

    // Menu principal
    public void exibirMenu() {
        String opcao;
        do {
            System.out.println("\n--- Menu de Gerenciamento de Automóveis ---");
            System.out.println("1 - Incluir automóvel");
            System.out.println("2 - Remover automóvel");
            System.out.println("3 - Alterar dados de automóvel");
            System.out.println("4 - Consultar automóvel por placa");
            System.out.println("5 - Listar automóveis (ordenado)");
            System.out.println("6 - Salvar e sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    incluirAutomovel();
                    break;
                case "2":
                    removerAutomovel();
                    break;
                case "3":
                    alterarAutomovel();
                    break;
                case "4":
                    consultarAutomovelPorPlaca();
                    break;
                case "5":
                    listarAutomoveisOrdenados();
                    break;
                case "6":
                    salvarDados();
                    System.out.println("Saindo do programa. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (!opcao.equals("6"));
        scanner.close();
    }

    public static void main(String[] args) {
        GerenciadorAutomoveis app = new GerenciadorAutomoveis();
        app.exibirMenu();
    }
}