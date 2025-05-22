package com.georgef.automatas.core;

import com.georgef.automatas.model.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author georgef
 */
public class AFDAutomata {
    Set<State> states = new HashSet<>();
    Set<Character> alphabet= new HashSet<>();
    Map<StateSymbol, State> transitions = new HashMap<>();
    State initialState;
    Set<State> finalStates = new HashSet<>();
    
    public void addState(State state){
        states.add(state);
    }
    
    public void defineInitial(State state){
        initialState = state;
        addState(state);
    }
    
    public void addFinalState(State state){
        finalStates.add(state);
        addState(state);
    }
    
    public void defineAlphabet(Set<Character> alphabet){
        this.alphabet = new HashSet<>(alphabet);
    };
    
    
    public void AddTransition(State origin, char symbol, State destination){
        if(!alphabet.contains(symbol)){
            throw new IllegalArgumentException("Simbolo :"+ symbol + "'não pertence ao alfabeto definido");
        }
        transitions.put(new StateSymbol(origin, symbol), destination);
        addState(origin);
        addState(destination);
    }
    

    public boolean process(String chain){
        State current = initialState;
        
        for(char symbol : chain.toCharArray()){
            if(!alphabet.contains(symbol)){
                System.out.println("Simbolo '"+ symbol +"' fora do alfabeto");
                return false;
            }
            StateSymbol pair = new StateSymbol(current, symbol);
            if(!transitions.containsKey(pair)){
                System.out.println("Transicao de '"+ current+ "' com simbolo '"+ symbol +"' Nao definida");
                return false;
            }
            current = transitions.get(pair);
        }
        
        return finalStates.contains(current);
    }
}
