package datastructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTest {
	
	AVL<Integer,String> avl = new AVL<Integer,String>();
	
	void setup1() {
		avl.put(12, "Jhon");
		avl.put(23, "Julian");
		avl.put(37, "Anderson");
		avl.put(2, "Carlos");
		avl.put(45, "Darwin");
		avl.put(1, "Paul");
	}
	
	@Test
	void searchTest() {
		setup1();
		assertEquals("Jhon", avl.search(12).toString());
		assertEquals("Paul", avl.search(1).toString());
		assertEquals("Anderson", avl.search(37).toString());
	}

	@Test
	void removeTest() {
		setup1();
		avl.remove(12);
		assertEquals(null, avl.search(12));
		avl.remove(2);
		assertEquals(null, avl.search(2));
		avl.remove(2);
		assertEquals(null, avl.search(2));
	}
	
}
