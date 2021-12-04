package com.example.demo.data;

import com.fasterxml.jackson.databind.util.LinkedNode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

interface A {
}

interface C {
}

interface B extends A, C {

}

class ListNode implements B {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    private void traverse(LinkedNode<String> linkedNode) {
        traverse(linkedNode.next());
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;

    public int getVal() {
        return val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    private void traverse(TreeNode treeNode) {
        // 前序遍历
        traverse(treeNode.left);
        // 中序遍历
        traverse(treeNode.right);
        // 后序遍历
    }
}

class Node {
    int val;
    Node left;
    Node right;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    private void traverse(TreeNode treeNode) {
        // 前序遍历
        traverse(treeNode.left);
        // 中序遍历
        traverse(treeNode.right);
        // 后序遍历
    }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }
        ListNode pre = null;
        ListNode next;
        ListNode leftNode;
        ListNode rightNode;
        ListNode curr = head;
        ListNode first;
        ListNode end;
        int currIndex = 1;
        while(currIndex != left) {
            pre = curr;
            curr = curr.next;
            currIndex++;
        }
        leftNode = curr;
        first = pre;
        pre = curr;
        curr = curr.next;
        currIndex++;
        while (currIndex != right) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
            currIndex++;
        }
        rightNode = curr;
        end = curr.next;
        rightNode.next = pre;

        if (first != null) {
            first.next = rightNode;
        } else {
            head = rightNode;
        }
        leftNode.next = end;
        return head;



    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2");
        list = list.stream().filter("aaa"::equals).collect(Collectors.toList());

    }

    // i 城市  j 上一个城市的基站的值 k 该城市能取的最大值
    public static int getTimes(int i, int j, int k, int n, int o) {
        if (i > n || j > k) {
            return 0;
        }
        if (j == k && i == n) {
//            System.out.println("第"+o+"层" + j);
            return 1;
        } else if (j == k){
            return 0;
        }
        if (i == n) {
//            System.out.println("第"+o+"层" + k);
            return 1;
        }
        int times = 0;
        for (int a = j; a <= k; a++) {
//            System.out.print("第"+o+"层" + a + " ");
            times += getTimes(i+1, a, k - a, n, o+1);
        }
        return times % 1000000007;
    }
}