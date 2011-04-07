package zz.yy.core.transactionsproviders;

import java.util.Iterator;

import zz.yy.core.accounts.AccountTransaction;

public interface TransactionProvider {

	Iterator<AccountTransaction> iterateTransactions();

}
