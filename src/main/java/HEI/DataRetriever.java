package HEI;

import java.sql.*;
import java.time.Instant;
import java.util.*;

public class DataRetriever {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }


    public Ingredient saveIngredient(Ingredient toSave) throws SQLException {
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            String sqlIng = toSave.getId() > 0 ?
                    "UPDATE Ingredient SET name = ?, price = ?, category = ?::ingredient_category WHERE id = ? RETURNING id" :
                    "INSERT INTO Ingredient (name, price, category) VALUES (?, ?, ?::ingredient_category) RETURNING id";

            try (PreparedStatement ps = conn.prepareStatement(sqlIng)) {
                ps.setString(1, toSave.getName());
                ps.setDouble(2, toSave.getPrice());
                ps.setString(3, toSave.getCategory().name());
                if (toSave.getId() > 0) {
                    ps.setInt(4, toSave.getId());
                }
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        toSave.setId(rs.getInt(1));
                    }
                }
            }

            for (StockMovement sm : toSave.getStockMovementList()) {
                String sqlMv = """
                            INSERT INTO StockMovement
                            (id_ingredient, quantity, type, unit, creation_datetime)
                            VALUES (?, ?, ?::movement_type, ?::unit_type, ?)
                        """;

                try (PreparedStatement ps = conn.prepareStatement(sqlMv)) {
                    ps.setInt(1, toSave.getId());
                    ps.setDouble(2, sm.getValue().getQuantity());
                    ps.setString(3, sm.getType().name());
                    ps.setString(4, sm.getValue().getUnit().name());
                    ps.setTimestamp(5, Timestamp.from(sm.getCreationDateTime()));
                    ps.executeUpdate();
                }
            }

            conn.commit();
            return toSave;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ignored) {}
            }
            throw e;
        } finally {
            if (conn != null) {
                try { conn.setAutoCommit(true); } catch (SQLException ignored) {}
            }
        }
    }

    public List<Ingredient> findAllIngredients() throws SQLException {
        List<Ingredient> result = new ArrayList<>();
        Map<Integer, Ingredient> map = new HashMap<>();

        String sql = """
        SELECT i.id, i.name, i.price, i.category,
               sm.id AS sm_id, sm.quantity, sm.type, sm.unit, sm.creation_datetime
        FROM Ingredient i
        LEFT JOIN StockMovement sm ON i.id = sm.id_ingredient
        ORDER BY i.id, sm.creation_datetime
    """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String category = rs.getString("category");

                Ingredient ing = map.computeIfAbsent(id, k ->
                        new Ingredient(id, name, price, IngredientCategory.valueOf(category))
                );

                int smId = rs.getInt("sm_id");
                if (!rs.wasNull()) {
                    StockMovement sm = new StockMovement(
                            smId,
                            new StockValue(rs.getDouble("quantity"), Unit.valueOf(rs.getString("unit"))),
                            MovementType.valueOf(rs.getString("type")),
                            rs.getTimestamp("creation_datetime").toInstant()
                    );
                    ing.addStockMovement(sm);
                }
            }

            result.addAll(map.values());
        }

        return result;
    }
    public void close() {
        DBConnection.closeConnection();
    }
}