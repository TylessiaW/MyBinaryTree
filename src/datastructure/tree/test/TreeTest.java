package datastructure.tree.test;

import datastructure.tree.BinaryTree;

public class TreeTest {

    public static void main(String[] args) {
        BinaryTree<Character> bt = new BinaryTree<Character>();

        for (char c = 'A'; c <= 'F'; c++) {
            bt.add(c);
        }

        System.out.println(bt);
        System.out.println(bt.getSize());
        bt.remove('E');
        bt.add('G');
        System.out.println(bt);
        System.out.println(bt.getSize());

        System.out.println("Preorder: " + bt.preorder());
        System.out.println("Inorder: " + bt.inorder());
        System.out.println("Postorder: " + bt.postorder());
    }
}