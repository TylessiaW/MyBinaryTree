package datastructure.tree.test;

import datastructure.tree.BinaryTree;

public class TreeTest {

    public static void main(String[] args) {
        BinaryTree<Character> bt = new BinaryTree<>();

        for (char c = 'A'; c <= 'J'; c++) {
            bt.add(c);
        }

        System.out.println(bt);
    }
}