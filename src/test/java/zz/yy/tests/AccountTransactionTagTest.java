package zz.yy.tests;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import zz.yy.core.accounts.AccountTransactionTag;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;

public class AccountTransactionTagTest {

	@Test
	public void testZZ() throws Exception {
		AccountTransactionTag tag = new AccountTransactionTag("tag1");

		Deposit accountTransaction = new Deposit(Money.build(1000), new Date(), "deposit");
		tag.add(accountTransaction);

		Withdraw accountTransaction2 = new Withdraw(Money.build(100), new Date(), "debit");
		tag.add(accountTransaction2);

		Assert.assertTrue(tag.appliesTo(accountTransaction));
	}

}
