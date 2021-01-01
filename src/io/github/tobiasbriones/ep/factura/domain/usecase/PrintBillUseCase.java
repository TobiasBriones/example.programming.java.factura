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

package io.github.tobiasbriones.ep.factura.domain.usecase;

import io.github.tobiasbriones.ep.factura.domain.model.bill.Bill;
import io.github.tobiasbriones.ep.factura.domain.model.bill.BillPrinter;

public final class PrintBillUseCase {

    private final Bill bill;

    public PrintBillUseCase(Bill bill) {
        this.bill = bill;
    }

    public void execute(BillPrinter printer) {
        printer.clear();

        printer.setCustomer(bill.getCustomer());
        printer.setDate(bill.getDate());
        printer.setRtn(bill.getRtn());
        bill.getItems().forEach(printer::addItem);
        printer.setBillResume(bill);
        printer.print();
    }

}
