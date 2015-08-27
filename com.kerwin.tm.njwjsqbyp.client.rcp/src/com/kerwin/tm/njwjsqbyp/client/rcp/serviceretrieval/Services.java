package com.kerwin.tm.njwjsqbyp.client.rcp.serviceretrieval;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.service.Http;

public class Services {
	public static AccountDBService getAccountService() {
		BundleContext bundleContext = FrameworkUtil.getBundle(Services.class).getBundleContext();

		ServiceReference<AccountDBService> reference = bundleContext.getServiceReference(AccountDBService.class);
		AccountDBService service = bundleContext.getService(reference);
		return service;
	}

	public static Http getHttpService() {
		BundleContext bundleContext = FrameworkUtil.getBundle(Services.class).getBundleContext();

		ServiceReference<Http> reference = bundleContext.getServiceReference(Http.class);
		Http service = bundleContext.getService(reference);
		return service;
	}
}
