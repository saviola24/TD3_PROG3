package HEI;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dr = new DataRetriever();

        try {

            Ingredient laitue = new Ingredient(0, "Laitue", 800.0, IngredientCategory.VEGETABLE);
            laitue.addStockMovement(new StockMovement(0, new StockValue(5.0, Unit.KG), MovementType.IN, Instant.parse("2024-01-05T08:00:00Z")));
            laitue.addStockMovement(new StockMovement(0, new StockValue(0.2, Unit.KG), MovementType.OUT, Instant.parse("2024-01-06T12:00:00Z")));
            dr.saveIngredient(laitue);


            Ingredient tomate = new Ingredient(0, "Tomate", 600.0, IngredientCategory.VEGETABLE);
            tomate.addStockMovement(new StockMovement(0, new StockValue(4.0, Unit.KG), MovementType.IN, Instant.parse("2024-01-05T08:00:00Z")));
            tomate.addStockMovement(new StockMovement(0, new StockValue(0.15, Unit.KG), MovementType.OUT, Instant.parse("2024-01-06T12:00:00Z")));
            dr.saveIngredient(tomate);


            Instant t = Instant.parse("2024-01-06T12:00:00Z");
            List<Ingredient> ingredients = dr.findAllIngredients();

            System.out.println("\nNiveau de stock à " + t + " :");
            for (Ingredient ing : ingredients) {
                StockValue sv = ing.getStockValueAt(t);
                System.out.printf("%s : %.2f %s%n", ing.getName(), sv.getQuantity(), sv.getUnit());
            }


            System.out.println("\nListe des ingrédients en base :");
            for (Ingredient ing : ingredients) {
                System.out.println(ing.getName() + " (" + ing.getCategory() + ") - Prix : " + ing.getPrice());
            }

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
            e.printStackTrace();
        } finally {
            dr.close();
        }
    }
}