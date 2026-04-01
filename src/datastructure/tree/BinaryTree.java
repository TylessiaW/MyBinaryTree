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

    public void remove(T toRemove) {
        if (isEmpty()) {
            throw new EmptyCollectionException("Binary tree is empty.");
        }

        if (size == 1) {
            if (root.data.equals(toRemove)) {
                clear();
                return;
            } else {
                throw new RuntimeException("Node does not exist.");
            }
        }

        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        Node current;
        Node parent = null;
        Node nodeToRemove = null;
        Node parentOfRemove = null;

        while (!q.isEmpty()) {
            current = q.remove();

            if (current.leftChild != null) {
                if (current.leftChild.data.equals(toRemove)) {
                    nodeToRemove = current.leftChild;
                    parentOfRemove = current;
                }
                q.add(current.leftChild);
            }

            if (current.rightChild != null) {
                if (current.rightChild.data.equals(toRemove)) {
                    nodeToRemove = current.rightChild;
                    parentOfRemove = current;
                }
                q.add(current.rightChild);
            }
        }

        if (root.data.equals(toRemove)) {
            nodeToRemove = root;
        }

        if (nodeToRemove == null) {
            throw new RuntimeException("Node does not exist.");
        }

        if (nodeToRemove == root) {
            throw new RuntimeException("Cannot remove root when tree has children.");
        }

        if (nodeToRemove.leftChild == null && nodeToRemove.rightChild == null) {
            if (parentOfRemove.leftChild == nodeToRemove) {
                parentOfRemove.leftChild = null;
            } else {
                parentOfRemove.rightChild = null;
            }

            size -= 1;
        } else {
            throw new RuntimeException("Node is not a leaf.");
        }
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

    public String preorder() {
        StringBuffer sb = new StringBuffer();
        preorder(root, sb);
        return sb.toString().trim();
    }

    private void preorder(Node current, StringBuffer sb) {
        if (current != null) {
            sb.append(current.data).append(" ");
            preorder(current.leftChild, sb);
            preorder(current.rightChild, sb);
        }
    }

    public String inorder() {
        StringBuffer sb = new StringBuffer();
        inorder(root, sb);
        return sb.toString().trim();
    }

    private void inorder(Node current, StringBuffer sb) {
        if (current != null) {
            inorder(current.leftChild, sb);
            sb.append(current.data).append(" ");
            inorder(current.rightChild, sb);
        }
    }

    public String postorder() {
        StringBuffer sb = new StringBuffer();
        postorder(root, sb);
        return sb.toString().trim();
    }

    private void postorder(Node current, StringBuffer sb) {
        if (current != null) {
            postorder(current.leftChild, sb);
            postorder(current.rightChild, sb);
            sb.append(current.data).append(" ");
        }
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

            if (!q.isEmpty() || current.leftChild != null || current.rightChild != null) {
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