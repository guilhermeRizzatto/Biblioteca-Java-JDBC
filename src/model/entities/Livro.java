package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String title;
	private String autor;
	private String publisher;
	private LocalDate releaseDate;
	private Genrer genrer;
	private Integer bibliotecaId;
	
	public Livro() {
	}

	public Livro(Integer id, String title, String autor, String publisher, LocalDate releaseDate, Genrer genrer, Integer bibliotecaId) {
		this.id = id;
		this.title = title;
		this.autor = autor;
		this.publisher = publisher;
		this.releaseDate = releaseDate;
		this.genrer = genrer;
		this.bibliotecaId = bibliotecaId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Genrer getGenrer() {
		return genrer;
	}

	public void setGenrer(Genrer genrer) {
		this.genrer = genrer;
	}
	
	public Integer getBibliotecaId() {
		return bibliotecaId;
	}

	public void setBibliotecaId(Integer bibliotecaId) {
		this.bibliotecaId = bibliotecaId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, bibliotecaId, genrer, id, publisher, releaseDate, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(bibliotecaId, other.bibliotecaId)
				&& Objects.equals(genrer, other.genrer) && Objects.equals(id, other.id)
				&& Objects.equals(publisher, other.publisher) && Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", title=" + title + ", autor=" + autor + ", publisher=" + publisher
				+ ", releaseDate=" + releaseDate + ", genrer=" + genrer + ", bibliotecaId=" + bibliotecaId + "]";
	}

	

}
