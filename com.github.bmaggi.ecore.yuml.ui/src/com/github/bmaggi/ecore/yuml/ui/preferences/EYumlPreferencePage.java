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
package com.github.bmaggi.ecore.yuml.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.github.bmaggi.ecore.yuml.ui.Activator;

/**
 * Preferences for Ecore 2 Yuml tranformation
 *  - EAnnotation to Note : allow to include "comments" in the generated diagram
 *  - Disable SSl Check : Disable ssl check that raise some issues
 */

public class EYumlPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public EYumlPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("Options for Ecore To Yuml transformation"); //TODO allow internationalization
	}

	@Override
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(EYumlPreferences.EANNOTATION_TO_NOTE,"&Transform EAnnotation to note", getFieldEditorParent()));
		addField(new BooleanFieldEditor(EYumlPreferences.SSL_CHECK,"&SSL Check", getFieldEditorParent()));		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(IWorkbench workbench) {
	}

}