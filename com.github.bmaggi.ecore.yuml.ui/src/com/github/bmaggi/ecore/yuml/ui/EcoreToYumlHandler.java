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
package com.github.bmaggi.ecore.yuml.ui;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import com.github.bmaggi.ecore.yuml.Ecore2Yuml;
import com.github.bmaggi.ecore.yuml.ui.preferences.EYumlPreferences;
import com.github.bmaggi.ecore.yuml.util.AllTrustManager;
import com.github.bmaggi.ecore.yuml.util.TrustAllHostNameVerifier;
import com.github.bmaggi.ecore.yuml.yuml.YumlMeType;

public class EcoreToYumlHandler extends AbstractHandler implements IHandler {

	/* PARAMETER_ID defined in plugin.xml */
	private static final String ECORE_YUML_COMMAND_PARAMETER_TYPE = "ecore.yuml.command.parameter.type"; //$NON-NLS-1$

	private static final List<String> FILE_EXTENSIONS = Arrays.asList(new String[] { "ecore", "emof" });

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (!(currentSelection instanceof IStructuredSelection) || currentSelection.isEmpty()) {
			return Status.ERROR;
		}
		final IStructuredSelection selectionF = (IStructuredSelection) currentSelection;
		Object selection = selectionF.getFirstElement();
		final IFile selectedEcoreFile = selection instanceof IFile
				&& FILE_EXTENSIONS.contains(((IFile) selection).getFileExtension()) ? (IFile) selection : null;

		String parameter = event.getParameter(ECORE_YUML_COMMAND_PARAMETER_TYPE);

		printFile(YumlMeType.valueOf(parameter), selectedEcoreFile);
		return Status.OK_STATUS;
	}

	private void printFile(YumlMeType yumlMeType, final IFile selectedEcoreFile) {
		String ecoreToYuml = Ecore2Yuml.ecoreToYuml(selectedEcoreFile);

		try {
			String yumlMeURL = yumlMeType.toYumlMeURL(ecoreToYuml);
			IPath fullPath = selectedEcoreFile.getLocation();
			IPath addFileExtension = fullPath.addFileExtension(yumlMeType.getExtension());
			IPath makeAbsolute = addFileExtension.makeAbsolute();
			getImage(yumlMeURL, makeAbsolute.toFile(),
					Activator.getDefault().getPreferenceStore().getBoolean(EYumlPreferences.SSL_CHECK));
		} catch (UnsupportedEncodingException e) {
			Activator.log(e);
		}
	}

	public static void getImage(String bodyURL, File image, boolean checkSSL) {
		try {
			if (!checkSSL) {
				TrustManager[] trustAllCerts = new TrustManager[] { new AllTrustManager() };
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, trustAllCerts, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
				HttpsURLConnection.setDefaultHostnameVerifier(new TrustAllHostNameVerifier());
			}
			URL url = new URL(bodyURL);
			URLConnection openConnection = url.openConnection();
			InputStream in = new BufferedInputStream(openConnection.getInputStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			out.close();
			in.close();
			byte[] response = out.toByteArray();
			image.createNewFile();
			FileOutputStream fos = new FileOutputStream(image);
			fos.write(response);
			fos.close();
		} catch (Exception e) {
			Activator.log(e);
		}
	}

}
