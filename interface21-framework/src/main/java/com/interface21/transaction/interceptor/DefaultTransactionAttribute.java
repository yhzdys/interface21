/*
 * Copyright (c) 2011-2025 PiChen
 */

/*
 * The Spring Framework is published under the terms
 * of the Apache Software License.
 */

package com.interface21.transaction.interceptor;

import com.interface21.transaction.support.DefaultTransactionDefinition;

/**
 * Transaction attribute that takes EJB approach to rolling
 * back on runtime, but not checked, exceptions.
 *
 * @author Rod Johnson
 * @version $Id$
 * @since 16-Mar-2003
 */
public class DefaultTransactionAttribute extends DefaultTransactionDefinition
        implements TransactionAttribute {

    public DefaultTransactionAttribute() {
    }

    public DefaultTransactionAttribute(int propagationBehavior) {
        super(propagationBehavior);
    }

    /**
     * Default behaviour is as with EJB: rollback on unchecked exception.
     * Consistent with TransactionTemplate's behavior.
     */
    public boolean rollbackOn(Throwable t) {
        return (t instanceof RuntimeException);
    }

}
