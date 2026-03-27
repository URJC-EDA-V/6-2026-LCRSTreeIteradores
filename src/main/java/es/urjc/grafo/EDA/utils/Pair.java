package es.urjc.grafo.EDA.utils;

import java.util.Objects;

public class Pair<T1, T2> {

    private T1 first;
    private T2 second;

    public Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public void setFirst(T1 first) {
        this.first = first;
    }

    public T2 getSecond() {
        return second;
    }

    public void setSecond(T2 second) {
        this.second = second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Pair other = (Pair) obj;
        if (first == null) {
            if (other.first != null) return false;
        } else if (!first.equals(other.first)) return false;
        if (second == null) {
            return other.second == null;
        } else return second.equals(other.second);
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

}
