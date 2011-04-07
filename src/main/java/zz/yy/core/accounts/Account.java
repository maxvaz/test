package zz.yy.core.accounts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import zz.yy.core.accounts.visitors.AccountBalance;

public class Account implements Serializable {

	String description;
	List<AccountTransaction> transactions;

	public Account(String description) {
		this.description = description;
		transactions = new ArrayList<AccountTransaction>();
	}

	public void register(AccountTransaction transaction) {
		transactions.add(transaction);
	}

	public String description() {
		return description;
	}

	// TODO: cambiar por un TransactionVisitor
	public void visitTransactions(AccountBalance accountBalance) {
		for (AccountTransaction transaction : transactions) {
			transaction.visit(accountBalance);
		}
	}

	public List<AccountTransaction> getTransactions() {
		return transactions;
	}
}
