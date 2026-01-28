package HEI;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private String reference;
    private double totalHt;
    private double totalTtc;
    private Timestamp creationDate;
    private List<DishOrder> dishOrders = new ArrayList<>();


    public Order() {}


    public Order(List<DishOrder> dishOrders) {
        this.dishOrders = dishOrders;

    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public double getTotalHt() { return totalHt; }
    public void setTotalHt(double totalHt) { this.totalHt = totalHt; }
    public double getTotalTtc() { return totalTtc; }
    public void setTotalTtc(double totalTtc) { this.totalTtc = totalTtc; }
    public Timestamp getCreationDate() { return creationDate; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
    public List<DishOrder> getDishOrders() { return dishOrders; }
    public void setDishOrders(List<DishOrder> dishOrders) { this.dishOrders = dishOrders; }


    public void addDishOrder(Dish dish, int quantity) {
        dishOrders.add(new DishOrder(dish, quantity));
    }
}
