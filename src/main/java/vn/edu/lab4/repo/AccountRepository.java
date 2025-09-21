package vn.edu.lab4.repo;

import vn.edu.lab4.model.Account;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountRepository {
    private static final List<Account> store = new ArrayList<>();
    private static final AtomicInteger seq = new AtomicInteger(1);

    public List<Account> findAll(){ return Collections.unmodifiableList(store); }

    public void save(Account a){
        a.setId(seq.getAndIncrement());
        store.add(a);
    }
}
