package com.georgef.automatas.model;

import java.util.Objects;

/**
 *
 * @author georgef
 */
public class StateSymbol {
        State state;
        char symbol;

    public StateSymbol(State state, char symbol) {
        this.state = state;
        this.symbol = symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, symbol);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof StateSymbol)) return false;
        StateSymbol pair = (StateSymbol) obj;
        return symbol == pair.symbol && Objects.equals(state, pair.state);
    }
}
