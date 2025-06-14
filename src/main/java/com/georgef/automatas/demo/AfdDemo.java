package com.georgef.automatas.demo;

import com.georgef.automatas.core.AFDAutomata;
import com.georgef.automatas.core.AFDMealy;
import com.georgef.automatas.model.State;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author georgef
 */
public class AfdDemo {
    public static void runnerCase1(){
        System.out.println("Todas as cadeias em {0,1}* que representam cada 1 seguido imediatamente de dois 0.");
        AFDAutomata afd = new AFDAutomata();
        Set<Character> alphabet = new HashSet<>(Arrays.asList('1', '0'));
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");

        afd.defineAlphabet(alphabet);
        afd.defineInitial(q0);
        afd.addFinalState(q0);

        afd.addTransition(q0, '0', q0);
        afd.addTransition(q0, '1', q1);
        afd.addTransition(q1, '0', q2);
        afd.addTransition(q1, '1', q3);
        afd.addTransition(q2, '0', q0);
        afd.addTransition(q2, '1', q3);
        afd.addTransition(q3, '0', q3);
        afd.addTransition(q3, '1', q3);

        //Deve aceitar todas: 
        System.out.println("Cadeia 0: " + afd.process("0"));
        System.out.println("Cadeia 0100: " +afd.process("0100"));
        System.out.println("Cadeia 100: " +afd.process("100"));

        //Rejeitar:
        System.out.println("Cadeia 1: " +afd.process("1"));
        System.out.println("Cadeia 1001: " +afd.process("1001"));
        System.out.println("Cadeia 010: " +afd.process("010"));
        System.out.println("Cadeia 0001: " +afd.process("0001"));
    };
    
    public static void runnerCase2(){
    System.out.println("Todas as cadeias em {a,b}* de modo que o último símbolo seja b e o número de símbolos a seja par.");
    AFDAutomata afd = new AFDAutomata();
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        Set<Character> alphabet = new HashSet<>(Arrays.asList('a', 'b'));
        afd.defineAlphabet(alphabet);

        afd.defineInitial(q0);
        afd.addFinalState(q2);
        
        afd.addTransition(q0, 'a', q1);
        afd.addTransition(q0, 'b', q2);
        afd.addTransition(q1, 'a', q0);
        afd.addTransition(q1, 'b', q3);
        afd.addTransition(q2, 'a', q1);
        afd.addTransition(q2, 'b', q2);
        afd.addTransition(q3, 'a', q0);
        afd.addTransition(q3, 'b', q3);
        
        //aceitas
        System.out.println("Cadeia b: " +afd.process("b"));
        System.out.println("Cadeia babab: " +afd.process("babab"));
        System.out.println("Cadeia baab: " +afd.process("baab"));
        System.out.println("Cadeia aab: " +afd.process("aab"));
        
        //rejeitadas
        System.out.println("Cadeia a: " +afd.process("a"));
        System.out.println("Cadeia ab: " +afd.process("ab"));
        System.out.println("Cadeia bab: " +afd.process("bab"));
        System.out.println("Cadeia aba: " +afd.process("aba"));
    };
    
    public static void runnerCase3(){
        String text = "O computador é uma máquina capaz de variados tipos de tratamento automático de informações ou processamento de dados. Entende-se por computador um sistema físico que realiza algum tipo de computação. Assumiu-se que os computadores pessoais e laptops são ícones da era da informação.O primeiro computador eletromecânico foi construído por Konrad Zuse (1910–1995).  Atualmente, um microcomputador é também chamado computador pessoal ou ainda computador doméstico.";

        AFDAutomata afdText = new AFDAutomata();

        afdText.textSetup("computador");
        
        List<Integer> positions = afdText.findOccurrences(text);

        System.out.println("Ocorrências exatas de 'computador':");
        for (int pos : positions) {
            System.out.println("Posição: " + pos );
            System.out.println("Pro: ..." + text.substring(Math.max(0, pos - 10), Math.min(text.length(), pos + 20)) + "...");
        }
    }
    
    public static void runnerCase4(){
        AFDMealy machine = new AFDMealy();

        // Estados representam o total acumulado: q0 = 0, q25 = 25, q50 = 50, ..., q75, q100, etc.
        State q0 = new State("q0");
        State q25 = new State("q25");
        State q50 = new State("q50");
        State q75 = new State("q75");

        machine.defineInitialState(q0);

        // Transições com acúmulo e liberação (transborda para reutilização do troco)
        machine.addTransition(q0, 25, q25, 0);
        machine.addTransition(q0, 50, q50, 0);
        machine.addTransition(q0, 100, q0, 1);

        machine.addTransition(q25, 25, q50, 0);
        machine.addTransition(q25, 50, q75, 0);
        machine.addTransition(q25, 100, q0, 1); // 25 + 100 = 125 -> libera e reinicia com 25 de troco

        machine.addTransition(q50, 25, q75, 0);
        machine.addTransition(q50, 50, q0, 1);
        machine.addTransition(q50, 100, q25, 1);

        machine.addTransition(q75, 25, q0, 1);
        machine.addTransition(q75, 50, q25, 1);
        machine.addTransition(q75, 100, q50, 1);

        List<Integer> entry = Arrays.asList(100,25, 25, 25, 25,100,50,50,100,100,25,50,25,50,25,25,100);
        List<Integer> result = machine.process(entry);

        System.out.println("Entrada: " + entry);
        System.out.println("Saída:   " + result);
    }

}
