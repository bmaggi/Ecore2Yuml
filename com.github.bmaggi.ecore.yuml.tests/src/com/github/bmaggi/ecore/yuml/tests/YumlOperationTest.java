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
package com.github.bmaggi.ecore.yuml.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.bmaggi.ecore.yuml.yuml.YumlOperation;

public class YumlOperationTest {

	@Test
	public void testNoParameter() {
		String operationName = "operation";
		assertEquals("Wrong syntax for Yuml operation", operationName + "()", new YumlOperation(operationName).toYUMLString());
	}

}
