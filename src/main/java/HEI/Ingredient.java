package HEI;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private Double price;
    private IngredientCategory category;
    private List<StockMovement> stockMovementList = new ArrayList<>();

    public Ingredient(int id, String name, Double price, IngredientCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public StockValue getStockValueAt(Instant t){
        double qty = 0.0;

        for  (StockMovement sm : stockMovementList){
            if(sm.getCreationDateTime() != null && sm.getCreationDateTime().isAfter(t)){
                double q = sm.getValue().getQuantity();
                if(sm.getType() == MovementType.IN){
                    qty += q;
                } else if(sm.getType() == MovementType.OUT){
                    qty -= q;
                }
            }
        }
        return new StockValue(qty, Unit.KG);
    }

    public void addStockMovement(StockMovement stockMovement){
        stockMovementList.add(stockMovement);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public List<StockMovement> getStockMovementList() {
        return stockMovementList;
    }

    public void setStockMovementList(List<StockMovement> stockMovementList) {
        this.stockMovementList = stockMovementList;
    }
}
