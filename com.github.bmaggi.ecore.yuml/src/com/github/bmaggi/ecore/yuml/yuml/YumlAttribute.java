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

public class YumlAttribute implements IYUMLString {

	private static final String ATTRIBUTE_TYPE_SEPARATOR = ":";

	String name;

	String type;

	public YumlAttribute(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public YumlAttribute(String name) {
		super();
		this.name = name;
		this.type = "";
	}	
	
	@Override
	public String toYUMLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(name);
		stringBuilder.append(ATTRIBUTE_TYPE_SEPARATOR);
		stringBuilder.append(type);
		return stringBuilder.toString();
	}

}
