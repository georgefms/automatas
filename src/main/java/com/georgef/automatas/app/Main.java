package com.georgef.automatas.app;

import com.georgef.automatas.demo.AfdDemo;
import java.util.Scanner;
//import com.georgef.automatas.core.AFDAutomata;
//import com.georgef.automatas.model.*;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
/**
 * Ponto de entrada principal para a execução do projeto.
 * Apresenta um menu para selecionar o caso de teste.
 * @author georgef
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- MENU DE TESTES DE AUTÔMATOS (CÓDIGO ORIGINAL) ---");
            System.out.println("Escolha uma opção para executar:");
            System.out.println("1. Executar runnerCase1 (Questão 1.a: '1' seguido de '00')");
            System.out.println("2. Executar runnerCase2 (Questão 1.b: final 'b' e 'a' par)");
            System.out.println("3. Executar runnerCase3 (Questão 2: Busca por 'computador')");
            System.out.println("4. Executar runnerCase4 (Questão 3: Máquina de Vendas)");
            System.out.println("0. Sair");
            System.out.print("Sua escolha: ");

            String escolha = scanner.nextLine();
            System.out.println("-------------------------------------------------------\n");

            switch (escolha) {
                case "1" -> AfdDemo.runnerCase1();
                case "2" -> AfdDemo.runnerCase2();
                case "3" -> AfdDemo.runnerCase3();
                case "4" -> AfdDemo.runnerCase4();
                case "0" -> {
                    System.out.println("Encerrando programa.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida! Por favor, tente novamente.");
            }
        }
    }
}