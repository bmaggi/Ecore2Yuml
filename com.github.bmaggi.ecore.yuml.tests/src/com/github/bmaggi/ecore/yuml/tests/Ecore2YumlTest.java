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
package com.github.bmaggi.ecore.yuml.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.bmaggi.ecore.yuml.Ecore2Yuml;

@RunWith(Parameterized.class)
public class Ecore2YumlTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { "resources/Sample.ecore", "[myEClass;myEAttribute:]," },  
                 { "resources/extlibrary.ecore", "[Book;title:EString;pages:EInt;category:BookCategory],[Library;name:EString;people:EFeatureMapEntry],[Writer;name:EString],[Item;publicationDate:EDate],[Lendable;copies:EInt],[CirculatingItem],[Periodical;title:EString;issuesPerYear:EInt],[AudioVisualItem;title:EString;minutesLength:EInt;damaged:EBoolean],[BookOnTape],[VideoCassette],[Borrower],[Person;firstName:EString;lastName:EString],[Employee],[Addressable;address:EString],[Book;title:EString;pages:EInt;category:BookCategory]^[CirculatingItem],[Book;title:EString;pages:EInt;category:BookCategory]author->[Writer;name:EString],[Library;name:EString;people:EFeatureMapEntry]^[Addressable;address:EString],[Library;name:EString;people:EFeatureMapEntry]writers->[Writer;name:EString],[Library;name:EString;people:EFeatureMapEntry]employees->[Employee],[Library;name:EString;people:EFeatureMapEntry]borrowers->[Borrower],[Library;name:EString;people:EFeatureMapEntry]stock->[Item;publicationDate:EDate],[Library;name:EString;people:EFeatureMapEntry]books->[Book;title:EString;pages:EInt;category:BookCategory],[Library;name:EString;people:EFeatureMapEntry]branches->[Library;name:EString;people:EFeatureMapEntry],[Library;name:EString;people:EFeatureMapEntry]parentBranch->[Library;name:EString;people:EFeatureMapEntry],[Writer;name:EString]^[Person;firstName:EString;lastName:EString],[Writer;name:EString]books->[Book;title:EString;pages:EInt;category:BookCategory],[Lendable;copies:EInt]borrowers->[Borrower],[CirculatingItem]^[Item;publicationDate:EDate],[CirculatingItem]^[Lendable;copies:EInt],[Periodical;title:EString;issuesPerYear:EInt]^[Item;publicationDate:EDate],[AudioVisualItem;title:EString;minutesLength:EInt;damaged:EBoolean]^[CirculatingItem],[BookOnTape]^[AudioVisualItem;title:EString;minutesLength:EInt;damaged:EBoolean],[BookOnTape]reader->[Person;firstName:EString;lastName:EString],[BookOnTape]author->[Writer;name:EString],[VideoCassette]^[AudioVisualItem;title:EString;minutesLength:EInt;damaged:EBoolean],[VideoCassette]cast->[Person;firstName:EString;lastName:EString],[Borrower]^[Person;firstName:EString;lastName:EString],[Borrower]borrowed->[Lendable;copies:EInt],[Person;firstName:EString;lastName:EString]^[Addressable;address:EString],[Employee]^[Person;firstName:EString;lastName:EString],[Employee]manager->[Employee],"}  
           });
    }

    private String path;

    private String expectedYuml;

    public Ecore2YumlTest(String path, String expectedYuml) {
    	this.path= path;
        this.expectedYuml= expectedYuml;
    }

    @Test
    public void test() {
		URI createFileURI = URI.createFileURI(path);
		String sampleYuml = Ecore2Yuml.ecoreToYuml(createFileURI);
		assertEquals("Sample ecore gave a wrong yuml", expectedYuml,sampleYuml);
    }	

	
}
