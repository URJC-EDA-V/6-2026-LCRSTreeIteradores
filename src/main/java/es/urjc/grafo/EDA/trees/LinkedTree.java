package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A linked class for a tree where nodes have an arbitrary number of children.
 *
 * @param <E> The element stored in the tree
 */
public class LinkedTree<E> implements NAryTree<E> {

    private TreeNode<E> root = null;
    private int size = 0;

    /**
     * Creates an empty tree.
     */
    public LinkedTree() {
    }

    /**
     * Returns whether the tree is empty.
     *
     * @return True if is empty.
     *
     */
    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns whether a node is internal.
     *
     * @param position
     * @return
     */
    @Override
    public boolean isInternal(Position<E> position) {
        return !isLeaf(position);
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
        TreeNode<E> node = checkPosition(p);
        return (node.getChildren() == null) || (node.getChildren().isEmpty());
    }

    /**
     * Returns whether a node is external.
     *
     * @param position
     * @return
     */
    public boolean isLeaf(Position<E> position) {
        return this.isExternal(position);
    }

    /**
     * Returns whether a node is the root.
     *
     * @param position
     * @return
     */
    @Override
    public boolean isRoot(Position<E> position) {
        TreeNode<E> node = checkPosition(position);
        return (node == this.root());
    }

    /**
     * Returns the root of the tree.
     *
     * @return
     */
    @Override
    public Position<E> root() {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    /**
     * Returns the parent of a node.
     *
     * @param position
     * @return
     */
    @Override
    public Position<E> parent(Position<E> position) {
        TreeNode<E> node = checkPosition(position);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("No parent");
        }
        return parentPos;
    }

    /**
     * Returns an iterable collection of the children of a node.
     *
     * @param position
     */
    @Override
    public Iterable<? extends Position<E>> children(Position<E> position) {
        TreeNode<E> node = checkPosition(position);
        return node.getChildren();
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param position A valid Position within the tree
     * @return number of children of Position p
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public int numChildren(Position<E> position) throws IllegalArgumentException {
        TreeNode<E> node = checkPosition(position);
        return node.children.size();
    }

    /**
     * Returns an iterator of the elements stored at the nodes. The nodes are
     * visited according to a breath-first search
     */
    @Override
    public Iterator<E> iterator() {
        return new BreadthFirstTreeIterator<>(this); // An iterator of elements
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
            return new BreadthFirstTreePositionsIterator<>(LinkedTree.this);
        }
    }


    /**
     * Replaces element at position by the new element received
     * @param position position which element must be replaced
     * @param element element to be placed in position
     * @return element previously contained in Position position
     */
    @Override
    public E replace(Position<E> position, E element) {
        TreeNode<E> node = checkPosition(position);
        E temp = position.getElement();
        node.setElement(element);
        return temp;
    }

    /**
     * Adds a root node to an empty tree
     */
    @Override
    public Position<E> addRoot(E element) {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        root = new TreeNode<>(element, null, new LinkedList<>());
        this.size++;
        return root;
    }

    /**
     * Swap the elements at two different nodes
     */
    @Override
    public void swapElements(Position<E> position1, Position<E> position2) {
        TreeNode<E> node1 = checkPosition(position1);
        TreeNode<E> node2 = checkPosition(position2);
        E temp = position2.getElement();
        node2.setElement(position1.getElement());
        node1.setElement(temp);
    }

    /**
     * If position is a good tree node, cast to TreePosition, else throw exception
     */
    private TreeNode<E> checkPosition(Position<E> position) {
        if (!(position instanceof LinkedTree.TreeNode<E> aux)) {
            throw new RuntimeException("The position is invalid");
        }
        return aux;
    }

    /**
     * Add a new node whose parent is pointed by a given position.
     *
     * @param position The position of the parent, e the element stored in the new
     *                 created node.
     */
    @Override
    public Position<E> add(E element, Position<E> position) {
        TreeNode<E> parent = checkPosition(position);
        TreeNode<E> newNode = new TreeNode<>(element, parent, new LinkedList<>());
        List<TreeNode<E>> l = parent.getChildren();
        l.add(newNode);
        this.size++;
        return newNode;
    }

    /**
     * Add a new node whose parent is pointed by a given position,
     * and set the child at the position index if possible.
     *
     * @param position The position of the parent, e the element stored in the new
     *                 created node.
     */
    @Override
    public Position<E> add(E element, Position<E> position, final int index) {
        TreeNode<E> parent = checkPosition(position);
        TreeNode<E> newNode = new TreeNode<>(element, parent, new LinkedList<>());
        List<TreeNode<E>> l = parent.getChildren();
        l.add(index, newNode);
        this.size++;
        return newNode;
    }

    /**
     * Remove a node and its corresponding subtree rooted at node.
     *
     * @param position The position of the node to be removed.
     */
    @Override
    public void remove(Position<E> position) {
        TreeNode<E> node = checkPosition(position);

        // Remove node
        if (node.getParent() != null) {
            TreeNode<E> parent = node.getParent();
            parent.getChildren().remove(node);
        } else {
            this.root = null;
        }

        // Update size of the tree
        Iterator<Position<E>> iterator = new BreadthFirstTreePositionsIterator<>(this, position);
        while (iterator.hasNext()) {
            iterator.next();
            this.size--;
        }
    }

    /**
     * Create a new tree from node position.
     *
     * @param position new root node
     * @return The new tree.
     */
    @Override
    public LinkedTree<E> subTree(Position<E> position) {
        int sizeBefore = this.size();
        remove(position);
        int sizeSubtree = sizeBefore - this.size();

        TreeNode<E> newRoot = checkPosition(position);
        newRoot.parent = null;
        LinkedTree<E> otherTree = new LinkedTree<>();
        otherTree.root = newRoot;
        otherTree.size = sizeSubtree;
        return otherTree;
    }

    /**
     * Attach tree otherTree as children of node position
     *
     * @param position  - Node in which otherTree will be attached or null if otherTree is attached in the root.
     * @param otherTree - Tree to be attached.
     */
    @Override
    public void attach(Position<E> position, NAryTree<E> otherTree) {
        if (otherTree.getClass() != this.getClass()) {
            throw new RuntimeException("Cannot attach trees of different classes");
        } else if (otherTree == this) {
            throw new RuntimeException("Cannot attach a tree over himself");
        } else if ((position == null)) {
            throw new RuntimeException("Cannot attach a tree given a null position");
        }

        LinkedTree<E> lt = (LinkedTree<E>) otherTree;
        TreeNode<E> node = checkPosition(position);
        if (!otherTree.isEmpty()) {
            TreeNode<E> r = checkPosition(otherTree.root());
            node.children.add(r);
            r.setParent(node);
            lt.root = null;
        }
    }

    private static class TreeNode<E> implements Position<E> {

        private E element;
        private TreeNode<E> parent;
        private List<TreeNode<E>> children;

        /**
         * Main constructor
         */
        public TreeNode(E element, TreeNode<E> parent, List<TreeNode<E>> children) {
            this.element = element;
            this.parent = parent;
            this.children = children;
        }

        /**
         * Returns the element stored at this position
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the element stored at this position
         */
        public final void setElement(E element) {
            this.element = element;
        }

        /**
         * Returns the children of this position
         */
        public List<TreeNode<E>> getChildren() {
            return children;
        }

        /**
         * Sets the right child of this position
         */
        public final void setChildren(List<TreeNode<E>> children) {
            this.children = children;
        }

        /**
         * Returns the parent of this position
         */
        public TreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of this position
         */
        public final void setParent(TreeNode<E> position) {
            parent = position;
        }

    }

}
