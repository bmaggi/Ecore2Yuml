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

// [note: How is this{bg:yellow}]
public class YumlNote implements IYUMLString{

	private String text;
	
	public YumlNote(String text) {
		this.text = text;
	}
	
	@Override
	public String toYUMLString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[note:");
		stringBuilder.append(text);
		stringBuilder.append("{bg:yellow}]");	
		return stringBuilder.toString();
	}

	
}
