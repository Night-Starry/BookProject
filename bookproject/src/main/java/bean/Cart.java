package bean;


public class Cart {

  private String name;
  private String isbn;

  public Cart(){}

  public Cart(String name, String isbn) {
    this.name = name;
    this.isbn = isbn;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  @Override
  public String toString() {
    return "Cart{" +
            "name='" + name + '\'' +
            ", isbn='" + isbn + '\'' +
            '}';
  }
}
