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
package org.kuali.coeus.s2sgen.impl.hash;

import org.apache.xml.security.algorithms.MessageDigestAlgorithm;
import org.apache.xml.security.c14n.CanonicalizationException;
import org.apache.xml.security.c14n.Canonicalizer;
import org.apache.xml.security.c14n.InvalidCanonicalizerException;
import org.apache.xml.security.signature.XMLSignatureException;
import org.apache.xml.security.utils.Base64;
import org.apache.xml.security.utils.DigesterOutputStream;
import org.kuali.coeus.s2sgen.api.core.S2SException;
import org.kuali.coeus.s2sgen.impl.util.XPathExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.transform.TransformerException;

/**
 * This is a convenience object that simplifies the hashing processing to one
 * method call.
 * 
 * @author David Wong
 */
public class GrantApplicationHash {

	private static final Logger log = LoggerFactory.getLogger(GrantApplicationHash.class
            .getName());

	static java.security.MessageDigest messageDigester = null;

	static {
		org.apache.xml.security.Init.init();
		try {
			messageDigester = java.security.MessageDigest.getInstance("SHA-1");
		} catch (Exception ex) {
			log.error(
					"Unable to get instance of java.security.MessageDigester",
					ex);
		}
	}

	/**
	 * Added private constructor to prevent creation by user.
	 */
	private GrantApplicationHash() {

	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xml
	 *            The Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws S2SException
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(String xml) throws S2SException {
		GrantApplicationXpath xpath;
        try {
            xpath = new GrantApplicationXpath(xml);
            return _hash(xpath);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new S2SException(e.getMessage(),e);
        }
	}

	/**
	 * Computes the hash of an binary attachment.
	 * 
	 * @param attachment
	 * @return The SHA-1 hash value of the attachment byte array.
	 */
	public final static String computeAttachmentHash(byte[] attachment) {

		byte[] rawDigest = messageDigester.digest(attachment);

		return Base64.encode(rawDigest);

	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xpath
	 *            An xpath object holding the Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws Exception
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(GrantApplicationXpath xpath)
			throws Exception {
		return _hash(xpath);
	}

	/**
	 * Computes the hash value for the Grants.gov application XML.
	 * 
	 * @param xml
	 *            The Grants.gov application XML.
	 * @return The SHA-1 hash value of &lt;grant:forms&gt; tag inside the
	 *         application XML.
	 * @throws Exception
	 *             When the XML cannot be parsed.
	 */
	public final static String computeGrantFormsHash(Document xml) throws Exception {
		XPathExecutor executor = new XPathExecutor(null);
		executor.setDoc(xml);
		GrantApplicationXpath xpath = new GrantApplicationXpath(null);
		xpath.setExecutor(executor);
		return _hash(xpath);
	}

	private static String _hash(GrantApplicationXpath xpath)
			throws TransformerException, XMLSignatureException,
			InvalidCanonicalizerException, CanonicalizationException {
		Node formsNode = xpath.getFormsNode();
		DigesterOutputStream digester = _createDigesterOutputStream(xpath
				.getExecutor().getDoc());
		Canonicalizer canonicalizer = Canonicalizer
				.getInstance(Canonicalizer.ALGO_ID_C14N_EXCL_OMIT_COMMENTS);
		canonicalizer.setWriter(digester);
		canonicalizer.canonicalizeSubtree(formsNode);
		byte[] hash = digester.getDigestValue();
		return Base64.encode(hash);
	}

	private static DigesterOutputStream _createDigesterOutputStream(Document doc)
			throws XMLSignatureException {
		DigesterOutputStream stream = null;
		if (doc != null) {
			stream = new DigesterOutputStream(MessageDigestAlgorithm
					.getInstance(doc,
							MessageDigestAlgorithm.ALGO_ID_DIGEST_SHA1));
		}
		return stream;
	}
}
