package bean;


public class Goods {

  private String isbn;

  private int num;
  private double price;
  private String id;

  public Goods(){}

  public Goods(String isbn, int num, String id) {
    this.isbn = isbn;
    this.num = num;
    this.id = id;
  }

  public int getNum() {
    return num;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public Goods(String isbn, int num, double price, String id) {
    this.isbn = isbn;
    this.num = num;
    this.price = price;
    this.id = id;
  }

  public Goods(String isbn, int num, double price) {
    this.isbn = isbn;
    this.num = num;
    this.price = price;
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


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Goods{" +
            "isbn='" + isbn + '\'' +
            ", num=" + num +
            ", price=" + price +
            ", id='" + id + '\'' +
            '}';
  }
}
