package com.learn.universityjpa.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.type.EnumType;

/**
 *  @author Grigoriy Zemlyanskiy
 *  @version 1.0
 * class for Convert value gender
 */
public class GenderConverter extends EnumType<Enum<?>>  {
    public void nullSafeSet(
            PreparedStatement st,
            Object value,
            int index,
            SharedSessionContractImplementor session)
            throws HibernateException, SQLException {
        st.setObject(
                index,
                value != null ?
                        ((Enum<?>) value).name() :
                        null,
                Types.OTHER
        );
    }
}
