package HEI;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever();

        try {
            
            Ingredient ingredient = new Ingredient();
            ingredient.setName("Tomate");
            ingredient.setPrice(1.5);
            ingredient.setCategory(IngredientCategory.VEGETABLE);
            ingredient = dataRetriever.saveIngredient(ingredient);
            System.out.println("Ingrédient sauvegardé : " + ingredient);

            // Test 2: Récupérer tous les ingrédients
            List<Ingredient> ingredients = dataRetriever.findAllIngredients();
            System.out.println("Liste des ingrédients :");
            for (Ingredient ing : ingredients) {
                System.out.println(ing);
            }

            
            Table table = new Table(1, 10);
            Order order = new Order();
            order.setReference("CMD001");
            order.setTotalHt(50.0);
            order.setTotalTtc(60.0);
            order.setCreationDateTime(Instant.now());
            order.setTable(table);

            order = dataRetriever.saveOrder(order);
            System.out.println("Commande sauvegardée : " + order);

        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        } finally {
            dataRetriever.close();
        }
    }
}