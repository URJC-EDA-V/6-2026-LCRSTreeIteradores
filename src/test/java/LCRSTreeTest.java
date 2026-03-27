import es.urjc.grafo.EDA.trees.LCRSTree;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LCRSTreeTest {
    
    public LCRSTreeTest() {
    }
    
    private LCRSTree<Integer> tree = new LCRSTree<>();

	@Test
	public void setTree() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);

		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);

		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);

		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);
	}

	@Test
	public void testSize() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		Position<Integer> h = this.tree.add(300, p);
		this.tree.add(400, h);
		this.tree.add(500, h);
		Assertions.assertEquals(5, this.tree.size());
	}

	@Test
	public void testSize2() {
		this.setTree();
		Assertions.assertEquals(12, this.tree.size());
	}

	@Test
	public void testRoot() {
		this.setTree();
		Integer a = this.tree.root().getElement();
		Assertions.assertEquals(1, a);
	}

	@Test
	public void testIsEmpty() {
        Assertions.assertTrue(this.tree.isEmpty());
	}

	@Test
	public void testIsEmpty2() {
		Position<Integer> p = this.tree.addRoot(2);
		this.tree.add(3, p);
        Assertions.assertFalse(this.tree.isEmpty());
	}

	@Test
	public void testParent2() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);
		Assertions.assertEquals(p2, tree.parent(p3));
	}

	@Test
	public void testParent3() {
		this.setTree();
		try {
			this.tree.parent(null);
		} catch (RuntimeException e) {
			Assertions.assertTrue(true);
		}
	}

	@Test
	public void testPositions() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);
		StringBuilder salida = new StringBuilder();
		for (Position<Integer> e : this.tree) {
			salida.append(e.getElement());
		}
		Assertions.assertEquals("100200300", salida.toString());
	}

	@Test
	public void testRemove() {
		Position<Integer> p = this.tree.addRoot(100);
		Position<Integer> q = this.tree.add(200, p);
		Position<Integer> h = this.tree.add(300, p);
		this.tree.add(400, h);
		this.tree.add(500, h);
		this.tree.remove(h);
		Assertions.assertEquals(2, this.tree.size());
	}

	@Test
	public void testRemove2() {
		this.setTree();
		this.tree.remove(this.tree.root());
		Assertions.assertEquals(0, this.tree.size());
	}

	@Test
	public void testRemove3() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);

		this.tree.remove(p2);

		StringBuilder s = new StringBuilder();
		for (Position<Integer> pos : this.tree) {
			s.append(pos.getElement());
		}
		Assertions.assertEquals("12354", s.toString());
	}

	@Test
	public void testGetUnmodifiableChildren() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);
		Iterable<? extends Position<Integer>> l = this.tree.children(p);
		try {
			l.iterator().remove();
			Assertions.fail("The children collection has been modified");
		} catch (Exception e) {
			Assertions.assertTrue(true);
		}
	}

	@Test
	public void testGetChildren() {
		Position<Integer> p = this.tree.addRoot(100);
		this.tree.add(200, p);
		this.tree.add(300, p);

		StringBuilder salida = new StringBuilder();
		for (Position<Integer> e : this.tree.children(p)) {
			salida.append(e.getElement());
		}
		Assertions.assertEquals("200300", salida.toString());
	}

	@Test
	public void testGetChildren2() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);

		StringBuilder salida = new StringBuilder();
		for (Position<Integer> e : this.tree.children(p3)) {
			salida.append(e.getElement());
		}
		Assertions.assertEquals("9101112", salida.toString());
	}

	@Test
	public void testIterator() {
		this.setTree();

		StringBuilder s = new StringBuilder();
		for (Position<Integer> pos : this.tree) {
			s.append(pos.getElement());
		}
		Assertions.assertEquals("123567891011124", s.toString());
	}

	@Test
	public void testIsRoot() {
		this.setTree();
		Integer a = this.tree.root().getElement();
		Assertions.assertEquals(1, a);

	}

	@Test
	public void testIsRoot2() {
		try {
			this.tree.isRoot(null);
		} catch (RuntimeException e) {
			Assertions.assertTrue(true);
		}
	}

	@Test
	public void testSwapElements() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);


		this.tree.swapElements(p, p1);
		this.tree.swapElements(p2, p3);
		
		StringBuilder salida = new StringBuilder();
		for (Position<Integer> e : this.tree) {
			salida.append(e.getElement());
		}
		Assertions.assertEquals(salida.toString(), "321587691011124");
	}

	@Test
	public void testReplace() {
		Position<Integer> p = tree.addRoot(1);
		tree.add(2, p);
		Position<Integer> p1 = tree.add(3, p);
		tree.add(4, p);
		tree.add(5, p1);
		Position<Integer> p2 = tree.add(6, p1);
		tree.add(7, p2);
		Position<Integer> p3 = tree.add(8, p2);
		tree.add(9, p3);
		tree.add(10, p3);
		tree.add(11, p3);
		tree.add(12, p3);


		this.tree.replace(p, -1);
		this.tree.replace(p1, -2);
		this.tree.replace(p2, -3);
		this.tree.replace(p3, -4);
		
		StringBuilder salida = new StringBuilder();
		for (Position<Integer> e : this.tree) {
			salida.append(e.getElement());
		}
		Assertions.assertEquals(salida.toString(), "-12-25-37-491011124");
	}    
}
