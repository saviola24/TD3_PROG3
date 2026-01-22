package HEI;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private int id;
    private String name;
    private DishType dishType;
    private Double sellingPrice;

    private List<DishIngredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient, double quantity, Unit unit) {
        DishIngredient di = new DishIngredient(this, ingredient, quantity, unit);
        ingredients.add(di);
    }

    public Dish(int id, String name, DishType dishType, Double sellingPrice) {
        this.id = id;
        this.name = name;
        this.dishType = dishType;
        this.sellingPrice = sellingPrice;
    }

    public double getDishCost(){
        double cost = 0.0;

        for (DishIngredient di : ingredients){
            double ingPrice = 0;
            cost += ingPrice * di.getQuantityRequired();
        }
        return cost;
    }

    public double getGrossMargin(){
        if(sellingPrice == null){
            throw new IllegalStateException("Le prix de vente est null pour ce plat: "+ name);
        }
        return sellingPrice - getDishCost();
    }

    public void addDishIngredient(DishIngredient di){
        ingredients.add(di);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public List<DishIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<DishIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
