/*
 * Copyright (c) 2019 Tobias Briones.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.github.tobiasbriones.ep.factura.model;

import io.github.tobiasbriones.ep.factura.model.customer.Customer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class Bill {

    private final List<BasketItem> items;
    private Customer customer;
    private String rtn;
    private String date;
    private double subtotal;
    private double isv;
    private double total;
    private int totalItems;

    public Bill() {
        this.items = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer value) {
        this.customer = value;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String value) {
        this.rtn = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String value) {
        this.date = value;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIsv() {
        return isv;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return items.size() + " productos, en total " + totalItems
               + "- total $" + new DecimalFormat(".##").format(total);
    }

    public void addItem(BasketItem item) {
        items.add(item);
        subtotal += item.getAmount();
        isv += item.getISV();
        total += item.getTotal();
        totalItems += item.getQuantity();
    }

    public void clear() {
        items.clear();
        customer = null;
        rtn = null;
        date = null;
        subtotal = 0;
        isv = 0;
        total = 0;
    }

}
