package zz.yy.web;

import java.io.IOException;
import java.util.Iterator;

import org.apache.wicket.extensions.ajax.markup.html.form.upload.UploadProgressBar;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.lang.Bytes;

import zz.yy.core.accounts.Account;
import zz.yy.core.accounts.AccountTransaction;
import zz.yy.core.repositories.AccountRepository;
import zz.yy.core.repositories.InMemoryAccountRepository;
import zz.yy.core.transactionsproviders.SantanderTransactionProvider;

public class UploadPage extends Panel {

	AccountRepository accountRepository;

	public UploadPage(String id) {
		super(id);

		accountRepository = new InMemoryAccountRepository();

		// Create feedback panels
		final FeedbackPanel uploadFeedback = new FeedbackPanel("uploadFeedback");

		// Add uploadFeedback to the page itself
		add(uploadFeedback);

		// Add upload form with ajax progress bar
		final FileUploadForm ajaxSimpleUploadForm = new FileUploadForm("ajax-simpleUpload");

		ajaxSimpleUploadForm.add(new UploadProgressBar("progress", ajaxSimpleUploadForm));
		add(ajaxSimpleUploadForm);
	}

	private class FileUploadForm extends Form<Void> {
		private FileUploadField fileUploadField;

		public FileUploadForm(String name) {
			super(name);

			// set this form to multipart mode (allways needed for uploads!)
			setMultiPart(true);

			// Add one file input field
			add(fileUploadField = new FileUploadField("fileInput"));

			// Set maximum size
			setMaxSize(Bytes.kilobytes(1000));
		}

		@Override
		protected void onSubmit() {
			try {
				FileUpload fileUpload = fileUploadField.getFileUpload();

				SantanderTransactionProvider santanderTransactionProvider = new SantanderTransactionProvider(
						fileUpload.getInputStream());
				Iterator<AccountTransaction> transactions = santanderTransactionProvider.iterateTransactions();

				Account account = accountRepository.accounts().iterator().next();

				while (transactions.hasNext()) {
					account.register(transactions.next());
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
	}

}