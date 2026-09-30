package es.urjc.grafo.EDA.trees;

import es.urjc.grafo.EDA.utils.Position;

/**
 *
 * @param <E> Elements stored in the tree nodes
 */
public interface NAryTree<E> extends Tree<E> {

    /**
     * Adds a root node to an empty tree
     *
     * @param element
     * @return
     */
    Position<E> addRoot(E element);

    /**
     * Add a new node whose parent is pointed by a given position.
     *
     * @param element
     * @param position The position of the parent, element the element stored in the new
     *                 created node.
     * @return
     */
    Position<E> add(E element, Position<E> position);

    /**
     * Add a new node whose parent is pointed by a given position, and set the
     * child at the position n if possible.
     *
     * @param element
     * @param position The position of the parent, element the element stored in the new
     *                 created node.
     * @param n
     * @return
     */
    Position<E> add(E element, Position<E> position, final int n);

    /**
     * Swap the elements at two nodes
     *
     * @param position1
     * @param position2
     */
    void swapElements(Position<E> position1, Position<E> position2);

    /**
     * Replaces the element at a node.
     *
     * @param position
     * @param element
     * @return
     */
    E replace(Position<E> position, E element);

    /**
     * Remove a node and its corresponding subtree rooted at node.
     *
     * @param position The position of the node to be removed.
     */
    void remove(Position<E> position);

    /**
     * Create un new tree from node v of the same type that invoqued class.
     *
     * @param v new root node
     * @return The new tree.
     */
    NAryTree<E> subTree(Position<E> v);


    /**
     * Attach tree t as children of node position if t and "this" are of the same class.
     *
     * @param position - Node in which t will be attached or null if t is attached in the root.
     * @param t        - Tree to be attached.
     */
    void attach(Position<E> position, NAryTree<E> t);

}
