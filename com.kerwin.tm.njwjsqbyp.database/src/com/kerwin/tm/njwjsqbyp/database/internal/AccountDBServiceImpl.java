package com.kerwin.tm.njwjsqbyp.database.internal;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.config.PersistenceUnitProperties;

import com.kerwin.tm.njwjsqbyp.database.AccountDBService;
import com.kerwin.tm.njwjsqbyp.database.AccountEvent;
import com.kerwin.tm.njwjsqbyp.database.IAccountDBChangeObserver;
import com.kerwin.tm.njwjsqbyp.domain.Account;

public class AccountDBServiceImpl implements AccountDBService {

	private EntityManagerFactory emf;
	private EntityManager em;
	private List<IAccountDBChangeObserver> observers;

	@SuppressWarnings("unchecked")
	protected void activate() {
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put(PersistenceUnitProperties.CLASSLOADER, getClass().getClassLoader());

		org.eclipse.persistence.jpa.PersistenceProvider persistenceProvider = new org.eclipse.persistence.jpa.PersistenceProvider();
		emf = persistenceProvider.createEntityManagerFactory("com.kerwin.tm.njwjsqbyp", map);
		em = emf.createEntityManager();

		observers = new LinkedList<>();
	}

	protected void deactivate() {
		observers = null;
		em.close();
		emf.close();
		em = null;
		emf = null;
	}

	@Override
	public void addAccount(Account account) {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		sendEvent(AccountEvent.ADDED, account);
	}

	@Override
	public void modifyAccount(Account account) {
		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();
		sendEvent(AccountEvent.CHANGED, account);
	}

	@Override
	public void removePersion(Account account) {
		em.getTransaction().begin();
		Account find = em.find(Account.class, account.getId());
		em.remove(find);
		em.getTransaction().commit();
		sendEvent(AccountEvent.REMOVED, account);
	}

	@Override
	public List<Account> getAccounts() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> rootEntry = cq.from(Account.class);
		CriteriaQuery<Account> all = cq.select(rootEntry);
		TypedQuery<Account> allQuery = em.createQuery(all);

		List<Account> resultList = allQuery.getResultList();
		return resultList;
	}

	@Override
	public void registerAccountObserver(IAccountDBChangeObserver observer) {
		if (!observers.contains(observer))
			observers.add(observer);
	}

	@Override
	public void unregisterAccountObserver(IAccountDBChangeObserver observer) {
		if (observers.contains(observer))
			observers.remove(observer);
	}

	private void sendEvent(String eventID, Account affectedAccount) {
		for (IAccountDBChangeObserver observer : observers)
			observer.changed(eventID, affectedAccount);
	}

}
