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

import java.io.UnsupportedEncodingException;

public enum YumlMeType {

	EDIT("","Edit Later To come back and edit the image later."),
	PNG ("png","PNG Image For blogs, wikis or email. Transparent background."),
	PDF("pdf","PDF Document Great for printing. Infinite zoom.	"),
	JPEG("jpeg","JPEG Image	Solid background. Plays nicer with some desktop tools."),
	JSON("json","JSON File To programatically save metadata."),
	SVG("svg","SVG Vector Graphics Experimental!");
	
	private static final String YUMLME = "https://yuml.me/diagram/scruffy/class/";
	

	private String extension;
	private String description;

	private YumlMeType(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	public String getExtension() {
		return extension;
	}

	public String getDescription() {
		return description;
	}

	public String toYumlMeURL(String yumlString) throws UnsupportedEncodingException {
//		String encode = java.net.URLEncoder.encode(yumlString, "UTF-8");
		return YUMLME +yumlString+"."+extension;
	}

}
