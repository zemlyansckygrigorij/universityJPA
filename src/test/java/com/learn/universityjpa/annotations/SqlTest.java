package com.learn.universityjpa.annotations;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * annotation SqlTest
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test
@SqlGroup({
        @Sql(
                scripts = "/db/sql/clean.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),
        @Sql(
                scripts = "/db/sql/insert.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),
        @Sql(
                scripts = "/db/sql/insertSubject.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),
        @Sql(
                scripts = "/db/sql/insertStudent.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),

        @Sql(
                scripts = "/db/sql/insertGS.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),
        @Sql(
                scripts = "/db/sql/insertTeacher.sql ",
                executionPhase = BEFORE_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED)),
        @Sql(
                scripts = "/db/sql/clean.sql ",
                executionPhase = AFTER_TEST_METHOD,
                config = @SqlConfig(transactionMode = ISOLATED))
})
public @interface SqlTest {
}
