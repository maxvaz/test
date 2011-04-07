package zz.yy.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import zz.yy.core.accounts.Account;
import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.repositories.AccountRepository;
import zz.yy.core.repositories.InMemoryAccountRepository;
import zz.yy.web.table.AccountTransactionsTableBuilder;

public class AccountPage extends WebPage {

	AccountRepository accountRepository;

	public AccountPage() {

		this.setOutputMarkupId(true);

		accountRepository = new InMemoryAccountRepository();

		ArrayList<Account> accounts = new ArrayList<Account>(accountRepository.accounts());

		final IModel<Account> model = new Model<Account>();
		model.setObject(accounts.get(0));

		IChoiceRenderer<Account> renderrs = new IChoiceRenderer<Account>() {
			public Object getDisplayValue(Account object) {
				return object.description();
			};

			public String getIdValue(Account object, int index) {
				return index + "";
			}
		};
		final DropDownChoice<Account> dropDownChoice = new DropDownChoice<Account>("accounts", model, accounts,
				renderrs) {

		};

		add(new Label("description", new Model<String>() {
			@Override
			public String getObject() {
				return model.getObject().description();
			}
		}));

		ListModel<AccountTransaction> listModel = new ListModel<AccountTransaction>() {
			@Override
			public List<AccountTransaction> getObject() {
				return model.getObject().getTransactions();
			}
		};

		final DefaultDataTable<AccountTransaction> table = AccountTransactionsTableBuilder.build("transactions",
				listModel);
		table.setOutputMarkupId(true);
		add(table);

		add(new UploadPage("upload"));

		dropDownChoice.add(new AjaxFormComponentUpdatingBehavior("onchange") {
			protected void onUpdate(AjaxRequestTarget target) {
				target.addComponent(AccountPage.this);
			}
		});
		add(dropDownChoice);

	}
}
