package org.indivaragroup.coffeeshop.menu;

import org.indivaragroup.coffeeshop.interfacing.IMenu;


public class MenuItem implements IMenu {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
