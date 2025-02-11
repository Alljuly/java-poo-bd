package br.edu.ifal;


import br.edu.ifal.domain.Client;
import br.edu.ifal.domain.Order;
import br.edu.ifal.domain.Product;
import br.edu.ifal.domain.ProductOrder;
import br.edu.ifal.service.ClientService;
import br.edu.ifal.service.OrderService;
import br.edu.ifal.service.ProductOrderService;
import br.edu.ifal.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);

        ProductService productService = new ProductService();
        ClientService clientService = new ClientService();
        OrderService orderService = new OrderService();
        ProductOrderService productOrderService = new ProductOrderService();
        int option;

        do {
            System.out.println("========== MENU PRINCIPAL ==========");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Buscar Produto (procurar produto pelo ID)");
            System.out.println("4. Listar todos os produtos disponíveis (Quantidade em estoque e Valor unitário)");
            System.out.println("5. Efetuar Venda");
            System.out.println("6. Listar Vendas Realizadas");
            System.out.println("0. Sair");
            System.out.println("====================================");
            System.out.print("Digite a opção desejada: ");
            option = e.nextInt();
            e.nextLine();


            switch (option) {
                case 1:
                    System.out.println("=== Cadastrar Produto ===");
                    System.out.print("Digite o nome do produto: ");
                    String productName = e.nextLine();

                    System.out.print("Digite a quantidade em estoque: ");
                    int q = e.nextInt();

                    System.out.print("Digite o valor unitário do produto: ");
                    double value = e.nextDouble();
                    e.nextLine();

                    Product p = new Product(productName, value, q);

                    String res = productService.addNewProduct(p);
                    System.out.println(res);

                    break;

                case 2:

                    System.out.println("=== Cadastrar Cliente ===");
                    System.out.print("Digite o nome do cliente: ");
                    String clientName = e.nextLine();

                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = e.nextLine();

                    System.out.print("Digite o endereço do cliente: ");
                    String address = e.nextLine();

                    System.out.print("Digite o contato do cliente: ");
                    String contact = e.nextLine();
                    Client c = new Client(clientName, cpf, address, contact);

                    res = clientService.addClient(c);
                    System.out.println(res);

                    break;


                case 3:
                    System.out.println("=== Buscar Produto ===");
                    System.out.print("Digite o ID do produto: ");
                    int productId = e.nextInt();
                    e.nextLine();

                    Product productRes = productService.getProduct(productId);
                    if(productRes != null){
                        System.out.println(productRes.toString());
                    }else{
                    System.out.println("=== Lista Vazia ===");
                    }

                    break;




                case 4:
                    System.out.println("=== Listar Produtos Disponíveis ===");
                    productService.getAllProducts().forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("=== Efetuar Venda ===");

                    System.out.print("Digite o CPF do vendedor: ");
                    String vendedor = e.nextLine();

                    System.out.print("Digite o CPF do cliente: ");
                    String cliente = e.nextLine();

                    System.out.print("Digite o ID do produto: ");
                    int idProdutoVenda = e.nextInt();

                    if(productService.productExists(idProdutoVenda)){

                        System.out.print("Digite a quantidade vendida: ");
                        int quantidadeVendida = e.nextInt();
                        e.nextLine();

                            if(quantidadeVendida <= productService.getProduct(idProdutoVenda).getQuantity()){
                                Order order = new Order(cliente, vendedor, idProdutoVenda, quantidadeVendida);
                                int orderCreated = orderService.addNewOrder(order);
                                order.setId(orderCreated);
                                ProductOrder po = new ProductOrder(orderCreated, idProdutoVenda, quantidadeVendida);

                                String itemPedidoRes = productOrderService.addNewProductOrder(po);

                                System.out.println(itemPedidoRes);

                                Product updatedProduct = productService.getProduct(idProdutoVenda);
                                updatedProduct.setQuantity(updatedProduct.getQuantity() - quantidadeVendida);
                                productService.updateProduct(updatedProduct);


                        }
                            else {
                                System.out.println("Quantidade insuficiente, aguarde estoque ser atualizado.");
                                System.out.println("Disponivel:  " + productService.getProduct(idProdutoVenda).getQuantity() + "unidades desse produto.");

                            }
                    }
                    else{
                    System.out.println("Produto não existe, verifique a lista disponível na opção 4");

                    }
                    break;
                case 6:
                    System.out.println("=== Listar Vendas Realizadas ===");
                    List<Order> orders = orderService.getAllOrders();

                    if(!orders.isEmpty()) {
                        for (Order o : orders) {
                            System.out.println(o.toString());
                        }
                    }

                    break;

                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, digite uma opção válida.");
                    break;
            }

        } while (option != 0);

        e.close();
    }
}

//# 04881132105
//        # 25806037606
//        #39