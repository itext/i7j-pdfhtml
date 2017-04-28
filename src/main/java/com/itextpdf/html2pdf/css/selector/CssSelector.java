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
package com.itextpdf.html2pdf.css.selector;

import com.itextpdf.html2pdf.css.parse.CssSelectorParser;
import com.itextpdf.html2pdf.css.pseudo.CssPseudoElementNode;
import com.itextpdf.html2pdf.css.selector.item.CssSeparatorSelectorItem;
import com.itextpdf.html2pdf.css.selector.item.ICssSelectorItem;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.html2pdf.html.node.INode;
import java.util.List;

public class CssSelector extends AbstractCssSelector {

    public CssSelector(List<ICssSelectorItem> selectorItems) {
        super(selectorItems);
    }

    public CssSelector(String selector) {
        this(CssSelectorParser.parseSelectorItems(selector));
    }

    public boolean matches(INode element) {
        return matches(element, selectorItems.size() - 1);
    }

    private boolean matches(INode element, int lastSelectorItemInd) {
        if (!(element instanceof IElementNode)) {
            return false;
        }
        if (lastSelectorItemInd < 0) {
            return true;
        }
        boolean isPseudoElement = element instanceof CssPseudoElementNode;
        for (int i = lastSelectorItemInd; i >= 0; i--) {
            if (isPseudoElement && i < lastSelectorItemInd) {
                // Pseudo element and class selector item shall be at the end of the selector string 
                // and be single pseudo selector item in it. All other selector items are checked against 
                // pseudo element node parent.
                element = element.parentNode();
                isPseudoElement = false;
            }
            ICssSelectorItem currentItem = selectorItems.get(i);
            if (currentItem instanceof CssSeparatorSelectorItem) {
                char separator = ((CssSeparatorSelectorItem) currentItem).getSeparator();
                switch (separator) {
                    case '>':
                        return matches(element.parentNode(), i - 1);
                    case ' ': {
                        INode parent = element.parentNode();
                        while (parent != null) {
                            boolean parentMatches = matches(parent, i - 1);
                            if (parentMatches) {
                                return true;
                            } else {
                                parent = parent.parentNode();
                            }
                        }
                        return false;
                    }
                    case '~': {
                        INode parent = element.parentNode();
                        if (parent != null) {
                            int indexOfElement = parent.childNodes().indexOf(element);
                            for (int j = indexOfElement - 1; j >= 0; j--) {
                                if (matches(parent.childNodes().get(j), i - 1)) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                    case '+': {
                        INode parent = element.parentNode();
                        if (parent != null) {
                            int indexOfElement = parent.childNodes().indexOf(element);
                            INode previousElement = null;
                            for (int j = indexOfElement - 1; j >= 0; j--)
                                if (parent.childNodes().get(j) instanceof IElementNode) {
                                    previousElement = parent.childNodes().get(j);
                                    break;
                                }
                            if (previousElement != null)
                                return indexOfElement > 0 && matches(previousElement, i - 1);
                        }
                        return false;
                    }
                    default:
                        return false;
                }
            } else {
                if (!currentItem.matches(element)) {
                    return false;
                }
            }
        }

        return true;
    }

}
