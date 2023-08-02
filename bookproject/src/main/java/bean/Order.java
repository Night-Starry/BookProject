package bean;


import java.util.ArrayList;

public class Order {

  private String id;
  private String name;
  private String time;
  private double total;

  private ArrayList<Goods> all_goods = new ArrayList<>();

  public Order(){}

  public Order(String id, String name, String time, double total) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.total = total;
  }

  public Order(String id, String name, String time, double total, ArrayList<Goods> all_goods) {
    this.id = id;
    this.name = name;
    this.time = time;
    this.total = total;
    this.all_goods = all_goods;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public ArrayList<Goods> getAll_goods() {
    return all_goods;
  }

  public void setAll_goods(ArrayList<Goods> all_goods) {
    this.all_goods = all_goods;
  }

  @Override
  public String toString() {
    return "Order{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", time='" + time + '\'' +
            ", total=" + total +
            '}';
  }
}
