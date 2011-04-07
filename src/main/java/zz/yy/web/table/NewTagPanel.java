package zz.yy.web.table;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.AccountTransactionTag;
import zz.yy.core.repositories.InMemoryAccountTransactionTagRepository;
import zz.yy.web.AccountPage;

public class NewTagPanel extends WebPage {

	private String tagName;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public NewTagPanel(final IModel<AccountTransaction> iModel) {

		this.setDefaultModel(new CompoundPropertyModel<Void>(this));

		Form<Void> form = new Form<Void>("newTagForm") {
			@Override
			protected void onSubmit() {
				super.onSubmit();
			}
		};
		add(form);

		TextField<String> tagNameTextField = new TextField<String>("tagName");
		form.add(tagNameTextField);

		form.add(new Button("accept") {
			@Override
			public void onSubmit() {

				AccountTransactionTag accountTransactionTag = new AccountTransactionTag(tagName);
				accountTransactionTag.add(iModel.getObject());

				InMemoryAccountTransactionTagRepository repo = new InMemoryAccountTransactionTagRepository();
				repo.add(accountTransactionTag);

				this.setResponsePage(AccountPage.class);
			}
		});

	}
}
