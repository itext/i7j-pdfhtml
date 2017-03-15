/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: iText Software.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.html2pdf.css.resolve;

import com.itextpdf.html2pdf.css.CssContextNode;
import com.itextpdf.html2pdf.css.pseudo.CssPseudoElementUtil;
import com.itextpdf.html2pdf.html.node.IAttribute;
import com.itextpdf.html2pdf.html.node.IAttributes;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.html2pdf.html.node.INode;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

class CssContentElementNode extends CssContextNode implements IElementNode {
    private Attributes attributes;
    private String tagName;

    public CssContentElementNode(INode parentNode, String pseudoElementName, Map<String, String> attributes) {
        super(parentNode);
        this.tagName = CssPseudoElementUtil.createPseudoElementTagName(pseudoElementName);
        this.attributes = new Attributes(attributes);
    }

    @Override
    public String name() {
        return tagName;
    }

    @Override
    public IAttributes getAttributes() {
        return attributes;
    }

    @Override
    public String getAttribute(String key) {
        return attributes.getAttribute(key);
    }

    @Override
    public List<Map<String, String>> getAdditionalHtmlStyles() {
        return null;
    }

    @Override
    public void addAdditionalHtmlStyles(Map<String, String> styles) {
        throw new UnsupportedOperationException("addAdditionalHtmlStyles");
    }

    @Override
    public String getLang() {
        return null;
    }

    private class Attributes implements IAttributes {
        private Map<String, String> attributes;

        public Attributes(Map<String, String> attributes) {
            this.attributes = attributes;
        }

        @Override
        public String getAttribute(String key) {
            return attributes.get(key);
        }

        @Override
        public void setAttribute(String key, String value) {
            throw new UnsupportedOperationException("setAttribute");
        }

        @Override
        public int size() {
            return attributes.size();
        }

        @Override
        public Iterator<IAttribute> iterator() {
            return new AttributeIterator(attributes.entrySet().iterator());
        }
    }

    private class Attribute implements IAttribute {
        private Map.Entry<String, String> entry;

        public Attribute(Map.Entry<String, String> entry) {
            this.entry = entry;
        }

        @Override
        public String getKey() {
            return entry.getKey();
        }

        @Override
        public String getValue() {
            return entry.getValue();
        }
    }

    private class AttributeIterator implements Iterator<IAttribute> {
        private Iterator<Map.Entry<String, String>> iterator;

        public AttributeIterator(Iterator<Map.Entry<String, String>> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public IAttribute next() {
            return new Attribute(iterator.next());
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
