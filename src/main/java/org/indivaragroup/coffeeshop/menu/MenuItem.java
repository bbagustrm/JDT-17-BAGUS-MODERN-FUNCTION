package org.indivaragroup.coffeeshop.menu;

import org.indivaragroup.coffeeshop.interfacing.IMenu;

import java.math.BigDecimal;

public class MenuItem implements IMenu {

    private String name;
    private Integer quantity;
    private BigDecimal price;

    public MenuItem(String name, Integer quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}