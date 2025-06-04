package com.georgef.automatas.core;

/**
 *
 * @author georgef
 */    
import com.georgef.automatas.model.*;
import java.util.*;

public class AFDMealy {
    private Set<State> states = new HashSet<>();
    private State InitialState;
    private Map<StateSymbol, MealyTransitions> transitions = new HashMap<>();
    
    public void addState(State state){
        states.add(state);
    }
    
    public void defineInitialState(State state){
        addState(state);
        this.InitialState = state;
    }
    
    public void addTransition(State origin, int entry, State destination, int result) {
    addState(origin);
    addState(destination);
    StateSymbol key = new StateSymbol(origin, (char) entry);
    transitions.put(key, new MealyTransitions(origin, entry, destination, result));
    }

    public List<Integer> process(List<Integer> entries){
       List<Integer> results = new ArrayList<>();
        State current = InitialState;

        for (int entry : entries) {
            StateSymbol key = new StateSymbol(current, (char) entry);
            MealyTransitions transition = transitions.get(key);

            if (transition == null) {
                throw new RuntimeException("Transição indefinida para o estado " + current + " com entrada " + entry);
            }

            results.add(transition.getResult());
            current = transition.getDestinationState();
        }

        return results; 
    }
}
