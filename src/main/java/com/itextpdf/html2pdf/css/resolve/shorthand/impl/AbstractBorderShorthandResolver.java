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
package com.itextpdf.html2pdf.css.resolve.shorthand.impl;

import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.css.CssDeclaration;
import com.itextpdf.html2pdf.css.resolve.shorthand.IShorthandResolver;
import com.itextpdf.html2pdf.css.util.CssUtils;
import com.itextpdf.kernel.color.WebColors;

import java.text.MessageFormat;
import java.util.*;

public abstract class AbstractBorderShorthandResolver implements IShorthandResolver {

    private static final String _0_WIDTH = "{0}-width";
    private static final String _0_STYLE = "{0}-style";
    private static final String _0_COLOR = "{0}-color";

    // TODO consider moving to the more appropriate place
    private static final Set<String> BORDER_WIDTH_CONST = new HashSet<String>(
            Arrays.asList(new String[] { CssConstants.THIN, CssConstants.MEDIUM, CssConstants.THICK }));
    // TODO consider moving to the more appropriate place
    private static final Set<String> BORDER_STYLE_CONST = new HashSet<String>(
            Arrays.asList(new String[] { CssConstants.NONE, CssConstants.HIDDEN, CssConstants.DOTTED, CssConstants.DASHED,
                    CssConstants.SOLID, CssConstants.DOUBLE, CssConstants.GROOVE, CssConstants.RIDGE, CssConstants.INSET,
                    CssConstants.OUTSET}));

    protected abstract String getPrefix();

    @Override
    public List<CssDeclaration> resolveShorthand(String shorthandExpression) {
        String[] props = shorthandExpression.split(" ");
        List<CssDeclaration> resolvedDecl = new ArrayList<>();
        String widthProp = MessageFormat.format(_0_WIDTH, getPrefix());
        String styleProp = MessageFormat.format(_0_STYLE, getPrefix());
        String colorProp = MessageFormat.format(_0_COLOR, getPrefix());
        for (String value : props) {
            if (BORDER_WIDTH_CONST.contains(value) || CssUtils.isNumericValue(value) || CssUtils.isMetricValue(value)) {
                resolvedDecl.add(new CssDeclaration(widthProp, value));
            } else if (BORDER_STYLE_CONST.contains(value)) {
                resolvedDecl.add(new CssDeclaration(styleProp, value));
            } else if (WebColors.isColorProperty(value)) {
                resolvedDecl.add(new CssDeclaration(colorProp, value));
            }
        }
        return resolvedDecl;
    }
}
