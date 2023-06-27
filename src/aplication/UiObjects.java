package aplication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.BibliotecaDao;
import model.dao.DaoFactory;
import model.dao.GenrerDao;
import model.dao.LivroDao;
import model.entities.Biblioteca;
import model.entities.Genrer;
import model.entities.Livro;

public class UiObjects {
	
		public static void genrerUi(Scanner sc) {

		GenrerDao genrerDao = DaoFactory.createGenrerDao();

		System.out.println("DB FUNC: 1 || INSERTING 32 NEW genrer INTO genres");
		
		List<Genrer> listOfGenres = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Guilherme\\Documents\\VsCode WorkSpace\\genres.csv"))){
			
			String line = br.readLine();
			while(line != null) {
				String nameGenrer = line;				
				Genrer genrer = new Genrer(nameGenrer);
				listOfGenres.add(genrer);
				line = br.readLine();
			}	
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		for(Genrer x : listOfGenres) {
			genrerDao.insert(x);			
		}
		
		System.out.println("INSERTED!");
	
		System.out.println("-----------------------------------------------------");

		System.out.println("DB FUNC: 2 || FIND ALL IN genres ORDER BY NAME_GENRER ");
		List<Genrer> list = genrerDao.findAll();
		for (Genrer x : list) {
			System.out.println(x);
		}
		
		System.out.println("-----------------------------------------------------");

		System.out.println("DB FUNC: 3 || FIND BY Name_Genrer IN genres");
		System.out.print("INSERT NAME_GENRER: ");
		sc.nextLine();
		String nameGenrer = sc.nextLine();
		Genrer genrer = genrerDao.findByName(nameGenrer);
		System.out.println(genrer);

		System.out.println("-----------------------------------------------------");
	
		System.out.println("DB FUNC: 4 || UPDATE IN genres");
		System.out.print("INSERT AN OLD NAME_GENRER: ");
		nameGenrer = sc.nextLine();
		String oldNameGenrer = genrerDao.findByName(nameGenrer).getName_genrer();
		System.out.println(genrer);
		System.out.print("NOW INSERT A NEW NAME_GENRER: ");
		String newNameGenrer = sc.nextLine();		
		genrer.setName_genrer(newNameGenrer);
		genrerDao.update(genrer, oldNameGenrer);
		System.out.println("UPDATE COMPLETE!");						
	
		System.out.println("-----------------------------------------------------");	
	
		System.out.println("DB FUNC: 5 || DELETE BY Name_Genrer IN genres");
		System.out.print("INSERT NAME_GENRER: ");
		nameGenrer = sc.nextLine();
		genrerDao.deleteByName(nameGenrer);
		System.out.println("DELETED!!");
		System.out.println();
		}
		public static void livroUi(Scanner sc) {			

			LivroDao livroDao = DaoFactory.createLivroDao();

			System.out.println("DB FUNC: 1 || INSERT NEW Livro INTO livros");
			System.out.print("INSERT TITLE: ");
			String title = sc.nextLine();
			System.out.print("INSERT AUTOR: ");
			String autor = sc.nextLine();
			System.out.print("INSERT PUBLISHER: ");
			String publisher = sc.nextLine();
			System.out.print("INSERT RELEASE_DATE: (YYYY-MM-DD) ");
			String releaseDate = sc.nextLine();
			System.out.print("INSERT GENRER: ");
			String nameGenrer = sc.nextLine();
			System.out.print("INSERT BIBLIOTECA ID: ");
			int bibliotecaId = sc.nextInt();
			Livro newLivro = new Livro(null, title, autor, publisher, LocalDate.parse(releaseDate), new Genrer(nameGenrer), bibliotecaId);
			livroDao.insert(newLivro);
			System.out.println("INSERTED! NEW ID = " + newLivro.getId());
			
			System.out.println("-----------------------------------------------------");

			System.out.println("DB FUNC: 2 || FIND ALL IN livros ORDER BY TITLE ");
			List<Livro> list = livroDao.findAll();
			for (Livro x : list) {
				System.out.println(x);
			}
			
			System.out.println("-----------------------------------------------------");

			System.out.println("DB FUNC: 3 || FIND BY Id IN livros");
			System.out.print("INSERT AN ID: ");
			int id = sc.nextInt();
			Livro livro = livroDao.findById(id);
			System.out.println(livro);

			System.out.println("-----------------------------------------------------");
			
			System.out.println("DB FUNC: 4 || FIND BY Genrer IN livros");
			System.out.print("INSERT A GENRER: ");
			sc.nextLine();
			nameGenrer = sc.nextLine();
			Genrer genrer = new Genrer(nameGenrer);
			list = livroDao.findByGenrer(genrer);
			for (Livro x : list) {
				System.out.println(x);
			}
			
			System.out.println("-----------------------------------------------------");
			
			System.out.println("DB FUNC: 5 || UPDATE IN livros");
			System.out.print("INSERT AN ID: ");
			id = sc.nextInt();
			livro = livroDao.findById(id);
			System.out.println(livro);
			System.out.print("WHICH DATA YOU WANT TO UPDATE? 1 - TITLE, 2 - PUBLISHER, 3 - RELEASE_DATE, 4 - GENRER, 5 - BIBLIOTECA ID : ");
			int opt = sc.nextInt();
			sc.nextLine();
			switch(opt) {
			case 1: 
				System.out.print("INSERT TITLE: ");
				String newTitle = sc.nextLine();
				livro.setTitle(newTitle);
				livroDao.update(livro);
				System.out.println("UPDATE COMPLETE!");
				break;
			case 2: 
				System.out.print("INSERT PUBLISHER: ");
				String newPublisher = sc.nextLine();
				livro.setPublisher(newPublisher);
				livroDao.update(livro);
				System.out.println("UPDATE COMPLETE!");
				break;
			case 3: 
				System.out.print("INSERT RELEASE_DATE: (YYYY-MM-DD) ");
				String newRelease_Date = sc.nextLine();
				livro.setReleaseDate(LocalDate.parse(newRelease_Date));
				livroDao.update(livro);
				System.out.println("UPDATE COMPLETE!");
				break;
			case 4: 
				System.out.print("INSERT GENRER: ");
				String newGenrer = sc.nextLine();
				livro.setGenrer(new Genrer(newGenrer));
				livroDao.update(livro);
				System.out.println("UPDATE COMPLETE!");
				break;
			case 5: 
				System.out.print("INSERT BIBLIOTECA ID: ");
				int newBibliotecaId = sc.nextInt();
				livro.setBibliotecaId(newBibliotecaId);
				livroDao.update(livro);
				System.out.println("UPDATE COMPLETE!");
				break;		
			}
			
			System.out.println("-----------------------------------------------------");	
			
			System.out.println("DB FUNC: 6 || DELETE BY Id IN livros");
			System.out.print("INSERT ID: ");
			id = sc.nextInt();
			livroDao.deleteById(id);
			System.out.println("DELETED!!");
			System.out.println();
			
			
		}
		public static void bibliotecaUi(Scanner sc) {			

			BibliotecaDao bibliotecaDao = DaoFactory.createBibliotecaDao();

			System.out.println("DB FUNC: 1 || INSERT NEW Biblioteca INTO bibliotecas");
			System.out.print("INSERT NAME: ");
			String name = sc.nextLine();
			System.out.print("INSERT LOCATION: ");
			String location = sc.nextLine();
			Biblioteca newBiblioteca = new Biblioteca(null, name, location);
			bibliotecaDao.insert(newBiblioteca);
			System.out.println("INSERTED! NEW ID = " + newBiblioteca.getId());
			
			System.out.println("-----------------------------------------------------");

			System.out.println("DB FUNC: 2 || FIND ALL IN bibliotecas ORDER BY NAME ");
			List<Biblioteca> list = bibliotecaDao.findAll();
			for (Biblioteca x : list) {
				System.out.println(x);
			}
			
			System.out.println("-----------------------------------------------------");

			System.out.println("DB FUNC: 3 || FIND BY Id IN bibliotecas");
			System.out.print("INSERT AN ID: ");
			int id = sc.nextInt();
			Biblioteca biblioteca = bibliotecaDao.findById(id);
			System.out.println(biblioteca);

			System.out.println("-----------------------------------------------------");
			
			System.out.println("DB FUNC: 4 || UPDATE IN bibliotecas");
			System.out.print("INSERT AN ID: ");
			id = sc.nextInt();
			biblioteca = bibliotecaDao.findById(id);
			System.out.println(biblioteca);
			System.out.print("WHICH DATA YOU WANT TO UPDATE? 1 - NAME OR 2 - LOCATION: ");
			int opt = sc.nextInt();
			sc.nextLine();
			switch(opt) {
			case 1: 
				System.out.print("INSERT NAME: ");
				String newName = sc.nextLine();
				biblioteca.setName(newName);
				bibliotecaDao.update(biblioteca);
				System.out.println("UPDATE COMPLETE!");
				break;
			case 2: 
				System.out.print("INSERT LOCATION: ");
				String newLocation = sc.nextLine();
				biblioteca.setLocation(newLocation);
				bibliotecaDao.update(biblioteca);
				System.out.println("UPDATE COMPLETE!");
				break;
			}
			
			System.out.println("-----------------------------------------------------");	
			
			System.out.println("DB FUNC: 5 || DELETE BY Id IN bibliotecas");
			System.out.print("INSERT ID: ");
			id = sc.nextInt();
			bibliotecaDao.deleteById(id);
			System.out.println("DELETED!!");
			System.out.println();
		}
		
}

