package minimax.v5_alpha_beta;

import static org.junit.Assert.*;

import java.util.Arrays;

import model.Board;
import project.caro.config.ConfigGame.Target;

public class Test {

	@org.junit.Test
	public void test() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		int[] l=node.nextLocation(0);
		assertArrayEquals(new int[] {0,0}, l);
	}
	@org.junit.Test
	public void test1() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		node.rowIndexBefore=2;
		node.colIndexBefore=2;
		int[] l=node.nextLocation(0);
		assertNull(l);
	}
	@org.junit.Test
	public void test3() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		node.rowIndexBefore=1;
		node.colIndexBefore=2;
		int[] l=node.nextLocation(0);
		assertArrayEquals(new int[] {2,0}, l);
	}
	@org.junit.Test
	public void test4() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		node.rowIndexBefore=1;
		node.colIndexBefore=1;
		int[] l=node.nextLocation(0);
		assertArrayEquals(new int[] {1,2}, l);
	}
	@org.junit.Test
	public void test5() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		node.initLeaf(1);
		int[] l=node.nextLocation(1);
//		System.out.println(l);
//		System.out.println(Arrays.toString(l));
		assertArrayEquals(new int[] {0,1}, l);
	}
	@org.junit.Test
	public void test6() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		Node leaf= node.initLeaf(1);
		int[] l=leaf.nextLocation(1);
		assertArrayEquals(new int[] {0,1}, l);
	}
	@org.junit.Test
	public void test7() {
		Node node = new Node(new Board(3, 3, 3), Target.X, true,0);
		Node leaf=node.initLeaf(1);
		Node childSameDepth = leaf.initNodeSameDepthSameParent( 0, 1);
		assertArrayEquals(new int[] {0,2}, new int[] {childSameDepth.rowIndexBefore, childSameDepth.colIndexBefore});
	}

}
