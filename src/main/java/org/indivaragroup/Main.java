package org.indivaragroup;

import org.indivaragroup.coffeeshop.menu.MenuItem;
import org.indivaragroup.coffeeshop.order.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<MenuItem> menus = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        MenuItem[] itemSelected = new MenuItem[3];

        menus.add(new MenuItem("1.americano", 0, BigDecimal.ZERO));
        menus.add(new MenuItem("2.latte", 0, BigDecimal.ZERO));
        menus.add(new MenuItem("3.cappuccino", 0, BigDecimal.ZERO));


        System.out.println("\n============ MENU ==============");

        for (MenuItem menu : menus) {
            System.out.println(menu.getName());
        }

        for (int i = 0; i < 3; i++) {

            // MENU
            System.out.print("\nEnter menu number: ");

            String menuNumber = scanner.nextLine();

            MenuItem selectedMenu = Order.selectMenu(menus, menuNumber);

            if (Objects.isNull(selectedMenu)) {
                System.out.println("Invalid menu number");
                i--;
                continue;
            }



            // QUANTITY
            System.out.print("Enter quantity: ");

            String quantity = scanner.nextLine();

            if (!quantity.matches("\\d+") || Integer.parseInt(quantity) <= 0) {
                System.out.println("Quantity must be numeric and greater than zero");
                i--;
                continue;
            }

            Integer quantityInput = Integer.parseInt(quantity);



            // PRICE
            System.out.print("Enter price: ");

            String price = scanner.nextLine();

            if (!price.matches("\\d+") || new BigDecimal(price).compareTo(BigDecimal.ZERO) <= 0 ) {
                System.out.println("Price must be numeric and greater than zero");
                i--;
                continue;
            }

            BigDecimal priceInput = new BigDecimal(price);




            // CREATE ORDER ITEM
            MenuItem orderItem = new MenuItem(selectedMenu.getName(), quantityInput, priceInput);

            itemSelected[i] = orderItem;



            if(i != 2){
                // AGAIN
                System.out.print("Select again? (yes/no) : ");

                String again = scanner.nextLine();

                if (again.equalsIgnoreCase("no")) {
                    break;
                }
            }
        }

        Order order = new Order(itemSelected);
        order.printReceipt();
        scanner.close();
    }
}