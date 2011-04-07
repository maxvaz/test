package zz.yy.web.table;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.AccountTransactionTag;
import zz.yy.core.repositories.AccountTransactionTagRepository;
import zz.yy.core.repositories.InMemoryAccountTransactionTagRepository;

public class TagsPanel extends Panel {

	AccountTransactionTagRepository tagRepository;

	public TagsPanel(String id, IModel<AccountTransaction> transactionModel) {
		super(id);

		tagRepository = new InMemoryAccountTransactionTagRepository();

		List<AccountTransactionTag> tags = tagRepository.tagsFor(transactionModel.getObject());

		ListView<AccountTransactionTag> tagsListView = new ListView<AccountTransactionTag>("tags", tags) {
			@Override
			protected void populateItem(ListItem<AccountTransactionTag> item) {
				item.add(new Label("tag", item.getModelObject().getId()));
			}
		};
		add(tagsListView);

		AjaxLink<AccountTransaction> addTagLink = new AjaxLink<AccountTransaction>("addTagLink", transactionModel) {
			@Override
			public void onClick(AjaxRequestTarget target) {
				final NewTagPanel modalPanel = new NewTagPanel(getModel());
				this.setResponsePage(modalPanel);
			}
		};
		add(addTagLink);
	}
}
