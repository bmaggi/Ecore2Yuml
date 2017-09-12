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

import java.util.ArrayList;
import java.util.List;

public class YumlModel implements IYUMLString{

	// TODO : maybe rename in class diagram ?
	
	private static String yumlme = "https://yuml.me/diagram/scruffy/class/";
	
	List<YumlClass> classList = new ArrayList<>();
	List<YumlNote> noteList = new ArrayList<>();
	List<YumlAssociation> associationList = new ArrayList<>();

	public YumlModel() {
	}

	public void addClass(YumlClass yumlClass) {
		classList.add(yumlClass);
	}

	public void addNote(YumlNote yumlNote) {
		noteList.add(yumlNote);
	}

	public void addAssociation(YumlAssociation createAssociation) {
		associationList.add(createAssociation);
	}
	
	@Override
	public String toYUMLString() {
		StringBuilder stringBuilder = new StringBuilder();

		for (YumlClass yumlClass : classList) {
			stringBuilder.append(yumlClass.toYUMLString());
			stringBuilder.append(",");
		}

		for (YumlAssociation yumlAssociation : associationList) {
			stringBuilder.append(yumlAssociation.toYUMLString());
			stringBuilder.append(",");
		}

		for (YumlNote yumlNote : noteList) {
			stringBuilder.append(yumlNote.toYUMLString());
		}
		return stringBuilder.toString();
	}


	public String toYUMLMe(){
		return yumlme+ toYUMLString();
	}
}
