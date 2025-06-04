package com.georgef.automatas.model;
/**
 *
 * @author georgef
 */
public class MealyTransitions {
    private State originState;
    private int entry; 
    private State destinationState;
    private int result;

    public MealyTransitions(State originState, int entry, State destinationState, int result) {
        this.originState = originState;
        this.entry = entry;
        this.destinationState = destinationState;
        this.result = result;
    }

    public State getOriginState() {
        return originState;
    }

    public int getEntry() {
        return entry;
    }

    public State getDestinationState() {
        return destinationState;
    }

    public int getResult() {
        return result;
    }
    
    
}
