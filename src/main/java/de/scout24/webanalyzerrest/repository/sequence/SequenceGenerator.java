package de.scout24.webanalyzerrest.repository.sequence;

import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Query;
import java.math.BigInteger;

public class SequenceGenerator {

    public static final String SEQUENCE_QUERY = "select MYSEQ.nextval as num from dual";
    @Autowired
    private Session session;

    public Long generateId() {
        Query query = (Query) session.createSQLQuery(SEQUENCE_QUERY)
                .addScalar("num", StandardBasicTypes.LONG);

        return ((BigInteger) query.getResultList().get(0)).longValue();
    }
}
