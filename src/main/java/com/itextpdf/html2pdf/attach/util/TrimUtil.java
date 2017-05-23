/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: Bruno Lowagie, Paulo Soares, et al.

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
import com.itextpdf.layout.layout.LayoutPosition;
import com.itextpdf.layout.property.FloatPropertyValue;
import com.itextpdf.layout.property.Property;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to trim content.
 */
public final class TrimUtil {

    /**
     * Creates a new <code>TrimUtil</code> instance.
     */
    private TrimUtil() {
    }

    /**
     * Trim leaf elements, and sanitize.
     *
     * @param leafElements the leaf elements
     * @return the trimmed and sanitized list
     */
    public static List<ILeafElement> trimLeafElementsAndSanitize(List<ILeafElement> leafElements) {
        ArrayList<ILeafElement> waitingLeaves = new ArrayList<ILeafElement>(leafElements);

        trimSubList(waitingLeaves, 0, waitingLeaves.size(), false);
        trimSubList(waitingLeaves, 0, waitingLeaves.size(), true);

        int pos = 0;
        while (pos < waitingLeaves.size() - 1) {
            if (waitingLeaves.get(pos) instanceof Text) {
                Text first = (Text) waitingLeaves.get(pos);
                int firstEnd = getIndexAfterLastNonSpace(first);
                if (firstEnd < first.getText().length()) {
                    trimSubList(waitingLeaves, pos + 1, waitingLeaves.size(), false);
                    first.setText(first.getText().substring(0, firstEnd + 1));
                }
            }
            pos++;
        }

        return waitingLeaves;
    }

    /**
     * Checks if a character is white space value that doesn't cause a newline.
     *
     * @param ch the character
     * @return true, if the character is a white space character, but no newline
     */
    static boolean isNonLineBreakSpace(char ch) {
        return Character.isWhitespace(ch) && ch != '\n';
    }

    /**
     * Trims a sub list of leaf elements.
     *
     * @param list the list of leaf elements
     * @param begin the index where to begin
     * @param end the index where to end
     * @param last indicates where to start, if true, we start at the end
     */
    private static void trimSubList(ArrayList<ILeafElement> list, int begin, int end, boolean last) {
        while (end > begin) {
            int pos = last ? end - 1 : begin;
            ILeafElement leaf = list.get(pos);
            if (leaf instanceof Text) {
                Text text = (Text) leaf;
                trimLeafElement(text, last);
                if (text.getText().length() == 0) {
                    list.remove(pos);
                    end--;
                    continue;
                }
            }
            break;
        }
    }

    /**
     * Trims a leaf element.
     *
     * @param leafElement the leaf element
     * @param last indicates where to start, if true, we start at the end
     * @return the leaf element
     */
    private static ILeafElement trimLeafElement(ILeafElement leafElement, boolean last) {
        if (leafElement instanceof Text) {
            Text text = (Text) leafElement;
            int begin = last ? 0 : getIndexOfFirstNonSpace(text);
            int end = last ? getIndexAfterLastNonSpace(text) : text.getText().length();
            text.setText(text.getText().substring(begin, end));
        }
        return leafElement;
    }

    /**
     * Gets the index of first character that isn't white space in some text.
     * Note: newline characters aren't counted as white space characters.
     *
     * @param text the text
     * @return the index of first character that isn't white space
     */
    private static int getIndexOfFirstNonSpace(Text text) {
        int pos = 0;
        while (pos < text.getText().length() && isNonLineBreakSpace(text.getText().charAt(pos))) {
            pos++;
        }
        return pos;
    }

    /**
     * Gets the index of last character following a character that isn't white space in some text.
     * Note: newline characters aren't counted as white space characters.
     *
     * @param text the text
     * @return the index following the last character that isn't white space
     */
    private static int getIndexAfterLastNonSpace(Text text) {
        int pos = text.getText().length();
        while (pos > 0 && isNonLineBreakSpace(text.getText().charAt(pos - 1))) {
            pos--;
        }
        return pos;
    }
}
