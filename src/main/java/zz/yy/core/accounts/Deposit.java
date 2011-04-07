package zz.yy.core.accounts;

import java.io.Serializable;
import java.util.Date;

import zz.yy.core.accounts.visitors.AccountTransactionVisitor;

public class Deposit implements AccountTransaction, Serializable {

	private Money amount;
	private String description;
	private Date transactionDate;

	public Deposit(Money amount, Date transactionDate, String description) {
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.description = description;
	}

	public Money amount() {
		return amount;
	}

	public String description() {
		return description;
	}

	public Date transactionDate() {
		return transactionDate;
	}

	public void visit(AccountTransactionVisitor accountTransactionVisitor) {
		accountTransactionVisitor.visitDeposit(this);
	}

}
