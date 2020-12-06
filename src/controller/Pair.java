package controller;

/**
 * identifies and gets pair values
 * @param <A>
 * @param <B>
 */

public class Pair<A, B> {
    private A first;
    private B second;

    /**
     * calls super and sets pair values
     * @param first
     * @param second
     */

    public Pair(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    /**
     * gets first pair value
     * @return first
     */

    public A getFirst() {
        return first;
    }

    /**
     * sets first pair value
     */

    public void setFirst(A first) {
        this.first = first;
    }

    /**
     * gets second pair value
     * @return secoond
     */

    public B getSecond() {
        return second;
    }

    /**
     * sets second pair value
     */

    public void setSecond(B second) {
        this.second = second;
    }
}