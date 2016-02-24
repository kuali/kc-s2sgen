/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.s2sgen.impl.util;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;

/**
 * Class processes XPath Queries.
 * 
 */
public class XPathExecutor {

	private Document doc;
	
	public XPathExecutor( String xml ) throws Exception{
		
		init( xml );
	}
	
	/**
	 * Method evaulates the XPath expression against the xml string.
	 * Currently utilizing a DOM implementation.
	 * @param xPath
	 * @return first node value returned
	 * @throws Exception
	 */
	public String execute( String xPath ) throws Exception {

		if ( xPath == null ){
			return null;
		}
		
		// Evaluate the xpath expression
		return XPathAPI.eval( getDoc(), xPath ).toString();
		
	
	}
	
	/**
	 * For a given XPath, a DOM Node that the XPath resolve to is returned.
	 * @param xpath A valid XPath referring to the Node that is to be returned
	 * @return The Node referred to by the xpath argument.
	 * @throws TransformerException
	 */
	public Node getNode(String xpath)
		throws TransformerException
	{
	    return XPathAPI.selectSingleNode(getDoc(), xpath);
	}
	
	private void init( String xml ) throws Exception {
	
		if ( xml == null ) {
			return;
		}
		

		try(ByteArrayInputStream stream = new ByteArrayInputStream(xml.getBytes())) {
		    DocumentBuilderFactory dfactory = DocumentBuilderFactory.newInstance();
		    dfactory.setNamespaceAware(true);
		    setDoc( dfactory.newDocumentBuilder().parse(stream) );
		}
	}

	
	/**
	 * @return the Document.
	 */
	public Document getDoc() {
		return doc;
	}
	/**
	 * @param doc the Document.
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}

}

