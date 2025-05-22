package com.georgef.automatas.model;

import java.util.Objects;

/**
 *
 * @author georgef
 */
public class State {
    String label;

    public State(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof State)) return false;
        State state = (State) obj;
        return Objects.equals(label, state.label);
    }
}
