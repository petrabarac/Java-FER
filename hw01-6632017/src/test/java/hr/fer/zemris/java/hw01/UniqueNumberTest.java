package hr.fer.zemris.java.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static hr.fer.zemris.java.hw01.UniqueNumbers.*;

public class UniqueNumberTest {

	@Test
	public void dodajcvor() {
		TreeNode glava = null;
		glava = addNode(glava, 42);
		glava = addNode(glava, 76);
		glava = addNode(glava, 21);
		glava = addNode(glava, 76);
		glava = addNode(glava, 35);
		assertEquals(42, glava.value);
		assertEquals(21, glava.left.value);
		assertEquals(35, glava.left.right.value);
		assertEquals(76, glava.right.value);
	}
	
	@Test
	public void duljinaStabla() {
		TreeNode glava = null;
		assertEquals(0, UniqueNumbers.treeSize(glava));
	}
	
	@Test
	public void duljinaStabla4() {
		TreeNode glava = null;
		glava = addNode(glava, 42);
		glava = addNode(glava, 76);
		glava = addNode(glava, 21);
		glava = addNode(glava, 76);
		glava = addNode(glava, 35);
		assertEquals(4, UniqueNumbers.treeSize(glava));
		
	}
	
	
}
