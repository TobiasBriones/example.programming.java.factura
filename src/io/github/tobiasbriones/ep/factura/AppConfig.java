/*
 * Copyright (c) 2020 Tobias Briones. All rights reserved.
 *
 * SPDX-License-Identifier: MIT
 *
 * This file is part of Example Project: Factura.
 *
 * This source code is licensed under the MIT License found in the
 * LICENSE file in the root directory of this source tree or at
 * https://opensource.org/licenses/MIT.
 */

package io.github.tobiasbriones.ep.factura;

import io.github.tobiasbriones.ep.factura.data.CityDao;
import io.github.tobiasbriones.ep.factura.data.CommunityDao;
import io.github.tobiasbriones.ep.factura.data.ProductDao;
import io.github.tobiasbriones.ep.factura.database.InMemoryProductDao;
import io.github.tobiasbriones.ep.factura.database.LocalStorageCityDao;
import io.github.tobiasbriones.ep.factura.database.LocalStorageCommunityDao;
import io.github.tobiasbriones.ep.factura.domain.model.basket.BasketList;
import io.github.tobiasbriones.ep.factura.domain.model.basket.BasketModel;
import io.github.tobiasbriones.ep.factura.ui.mainbilling.MainBillingWindow;

final class AppConfig {

    private final BasketModel basket;
    private final ProductDao productDao;
    private final CityDao cityDao;
    private final CommunityDao communityDao;

    AppConfig() {
        this.basket = new BasketList();
        this.productDao = new InMemoryProductDao();
        this.cityDao = new LocalStorageCityDao();
        this.communityDao = new LocalStorageCommunityDao();
    }

    MainBillingWindow.DependencyConfig newMainDependencyConfig() {
        return new MainBillingWindow.DependencyConfig(basket, productDao, cityDao, communityDao);
    }

}
