package org.indivaragroup.coffeeshop.order;

import org.indivaragroup.coffeeshop.menu.MenuItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Order {

    private MenuItem menu;
    private int quantity;
    private BigDecimal price;

    public Order(MenuItem menu, int quantity, BigDecimal price) {
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public void printReceipt() {
        StringBuilder receipt = new StringBuilder();

        receipt.append("\n==== RECEIPT ====\n");
        receipt.append("Item \t\t: ").append(menu.getName()).append("\n");
        receipt.append("Qty \t\t: ").append(quantity).append("\n");
        receipt.append("Price \t\t: ").append(price).append("\n");
        receipt.append("Subtotal \t: ").append(getSubtotal()).append("\n");
        receipt.append("Total \t\t: ").append(getSubtotal()).append("\n");
        receipt.append("================");

        System.out.println(receipt);
    }

    public static MenuItem selectMenu(ArrayList<MenuItem> menus, String menuName) {

        for (MenuItem menu : menus) {
            if (menu.getName().split("\\.")[0].equalsIgnoreCase(menuName)) {
                return menu;
            }
        }
        return null;
    }

}
