package bean;


public class Book {

    private final String default_path = "/WEB-INF/static/img/default.png";

    private String isbn;
    private String name;
    private String author;
    private String version;

    private double price;
    private int stock;
    private int sale;
    private String img;


    public Book() {}

    public Book(String isbn, String name, String author) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.img = this.default_path;
    }

    public Book(String isbn, String name, String author, String version, double price, int stock, int sale) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.version = version;
        this.price = price;
        this.stock = stock;
        this.sale = sale;
        this.img = this.default_path;
    }

    public Book(String isbn, String name, String author, String version, double price, int stock, int sale, String img) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.version = version;
        this.price = price;
        this.stock = stock;
        this.sale = sale;
        if (img == null || img.equals("")) {
            this.img = this.default_path;
        } else {
            this.img = img;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        if (img == null || img.equals("") || img.equals("null")) {
            this.img = this.default_path;
        } else {
            this.img = img;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "default_path='" + default_path + '\'' +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", sale=" + sale +
                ", img='" + img + '\'' +
                '}';
    }

}
