package com.dare.adekunle.primegen.db;

import java.io.Serializable;
import java.util.List;

import com.dare.adekunle.primegen.entities.PrimeLog;

public class PrimeDataService implements Serializable {

	private static final long serialVersionUID = 1L;

	public static PrimeDataService getInstance() {
        return new PrimeDataService();
    }

    private PrimeDataService() {}

    public PrimeLog fetchPrimeLog(Long id) throws Exception {
        return DBManager.getInstance().fetchPrimeLog(id);
    }

    public void savePrimeLog(PrimeLog primeLog) throws Exception  {
        DBManager.getInstance().savePrimeLog(primeLog);
    }

    public List<PrimeLog> fetchAllPrimeLogs() throws Exception {
        return DBManager.getInstance().fetchAllPrimeLogs();
    }

}
