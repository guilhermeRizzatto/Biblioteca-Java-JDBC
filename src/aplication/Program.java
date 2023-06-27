package aplication;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int opt;
		
		do {
			System.out.println(" SELECT WHAT YOU WANT (ADD/MODIFY/REMOVE/SHOW) : ");
			
			System.out.println("IMPORTANT -> IF DON'T HAVE ANY LIBRARIES INSERTED, PLEASE INSERT ONE FOR INSERT A NEW BOOK (do the same to the Genrer)");
			System.out.println("-------------------------");
			System.out.println("1  - Genrer");
			System.out.println("2  - Book");
			System.out.println("3  - Library");
			System.out.println("-------------------------");
			System.out.println("0  - EXIT");
			
			opt = sc.nextInt();
			
			switch(opt) {
			case 1 -> UiObjects.genrerUi(sc);
			case 2 -> UiObjects.livroUi(sc);
			case 3 -> UiObjects.bibliotecaUi(sc);
			}

		}while(opt != 0);
		System.out.println("exiting....");
	}

}
