package com.georgef.automatas.demo;

import com.georgef.automatas.core.AFDAutomata;
import com.georgef.automatas.model.State;
import java.util.Arrays;
import java.util.HashSet;
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

        afd.AddTransition(q0, '0', q0);
        afd.AddTransition(q0, '1', q1);
        afd.AddTransition(q1, '0', q2);
        afd.AddTransition(q1, '1', q3);
        afd.AddTransition(q2, '0', q0);
        afd.AddTransition(q2, '1', q3);
        afd.AddTransition(q3, '0', q3);
        afd.AddTransition(q3, '1', q3);

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
        
        afd.AddTransition(q0, 'a', q1);
        afd.AddTransition(q0, 'b', q2);
        afd.AddTransition(q1, 'a', q0);
        afd.AddTransition(q1, 'b', q3);
        afd.AddTransition(q2, 'a', q1);
        afd.AddTransition(q2, 'b', q2);
        afd.AddTransition(q3, 'a', q0);
        afd.AddTransition(q3, 'b', q3);
        
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
    
    
}
