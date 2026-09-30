package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LCRSTree<E> implements NAryTree<E> {

    private LCRSNode<E> root = null;
    private int size = 0;

    @Override
    public Position<E> addRoot(E e) {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        root = new LCRSNode<>(e, null);
        this.size++;
        return root;
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Position<E> add(E element, Position<E> p, int n) {
        LCRSNode<E> parent = checkPosition(p);
        LCRSNode<E> newNode = new LCRSNode<>(element, parent);
        if (n == 0) {
            newNode.setRightSibling(parent.getLeftChild());
            parent.setLeftChild(newNode);
        } else {
            LCRSNode<E> prev = parent.getLeftChild();
            for (int i = 0; i < n - 1; i++) {
                if (prev.getRightSibling() == null) {
                    break;
                }
                prev = prev.getRightSibling();
            }
            newNode.setRightSibling(prev.getRightSibling());
            prev.setRightSibling(newNode);
        }
        this.size++;
        return newNode;
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        LCRSNode<E> node1 = checkPosition(p1);
        LCRSNode<E> node2 = checkPosition(p2);
        E temp = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(temp);
    }

    @Override
    public E replace(Position<E> p, E e) {
        LCRSNode<E> node = checkPosition(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public void remove(Position<E> p) {
        LCRSNode<E> node = checkPosition(p);

        // Update size of the tree
        Iterator<Position<E>> iterator = new BreadthFirstTreePositionsIterator<>(this, p);
        while (iterator.hasNext()) {
            iterator.next();
            this.size--;
        }

        // Remove node
        if (node.getParent() != null) {
            LCRSNode<E> parent = node.getParent();
            LCRSNode<E> prev = parent.getLeftChild();
            if (prev == node) {
                parent.setLeftChild(node.getRightSibling());
            }
            else {
                while (prev.getRightSibling() != node) {
                    prev = prev.getRightSibling();
                }
                prev.setRightSibling(node.getRightSibling());
            }
        } else {
            this.root = null;
        }
    }

    @Override
    public NAryTree<E> subTree(Position<E> v) {
        int sizeBefore = this.size();
        remove(v);
        int sizeSubtree = sizeBefore - this.size();

        LCRSNode<E> newRoot = checkPosition(v);
        newRoot.parent = null;
        LCRSTree<E> otherTree = new LCRSTree<>();
        otherTree.root = newRoot;
        otherTree.size = sizeSubtree;
        return otherTree;
    }

    @Override
    public void attach(Position<E> p, NAryTree<E> t) {
        if (t.getClass() != this.getClass()) {
            throw new RuntimeException("Cannot attach trees of different classes");
        } else if (t == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        } else if (p == null) {
            throw new RuntimeException("Cannot attach a tree given a null position");
        }

        LCRSTree<E> other = (LCRSTree<E>) t;
        LCRSNode<E> node = checkPosition(p);
        if (!other.isEmpty()) {
            LCRSNode<E> r = checkPosition(other.root());
            if (node.getLeftChild() == null) {
                node.setLeftChild(r);
            } else {
                LCRSNode<E> last = node.getLeftChild();
                while (last.getRightSibling() != null) {
                    last = last.getRightSibling();
                }
                last.setRightSibling(r);
            }
            r.setParent(node);
            r.setRightSibling(null);

            this.size += other.size;
            other.root = null;
            other.size = 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public Position<E> root() {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        List<Position<E>> children = new LinkedList<>();
        for (LCRSNode<E> child = node.getLeftChild(); child != null; child = child.getRightSibling()) {
            children.add(child);
        }
        return Collections.unmodifiableList(children);
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param p A valid Position within the tree
     * @return number of children of Position p
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public int numChildren(Position<E> p) throws IllegalArgumentException {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isInternal(Position<E> v) {
        return !isLeaf(v);
    }

    /**
     * Returns true if Position p does not have any children.
     *
     * @param p A valid Position within the tree
     * @return true if p has zero children, false otherwise
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return isLeaf(p);
    }

    public boolean isLeaf(Position<E> v) {
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isRoot(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        return node == this.root;
    }

    @Override
    public Iterator<E> iterator() {
        return new DepthFirstTreeIterator<>(this);
    }

    /**
     * Returns an iterable collection of the positions of the tree.
     *
     * @return iterable collection of the tree's positions
     */
    @Override
    public Iterable<Position<E>> positions() {
        return new PositionsIterable();
    }

    private class PositionsIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new DepthFirstTreePositionsIterator<>(LCRSTree.this);
        }
    }

    public int size() {
        return this.size;
    }

    /**
     * If p is a valid LCRSNode of this tree, cast it, else throw exception.
     */
    private LCRSNode<E> checkPosition(Position<E> p) {
        if (!(p instanceof LCRSTree.LCRSNode<E> node)) {
            throw new RuntimeException("The position is invalid");
        }
        return node;
    }

    /**
     * Counts the number of nodes in the subtree rooted at node.
     */
    private int countSubtree(LCRSNode<E> node) {
        int count = 1;
        for (LCRSNode<E> child = node.getLeftChild(); child != null; child = child.getRightSibling()) {
            count += countSubtree(child);
        }
        return count;
    }

    private static class LCRSNode<T> implements Position<T> {

        private T element;
        private LCRSNode<T> parent;
        private LCRSNode<T> leftChild;
        private LCRSNode<T> rightSibling;

        LCRSNode(T element, LCRSNode<T> parent) {
            // TODO
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public LCRSNode<T> getParent() {
            return parent;
        }

        public void setParent(LCRSNode<T> parent) {
            this.parent = parent;
        }

        public LCRSNode<T> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(LCRSNode<T> leftChild) {
            this.leftChild = leftChild;
        }

        public LCRSNode<T> getRightSibling() {
            return rightSibling;
        }

        public void setRightSibling(LCRSNode<T> rightSibling) {
            this.rightSibling = rightSibling;
        }
    }

}
