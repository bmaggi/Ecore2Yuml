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
package com.github.bmaggi.ecore.yuml;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import com.github.bmaggi.ecore.yuml.yuml.YumlAssociation;
import com.github.bmaggi.ecore.yuml.yuml.YumlAttribute;
import com.github.bmaggi.ecore.yuml.yuml.YumlClass;
import com.github.bmaggi.ecore.yuml.yuml.YumlNote;
import com.github.bmaggi.ecore.yuml.yuml.YumlOperation;

public class EcoreToYumlFactory {
	
	public static YumlNote createNote(EAnnotation eObject) {
		return new YumlNote(eObject.getSource());
	}

	public static YumlNote createNote(EStringToStringMapEntryImpl eObject) {
		return new YumlNote(eObject.getValue());
	}	
	
	public static YumlAssociation createAssociation(EReference eReference) {
		//TODO: label eReference.getLowerBound();
		return new YumlAssociation(eReference.getName(), createClass(eReference.getEContainingClass()), createClass(eReference.getEReferenceType()));
	}		
	
	public static YumlAssociation createGeneralization(EClass child, EClass parent) {
		return new YumlAssociation("", createClass(child), createClass(parent),"^");
	}			
	
	public static YumlAttribute createAttribute(EAttribute eAttribute) {
		EClassifier eType = eAttribute.getEType();
		if (eType != null && !eType.eIsProxy()) {
			// TODO make something for the proxy
			return new YumlAttribute(eAttribute.getName(), eType.getName());
		}	
		return new YumlAttribute(eAttribute.getName());
	}		

	public static YumlOperation createOperation(EOperation eOperation) {
		return new YumlOperation(eOperation.getName());
	}		
	
	
	public static YumlClass createClass(EClass eClass) {
		List<YumlAttribute> attributes = eClass.getEAttributes().stream().map(a -> createAttribute(a)).collect(Collectors.toList());
		List<YumlOperation> operations = eClass.getEOperations().stream().map(a -> createOperation(a)).collect(Collectors.toList());
		return new YumlClass(eClass.getName(),attributes,operations);
	}			
	
	/* Only make sense for UML profile
	EList<EReference> eReferences = eClass.getEReferences();
	for (EReference eReference : eReferences) {
		String referenceName = eReference.getName();
		stringBuilder.append(ATTRIBUTE_SEPARATOR);
		stringBuilder.append(referenceName);
		EClassifier eType = eReference.getEType();
		if (eType!=null && !eType.eIsProxy() ){
			//TODO make something for the proxy 
			stringBuilder.append(ATTRIBUTE_TYPE_SEPARATOR + eType.getName()+"{bg:yellow}");
		}				
	}
	*/

	
	
}
