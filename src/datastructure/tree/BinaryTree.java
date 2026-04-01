package datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<T> {

    private Node root;
    private int size;

    public BinaryTree() {
        clear();
    }

    public void add(T newItem) {
        if (isEmpty()) {
            root = new Node(newItem);
        } else {
            Node current = root;
            Queue<Node> q = new LinkedList<Node>();
            q.add(current);

            while (true) {
                current = q.remove();

                if (current.leftChild != null) {
                    q.add(current.leftChild);
                } else {
                    break;
                }

                if (current.rightChild != null) {
                    q.add(current.rightChild);
                } else {
                    break;
                }
            }

            if (current.leftChild == null) {
                current.leftChild = new Node(newItem);
            } else {
                current.rightChild = new Node(newItem);
            }
        }

        size += 1;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        levelOrderString(root, sb);
        sb.append(" }");
        return sb.toString();
    }

    private void levelOrderString(Node current, StringBuffer sb) {
        if (current == null) {
            return;
        }

        Queue<Node> q = new LinkedList<Node>();
        q.add(current);

        while (!q.isEmpty()) {
            current = q.remove();
            sb.append(current.data);

            if (current.leftChild != null || current.rightChild != null || !q.isEmpty()) {
                sb.append(", ");
            }

            if (current.leftChild != null) {
                q.add(current.leftChild);
            }

            if (current.rightChild != null) {
                q.add(current.rightChild);
            }
        }
    }

    private class Node {
        private Node leftChild;
        private Node rightChild;
        private T data;

        public Node(T data) {
            this.data = data;
        }
    }
}