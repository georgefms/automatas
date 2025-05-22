package com.georgef.automatas.app;

import com.georgef.automatas.core.AFDAutomata;
import com.georgef.automatas.model.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author georgef
 */
public class Main {

    public static void main(String[] args) {
        AFDAutomata afd = new AFDAutomata();
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");
        Set<Character> alphabet = new HashSet<>(Arrays.asList('1', '0'));
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
        System.out.println(afd.process("0"));
        System.out.println(afd.process("0100"));
        System.out.println(afd.process("100"));
        
        //Rejeitar:
        System.out.println(afd.process("1"));
        System.out.println(afd.process("1001"));
        System.out.println(afd.process("010"));
        System.out.println(afd.process("0001"));
    }
}
