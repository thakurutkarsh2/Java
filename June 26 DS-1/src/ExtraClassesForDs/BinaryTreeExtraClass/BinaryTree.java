package ExtraClassesForDs.BinaryTreeExtraClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import ExtraClassesForDs.GenericHashMap.HashMap;

class BinaryTree {

	private class Node {
		Node left;
		Node right;
		int data;

	}

	int size;
	Node root;

	public BinaryTree() {

		Scanner scn = new Scanner(System.in);
		root = takeInput(this.root, false, scn);
	}

	public BinaryTree(int[] pre, int[] in) {
		root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
	}
	// only for expression tree

	public BinaryTree(char[] ch) {
		root = expressiontree(ch);
	}
	// expression tree end here

	// remove leaf node length < k

	public BinaryTree(int k) {
		root = removenodeslengthk(k);
	}

	// end remove leaf node

	public Node construct(int[] pre, int[] in, int psi, int pei, int isi, int iei) {

		if (psi > pei || isi > iei) {
			return null;
		}

		Node node = new Node();
		node.data = pre[psi];
		this.size++;

		int idx = -1;
		for (int i = isi; i <= iei; i++) {
			if (in[i] == node.data) {
				idx = i;
				break;
			}
		}

		int clse = idx - isi;
		node.left = construct(pre, in, psi + 1, psi + clse, isi, idx - 1);
		node.right = construct(pre, in, psi + clse + 1, pei, idx + 1, iei);
		return node;

	}

	public Node construct2(int[] pos, int[] in, int psi, int pei, int isi, int iei) {

		if (psi > pei || isi > iei) {
			return null;
		}
		Node node = new Node();
		node.data = pos[pei];
		this.size++;

		int idx = -1;

		for (int i = isi; i <= iei; i++) {
			if (in[i] == node.data) {
				idx = i;
				break;
			}
		}
		int clse = idx - isi;
		node.left = construct2(pos, in, psi, psi + clse - 1, isi, idx - 1);
		node.right = construct2(pos, in, psi + clse, pei - 1, idx + 1, iei);

		return node;

	}

	public Node construct3(int[] pos, int[] pre, int psi, int pei, int presi, int preei) {

		if (psi > pei || presi > preei) {
			return null;
		}
		Node node = new Node();
		node.data = pre[psi];
		this.size++;

		int idx = -1;
		return node;

	}

	public BinaryTree(int[] arr) {
		root = construct(arr, 0, arr.length - 1);
	}

	private Node construct(int[] arr, int lo, int hi) {
		if (lo > hi) {
			return null;
		}

		int mid = (lo + hi) / 2;

		Node node = new Node();
		this.size++;
		node.data = arr[mid];

		node.left = construct(arr, lo, mid - 1);
		node.right = construct(arr, mid + 1, hi);

		return node;

	}

	private Node takeInput(Node parent, boolean ilc, Scanner scn) {

		if (parent == null) {
			System.out.println("root?");

		} else {
			if (ilc) {
				System.out.println("Data for left child");
			} else {
				System.out.println("Data for right child");
			}
		}

		int dat = scn.nextInt();
		Node child = new Node();
		this.size++;
		child.data = dat;

		// for left
		System.out.println("Does" + parent + " have a left child ?");
		boolean a = scn.nextBoolean();
		if (a) {
			child.left = takeInput(child, true, scn);
		}

		// for right
		System.out.println("Does" + parent + " have a right child ?");
		boolean b = scn.nextBoolean();
		if (b) {
			child.right = takeInput(child, false, scn);
		}

		return child;

	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void display() {
		display(this.root);
	}

	private void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";

		if (node.left != null) {
			str += node.left.data;
		} else {
			str += ".";
		}
		str += "=>" + node.data + "<=";
		if (node.right != null) {
			str += node.right.data;
		} else {
			str += ".";
		}
		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	private int size2(Node node) {
		if (node == null) {
			return 0;
		}

		int ls = size2(node.right);
		int rs = size2(node.left);

		return ls + rs + 1;
	}

	public int size2() {
		return size2(this.root);
	}

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}

		int l = height(node.left);
		int r = height(node.right);

		return Math.max(l, r) + 1;
	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int l = max(node.left);
		int r = max(node.right);

