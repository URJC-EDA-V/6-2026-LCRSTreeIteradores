package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstTreePositionsIterator<T> implements Iterator<Position<T>> {

    Queue<Position<T>> nodesToVisit = new LinkedList<>();
    Tree<T> tree;

    public BreadthFirstTreePositionsIterator(Tree<T> tree, Position<T> root) {
        this.nodesToVisit.add(root);
        this.tree = tree;
    }

    public BreadthFirstTreePositionsIterator(Tree<T> tree) {
        this(tree, tree.root());
    }

    @Override
    public boolean hasNext() {
        return !this.nodesToVisit.isEmpty();
    }

    /**
     * This method visits the nodes of a tree by following a breadth-first order
     */
    @Override
    public Position<T> next() {
        Position<T> currentPosition = this.nodesToVisit.poll();
        if (!tree.isExternal(currentPosition)) {
            for (Position<T> child : tree.children(currentPosition)) {
                this.nodesToVisit.add(child);
            }
        }
        return currentPosition;
    }
}
