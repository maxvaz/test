package zz.yy.core.repositories;

import java.util.Set;

import zz.yy.core.accounts.Account;

public interface AccountRepository {

	Set<Account> accounts();

	void add(Account account);

}
