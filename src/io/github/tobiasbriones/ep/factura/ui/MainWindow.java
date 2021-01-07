/*
 * Copyright (c) 2019-2020 Tobias Briones. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 *
 * This file is part of Example Project: Factura.
 *
 * This source code is licensed under the MIT License found in the
 * LICENSE file in the root directory of this source tree or at
 * https://opensource.org/licenses/MIT.
 */

package io.github.tobiasbriones.ep.factura.ui;

import io.github.tobiasbriones.ep.factura.data.ProductDao;
import io.github.tobiasbriones.ep.factura.domain.model.basket.BasketItemModel;
import io.github.tobiasbriones.ep.factura.domain.model.basket.BasketModel;
import io.github.tobiasbriones.ep.factura.domain.model.bill.Bill;
import io.github.tobiasbriones.ep.factura.domain.model.product.ProductModel;
import io.github.tobiasbriones.ep.factura.ui.core.rx.AnyObservable;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.header.Header;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.header.HeaderComponent;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.items.Items;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.items.ItemsComponent;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.summary.Summary;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.summary.SummaryComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class MainWindow extends JFrame implements Header.Output, Summary.Output, Items.Output {

    //                                                                                            //
    //                                                                                            //
    //                                           CLASS                                            //
    //                                                                                            //
    //                                                                                            //

    public interface Controller {

        ProductDao getProductDao();

        BasketModel getBasket();

        void pushToBasket(ProductModel product);

        void save(Bill bill);

    }

    //                                                                                            //
    //                                                                                            //
    //                                          INSTANCE                                          //
    //                                                                                            //
    //                                                                                            //

    private final Controller controller;
    private final AnyObservable basketObservable;

    public MainWindow(Controller controller) {
        super("Factura");
        this.controller = controller;
        this.basketObservable = new AnyObservable();

        init();
    }

    @Override
    public void onAddProduct(ProductModel product) {
        controller.pushToBasket(product);
        basketObservable.notifyObservers();
    }

    @Override
    public void onPrint() {

    }

    @Override
    public void onPrintWithNewCustomer() {

    }

    @Override
    public void onItemUpdated(BasketItemModel item) {
        basketObservable.notifyObservers();
    }

    private void init() {
        final var productDao = controller.getProductDao();
        final var basket = controller.getBasket();
        final var panel = new JPanel();
        final var headerComponent = HeaderComponent.newInstance(productDao);
        final var headerPanel = headerComponent.getViewComponent();
        final var itemsComponent = ItemsComponent.newInstance(basket);
        final var itemsPanel = itemsComponent.getViewComponent();
        final var summaryComponent = SummaryComponent.newInstance(basket);
        final var summaryPanel = summaryComponent.getViewComponent();

        headerComponent.setOutput(this);

        itemsComponent.setOutput(this);
        itemsComponent.subscribe(basketObservable);

        summaryComponent.setOutput(this);
        summaryComponent.subscribe(basketObservable);

        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(headerPanel, BorderLayout.PAGE_START);
        panel.add(itemsPanel, BorderLayout.CENTER);
        panel.add(summaryPanel, BorderLayout.PAGE_END);
        getContentPane().add(panel);

        setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}
