package zz.yy.core.accounts;

import java.io.Serializable;

import zz.yy.core.accounts.visitors.AccountTransactionVisitor;

//TODO: chequar si esta clase deberia ser Serializable, lo necesito para que sea un modelo de wicket
public interface AccountTransaction extends Serializable {

	void visit(AccountTransactionVisitor accountTransactionVisitor);

	String description();

}
