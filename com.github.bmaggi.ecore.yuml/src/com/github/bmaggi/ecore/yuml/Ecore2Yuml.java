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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

import com.github.bmaggi.ecore.yuml.yuml.YumlModel;

/**
 *  
 */
public class Ecore2Yuml {

	public static String ecoreToYuml(final IFile iFile) {
		String path = iFile.getFullPath().toString();
		return ecoreToYuml(path);
	}

	public static String ecoreToYuml(final String path) {
		URI createPlatformResourceURI = URI.createPlatformResourceURI(path, true);
		return ecoreToYuml(createPlatformResourceURI);
	}

	public static String ecoreToYuml(URI createPlatformResourceURI) {
		ResourceSet resourceSetImpl = new ResourceSetImpl();
		resourceSetImpl = UMLResourcesUtil.init(resourceSetImpl);
		resourceSetImpl.getResource(URI.createURI(UMLResource.STANDARD_PROFILE_URI), true);
		resourceSetImpl.getResource(URI.createURI(UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI), true);

		resourceSetImpl.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION,
				UMLResource.Factory.INSTANCE);

		UMLResourcesUtil.initGlobalRegistries();
		UMLResourcesUtil.initPackageRegistry(resourceSetImpl.getPackageRegistry());

		Resource resource = resourceSetImpl.getResource(createPlatformResourceURI, true);
		return ecoreToYuml(resource);
	}
	
	public static String ecoreToYuml(Resource resource) {
		TreeIterator<EObject> allContents = resource.getAllContents();

		YumlModel yumlModel = new YumlModel();

		while (allContents.hasNext()) {
			EObject eObject = allContents.next();

			if (eObject instanceof EClass) {
				yumlModel.addClass(EcoreToYumlFactory.createClass((EClass) eObject));
				EList<EClass> eSuperTypes = ((EClass) eObject).getESuperTypes();
				for (EClass eClass : eSuperTypes) {
					yumlModel.addAssociation(EcoreToYumlFactory.createGeneralization((EClass) eObject, eClass));
				}
			} else if (eObject instanceof EEnum) {
				// TODO Display an Enum 
				Activator.log(IStatus.WARNING,"EEnum not supported for the moment");
			} else if (eObject instanceof EGenericType) {
				// TODO Display an GenericType 
				Activator.log(IStatus.WARNING,"EGenericType not supported for the moment");
			} else if (eObject instanceof EAttribute) {
				// do nothing will be managed in EClass
			} else if (eObject instanceof EOperation) {
				// do nothing will be managed in EClass
			} else if (eObject instanceof EAnnotation) {
				//yumlModel.addNote(EcoreToYumlFactory.createNote((EAnnotation) eObject));
			} else if (eObject instanceof EPackage) {
				//TODO Nice printing classic vs Github
				Activator.log(IStatus.WARNING,"EPackage not supported for the moment");
			} else if (eObject instanceof EStringToStringMapEntryImpl) {
				//yumlModel.addNote(EcoreToYumlFactory.createNote((EStringToStringMapEntryImpl) eObject));
			} else if (eObject instanceof EReference) {
				EReference eReference = (EReference) eObject;
				String name = eReference.getName();
				if (!name.startsWith("base_")) { // Filter from profile
					yumlModel.addAssociation(EcoreToYumlFactory.createAssociation(eReference));
				}
			} else {
				Activator.log(IStatus.WARNING,"Unknown class " + eObject.getClass());
			}
		}
		return yumlModel.toYUMLString();
	}

}