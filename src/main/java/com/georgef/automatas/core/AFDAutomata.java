package com.georgef.automatas.core;

import com.georgef.automatas.model.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
    
    
    public void addTransition(State origin, char symbol, State destination){
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
                //Muitos prints no console
                //System.out.println("Transicao de '"+ current+ "' com simbolo '"+ symbol +"' Nao definida");
                return false;
            }
            current = transitions.get(pair);
        }
        
        return finalStates.contains(current);
    }

    //Processamento de texto
    public void textSetup(String word){
        
       word = word.toLowerCase();
       
       //limpar estrutura
       this.states.clear();
       this.finalStates.clear();
       this.transitions.clear();
       this.alphabet.clear();
       
       //definir alfabeto
       for (char c : word.toCharArray()){
           this.alphabet.add(c);
       };
       
       // Cria estados q0...qN
        State prevState = new State("q0");
        defineInitial(prevState);

        for (int i = 0; i < word.length(); i++) {
            State currentState = new State("q" + (i + 1));
            addTransition(prevState, word.charAt(i), currentState);
            prevState = currentState;
        }

        addFinalState(prevState);
    };
    
    public List<Integer> findOccurrences(String text) {
    text = text.toLowerCase();
    List<Integer> occurrences = new ArrayList<>();

    int wordSize = calcWordSize();

    for (int i = 0; i <= text.length() - wordSize; i++) {
        String sub = text.substring(i, i + wordSize);

        boolean validStart = i == 0 || !Character.isLetter(text.charAt(i - 1));
        boolean validEnd = (i + wordSize == text.length()) || !Character.isLetter(text.charAt(i + wordSize));

        if (validStart && validEnd && process(sub)) {
            occurrences.add(i);
        }
    } 
    return occurrences;
    }
    
    private int calcWordSize() {
    int max = 0;
    for (State state : finalStates) {
        String name = state.getLabel();
        if (name.startsWith("q")) {
            try {
                int n = Integer.parseInt(name.substring(1));
                if (n > max) max = n;
            } catch (NumberFormatException ignored) {}
        }
    }
    return max;
    }
}
