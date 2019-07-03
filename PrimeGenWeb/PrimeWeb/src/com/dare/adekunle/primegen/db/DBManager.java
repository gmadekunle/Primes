package com.dare.adekunle.primegen.db;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.dare.adekunle.primegen.entities.PrimeLog;

public class DBManager {

    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static final EntityManager ENTITY_MANAGER;
    private static final String PERSISTENCE_UNIT_NAME = "primeGen";

    static {
        try {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Error initialising Entity Manager", e);
            throw new ExceptionInInitializerError(e);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                shutdown();
            }
        });
    }

    public static void shutdown() {
        if (ENTITY_MANAGER_FACTORY.isOpen()) {//Release db resources
            ENTITY_MANAGER_FACTORY.close();//Entity Manager is also implicitly closed.
        }
    }

    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER;
    }

    public static synchronized DBManager getInstance() {
        return new DBManager();
    }

    public synchronized void savePrimeLog(PrimeLog primeLog) throws Exception{
        primeLog.setRequestTimestamp(new Date());

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(primeLog);
        em.getTransaction().commit();
    }
    
    public synchronized PrimeLog fetchPrimeLog(Long id) throws Exception {
    	EntityManager em = getEntityManager();
    	PrimeLog primeLog = em.find(PrimeLog.class, id);
    	return primeLog;
    }

    public synchronized List<PrimeLog> fetchAllPrimeLogs() throws Exception{
    	EntityManager em = getEntityManager();
        CriteriaQuery<PrimeLog> query = em.getCriteriaBuilder().createQuery(PrimeLog.class);
        query.select(query.from(PrimeLog.class));
        TypedQuery<PrimeLog> typedQuery = em.createQuery(query);
        List<PrimeLog> primeLogs = typedQuery.getResultList();
        return primeLogs;
    }
}
