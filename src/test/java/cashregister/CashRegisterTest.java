package cashregister;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CashRegisterTest {


    @Test
    public void should_print_the_real_purchase_when_call_process() {
        CashRegister cashRegister = new CashRegister(new Printer());
        Item[] items = {new Item("可乐", 3.0), new Item("薯片", 5.5)};
        Purchase purchase = new Purchase(items);

        assertThrows(UnsupportedOperationException.class, () -> cashRegister.process(purchase));
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        Printer printer = new Printer();
        CashRegister cashRegister = new CashRegister(printer);
        Purchase purchase = mock(Purchase.class);

        when(purchase.asString()).thenReturn("mock");

        assertThrows(UnsupportedOperationException.class, () -> cashRegister.process(purchase));
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        Printer mockPrinter = mock(Printer.class);
        CashRegister cashRegister = new CashRegister(mockPrinter);
        Purchase mockPurchse = mock(Purchase.class);

        cashRegister.process(mockPurchse);

        verify(mockPrinter).print(mockPurchse.asString());
        verify(mockPurchse, times(2)).asString();

    }


}
