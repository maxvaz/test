package zz.yy.web;

import org.apache.wicket.protocol.http.WebApplication;

import zz.yy.core.repositories.Mocks;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
		Mocks.create();
	}

	public Class<AccountPage> getHomePage() {
		return AccountPage.class;
	}

}
