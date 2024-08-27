
package com.brucemelo.infrastructure;

import org.hibernate.StatelessSession;

import java.util.function.Consumer;
import java.util.function.Function;

public class AppHibernate {

    public static void inTransaction(Consumer<StatelessSession> consumer) {
        AppHibernateConfig.getSessionFactory().inStatelessTransaction(consumer);
    }

    public static <R> R fromTransaction(Function<StatelessSession, R> function) {
        return AppHibernateConfig.getSessionFactory().fromStatelessTransaction(function);
    }

}
