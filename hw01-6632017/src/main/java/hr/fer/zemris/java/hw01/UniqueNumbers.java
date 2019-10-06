package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Program stvara binarno stablo. Stablo ne sadrži čvorove sa istim vrijednostima.
 * 
 * @author Petra
 * @version 1.0
 */

public class UniqueNumbers {

	
	 public static class TreeNode {
			TreeNode left;
			TreeNode right;
			int value;

		}
	 public static boolean containsValue(TreeNode head, int n) {
		 if(head == null) {
			 return false;
		 }
		 if(head.value == n) {
			 return true;
		 } else if(head.value > n) {
			 return containsValue(head.left, n);
		 } else {
			 return containsValue(head.right, n);
		 }
	 }
		 
	 
	 public static int treeSize(TreeNode head) {
		 
		 if(head == null) {
			 return 0;
		 } else {
			 return (treeSize(head.left) + 1 + treeSize(head.right)); 
		 }	 
	 }

/**
 * Metoda kreira čvor binarnog stabla. 
 * 
 * @param head sadrži root čvor
 * @param number sadrži cijeli broj koji se dodaje kao čvor stablu
 * @return root čvor
 */
	 
	 public static TreeNode addNode(TreeNode head, int number) {
		
		 if(head == null) {
			 TreeNode node = new TreeNode();
			 node.value = number;
			 head = node;
		 }
		 if(number < head.value) {
			 head.left = addNode(head.left, number);			 
		 } else if(number > head.value) {
			 head.right = addNode(head.right, number);
		 }
		 
		 return head;
	 }
	 
	 public static void printAscending(TreeNode current) {
		 if(current == null) {
			 return;
		 }
		 printAscending(current.left);
		 System.out.format("%d ", current.value);
		 printAscending(current.right);
	 }
	 
	 public static void printDescending(TreeNode current) {
		 if(current == null) {
			 return;
		 }
		 printDescending(current.right);
		 System.out.format("%d ", current.value);
		 printDescending(current.left);	 
	 }

/**
 * Metoda od koje kreće izvođenje programa 
 * @param args argumenti komandne linije 
 */
		 
		 
	 public static void main(String... args) {
		
			
		TreeNode glava = null;
		/*glava = addNode(glava, 42);
		glava = addNode(glava, 76);
		glava = addNode(glava, 21);
		glava = addNode(glava, 76);
		glava = addNode(glava, 35);*/
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("Unesite broj > ");
			String number = sc.next();
			if(number.equals("kraj")) {
				System.out.println("Doviđenja");
				break;
			}			
			try {
				int intValue = Integer.parseInt(number);
				if(!containsValue(glava,intValue)) {
					glava = addNode(glava, intValue);
				} else {
					System.out.println("Uneseni broj već postoji u stablu");
				}
				} catch (NumberFormatException e) {
					System.out.format( " '%s'  nije cijeli broj%n", number);
				}
			}
		
		/*if(containsValue(glava, 34)) {
			System.out.println("da");
		}*/
		System.out.print("Ispis od najmanjeg: ");
		printAscending(glava);
		
		System.out.println();
		
		System.out.print("Ispis od najvećeg: ");
		printDescending(glava);
		
		/*int velicina = treeSize(glava);
		System.out.print("Veličina stabla je: " + velicina);*/
	} 
	 
}
