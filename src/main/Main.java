package main;

import domain.Produto;
import service.ProdutoService;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    private static Map<String, List<Produto>> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            clear();
            menu();
            System.out.print(">> ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:

                        clear();
                        map = ProdutoService.unser();
                        System.out.println("==========Adicionar==========");
                        System.out.println("Digite o nome do produto");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o preço do produto" + '\n');
                        double preco = scanner.nextDouble();
                        scanner.nextLine();
                        Produto novoProduto = Produto.builder()
                                .nome(nome)
                                .preco(preco)
                                .quantidade(1)
                                .build();

                        if (map.containsKey(nome)) {
                            List<Produto> list = map.get(nome);
                            Produto existente = list.getFirst();
                            if (novoProduto.equals(existente)) {
                                existente.setQuantidade(existente.getQuantidade() + 1);
                            } else {
                                list.add(novoProduto);
                            }
                        } else {
                            map.put(nome, new ArrayList<>(List.of(novoProduto)));
                        }
                        ProdutoService.ser(map);
                        break;
                    case 2:
                        map = ProdutoService.unser();
                        clear();
                        System.out.println("==========Remover==========");
                        System.out.println("Digite o nome, enter para cancelar");
                        String nomeRemov = scanner.nextLine();
                        if (!nomeRemov.trim().isEmpty()) {

                            System.out.println("Digite a quantidade , maximo :'" + map.get(nomeRemov).getFirst().getQuantidade() + "'");
                            int quantidade = scanner.nextInt();
                            if (quantidade >= map.get(nomeRemov).getFirst().getQuantidade()) {
                                map.remove(nomeRemov);
                            } else {
                                map.get(nomeRemov).getFirst().setQuantidade(map.get(nomeRemov).getFirst().getQuantidade() - quantidade);
                            }
                        } else{
                            System.out.println("Cancelando...");
                            TimeUnit.MILLISECONDS.sleep(250);
                        }
                        ProdutoService.ser(map);
                        break;
                    case 3:
                        listAll();
                        System.out.println();
                        System.out.println("Aperte enter para sair");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Saindo......");
                        break;
                }
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } while (choice != 4 && choice != 0);


    }

    private static void menu() {
        System.out.println("==========Menu=========");
        System.out.println("Adicionar produto : [1]");
        System.out.println("Remover produto(s): [2]");
        System.out.println("Listar os produtos: [3]");
        System.out.println("Sair : [4]");
    }

    private static void clear() {
//        try {
//            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println();
    }

    private static void listAll() {

        System.out.print("_".repeat(99) + "\n");
        System.out.println("|                               |                                 |                               |");
        System.out.println("| NOME                          | PREÇO                           | QUANTIDADE                    |");
        System.out.println("|                               |                                 |                               |");
        System.out.print("_".repeat(99) + '\n');
        Map<String, List<Produto>> produtos = ProdutoService.unser();
        List<Produto> collect = produtos.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        for (Produto produto : collect) {
            System.out.println(produto.toString());
        }


    }
}
