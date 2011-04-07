package zz.yy.core.repositories;

import java.util.Date;

import zz.yy.core.accounts.Account;
import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.AccountTransactionTag;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;

public class Mocks {

	public static void create() {
		Account testAccount = new Account("Test account");
		AccountTransaction deposit1 = new Deposit(Money.build(1000), new Date(), "deposit1");
		testAccount.register(deposit1);
		AccountTransaction withdraw1 = new Withdraw(Money.build(100), new Date(), "withdraw1");
		testAccount.register(withdraw1);
		new InMemoryAccountRepository().add(testAccount);

		InMemoryAccountTransactionTagRepository tagsRepository = new InMemoryAccountTransactionTagRepository();

		AccountTransactionTag withdrawsTag = new AccountTransactionTag("withdraws");
		withdrawsTag.add(withdraw1);
		tagsRepository.add(withdrawsTag);

		AccountTransactionTag depositsTag = new AccountTransactionTag("deposits");
		depositsTag.add(deposit1);
		tagsRepository.add(depositsTag);

		AccountTransactionTag otherTag = new AccountTransactionTag("others");
		otherTag.add(deposit1);
		tagsRepository.add(otherTag);

		Account testAccount2 = new Account("Test account2");
		AccountTransaction deposit21 = new Deposit(Money.build(1000), new Date(), "deposit21");
		testAccount2.register(deposit21);
		new InMemoryAccountRepository().add(testAccount2);

	}

}
