package zz.yy.tests;

import junit.framework.Assert;

import org.junit.Test;

import zz.yy.core.accounts.Money;

public class MoneyTest {

	@Test
	public void testZZ() throws Exception {
		Money money1 = Money.build(100);
		Money money2 = Money.build(10);

		Assert.assertEquals(Money.build(90), money1.substract(money2));
	}

	@Test
	public void testZZ2() throws Exception {
		Money money1 = Money.build(100);
		Money money2 = Money.build(10);

		Assert.assertEquals(Money.build(110), money1.add(money2));
	}

	@Test
	public void testZZ3() throws Exception {
		Money money1 = Money.build(100);
		Assert.assertEquals(Money.build(100), money1.abs());
		Money money2 = Money.build(-100);
		Assert.assertEquals(Money.build(100), money2.abs());
	}

	@Test
	public void testZZ4() throws Exception {
		Assert.assertTrue(Money.build(100).isPositive());
		Assert.assertFalse(Money.build(100).isNegative());
	}

	@Test
	public void testZZ5() throws Exception {
		Assert.assertFalse(Money.build(-100).isPositive());
		Assert.assertTrue(Money.build(-100).isNegative());
	}

	@Test
	public void testZZ6() throws Exception {
		Assert.assertTrue(Money.build(0).isZero());
		Assert.assertFalse(Money.build(90).isZero());
		Money substract = Money.build(90.5).substract(Money.build(90.5));
		Assert.assertTrue(substract.isZero());
	}
}
