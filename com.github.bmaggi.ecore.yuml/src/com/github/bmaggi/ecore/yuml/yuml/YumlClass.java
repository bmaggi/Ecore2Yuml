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
package com.github.bmaggi.ecore.yuml.yuml;

import java.util.List;
/*
 * 
 * 
 * 
 * +   Public
#   Protected
-   Private

[Class|-privateVar:string|#protectedMethod($arg);+publicMethod($arg)]
 * 
 */
public class YumlClass implements IYUMLString{


	private static final String ATTRIBUTE_SEPARATOR = ";";
	private String name;
	
	List<YumlAttribute> attributes;
	List<YumlOperation> operations;
	
	public YumlClass(String name) {
		this.name = name;
	}
	
	public YumlClass(String name, List<YumlAttribute> attributes, List<YumlOperation> operations) {
		super();
		this.name = name;
		this.attributes = attributes;
		this.operations = operations;
	}

	@Override
	public String toYUMLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[" + name);
		
		for (YumlAttribute attribute : attributes) {
			stringBuilder.append(ATTRIBUTE_SEPARATOR);
			stringBuilder.append(attribute.toYUMLString());
		}

		
		for (YumlOperation operation : operations) {
			stringBuilder.append(operation.toYUMLString());
			stringBuilder.append(ATTRIBUTE_SEPARATOR);
		}
		
		stringBuilder.append("]");	
		return stringBuilder.toString();
	}

	
	public String getkey() { // unique identifier
		return name;
	}
	
}
