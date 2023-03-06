package io.github.jam01.rea.examples.distributor;

import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.commitments.SalesContract;
import io.github.jam01.rea.examples.distributor.commitments.SalesLine;
import io.github.jam01.rea.examples.distributor.agents.Customer;
import io.github.jam01.rea.examples.distributor.agents.Enterprise;
import io.github.jam01.rea.examples.distributor.agents.RevenueAgency;
import io.github.jam01.rea.examples.distributor.commitments.PaymentOrder;
import io.github.jam01.rea.examples.distributor.commitments.SalesOrder;
import io.github.jam01.rea.examples.distributor.commitments.SalesVAT;
import io.github.jam01.rea.examples.distributor.events.CollectionTransfer;
import io.github.jam01.rea.examples.distributor.events.Sale;
import io.github.jam01.rea.examples.distributor.events.Payment;
import io.github.jam01.rea.examples.distributor.resources.BankAccount;
import io.github.jam01.rea.examples.distributor.resources.Cash;
import io.github.jam01.rea.examples.distributor.resources.MoneyType;
import io.github.jam01.rea.examples.distributor.resources.ProductType;
import io.github.jam01.rea.examples.distributor.resources.ProductStock;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleTest {

    @Test
    public void test() {
        // units of measure
        var usd = new UnitOfMeasure("US Dollar", "$");
        var unit = new UnitOfMeasure("Unit", "u");
        var kilograms = new UnitOfMeasure("Kilograms", "kg");

        // the enterprise
        var enterprise = Enterprise.getInstance();

        // anon customer
        var customer = new Customer("anonymous");

        // tax office
        var revenueService = new RevenueAgency("internal revenue service");

        // financial resources -- consider funding events
        var dollarMoney = new MoneyType("dollar", usd);
        var register = new Cash(dollarMoney, "register", BigDecimal.valueOf(100));
        var bnkAcct = new BankAccount(dollarMoney, "checking", "central bank", "93012309876", BigDecimal.valueOf(100));

        // product types
        var bottledWater = new ProductType("bottled water", unit, Value.asDecimal(1, usd));
        var soda = new ProductType("soda", unit, Value.asDecimal(2, usd));
        var rice = new ProductType("rice", kilograms, Value.asDecimal(1, usd));

        // stock resources
        var waterInventory = new ProductStock<>(bottledWater, Value.of(100, unit));
        var sodaInventory = new ProductStock<>(soda, Value.of(100, unit));
        var riceInventory = new ProductStock<>(rice, Value.asDecimal(50, kilograms));

        // commitments
        var salesOrder = new SalesOrder(customer,
                List.of(new SalesLine(bottledWater, Value.of(20, unit), Value.asDecimal(2, usd)), // overriding price
                        new SalesLine(soda, Value.of(20, unit)), // using product price
                        new SalesLine(rice, Value.asDecimal(20, kilograms)))); // unit of measure != unit
        var payOrder = new PaymentOrder(customer, enterprise,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.total())));
        var vat = new SalesVAT(revenueService,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.vat())));

        // check the order's amounts
        System.out.println("sales order subtotal: " + salesOrder.subtotal().value());
        System.out.println("sales order vat: " + salesOrder.vat().value());
        System.out.println("sales order total: " + salesOrder.total().value());
        assertEquals(Value.of(new BigDecimal("100"), usd), salesOrder.subtotal());
        assertEquals(Value.of(new BigDecimal("15.00"), usd), salesOrder.vat());
        assertEquals(Value.of(new BigDecimal("115.00"), usd), salesOrder.total());

        System.out.println("payment order total: " + payOrder.total().value());
        assertEquals(Value.of(new BigDecimal("115.00"), usd), payOrder.total());

        System.out.println("vat total: " + vat.total().value());
        assertEquals(Value.of(new BigDecimal("15.00"), usd), vat.total());

        // create the contract
        var commitments = List.of(salesOrder, payOrder, vat);
        var saleContract = new SalesContract(commitments);

        // sale of resources
        var sale = new Sale(customer,
                List.of(new CollectionTransfer<>(new ProductStock<>(bottledWater, 20), null, waterInventory),
                        new CollectionTransfer<>(new ProductStock<>(soda, 20), null, sodaInventory),
                        new CollectionTransfer<>(new ProductStock<>(rice, BigDecimal.valueOf(20)), null, riceInventory)));

        // check the inventory is decreased
        assertEquals(Value.of(80, unit), waterInventory.quantity());
        assertEquals(Value.of(80, unit), sodaInventory.quantity());
        assertEquals(Value.asDecimal(30, kilograms), riceInventory.quantity());

        // not complete as we haven't registered any events
        assertFalse(saleContract.isComplete());

        // customer payments
        var payment1 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer<>(new Cash(dollarMoney, "anonymous", BigDecimal.valueOf(75)), register, null)));
        var payment2 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer<>(new Cash(dollarMoney, "anonymous", BigDecimal.valueOf(40)), register, null)));

        // tax payment
        var taxPayment = new Payment(customer, revenueService,
                List.of(new CollectionTransfer<>(new Cash(dollarMoney, "anonymous", BigDecimal.valueOf(15)), null, bnkAcct)));

        // check the money resources are increased/decreased
        assertEquals(Value.of(new BigDecimal("215"), usd), register.quantity());
        assertEquals(Value.of(new BigDecimal("85"), usd), bnkAcct.quantity());

        // execute commitments by respective events
        var execdSale = salesOrder.execute(List.of(sale));
        var execPay = payOrder.execute(List.of(payment1, payment2));
        var execdTaxPay = vat.execute(List.of(taxPayment));

        assertTrue(execdSale.isFulfilled());
        assertTrue(execPay.isFulfilled());
        assertTrue(execdTaxPay.isFulfilled());

        var execdSaleContract = saleContract.updateCommitments(List.of(execdSale, execPay, execdTaxPay));
        assertTrue(execdSaleContract.isComplete());
    }
}
