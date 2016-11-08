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
package com.itextpdf.html2pdf.attach.util;

import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WaitingInlineElementsHelper {

    private String textTransform;
    private boolean keepLineBreaks;
    private boolean collapseSpaces;

    public WaitingInlineElementsHelper(String whiteSpace, String textTransform) {
        keepLineBreaks = CssConstants.PRE.equals(whiteSpace) || CssConstants.PRE_WRAP.equals(whiteSpace) || CssConstants.PRE_LINE.equals(whiteSpace);
        collapseSpaces = !(CssConstants.PRE.equals(whiteSpace) || CssConstants.PRE_WRAP.equals(whiteSpace));
        this.textTransform = textTransform;
    }

    private List<ILeafElement> waitingLeaves = new ArrayList<>();

    public void add(String text) {
        if (!keepLineBreaks && collapseSpaces) {
            text = text.replaceAll("\\s+", " ");
        } else if (keepLineBreaks && collapseSpaces) {
            text = text.replaceAll("[\\s&&[^\n]]+", " ");
        }

        if (CssConstants.UPPERCASE.equals(textTransform)) {
            text = text.toUpperCase();
        } else if (CssConstants.LOWERCASE.equals(textTransform)) {
            text = text.toLowerCase();
        }

        waitingLeaves.add(new Text(text));
    }

    public void add(ILeafElement element) {
        waitingLeaves.add(element);
    }

    public void addAll(Collection<ILeafElement> collection) {
        waitingLeaves.addAll(collection);
    }

    public void flushHangingLeafs(IPropertyContainer container) {
        if (collapseSpaces) {
            waitingLeaves = TrimUtil.trimLeafElementsFirstAndSanitize(waitingLeaves);
        }
        if (CssConstants.CAPITALIZE.equals(textTransform)) {
            capitalize(waitingLeaves);
        }
        if (waitingLeaves.size() > 0) {
            Paragraph p = createParagraphContainer();
            for (ILeafElement leaf : waitingLeaves) {
                p.add(leaf);
            }
            if (container instanceof Document) {
                ((Document) container).add(p);
            } else if (container instanceof Paragraph) {
                for (ILeafElement leafElement : waitingLeaves) {
                    ((Paragraph) container).add(leafElement);
                }
            } else if (container instanceof Div) {
                ((Div) container).add(p);
            } else if (container instanceof Cell) {
                ((Cell) container).add(p);
            } else if (container instanceof com.itextpdf.layout.element.List) {
                ListItem li = new ListItem();
                li.add(p);
                ((com.itextpdf.layout.element.List) container).add(li);
            } else {
                throw new IllegalStateException("Unable to process hanging inline content");
            }
            waitingLeaves.clear();
        }
    }

    public Collection<ILeafElement> getWaitingLeaves() {
        return waitingLeaves;
    }

    public void clearWaitingLeaves() {
        waitingLeaves.clear();
    }

    public Paragraph createParagraphContainer() {
        return new Paragraph().setMargin(0);
    }

    private static void capitalize(List<ILeafElement> leaves) {
        boolean previousLetter = false;
        for (ILeafElement element : leaves) {
            if (element instanceof Text) {
                String text = ((Text) element).getText();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < text.length(); i++) {
                    if (Character.isLowerCase(text.charAt(i)) && !previousLetter) {
                        sb.append(Character.toUpperCase(text.charAt(i)));
                        previousLetter = true;
                    } else if (Character.isAlphabetic(text.charAt(i))) {
                        sb.append(text.charAt(i));
                        previousLetter = true;
                    } else {
                        sb.append(text.charAt(i));
                        previousLetter = false;
                    }
                }
                ((Text) element).setText(sb.toString());
            } else {
                previousLetter = false;
            }
        }
    }

}
