package zz.yy.web.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.Deposit;
import zz.yy.core.accounts.Withdraw;
import zz.yy.core.accounts.visitors.AccountTransactionVisitor;
import zz.yy.core.repositories.AccountTransactionTagRepository;
import zz.yy.core.repositories.InMemoryAccountTransactionTagRepository;

public class AccountTransactionsTableBuilder implements Serializable {

	public static DefaultDataTable<AccountTransaction> build(String id, ListModel<AccountTransaction> listModel) {

		AccountTransactionsTableBuilder builder = new AccountTransactionsTableBuilder();

		InMemoryAccountTransactionTagRepository tagRepository = new InMemoryAccountTransactionTagRepository();
		List<IColumn<AccountTransaction>> columns = builder.buildColumns(tagRepository);
		AccountTransactionsDataProvider dataProvider = new AccountTransactionsDataProvider(listModel);
		DefaultDataTable<AccountTransaction> table = new DefaultDataTable<AccountTransaction>(id, columns,
				dataProvider, 10);
		return table;
	}

	private List<IColumn<AccountTransaction>> buildColumns(AccountTransactionTagRepository tagRepository) {
		List<IColumn<AccountTransaction>> columns = new ArrayList<IColumn<AccountTransaction>>();

		columns.add(buildDescriptionColumn());
		columns.add(buildAmountColumn());
		columns.add(buildTagsColumn());

		return columns;
	}

	private IColumn<AccountTransaction> buildTagsColumn() {

		return new AbstractColumn<AccountTransaction>(Model.of("Tags")) {

			public void populateItem(final Item<ICellPopulator<AccountTransaction>> cellItem, final String componentId,
					IModel<AccountTransaction> rowModel) {
				cellItem.add(new TagsPanel(componentId, rowModel));
			}
		};
	}

	private IColumn<AccountTransaction> buildAmountColumn() {
		return new AbstractColumn<AccountTransaction>(Model.of("Amount")) {

			public void populateItem(final Item<ICellPopulator<AccountTransaction>> cellItem, final String componentId,
					IModel<AccountTransaction> rowModel) {

				AccountTransactionVisitor accountTransactionVisitor = new AccountTransactionVisitor() {

					public void visitWithdraw(Withdraw withdraw) {
						cellItem.add(new Label(componentId, withdraw.amount().toString()));
					}

					public void visitDeposit(Deposit deposit) {
						cellItem.add(new Label(componentId, deposit.amount().toString()));
					}
				};
				AccountTransaction accountTransaction = rowModel.getObject();
				accountTransaction.visit(accountTransactionVisitor);
			}
		};
	}

	private IColumn<AccountTransaction> buildDescriptionColumn() {
		return new AbstractColumn<AccountTransaction>(Model.of("Description")) {

			public void populateItem(Item<ICellPopulator<AccountTransaction>> cellItem, String componentId,
					IModel<AccountTransaction> rowModel) {

				cellItem.add(new Label(componentId, rowModel.getObject().description()));
			}
		};
	}

}
