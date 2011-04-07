package zz.yy.core.accounts;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AccountTransactionTag implements Serializable {

	private String id;
	private Set<AccountTransaction> accountTransactions;

	public AccountTransactionTag(String id) {
		this.id = id;
		accountTransactions = new HashSet<AccountTransaction>();
	}

	public void add(AccountTransaction accountTransaction) {
		accountTransactions.add(accountTransaction);
	}

	public boolean appliesTo(AccountTransaction accountTransaction) {
		return accountTransactions.contains(accountTransaction);
	}

	public String getId() {
		return id;
	}

	public Set<AccountTransaction> getAccountTransactions() {
		return accountTransactions;
	}
}
