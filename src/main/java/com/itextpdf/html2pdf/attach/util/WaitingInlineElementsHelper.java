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

import com.itextpdf.layout.Document;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WaitingInlineElementsHelper {

    private List<ILeafElement> waitingLeafs = new ArrayList<>();

    public void add(ILeafElement element) {
        waitingLeafs.add(element);
    }

    public void addAll(Collection<ILeafElement> collection) {
        waitingLeafs.addAll(collection);
    }

    public void flushHangingLeafs(IPropertyContainer container) {
        waitingLeafs = TrimUtil.trimLeafElementsFirstAndSanitize(waitingLeafs);
        if (waitingLeafs.size() > 0) {
            Paragraph p = new Paragraph();
            for (ILeafElement leaf : waitingLeafs) {
                p.add(leaf);
            }
            if (container instanceof Document) {
                ((Document) container).add(p);
            } else if (container instanceof Paragraph) {
                for (ILeafElement leafElement : waitingLeafs) {
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
            waitingLeafs.clear();
        }
    }

}
