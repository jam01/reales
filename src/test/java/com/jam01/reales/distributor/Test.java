package com.jam01.reales.distributor;

import com.jam01.reales.core.Reservation;
import com.jam01.reales.core.attributes.UnitOfMeasure;
import com.jam01.reales.core.attributes.Value;
import com.jam01.reales.distributor.agents.Customer;
import com.jam01.reales.distributor.agents.Enterprise;
import com.jam01.reales.distributor.agents.RevenueAgency;
import com.jam01.reales.distributor.commitments.PaymentOrder;
import com.jam01.reales.distributor.commitments.SalesOrder;
import com.jam01.reales.distributor.commitments.SalesVAT;
import com.jam01.reales.distributor.events.Delivery;
import com.jam01.reales.distributor.events.Payment;
import com.jam01.reales.distributor.resources.BankAccount;
import com.jam01.reales.distributor.resources.Cash;
import com.jam01.reales.distributor.resources.Money;
import com.jam01.reales.distributor.resources.Product;
import com.jam01.reales.distributor.resources.ProductInventory;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        // units of measure
        var usd = new UnitOfMeasure("US Dollar", "$");
        var unit = new UnitOfMeasure("Unit", "u");

        // the enterprise
        var enterprise = new Enterprise();

        // financial resources
        var dollarMoney = new Money("dollar", usd);
        var register = new Cash(dollarMoney, "register", Value.of(10, usd));
        // should I create a funding event? --
        var bnkAcct = new BankAccount(dollarMoney, "checking", "central bank", "93012309876", Value.of(100, usd));

        // product types
        var waterProduct = new Product("bottled water", unit, Value.of(1, usd));
        var sodaProduct = new Product("soda", unit, Value.of(2, usd));

        // inventory resources
        var waterInventory = new ProductInventory(waterProduct, 100);
        var sodaInventory = new ProductInventory(sodaProduct, 50);

        // anon customer
        var customer = new Customer("anonymous");

        // tax office
        var revenueService = new RevenueAgency("internal revenue service");

        // commitments
        var salesOrder = new SalesOrder(enterprise, customer,
                List.of(new SalesLine(waterProduct, 1, Value.of(1, usd)), // overriding price
                        new SalesLine(sodaProduct, 1))); // using product price
        var payOrder = new PaymentOrder(customer, enterprise,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.total())));
        var vat = new SalesVAT(enterprise, revenueService,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.vat())));
        

        System.out.println("sales order subtotal: " + salesOrder.subtotal().value());
        System.out.println("sales order vat: " + salesOrder.vat().value());
        System.out.println("sales order total: " + salesOrder.total().value());

        System.out.println("payment order total: " + payOrder.total().value());

        System.out.println("vat total: " + vat.total().value());


        // deliver goods
        var delivery = new Delivery(enterprise, customer,
                List.of(new CollectionTransfer(new ProductInventory(waterProduct, 1), null, null, waterInventory),
                        new CollectionTransfer(new ProductInventory(sodaProduct, 1), null, null, sodaInventory)));

        // customer payments
        var payment1 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", Value.of(1, usd)), null, register, null)));
        var payment2 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", Value.of(2.45, usd)), null, register, null)));

        // tax payment
        var taxPayment = new Payment(customer, revenueService,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", Value.of(2.45, usd)), null, register, null)));


//        var x = payOrder.func(payOrder).apply(payOrder, null);

        System.out.println();
        // try to derive the accounting artifacts and claims
        // profit and loss
        // sales and purchases
        // inventory stock value
        // inventory accounting...? kardex

        // ask  for chart of accounts print out







//        var vat = new VAT(enterprise, revenueService, List.of(), 15);

//        var enterprise = new Enterprise();
//        var bottledWater = new BottledWater(10);
//        var soda = new Soda(5);
//
//        var customer = new Customer();
//        var cust10usd = new Cash(USD_CASH, new Value(BigDecimal.valueOf(10), USD));
//
//
//        // reservations
////        var line1 = new SalesLine(bottledWater, new Value(BigDecimal.valueOf(1), UNIT));
////        var line2 = new SalesLine(soda, new Value(BigDecimal.valueOf(1), UNIT));
//
//        var salesOrder = new SalesOrder(List.of(line1, line2), enterprise, customer);



        // create commitments
//        var line1 = new SalesLine(new Reservation(bottledWater), enterprise, customer, new Value(BigDecimal.valueOf(1), UNIT));
//        var line2 = new SalesLine(new Reservation(soda), enterprise, customer, new Value(BigDecimal.valueOf(1), UNIT));
//        var paymentLine = new PaymentLine(new Reservation(USD_CASH), customer, enterprise, new Value(BigDecimal.valueOf(10), USD));


//         create contract
//        // always a contract ~ schedule (times)
//        // agreement -> (govern)  commitment
////        var salesOrder = new SalesOrder(List.of(line1, line2, paymentLine));
//
//        // record sale
//        var sale = new Sale(List.of(new Transfer(bottledWater, null)), enterprise, customer);
////        salesOrder.fulfill(sale);
//        // if quantity and types are the same, then auto-fulfilled
//        // otherwise fulfilled when marked **
//
//        assert !contract.isCompleted();
//
//        // record payment
//        var payment = new CashPayment(List.of(new Transfer(cust10usd, null)), customer, enterprise);
////        assert salesOrder.isCompleted(); // commitments are fulfilled, contract is completed
//
//        line1.fulfilledBy(sale);
//        line1.fulfilledBy(sale);
//        line1.fulfilledBy(payment);
    }
}

// sales lines is a relation between the commitment and the resource... can have properties like quantity
// sales order is the commitment (mccarthy)
// date on SO
// no date on SalesLine
// contracts can have several commitment
// multiple events fulfill multiple commitments

// what to call sales order contract?
// contract has-a sales order (commitment)
