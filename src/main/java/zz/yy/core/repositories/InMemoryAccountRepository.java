package zz.yy.core.repositories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import zz.yy.core.accounts.Account;

public class InMemoryAccountRepository implements AccountRepository, Serializable {

	static Set<Account> accounts;

	static {
		accounts = new HashSet<Account>();
	}

	public Set<Account> accounts() {
		return accounts;
	}

	public void add(Account account) {
		accounts.add(account);
	}

}
