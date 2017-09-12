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

public class YumlAssociation implements IYUMLString{
	
	public static final String DEFAULT_ASSOCIATION_ORIENTATION = "->";
	
	private String name;
	private YumlClass target;
	private YumlClass source;
	private String associationOrientation;
	
	public YumlAssociation(String name, YumlClass source, YumlClass target, String associationOrientation) {
		this.name = name;
		this.source = source;
		this.target = target;
		this.associationOrientation = associationOrientation;
	}
	
	public YumlAssociation(String name, YumlClass source, YumlClass target) {
		this(name, source, target, DEFAULT_ASSOCIATION_ORIENTATION);
	}

	
	@Override
	public String toYUMLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(source.toYUMLString());
		stringBuilder.append(name);
		stringBuilder.append(associationOrientation);
		stringBuilder.append(target.toYUMLString());
		return stringBuilder.toString();		
	}
	

}
