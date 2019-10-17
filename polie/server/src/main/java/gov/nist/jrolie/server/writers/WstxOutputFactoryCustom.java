/**
 * Portions of this software was developed by employees of the National Institute
 * of Standards and Technology (NIST), an agency of the Federal Government.
 * Pursuant to title 17 United States Code Section 105, works of NIST employees are
 * not subject to copyright protection in the United States and are considered to
 * be in the public domain. Permission to freely use, copy, modify, and distribute
 * this software and its documentation without fee is hereby granted, provided that
 * this notice and disclaimer of warranty appears in all copies.
 *
 * THE SOFTWARE IS PROVIDED 'AS IS' WITHOUT ANY WARRANTY OF ANY KIND, EITHER
 * EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY
 * THAT THE SOFTWARE WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, AND FREEDOM FROM
 * INFRINGEMENT, AND ANY WARRANTY THAT THE DOCUMENTATION WILL CONFORM TO THE
 * SOFTWARE, OR ANY WARRANTY THAT THE SOFTWARE WILL BE ERROR FREE. IN NO EVENT
 * SHALL NIST BE LIABLE FOR ANY DAMAGES, INCLUDING, BUT NOT LIMITED TO, DIRECT,
 * INDIRECT, SPECIAL OR CONSEQUENTIAL DAMAGES, ARISING OUT OF, RESULTING FROM, OR
 * IN ANY WAY CONNECTED WITH THIS SOFTWARE, WHETHER OR NOT BASED UPON WARRANTY,
 * CONTRACT, TORT, OR OTHERWISE, WHETHER OR NOT INJURY WAS SUSTAINED BY PERSONS OR
 * PROPERTY OR OTHERWISE, AND WHETHER OR NOT LOSS WAS SUSTAINED FROM, OR AROSE OUT
 * OF THE RESULTS OF, OR USE OF, THE SOFTWARE OR SERVICES PROVIDED HEREUNDER.
 */

package gov.nist.jrolie.server.writers;

import javax.xml.stream.XMLStreamException;

import org.codehaus.stax2.XMLStreamWriter2;

import com.ctc.wstx.api.WriterConfig;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.ctc.wstx.sw.NonNsStreamWriter;
import com.ctc.wstx.sw.RepairingNsStreamWriter;
import com.ctc.wstx.sw.SimpleNsStreamWriter;
import com.ctc.wstx.sw.XmlWriter;

public class WstxOutputFactoryCustom extends WstxOutputFactory {

	@Override
	protected XMLStreamWriter2 createSW(String enc, WriterConfig cfg, XmlWriter xw) {
		System.out.println("ASSUMING DIRECT CONTROL");
		if (cfg.willSupportNamespaces()) {
			if (cfg.automaticNamespacesEnabled()) {
				final RepairingNsStreamWriter rnsw = new RepairingNsStreamWriter(xw, enc, cfg);
				return rnsw;
			}
			final SimpleNsStreamWriter snsw = new SimpleNsStreamWriter(xw, enc, cfg);
			try {
				snsw.doSetPrefix("rolie", "urn:ietf:params:xml:ns:rolie-1.0");
				snsw.doSetPrefix("atom", "http://www.w3.org/2005/Atom");
				snsw.setDefaultNamespace("http://www.w3.org/2007/app");
			} catch (final XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return snsw;
		}
		return new NonNsStreamWriter(xw, enc, cfg);
	}

}
