package org.indivaragroup.coffeeshop.interfacing;


import java.math.BigDecimal;

public interface IMenu {
    String getName();
    Integer getQuantity();
    BigDecimal getPrice();

    void setName(String name);
    void setQuantity(Integer quantity);
    void setPrice(BigDecimal price);
}