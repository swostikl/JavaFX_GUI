package Task2;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple ShoppingCart class that can add, remove, and total items.
 */
public class ShoppingCart {
    private final Map<String, Double> items = new HashMap<>();

    /**
     * Adds an item to the cart.
     * @param name  The item name.
     * @param price The item price.
     */
    public void addItem(String name, double price) {
        items.put(name, price);
    }

    /**
     * Removes an item from the cart.
     * @param name The item name to remove.
     */
    public void removeItem(String name) {
        items.remove(name);
    }

    /**
     * Returns the total number of items.
     * @return The count of items in the cart.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Calculates the total cost of items in the cart.
     * @return The total cost.
     */
    public double calculateTotal() {
        double total = 0.0;
        for (double price : items.values()) {
            total += price;
        }
        return total;
    }
}
