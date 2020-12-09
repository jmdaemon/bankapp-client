package test.money;

import app.money.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*; 

public class MoneyTests {
  private Money five;
  private Bank bank;
  private Expression fiveBucks;
  private Expression tenFrancs;

  @BeforeEach
  public void setUp() {
    this.five = Money.dollar(5);
    this.bank = new Bank();
    this.bank.addRate("CHF", "USD", 2);
    this.fiveBucks = Money.dollar(5);
    this.tenFrancs = Money.franc(10);
  }

  @Test
  public void equals_Dollars_ReturnTrue() {
    assertTrue(Money.dollar(5).equals(Money.dollar(5)));
  }

  @Test
  public void times_Dollars_ReturnsProduct() {
    assertEquals(Money.dollar(10), five.times(2));
  }

  @Test
  public void currency_DollarsFrancs_ReturnsDollarsFrancs() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  public void plus_Money_ReturnsSum() {
    Expression sum = five.plus(five);
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  public void plus_FiveDollars_ReturnsSum() {
    Expression result = five.plus(five);
    Sum sum = (Sum) result;
    assertEquals(five, sum.augend);
    assertEquals(five, sum.addend);
  }

  @Test
  public void sum_Money_ReturnsMoney() {
    Expression sum = new Sum (Money.dollar(3), Money.dollar(4));
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  public void reduce_Money_ReturnsMoney() {
    Money result = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void reduce_DifferentCurrency_ReturnsMoney() {
    Money result = bank.reduce(Money.franc(2), "USD");
    assertEquals(Money.dollar(1), result);
  }

  @Test
  public void rate_SameCurrency_Return1() {
    assertEquals(1, new Bank().rate("USD", "USD"));
  }

  @Test
  public void sum_Money_ReturnSum() {
    Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
    assertEquals(Money.dollar(10), result);
  }

  @Test
  public void sum_DollarFranc_ReturnSum() {
    Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(15), result);
  }

  @Test
  public void times_Money_ReturnsProduct() {
    Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(20), result);
  }
}
