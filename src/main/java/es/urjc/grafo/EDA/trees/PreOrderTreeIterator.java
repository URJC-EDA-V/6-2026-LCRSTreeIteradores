package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.lists.LinkedPositionalList;
import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PreOrderTreeIterator<T> implements Iterator<Position<T>> {

    private final LinkedPositionalList<Position<T>> nodesToVisit;
    private final Tree<T> tree;

    public PreOrderTreeIterator(Tree<T> tree) {
        this(tree, tree.root());
    }

    public PreOrderTreeIterator(Tree<T> tree, Position<T> root) {
        this.tree = tree;
        this.nodesToVisit = new LinkedPositionalList<>();
        this.nodesToVisit.addFirst(root);
    }

    @Override
    public boolean hasNext() {
        return (!nodesToVisit.isEmpty());
    }

    /**
     * This method visits the nodes of a tree by following a pre-order
     */
    @Override
    public Position<T> next() {
        if (this.nodesToVisit.isEmpty()) {
            throw new NoSuchElementException();
        }
        Position<Position<T>> current = nodesToVisit.first();
        for (Position<T> child : this.tree.children(current.getElement())) {
            this.nodesToVisit.addBefore(current, child);
        }
        Position<T> element = current.getElement();
        System.out.println(element.getElement());
        this.nodesToVisit.remove(current);
        return element;
    }

}
