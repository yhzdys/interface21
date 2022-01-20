/*
 * Copyright (c) 2011-2025 PiChen
 */

package com.interface21.jdbc.core.support;

import javax.sql.DataSource;

import com.interface21.jdbc.core.JdbcTemplate;
import com.interface21.jdbc.datasource.DataSourceUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.interface21.beans.factory.InitializingBean;

/**
 * Convenient super class for JDBC data access objects.
 * Requires a DataSource to be set, providing a
 * JdbcTemplate based on it to subclasses.
 * <p>
 * <p>This base class is mainly intended for JdbcTemplate usage
 * but can also be used when working with DataSourceUtils directly
 * or with com.interface21.jdbc.object classes.
 *
 * @author Juergen Hoeller
 * @see #setDataSource
 * @see JdbcTemplate
 * @see DataSourceUtils
 * @since 28.07.2003
 */
public abstract class JdbcDaoSupport implements InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    private JdbcTemplate jdbcTemplate;

    /**
     * Set the JDBC DataSource to be used by this DAO.
     */
    public final void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Return the JDBC DataSource used by this DAO.
     */
    protected final DataSource getDataSource() {
        return jdbcTemplate.getDataSource();
    }

    /**
     * Return the JdbcTemplate for this DAO,
     * pre-initialized with the DataSource.
     */
    protected final JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public final void afterPropertiesSet() {
        if (this.jdbcTemplate == null) {
            throw new IllegalArgumentException("dataSource is required");
        }
        initDao();
    }

    /**
     * Subclasses can override this for custom initialization behavior.
     * Gets called after population of this instance's bean properties.
     */
    protected void initDao() {
    }

}
