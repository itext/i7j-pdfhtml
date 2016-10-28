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

import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.Text;
import java.util.ArrayList;
import java.util.List;

public final class TrimUtil {

    private TrimUtil() {
    }

    // Note that the end is not trimmed. Maybe we should trim the content here, but the end trim is performed during layout anyway
    public static List<ILeafElement> trimLeafElementsFirstAndSanitize(List<ILeafElement> leafElements) {
        List<ILeafElement> waitingLeafs = new ArrayList<>(leafElements);
        while (waitingLeafs.size() > 0 && waitingLeafs.get(0) instanceof Text) {
            Text text = (Text) waitingLeafs.get(0);
            trimLeafElementFirst(text);
            if (text.getText().length() == 0) {
                waitingLeafs.remove(0);
            } else {
                break;
            }
        }

        int pos = 0;
        while (pos < waitingLeafs.size() - 1) {
            if (waitingLeafs.get(pos) instanceof Text) {
                Text first = (Text) waitingLeafs.get(pos);
                if (first.getText().matches(".*\\s+$")) {
                    while (pos + 1 < waitingLeafs.size() && waitingLeafs.get(pos + 1) instanceof Text) {
                        Text second = (Text) waitingLeafs.get(pos + 1);
                        if (second.getText().matches("^\\s+.*")) {
                            second.setText(second.getText().replaceFirst("^\\s+", ""));
                        }
                        if (second.getText().length() == 0) {
                            waitingLeafs.remove(pos + 1);
                        } else {
                            break;
                        }
                    }
                }
            }
            pos++;
        }

        return waitingLeafs;
    }

    public static ILeafElement trimLeafElementFirst(ILeafElement leafElement) {
        if (leafElement instanceof Text) {
            Text text = (Text) leafElement;
            if (text.getText().matches("^\\s+.*")) {
                text.setText(text.getText().replaceFirst("^\\s+", ""));
            }
        }
        return leafElement;
    }

}
