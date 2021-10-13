import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import Modelo.Produto;
import Modelo.Venda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;


public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao = 0;
        Scanner in = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        List<Venda> vendas = new ArrayList<>();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.println("------------MENU-----------");
            System.out.println("1 - Incluir Produto");
            System.out.println("2 - Consultar Produto");
            System.out.println("3 - Listagem de Produtos");
            System.out.println("4 - Vendas por período – detalhado");
            System.out.println("5 - Realizar Venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                System.out.println("-----------CADASTRO DE PRODUTOS------------");
                    System.out.println("Código:");
                    int codigo = in.nextInt();
                    System.out.println("Nome:");
                    in.nextLine();
                    String nome = in.nextLine();
                    System.out.println("Valor:");
                    double valor = in.nextDouble();
                    in.nextLine();
                    System.out.println("Quantidade em estoque:");
                    int qtdEstoque = in.nextInt();
                    in.nextLine();
                    produtos.add(new Produto(codigo, nome, valor, qtdEstoque));
                    System.out.println("\nProduto cadastrado com sucesso.");
                    voltarMenu(in);
                    continue;
                
            } else if (opcao == 2) {
                System.out.println("-----------CONSULTA------------");
                // Se não tem ninguém cadastrado, caio fora
                if (produtos.isEmpty()) {
                    System.out.println("\nNão há produtos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Nome do produto:");
                    String pesqnome = in.nextLine();
                    for (Produto produto : produtos) {
                        if(produto.getNome().equalsIgnoreCase(pesqnome)){
                            System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n", "CODIGO", "NOME", "PREÇO", "QUANTIDADE");
                            System.out.println("----------------------------------------------------------------------------------------------------------------");
                            System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n", produto.getCodigo(), produto.getNome(), produto.getValor(), produto.getQtdEstoque());
                            System.out.println("----------------------------------------------------------------------------------------------------------------");
                }
              
                voltarMenu(in);
                continue;
            }


            } else if (opcao == 3) {
                System.out.println("-----------LISTA DE PRODUTOS------------");
                if (produtos.isEmpty()) {
                    System.out.println("\nNão há produtos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n", "CODIGO", "NOME", "PREÇO", "QUANTIDADE");
                for (Produto produto : produtos) {
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\n", produto.getCodigo(), produto.getNome(), produto.getValor(), produto.getQtdEstoque());
                    System.out.println("----------------------------------------------------------------------------------------------------------------");

                }
                DoubleSummaryStatistics dados = produtos.stream()
                .collect(Collectors.summarizingDouble(Produto::getValor));
                System.out.printf("Valor minimo: %s          Valor maximo: %s          Valor médio: %s \n\n", dados.getMin(), dados.getMax(), dados.getAverage());
                voltarMenu(in);
                continue;


            } else if (opcao == 4) {
                System.out.println("-------------- VENDAS POR PERÍODO-------------- \n");
                if (vendas.isEmpty()) {
                    System.out.println("\nAinda não foi realizada nenhuma venda.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Data de inicio:");
                String dataInicial = in.nextLine();
                System.out.println("Data final:");
                String dataFinal = in.nextLine();

                List<Venda> filtrarVendas = vendas.stream().filter(p -> {
                    Venda v = (Venda)p;
                    return (v.getDataDeVenda().isEqual(LocalDate.parse(dataInicial, df)) || v.getDataDeVenda().isEqual(LocalDate.parse(dataFinal, df)))|| (v.getDataDeVenda().isBefore(LocalDate.parse(dataFinal, df)) && (v.getDataDeVenda().isAfter(LocalDate.parse(dataInicial, df))));
                }).collect(Collectors.toList());

                for (Venda f : filtrarVendas) {
                    System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", "DATA", "NOME", "QUANTIDADE", "VALOR UNITARIO", "VALOR TOTAL");
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%-16.15s\t%-18.15s\t%-20.20s\t%-20.15s\t%-20.15s\n", f.getDataDeVenda(), f.getProduto().getNome(), f.getQuantVendida(), f.getProduto().getValor(), f.Calcular());
                    System.out.println("----------------------------------------------------------------------------------------------------------------");
                    
                }
            } else if (opcao == 5) {
                System.out.println("-------------- VENDAS -------------- \n");
                if (produtos.isEmpty()) {
                    System.out.println("\nAinda não existem produtos cadastrados.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Nome do produto:");
                String prod = in.nextLine();
                boolean acheiprod = false;
                for (Produto produto : produtos){
                    if(produto.getNome().equalsIgnoreCase(prod)){
                        acheiprod = true;
                        System.out.println("Insita a data nesse formato - dd/mm/aaaa");
                        String data = in.nextLine();
                        System.out.println("Quantidade:");
                        int quantidade = in.nextInt();
                        in.nextLine();
                        if(produto.getQtdEstoque() < quantidade){
                            System.out.println("Não há quantidade suficiente em estoque!");
                            voltarMenu(in);
                            continue;
                        }else{
                            produto.removerQuant(quantidade);
                            vendas.add(new Venda(quantidade, produto, LocalDate.parse(data, df)));
                            System.out.println("----VENDA CONCLUIDA----");
                            voltarMenu(in);
                            continue;
                        }
                    
                        }
                    if(acheiprod == false);
                    System.out.println("Produto não encontrado");
                    voltarMenu(in);
                    continue;
                    }
                }
                
            
            else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}