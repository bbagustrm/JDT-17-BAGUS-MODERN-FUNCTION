package org.indivaragroup;

import org.indivaragroup.coffeeshop.menu.MenuItem;
import org.indivaragroup.coffeeshop.order.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<MenuItem> menus = new ArrayList<>();

        menus.add(new MenuItem("1.americano"));
        menus.add(new MenuItem("2.latte"));
        menus.add(new MenuItem("3.cappuccino"));

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter item name: ");
        String itemChoice = scanner.nextLine();

        MenuItem selectedMenu = Order.selectMenu(menus, itemChoice);

        if (selectedMenu == null) {
            throw new IllegalArgumentException("Invalid item name");
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (quantity == 0) {
            throw new ArithmeticException("Cannot by zero");
        }

        System.out.print("Enter price: ");
        BigDecimal price = scanner.nextBigDecimal();

        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ArithmeticException("Cannot by minus");
        }

        Order order = new Order(selectedMenu, quantity, price);
        order.printReceipt();

        scanner.close();
    }
}