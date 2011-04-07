package zz.yy.core.accounts.visitors;

import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Withdraw;

public interface AccountTransactionVisitor {

	void visitDeposit(Deposit deposit);

	void visitWithdraw(Withdraw withdraw);

}
