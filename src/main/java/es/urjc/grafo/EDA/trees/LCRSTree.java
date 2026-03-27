package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;

public class LCRSTree<E> implements NAryTree<E> {

    @Override
    public Position<E> addRoot(E e) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E replace(Position<E> p, E e) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Position<E> p) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> root() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> parent(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isLeaf(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<Position<E>> iterator() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int size() {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class LCRSNode<T> implements Position<T> {

        // TODO: está permitido añadir atributos y métodos públicos a esta clase LCRSNode

        @Override
        public T getElement() {
            // TODO
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
