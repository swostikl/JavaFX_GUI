package dao;

import datasource.MariaDbConnection;
import entity.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDao {

    public double getRate(String abbreviation) {
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT rateTOUSD FROM CURRENCY WHERE abbreviation = ?";
        try (PreparedStatement state = conn.prepareStatement(sql)) {
            state.setString(1, abbreviation);
            try (ResultSet rs = state.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("rateTOUSD");
                } else {
                    throw new RuntimeException("Currency not found: " + abbreviation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        Connection conn = MariaDbConnection.getConnection();
        String sql = "SELECT abbreviation, name, rateTOUSD FROM CURRENCY";
        try (PreparedStatement state = conn.prepareStatement(sql);
             ResultSet rs = state.executeQuery()) {
            while (rs.next()) {
                currencies.add(new Currency(
                        rs.getString("abbreviation"),
                        rs.getString("name"),
                        rs.getDouble("rateTOUSD")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
        return currencies;
    }
}
