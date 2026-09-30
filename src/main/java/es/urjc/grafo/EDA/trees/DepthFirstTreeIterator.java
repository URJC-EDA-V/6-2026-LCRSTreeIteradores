package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;

public class DepthFirstTreeIterator<T> implements Iterator<T> {

    Iterator<Position<T>> positionsIterator;

    public DepthFirstTreeIterator(Tree<T> tree, Position<T> root) {
        this.positionsIterator = new DepthFirstTreePositionsIterator<>(tree, root);
    }

    public DepthFirstTreeIterator(Tree<T> tree) {
        this(tree, tree.root());
    }

    @Override
    public boolean hasNext() {
        return this.positionsIterator.hasNext();
    }

    /**
     * This method visits the nodes of a tree by following a depth-first order
     */
    @Override
    public T next() {
        return this.positionsIterator.next().getElement();
    }

}
