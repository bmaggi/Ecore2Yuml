/*****************************************************************************
 * Copyright (c) 2017 Benoît Maggi.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Benoît Maggi - Initial API and implementation
 *****************************************************************************/
package com.github.bmaggi.ecore.yuml.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * A hostnameVerifier to avoid ssl check
 */
public class TrustAllHostNameVerifier implements HostnameVerifier {

	@Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }

}