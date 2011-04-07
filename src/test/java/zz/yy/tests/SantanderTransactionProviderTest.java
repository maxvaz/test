package zz.yy.tests;

import java.io.InputStream;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Test;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;
import zz.yy.core.transactionsproviders.SantanderTransactionProvider;

public class SantanderTransactionProviderTest {

	@Test
	public void testZZ() throws Exception {

		InputStream transactionsDataIS = this.getClass().getResourceAsStream("/santander_transactions_test.xls");

		SantanderTransactionProvider transactionProvider = new SantanderTransactionProvider(transactionsDataIS);
		Iterator<AccountTransaction> iterateTransactions = transactionProvider.iterateTransactions();

		Assert.assertTrue(iterateTransactions.hasNext());
		Deposit deposit = (Deposit) iterateTransactions.next();
		Assert.assertEquals("Deposit 1", deposit.description());
		Assert.assertEquals(Money.build(1775.25), deposit.amount());

		Withdraw withdraw = (Withdraw) iterateTransactions.next();
		Assert.assertEquals("Withdraw 1", withdraw.description());
		Assert.assertEquals(Money.build(190), withdraw.amount());

		Assert.assertFalse(iterateTransactions.hasNext());

	}

}
