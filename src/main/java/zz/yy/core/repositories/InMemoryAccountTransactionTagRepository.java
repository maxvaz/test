package zz.yy.core.repositories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.accounts.AccountTransactionTag;

public class InMemoryAccountTransactionTagRepository implements AccountTransactionTagRepository, Serializable {

	private static Set<AccountTransactionTag> tags;

	static {
		tags = new HashSet<AccountTransactionTag>();
	}

	public Set<AccountTransactionTag> allTags() {
		return tags;
	}

	public void add(AccountTransactionTag tag) {
		tags.add(tag);
	}

	@SuppressWarnings("unchecked")
	public List<AccountTransactionTag> tagsFor(final AccountTransaction accountTransaction) {
		Predicate predicate = new Predicate() {
			public boolean evaluate(Object object) {
				AccountTransactionTag tag = (AccountTransactionTag) object;
				return tag.appliesTo(accountTransaction);
			}
		};
		return (List<AccountTransactionTag>) CollectionUtils.select(allTags(), predicate);
	}
}
