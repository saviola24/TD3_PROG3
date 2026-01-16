package HEI;

public class Dish {
    private int id;
    private String name;
    private String dishType;
    private Double sellingPrice;

    public Dish(int id, String name, String dishType, Double sellingPrice) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.sellingPrice = sellingPrice;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDishType() {
        return dishType;
    }
    public Double getSellingPrice() {
        return sellingPrice;
    }
     public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}