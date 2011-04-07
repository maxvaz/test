package zz.yy.tests;

import java.util.Arrays;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import zz.yy.core.accounts.Account;
import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;
import zz.yy.core.accounts.visitors.AccountBalance;

public class AccountBalanceTest {

	@Test
	public void testZZZ() throws Exception {
		Deposit deposit = new Deposit(Money.build(1000), new Date(), "");
		Withdraw withdraw = new Withdraw(Money.build(100), new Date(), "");

		Money balance = AccountBalance.balanceForTransactions(Arrays.<AccountTransaction> asList(deposit, withdraw));
		Assert.assertEquals(Money.build(900.0), balance);
	}

	@Test
	public void testAccountBalanceAfterDeposit() throws Exception {
		Account account = new Account("");

		account.register(new Deposit(Money.build(1000), new Date(), "description"));

		Money balance = AccountBalance.balanceFor(account);
		Assert.assertEquals(Money.build(1000), balance);
	}

	@Test
	public void testAccountBalanceAfterWithdraw() throws Exception {
		Account account = new Account("");

		account.register(new Withdraw(Money.build(100), new Date(), "description"));

		Money balance = AccountBalance.balanceFor(account);
		Assert.assertEquals(Money.build(-100), balance);
	}

	@Test
	public void testAccountBalanceAfterDepositAndWithdraw() throws Exception {
		Account account = new Account("");

		account.register(new Deposit(Money.build(1000), new Date(), "description"));
		account.register(new Withdraw(Money.build(100), new Date(), "description"));

		Money balance = AccountBalance.balanceFor(account);
		Assert.assertEquals(Money.build(900), balance);
	}

	@Test
	public void testAccountBalanceAfterWithdrawAndDeposit() throws Exception {
		Account account = new Account("");

		account.register(new Withdraw(Money.build(100), new Date(), "description"));
		account.register(new Deposit(Money.build(1000), new Date(), "description"));

		Money balance = AccountBalance.balanceFor(account);
		Assert.assertEquals(Money.build(900), balance);
	}

}
