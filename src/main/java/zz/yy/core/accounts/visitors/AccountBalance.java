package zz.yy.core.accounts.visitors;

import java.util.Collection;

import zz.yy.core.accounts.Account;
import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Money;
import zz.yy.core.accounts.Withdraw;

public class AccountBalance implements AccountTransactionVisitor {

	public void visitDeposit(Deposit deposit) {
		registerDeposit(deposit.amount());
	}

	public void visitWithdraw(Withdraw withdraw) {
		registerWithdraw(withdraw.amount());
	}

	private Money balance;

	public static Money balanceFor(Account account) {
		AccountBalance accountBalance = new AccountBalance();
		Money balance = accountBalance.balance(account);
		return balance;
	}

	public AccountBalance() {
		balance = Money.ZERO;
	}

	public Money balance(Account account) {
		account.visitTransactions(this);
		return balance;
	}

	private Money balance(Collection<AccountTransaction> transactions) {
		for (AccountTransaction accountTransaction : transactions) {
			accountTransaction.visit(this);
		}
		return balance;
	}

	public void registerDeposit(Money amount) {
		balance = balance.add(amount);
	}

	public void registerWithdraw(Money amount) {
		balance = balance.substract(amount);
	}

	public static Money balanceForTransactions(Collection<AccountTransaction> transactions) {
		AccountBalance accountBalance = new AccountBalance();
		Money balance = accountBalance.balance(transactions);
		return balance;
	}

}
