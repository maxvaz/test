package zz.yy.core.repositories;

import java.util.List;
import java.util.Set;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.AccountTransactionTag;

public interface AccountTransactionTagRepository {

	Set<AccountTransactionTag> allTags();

	List<AccountTransactionTag> tagsFor(AccountTransaction object);

}
