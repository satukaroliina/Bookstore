package BackEnd.Bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Book {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Title cannot be empty.")
	@Size(min=1, max=50)
	private String title;
	
	@Size(min=1, max=50)
	private String author;

	@Min(1000)
	private int publicationYear;
	
	@Size(min=1, max=50)
	private String isbn;
	
	private double price;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
	
	public Book() {}
	
	public Book(String title, String author, int publicationYear, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		if (this.category != null) {
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", isbn=" + isbn + ", price=" + price + "category =" + this.getCategory() + "]";
		} else {
			return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", isbn=" + isbn + ", price=" + price + "]";
		}
	}
}
