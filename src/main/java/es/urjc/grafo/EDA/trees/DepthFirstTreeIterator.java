package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.*;

public class DepthFirstTreeIterator<T> implements Iterator<Position<T>> {

    Iterator<Position<T>> iterator;

    public DepthFirstTreeIterator(Tree<T> tree, Position<T> root) {
        this.iterator = new PreOrderTreeIterator<T>(tree, root);
    }

    public DepthFirstTreeIterator(Tree<T> tree) {
        this(tree, tree.root());
    }
    
    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    /**
     * This method visits the nodes of a tree by following a depth-first order
     */
    @Override
    public Position<T> next() {
        return this.iterator.next();
    }

   
}