		return Math.max(l, Math.max(r, node.data));
	}

	public boolean find(int data) {
		return find(this.root, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (node.data == data) {
			return true;
		}

		boolean rv = find(node.left, data);
		if (rv) {
			return rv;
		} else {
			rv = find(node.right, data);
		}

		return rv;

	}

	public void removeNode() {
		removenode(null, this.root);
	}

	private void removenode(Node parent, Node node) {

		if (node == null) {
			return;
		}

		if (node.left == null && node.right == null) {
			parent.left = null;
			parent.right = null;
			return;
		}

		removenode(node, node.left);
		removenode(node, node.right);

	}

	public int diameter() {
		return diameter(this.root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}
		int l = diameter(node.left);
		int r = diameter(node.right);
		int max = Math.max(l, r);

		int l1 = height(node.left);
		int l2 = height(node.right);
		int sum = l1 + l2 + 2;

		return Math.max(max, sum);

	}

	private class diaPair {
		int height;
		int diameter;
	}

	public int diameter2() {
		return diameter2(this.root).diameter;
	}

	private diaPair diameter2(Node node) {
		if (node == null) {
			diaPair bp = new diaPair();
			bp.height = -1;
			bp.diameter = 0;
			return bp;
		}

		diaPair lp = diameter2(node.left);
		diaPair rp = diameter2(node.right);

		diaPair mp = new diaPair();

		mp.height = Math.max(lp.height, rp.height) + 1;
		mp.diameter = Math.max(lp.height + rp.height + 2, Math.max(lp.diameter, rp.diameter));

		return mp;
	}

	private class balPair {
		boolean isBal;
		int height;
	}
	// checking if height difference is >1 or not 

	public boolean IsBalanced() {
		return IsBalanced(this.root).isBal;
	}

	private balPair IsBalanced(Node node) {
		if (node == null) {
			balPair bp = new balPair();
			bp.isBal = true;
			bp.height = -1;
			return bp;
		}

		balPair lp = IsBalanced(node.left);
		balPair rp = IsBalanced(node.right);

		balPair mp = new balPair();
		mp.height = Math.max(lp.height, rp.height) + 1;

		if (lp.isBal && rp.isBal) {
			int gap = Math.abs(lp.height - rp.height);
			if (gap <= 1) {
				mp.isBal = true;

			} else {
				mp.isBal = false;
			}
		} else {
			mp.isBal = false;
		}

		return mp;
	}

	private class bstPair {
		boolean isBst;
		int min;
		int max;
		int size;

		// for largest bst in a tree

		Node largestBST;
		int largestBSTSize;

	}

	public boolean IsBst() {
		bstPair p = new bstPair();
		System.out.println(p.largestBSTSize);
		return IsBst(this.root).isBst;
	}

	private bstPair IsBst(Node node) {
		if (node == null) {
			bstPair bp = new bstPair();
			bp.min = Integer.MAX_VALUE;
			bp.max = Integer.MIN_VALUE;
			bp.isBst = true;
			bp.largestBSTSize = -1;
			return bp;
		}

		bstPair lp = IsBst(node.left);
		bstPair rp = IsBst(node.right);

		bstPair mp = new bstPair();
		// mp.max =Math.max(lp.max, Math.max(node.data, rp.max)); // sir new kraya
		// mp.min = Math.min(node.data , Math.min(lp.min, rp.min));

		mp.max = Math.max(node.data, lp.max);
		mp.min = Math.min(node.data, rp.min);
		if (lp.isBst && rp.isBst) {
			if (node.data > lp.max && node.data < rp.min) {
				mp.isBst = true;

			} else {
				mp.isBst = false;
			}
		} else {
			mp.isBst = false;
		}

		if (mp.isBst) {
			mp.largestBST = node;
			mp.largestBSTSize = mp.largestBSTSize;
		} else {
			if (lp.largestBSTSize > rp.largestBSTSize) {
				mp.largestBST = lp.largestBST;
				mp.largestBSTSize = lp.largestBSTSize;
			} else {
				mp.largestBST = rp.largestBST;
				mp.largestBSTSize = rp.largestBSTSize;
			}
		}

		return mp;

	}

	public void preOrder() {
		preOrder(this.root);
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}

		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);

	}

	public void postOrder() {
		postOrder(this.root);
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);

	}

	public void inOrder() {
		inOrder(this.root);
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}

		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);

	}

	public void preOrderI() {

		LinkedList<TraversalPair> stack = new LinkedList<>();

		TraversalPair rp = new TraversalPair();
		rp.node = root;

		stack.add(rp);

		while (!stack.isEmpty()) {

			TraversalPair get = stack.getFirst();

			if (get.self == false) {
				get.self = true;
				if (get.node != null) {
					System.out.println(get.node.data);
				}
			} else if (get.left == false) {

				// condition for null pointer
				if (get.node.left != null) {
					TraversalPair np = new TraversalPair();
					np.node = get.node.left;
					stack.addFirst(np);
				}
				get.left = true;

			} else if (get.right == false) {

				// condition for handling null pointer
				if (get.node.right != null) {
					TraversalPair np = new TraversalPair();
					np.node = get.node.right;
					stack.addFirst(np);
				}
				get.right = true;

			} else {
				stack.removeFirst();
			}

		}

	}

	private class TraversalPair {
		boolean self;
		boolean left;
		boolean right;
		Node node;
	}

	public void levelOrder() {

		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(this.root);
		while (!queue.isEmpty()) {

			Node r = queue.getFirst();
			if (r != null) {
				System.out.println(r.data);
			}
			queue.removeFirst();
			if (r != null) {

				queue.addLast(r.left);
				queue.addLast(r.right);
			}

		}

	}

	public void printWithNoSibling() {
		System.out.println(this.root.data);
		printWithNoSibling(null, this.root);
	}

	private void printWithNoSibling(Node parent, Node node) {
		if (node == null) {
			return;
		}

		if ((node.left != null && node.right == null) || (node.right != null && node.left == null)) {

			if ((node.left != null && node.right == null)) {
				printWithNoSibling(node, node.left);
				System.out.println(node.left.data);
			} else if ((node.right != null && node.left == null)) {
				printWithNoSibling(node, node.right);
				System.out.println(node.right.data);
			}
		} else {
			printWithNoSibling(node, node.left);
			printWithNoSibling(node, node.right);
		}

	}

	public Node largestBst() {

		bstPair bst = new bstPair();
		bst = IsBst(this.root);
		return bst.largestBST;

	}

	// private bstPair largestBst(Node node) {
	//
	//
	//
	//
	// }

	// level by level printing iterative

	// error prone code please check
	public void zicZacRecursive() {
		int height = height(this.root);

		boolean ilc = false;
		for (int i = 1; i <= height + 1; i++) {
			zicZacRecursive(this.root, i, ilc);

			if (ilc) {
				ilc = false;

			} else {
				ilc = true;
			}
		}
	}

	private void zicZacRecursive(Node node, int level, boolean ilc) {
		if (node == null) {
			return;
		} else if (level == 1) {
			System.out.print(node.data + " ");
		}

		else if (level > 1) {
			if (ilc) {

				zicZacRecursive(node.left, level - 1, ilc);
				zicZacRecursive(node.right, level - 1, ilc);
			} else {
				zicZacRecursive(node.right, level - 1, ilc);
				zicZacRecursive(node.left, level - 1, ilc);
			}

		}
	}

	// levelorder recursion

	public void levelorder2() {
		int h = height(this.root);
		int i;
		for (i = 1; i <= h; i++) {
			levelorder2(this.root, i);
		}
	}

	private void levelorder2(Node node, int i) {

		if (node == null) {
			return;
		} else if (i == 1) {
			System.out.println(node.data + " ");
		} else if (i > 1) {
			levelorder2(node.left, i - 1);
			levelorder2(node.right, i - 1);
		}

	}

	public void ziczacI() {
		ziczacI(this.root);
	}

	private void ziczacI(Node node) {
		if (node == null) {
			return;
		}
		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> stack = new LinkedList<>();

		queue.addLast(this.root);
		int count = 0;
		while (queue.size() != 0) {
			Node rm = queue.removeFirst();
			System.out.print(rm.data + " ");

			if (count % 2 == 0) {
				if (rm.right != null) {
					stack.addFirst(rm.right);
				}
				if (rm.left != null) {
					stack.addFirst(rm.left);
				}
				if (node.left == null && node.right == null) {
					return;
				}

			} else {
				if (rm.left != null) {
					stack.addFirst(rm.left);
				}
				if (rm.right != null) {
					stack.addFirst(rm.right);
				}
				if (node.left == null && node.right == null) {
					return;
				}
			}

			if (queue.size() == 0) {
				count++;
				queue = stack;
				stack = new LinkedList<>();

				// System.out.print(" ");
			}

		}
	}

	// cousins

	public boolean cousins(int a, int b) {
		return (cousins(this.root, a, 1) == cousins(this.root, b, 1) && !sameparent(this.root, a, b));
	}

	private int cousins(Node node, int a, int depth) {

		if (node == null) {
			return 0;
		}

		if (node.data == a) {
			return depth;
		}
		int c = cousins(node.left, a, depth + 1);
		if (c != 0) {
			return c;
		}
		return cousins(node.right, a, depth + 1);

	}

	private boolean sameparent(Node node, int a, int b) {

		if (node == null) {
			return false;
		}

		return ((node.left.data == a && node.right.data == b) || 
				(node.left.data == b && node.right.data == a)
				|| (sameparent(node.left, a, b)) || sameparent(node.right, a, b));

	}

	// fouldable binary tree

	public boolean isfoldable() {
		return isfoldable(this.root);
	}

	private boolean isfoldable(Node node) {

		mirror(node.left);

		boolean res = isStructSame(node.left, node.right);

		mirror(node.left);
		return res;
	}

	private boolean isStructSame(Node a, Node b) {
		if (a == null && b == null)
			return true;
		if (a != null && b != null && isStructSame(a.left, b.left) && isStructSame(a.right, b.right))
			return true;

		return false;

	}

	public Node mirror(Node node) {

		// 1 true 2 false true 4 false false true 3 true 5 false false false

		if (node == null) {
			return null;
		}

		Node left = mirror(node.left);
		Node right = mirror(node.right);

		node.left = right;
		node.right = left;

		return node;

	}

	// Check for Children Sum Property in a Binary Tree

	public boolean sumProperty() {
		return sumProperty(this.root);
		// 10 true 8 true 3 false false true 5 false false true 2 true 2 false false
		// false
	}

	private boolean sumProperty(Node node) {
		if (node == null || node.left == null && node.right == null) {
			return true;
		} else {
			int left = 0;
			int right = 0;
			if (node.left != null) {
				left = node.left.data;
			}
			if (node.right != null) {
				right = node.right.data;
			}

			if (node.data == left + right) {
				boolean lrv = sumProperty(node.left);
				boolean rrv = sumProperty(node.right);
				if (lrv && rrv) {
					return true;
				} else
					return false;
			} else
				return false;

		}

	}

	// Check if a given Binary Tree is SumTree

	public int issumtree() {
		int k = issumtree(this.root);
		if (k == 1) {
			System.out.println("true");
		} else {
			System.out.println("na ho paa raha h ye ");
		}
		return k;
	}

	// 26 true 10 true 4 false false true 6 false false true 3 false true 3 false
	// false

	private int issumtree(Node node) {

		if (node == null || node.left == null && node.left == null) {
			return 1;
		}

		int left = sum(node.left);
		int right = sum(node.right);

		int lleft = issumtree(node.left);
		int rright = issumtree(node.right);

		if ((node.data == left + right) && lleft != 0 && rright != 0) {
			return 1;
		}
		return 0;
	}

	private int sum(Node node) {
		if (node == null) {
			return 0;
		}
		int left = sum(node.left);
		int right = sum(node.right);

		return left + node.data + right;
	}

	// lef nodes

	public int noofleafnodes() {
		return leafnodes(this.root, 0);
	}

	private int leafnodes(Node node, int count) {

		if (node == null) {
			return 0;
		}
		if (node.left == null && node.right == null) {
			return 1;
		}

		int left = leafnodes(node.left, count);
		int right = leafnodes(node.right, count);

		return left + right;

	}

	// LOWEST COMMON ANCESTER
	public int loewstcommonancestor(int a, int b) {
		return loewstcommonancestor(this.root, a, b).data;
		// 1 true 2 true 4 false false true 5 false false true 3 true 6 false false true
//		 7 false false
	}

	private Node loewstcommonancestor(Node node, int a, int b) {

		if (node == null) {
			return null;
		}

		if (node.data == a || node.data == b) {
			return node;
		}

		Node left = loewstcommonancestor(node.left, a, b);
		Node right = loewstcommonancestor(node.right, a, b);

		if (left != null && right != null) {
			return node;
		}

		return (left != null) ? left : right;
	}

	// RIGHT VIEW WITHOUT RECURSION

	public void rightView() {
		rightview(this.root);
	}

	private void rightview(Node node) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		queue.add(null);
		boolean isLast = true;
		while (!queue.isEmpty()) {
			Node temp = queue.peek();
			queue.poll();
			if (isLast && temp != null) {
				System.out.println(temp.data + " ");
				if (temp.right != null) {
					queue.add(temp.right);
				}
				if (temp.left != null) {
					queue.add(temp.left);
				}
				isLast = false;
			}
			if (temp != null) {
				if (temp.right != null) {
					queue.add(temp.right);
				}
				if (temp.left != null) {
					queue.add(temp.left);
				}
			}
			if (temp == null) {
				queue.add(null);
				isLast = true;
			}

		}

	}

	private static class maxminlevel {
		static int max;
		int min;
	}

	// RIGHT VIEW with RECURSION
	public void rightViewnonrec() {
		rightViewnonrec(this.root, 1);
	}

	private void rightViewnonrec(Node node, int level) {
		maxminlevel maxi = new maxminlevel();
		if (node == null) {
			return;
		}
		if (level > maxi.max) {
			System.out.println(node.data + " ");
			maxi.max = level;
		}
		rightViewnonrec(node.right, level + 1);
		rightViewnonrec(node.left, level + 1);

	}

	// LEFT VIEW WITH RERCURSION

	public void leftviewwithrecursion() {

		leftviewwithrecursion(this.root, 1);
		// 4 true 5 false false true 2 true 3 true 6 false false true 7 false false true
		// 1 false false
	}

	private void leftviewwithrecursion(Node node, int level) {
		if (node == null) {
			return;
		}
		maxminlevel mini = new maxminlevel();

		if (mini.min < level) {
			System.out.print(node.data + " ");
			mini.min = level;
		}

		leftviewwithrecursion(node.left, level + 1);
		leftviewwithrecursion(node.right, level + 1);
	}

	// print all node at distance h from goven node

	private void printkdistanceNodeDown(Node node, int k) {
		// Base Case
		if (node == null || k < 0)
			return;

		// If we reach a k distant node, print it
		if (k == 0) {
			System.out.print(node.data);
			System.out.println("");
			return;
		}

		// Recur for left and right subtrees
		printkdistanceNodeDown(node.left, k - 1);
		printkdistanceNodeDown(node.right, k - 1);
	}

	// Prints all nodes at distance k from a given target node.
	// The k distant nodes may be upward or downward.This function
	// Returns distance of root from target node, it returns -1
	// if target node is not present in tree rooted with root.
	private int printkdistanceNode(Node node, Node target, int k) {
		// Base Case 1: If tree is empty, return -1
		if (node == null)
			return -1;

		// If target is same as root. Use the downward function
		// to print all nodes at distance k in subtree rooted with
		// target or root
		if (node == target) {
			printkdistanceNodeDown(node, k);
			return 0;
		}

		// Recur for left subtree
		int dl = printkdistanceNode(node.left, target, k);

		// Check if target node was found in left subtree
		if (dl != -1) {

			// If root is at distance k from target, print root
			// Note that dl is Distance of root's left child from
			// target
			if (dl + 1 == k) {
				System.out.print(node.data);
				System.out.println("");
			}

			// Else go to right subtree and print all k-dl-2 distant nodes
			// Note that the right child is 2 edges away from left child
			else
				printkdistanceNodeDown(node.right, k - dl - 2);

			// Add 1 to the distance and return value for parent calls
			return 1 + dl;
		}

		// MIRROR OF ABOVE CODE FOR RIGHT SUBTREE
		// Note that we reach here only when node was not found in left
		// subtree
		int dr = printkdistanceNode(node.right, target, k);
		if (dr != -1) {
			if (dr + 1 == k) {
				System.out.print(node.data);
				System.out.println("");
			} else
				printkdistanceNodeDown(node.left, k - dr - 2);
			return 1 + dr;
		}

		// If target was neither present in left nor in right subtree
		return -1;
	}

	public void printkdistanceNode(int k) {
		printkdistanceNode2(this.root, this.root.left.right, k);
		// 20 true 8 true 4 false false true 12 true 10 false false true 14 false false
		// true 22 false false
	}

	private int printkdistanceNode2(Node node, Node target, int k) {
		if (node == null) {
			return -1;
		}
		if (node == target) {
			printkdistanceNodeDown(node, k);
			return 0;
		}
		int dl = printkdistanceNode2(node.left, target, k);
		if (dl != -1) {

			if (dl + 1 == k) {
				System.out.println(node.data + " ");

			} else {
				printkdistanceNodeDown(node.right, k - dl - 2);
				return dl + 1;
			}

		}

		int dr = printkdistanceNode2(node.right, target, k);
		if (dr != -1) {

			if (dr + 1 == k) {
				System.out.println(node.data + " ");

			} else {
				printkdistanceNodeDown(node.left, k - dr - 2);
				return dr + 1;
			}

		}
		return -1;
	}

	// Find distance between two nodes of a Binary Tree
	public int findLevel(int a) {
		return findLevel(this.root, a, 0);
		// 1 true 2 true 4 false false true 5 false false true 3 true 6 false true 8
		// false false true 7 false false
	}

	private int findLevel(Node node, int a, int level) {

		if (node == null) {
			return -1;
		}
		if (node.data == a) {
			return level;
		}
		int k = findLevel(node.left, a, level + 1);
		if (k != -1) {
			return k;
		} else {
			return findLevel(node.right, a, level + 1);
		}

	}

	// Find distance between two nodes of a Binary Tree
	public int DIST(int a, int b) {
		return Dist(this.root, a, b);
	}

	private int Dist(Node node, int a, int b) {

		if (node == null) {
			return -1;
		}

		int n1 = findLevel(this.root, a, 0);
		int n2 = findLevel(this.root, b, 0);

		Node lca = loewstcommonancestor(this.root, a, b);
		// return lca.data;
		int n3 = findLevel(this.root, lca.data, 0);
		return n1 + n2 - 2 * n3;

	}

	// Expression Tree

	private Node expressiontree(char[] arr) {

		Stack<Node> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			if (!isexpression(arr[i])) {
				Node kk = new Node();
				kk.data = arr[i];
				stack.push(kk);
			} else {

				Node kk = new Node();
				kk.data = arr[i];

				Node n1 = stack.pop();
				Node n2 = stack.pop();

				kk.right = n1;
				kk.left = n2;

				stack.push(kk);

			}
		}

		Node n3 = stack.pop();
		return n3;

	}

	private boolean isexpression(char ch) {
		if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
			return true;
		}
		return false;
	}

	// public void expressiontreedsiplay(char [] arr) {
	// BinaryTree bt = new BinaryTree();
	//
	// Node kk = bt.expressiontree(arr);
	// display(kk);
	// }

	// print all leafs of the left
	// Find sum of all left leaves in a given Binary Tree

	public int sumofallleftleaves() {
		return sumofallleftleaves(this.root);
	}

	private int sumofallleftleaves(Node node) {
		if (node == null) {
			return 0;
		}
		int result = 0;

		if (isleaf(node.left)) {
			result += node.left.data;
		}
		result += sumofallleftleaves(node.left);

		result += sumofallleftleaves(node.right);

		return result;
	}

	private boolean isleaf(Node node) {
		if (node == null) {
			return false;
		}
		if (node.left == null && node.right == null) {
			return true;
		}
		return false;
	}

	// Remove nodes on root to leaf paths of length < K

	public Node removenodeslengthk(int k) {
		return removenodeslengthk(this.root, 1, k);
		// 1 true 2 true 4 true 7 false false false true 5 false false true 3 false true
		// 6 true 8 false false false
	}

	private Node removenodeslengthk(Node node, int level, int k) {

		if (node == null) {
			return null;
		}

		node.left = removenodeslengthk(node.left, level + 1, k);
		node.right = removenodeslengthk(node.right, level + 1, k);

		if (node.left == null && node.right == null && level < k) {
			return null;
		} else {
			return node;
		}

	}

	// Find Height of Binary Tree represented by Parent array

	public int findHeight(int parent[]) {

		int[] depth = new int[parent.length];
		Arrays.fill(depth, 0);

		for (int i = 0; i < parent.length; i++) {
			findHeightdepth(parent, i, depth);
		}

		Arrays.sort(depth);

		return depth[depth.length - 1];
		//
	}

	private void findHeightdepth(int[] parent, int i, int[] depth) {

		if (depth[i] != 0) {
			return;
		}
		if (parent[i] == -1) {
			depth[i] = 1;
			return;
		}

		if (depth[parent[i]] == 0) {
			findHeightdepth(parent, parent[i], depth);
		}

		depth[i] = depth[parent[i]] + 1;

	}
	// Print nodes between two given level numbers of a binary tree

	public void nodebwtwolevels(int a, int b) {
		nodebwtwolevels(this.root, a, b);
		// 20 true 8 true 4 false false true 12 true 10 false false true 14 false false
		// true 22 false false
	}

	private void nodebwtwolevels(Node node, int a, int b) {
		int level = 1;
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);
		queue.add(null);

		while (!queue.isEmpty()) {

			Node n = queue.peek();
			queue.poll();

			if (n == null) {
				System.out.println("");
				level++;

				if (queue.isEmpty() == true || level > b) {
					break;
				}

				queue.add(null);
				continue;
			}

			if (level >= a) {
				System.out.print(n.data + " ");
			}

			if (n.left != null) {
				queue.add(n.left);
			}
			if (n.right != null) {
				queue.add(n.right);
			}

		}

	}

	// Reverse alternate levels of a perfect binary tree

	public void reversealternative() {
		reversealternative(this.root);
		//
	}

	private void reversealternative(Node node) {

		reversealternative(node.left, node.right, 0);
		// 40 true 20 true 10 true 11 false false true 15 false false true 30 true 9
		// false false true 8 false false true 60 true 50 true 53 false false true 58
		// false false true 70 true 73 false false true 87 false false

	}

	private void reversealternative(Node node1, Node node2, int i) {

		if (node1 == null || node2 == null) {
			return;
		}

		if (i % 2 == 0) {
			int temp = node1.data;
			node1.data = node2.data;
			node2.data = temp;

		}

		reversealternative(node1.left, node2.right, i + 1);
		reversealternative(node1.right, node2.left, i + 1);

	}

	// Remove all nodes which don’t lie in any path with sum>= k
	public Node prune(int k) {
		int sum = 0;
		return prune(this.root, k);
		// 1 true 2 true 4 true 8 false false true 9 true 13 false false true 14 true 15
		// false false false true 5 true 12 false false false true 3 true 6 false false
		// true 7 true 10 false true 11 false false false
	}

	private Node prune(Node node, int sum) {

		if (node == null) {
			return null;
		}

		node.left = prune(node.left, sum - node.data);
		node.right = prune(node.right, sum - node.data);

		if (node.left == null && node.right == null) {
			if (node.data < sum) {
				return null;
			}
		}

		return node;
	}

	// print path node to leaf all paths

	public void printPaths() {
		printPaths(this.root, "");
	}

	private void printPaths(Node root, String path) {

		if (root == null)
			return;
		if (root.left == null && root.right == null) {
			System.out.println(path + root.data);
			return;
		}

		printPaths(root.left, path + root.data + " ");
		printPaths(root.right, path + root.data + " ");
	}

	// Check if all leaves are at same level
	private class samelevel {
		int level = 0;
	}

	public boolean allleavesareatsamelevel() {
		samelevel l = new samelevel();
		return allleavesareatsamelevel(this.root, 1, l);
		// 12 true 5 true 3 false false false true 7 false true 1 false false
	}

	private boolean allleavesareatsamelevel(Node node, int level, samelevel l) {

		if (node == null) {
			return true;
		}
		if (node.left == null && node.right == null) {
			if (l.level == 0) {
				l.level = level;
				return true;
			} else {
				return (level == l.level);
			}
		}

		boolean b1 = allleavesareatsamelevel(node.left, level + 1, l);
		boolean b2 = allleavesareatsamelevel(node.right, level + 1, l);

		return b1 && b2;

	}

	// Deepest left leaf node in a binary tree
	private static Node n;

	public void deepestleftleafnode() {
		samelevel l = new samelevel();
		deepestleftleafnode(this.root, 0, l, false);
		System.out.println(n.data);
		// 1 true 2 true 4 false false false true 3 true 5 false true 7 true 9 false
		// false false true 6 false true 8 false true 10 false false
	}

	private void deepestleftleafnode(Node node, int i, samelevel l, boolean isLeft) {

		if (node == null) {
			return;
		}

		if (isLeft == true && node.left == null && node.right == null && i > l.level) {

			n = node;
			l.level = i;
		}

		deepestleftleafnode(node.left, i + 1, l, true);
		deepestleftleafnode(node.right, i + 1, l, false);

	}
	// Diagonal Sum of a Binary Tree

	public void printDiagonal() throws Exception {
		HashMap<Integer, Integer> hm = new HashMap<>(5);
		int hd = 0;
		getprintDiagonalhm(this.root, hd, hm);

		hm.display();
		//
	}

	private void getprintDiagonalhm(Node node, int hd, HashMap<Integer, Integer> hm) throws Exception {

		if (node == null) {
			return;
		}

		Integer i = hm.get(hd);

		if (i == null) {
			i = new Integer(0);
			i += node.data;
		} else {
			i += node.data;
		}
		hm.put(hd, i);

		getprintDiagonalhm(node.left, hd + 1, hm);
		getprintDiagonalhm(node.right, hd, hm);

	}

	// closest leaf distance

	public void closestLeafDistance(int k) {
		closestLeafDistance(this.root, k);
		// 5 true 7 false false true 8 true 9 true 11 true 13 false false true 17 false
		// false false true 10 false true 14 true 15 false false false
	}

	private void closestLeafDistance(Node root, int key) {
		int[] ans = new int[1];// instead of using global variable
		closestLeafDist(root, key, ans);
		System.out.println(ans[0]);
	}

	private int closestLeafDist(Node node, int key, int[] ans) {

		if (node == null) {
			return 0;
		}

		if (node.data == key) {
			ans[0] = closestleafdistuntil(node);
			return 1;
		}

		int left = closestLeafDist(node.left, key, ans);
		int right = closestLeafDist(node.right, key, ans);

		if (left != 0) {
			ans[0] = Math.min(ans[0], left + 1 + closestleafdistuntil(node.right));
			return left + 1;
		} else if (right != 0) {
			ans[0] = Math.min(ans[0], right + 1 + closestleafdistuntil(node.left));
			return right + 1;
		}

		return 0;
	}

	private int closestleafdistuntil(Node node) {

		if (node == null) {
			return Integer.MAX_VALUE;
		}
		if (node.left == null && node.right == null) {
			return 0;
		}

		int left = closestleafdistuntil(node.left);
		int right = closestleafdistuntil(node.right);

		return 1 + Math.min(left, right);
	}

	// Sum of all the numbers that are formed from root to leaf paths

	public int sumofallno() {
		return sumofallno(this.root, 0);
		// 6 true 3 true 2 false false true 5 true 7 false false true 4 false false true
		// 5 false true 4 false false
	}

	private int sumofallno(Node node, int val) {
		if (node == null) {
			return 0;
		}

		val = val * 10 + node.data;

		if (node.left == null && node.right == null) {
			return val;
		}

		int left = sumofallno(node.left, val);
		int right = sumofallno(node.right, val);

		return left + right;

	}
	// Difference between sums of odd level and even level nodes of a Binary Tree

	public int sumofodd() {
		return sumofodd(this.root);
	}
// 5 true 2 true 1 false false true 4 true 3 false false false true 6 false true 8 true 7 false false true 9 false false
	private int sumofodd(Node node) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(this.root);
		queue.add(null);
		int odd = 0;
		int even = 0;
		int counter = 0;
		while (!queue.isEmpty()) {
			Node temp = queue.peek();
			queue.poll();
			
			if (temp != null) {
				if (counter % 2 == 0) {
					odd += temp.data;
				} else {
					even += temp.data;
				}
				if (temp.left != null) {
					queue.add(temp.left);
				}
				if (temp.right != null) {
					queue.add(temp.right);
				}

			}else {
				
				counter++;
				if(counter == 4) {
					break;
				}
				queue.add(null);
			}
		}

		return odd-even;
	}

}
