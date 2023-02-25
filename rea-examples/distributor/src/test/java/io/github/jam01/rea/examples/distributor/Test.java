package io.github.jam01.rea.examples.distributor;

import io.github.jam01.rea.Reservation;
import io.github.jam01.rea.attributes.UnitOfMeasure;
import io.github.jam01.rea.attributes.Value;
import io.github.jam01.rea.examples.distributor.domain.CollectionTransfer;
import io.github.jam01.rea.examples.distributor.domain.events.SalesLine;
import io.github.jam01.rea.examples.distributor.domain.agents.Customer;
import io.github.jam01.rea.examples.distributor.domain.agents.Enterprise;
import io.github.jam01.rea.examples.distributor.domain.agents.RevenueAgency;
import io.github.jam01.rea.examples.distributor.domain.commitments.PaymentOrder;
import io.github.jam01.rea.examples.distributor.domain.commitments.SalesOrder;
import io.github.jam01.rea.examples.distributor.domain.commitments.SalesVAT;
import io.github.jam01.rea.examples.distributor.domain.events.Sale;
import io.github.jam01.rea.examples.distributor.domain.events.Payment;
import io.github.jam01.rea.examples.distributor.domain.resources.BankAccount;
import io.github.jam01.rea.examples.distributor.domain.resources.Cash;
import io.github.jam01.rea.examples.distributor.domain.resources.MoneyType;
import io.github.jam01.rea.examples.distributor.domain.resources.ProductType;
import io.github.jam01.rea.examples.distributor.domain.resources.ProductInventory;

import java.math.BigDecimal;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        // units of measure
        var usd = new UnitOfMeasure<>("US Dollar", "$", BigDecimal.class);
        var unit = new UnitOfMeasure<>("Unit", "u", Integer.class);

        // the enterprise
        var enterprise = Enterprise.getInstance();

        // financial resources
        var dollarMoney = new MoneyType("dollar", usd);
        var register = new Cash(dollarMoney, "register", BigDecimal.TEN);
        // should I create a funding event? --
        var bnkAcct = new BankAccount(dollarMoney, "checking", "central bank", "93012309876", BigDecimal.valueOf(100));

        // product types
        var waterProduct = new ProductType("bottled water", unit, Value.asDecimal(1, usd));
        var sodaProduct = new ProductType("soda", unit, Value.asDecimal(2, usd));

        // inventory resources
        var waterInventory = new ProductInventory(waterProduct, 100);
        var sodaInventory = new ProductInventory(sodaProduct, 50);

        // anon customer
        var customer = new Customer("anonymous");

        // tax office
        var revenueService = new RevenueAgency("internal revenue service");

        // commitments
        var salesOrder = new SalesOrder(customer,
                List.of(new SalesLine(waterProduct, Value.of(1, unit), Value.asDecimal(1, usd)), // overriding price
                        new SalesLine(sodaProduct, Value.of(1, unit)))); // using product price
        var payOrder = new PaymentOrder(customer, enterprise,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.total())));
        var vat = new SalesVAT(revenueService,
                List.of(new Reservation.Specification(dollarMoney, salesOrder.vat())));
        

        System.out.println("sales order subtotal: " + salesOrder.subtotal().value());
        System.out.println("sales order vat: " + salesOrder.vat().value());
        System.out.println("sales order total: " + salesOrder.total().value());

        System.out.println("payment order total: " + payOrder.total().value());

        System.out.println("vat total: " + vat.total().value());


        // deliver goods
        var delivery = new Sale(customer,
                List.of(new CollectionTransfer(new ProductInventory(waterProduct, 1), null, waterInventory),
                        new CollectionTransfer(new ProductInventory(sodaProduct, 1), null, sodaInventory)));

        // customer payments
        var payment1 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", BigDecimal.ONE), register, null)));
        var payment2 = new Payment(customer, enterprise,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", BigDecimal.valueOf(2.45)), register, null)));

        // tax payment
        var taxPayment = new Payment(customer, revenueService,
                List.of(new CollectionTransfer(new Cash(dollarMoney, "anonymous", BigDecimal.valueOf(2.45)), register, null)));


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
