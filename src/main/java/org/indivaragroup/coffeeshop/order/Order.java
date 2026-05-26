package org.indivaragroup.coffeeshop.order;

import org.indivaragroup.Main;
import org.indivaragroup.coffeeshop.menu.MenuItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Base64;

public class Order {

    private MenuItem[] itemSelected;
    private Integer tax = (int) (Math.random() * 21);

    public Order(MenuItem[] itemSelected) {
        this.itemSelected = itemSelected;
    }

    public BigDecimal getItemSubtotal(int index) {
        MenuItem item = itemSelected[index];
        if (item == null) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(item.getQuantity()).multiply(item.getPrice());
    }


    public BigDecimal getSubtotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < itemSelected.length; i++) {
            if (itemSelected[i] == null) {
                continue;
            }
            total = total.add(getItemSubtotal(i));
        }

        BigDecimal taxAmount = total.multiply(BigDecimal.valueOf(tax)).divide(BigDecimal.valueOf(100));

        return total.add(taxAmount);
    }

    public void printReceipt() throws IOException {

        StringBuilder receipt = new StringBuilder();

        UUID receiptUUID = UUID.randomUUID();

        Properties prop = new Properties();

        prop.load(Main.class.getClassLoader().getResourceAsStream("application.properties"));

        Arrays.sort(
                itemSelected,
                Comparator.nullsLast(
                        Comparator.comparing(
                                item -> item.getName().split("\\.")[1]
                        )
                )
        );

        receipt.append("\n============= RECEIPT =============\n");

        receipt.append("Store \t\t: ").append(prop.getProperty("store.name")).append("\n");
        receipt.append("Cashier \t: ").append(prop.getProperty("cashier.name")).append("\n");
        receipt.append("UUID \t\t: ").append(receiptUUID).append("\n\n");

        for (int i = 0; i < itemSelected.length; i++) {

            MenuItem item = itemSelected[i];

            if (item == null) {
                continue;
            }

            receipt.append("---------------------\n");
            receipt.append("Item\t\t: ").append(item.getName().split("\\.")[1]).append("\n");
            receipt.append("Qty\t\t\t: ").append(item.getQuantity()).append("\n");
            receipt.append("Price\t\t: ").append(item.getPrice()).append("\n");
            receipt.append("Tax\t\t\t: ").append(tax).append("%").append("\n");
            receipt.append("Subtotal\t: ").append(getItemSubtotal(i)).append("\n");
            receipt.append("---------------------\n");
        }

        receipt.append("Total\t\t: ").append(getSubtotal()).append("\n");

        receipt.append("============================");

        // ENCODE RECEIPT
        String encodedReceipt = Base64.getEncoder().encodeToString(receipt.toString().getBytes());

        receipt.append("\n\nEncoded Receipt :\n");

        receipt.append(encodedReceipt);

        System.out.println(receipt);
    }


    public static MenuItem selectMenu(ArrayList<MenuItem> menus, String menuNumber) {
        for (MenuItem menu : menus) {
            String number = menu.getName().split("\\.")[0];

            if (number.equals(menuNumber)) {
                return menu;
            }
        }
        return null;
    }



}
