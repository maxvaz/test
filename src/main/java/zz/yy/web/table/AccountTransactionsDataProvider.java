package zz.yy.web.table;

import java.util.Iterator;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import zz.yy.core.accounts.AccountTransaction;

public class AccountTransactionsDataProvider extends SortableDataProvider<AccountTransaction> {

	private ListModel<AccountTransaction> transactions;

	public AccountTransactionsDataProvider(ListModel<AccountTransaction> listModel) {
		this.transactions = listModel;
	}

	@SuppressWarnings("unchecked")
	public Iterator<AccountTransaction> iterator(int first, int count) {
		// TODO: mejorar esto!
		Object[] array = transactions.getObject().toArray();
		return new ArrayIterator(array, first, first + count);
	}

	public int size() {
		return transactions.getObject().size();
	}

	public IModel<AccountTransaction> model(AccountTransaction object) {
		return Model.of(object);
	}

}
