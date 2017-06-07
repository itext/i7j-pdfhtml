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
package com.itextpdf.html2pdf.css.resolve.shorthand.impl;

import com.itextpdf.html2pdf.LogMessageConstant;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.css.CssDeclaration;
import com.itextpdf.html2pdf.css.resolve.shorthand.IShorthandResolver;
import com.itextpdf.html2pdf.css.util.CssUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackgroundShorthandResolver implements IShorthandResolver {


    private static final int UNDEFINED_TYPE = -1;
    private static final int BACKGROUND_COLOR_TYPE = 0;
    private static final int BACKGROUND_IMAGE_TYPE = 1;
    private static final int BACKGROUND_POSITION_TYPE = 2;
    private static final int BACKGROUND_POSITION_OR_SIZE_TYPE = 3; // might have the same type, but position always precedes size
    private static final int BACKGROUND_REPEAT_TYPE = 4;
    private static final int BACKGROUND_ORIGIN_OR_CLIP_TYPE = 5; // have the same possible values but apparently origin values precedes clip value
    private static final int BACKGROUND_CLIP_TYPE = 6;
    private static final int BACKGROUND_ATTACHMENT_TYPE = 7;

    // With CSS3, you can apply multiple backgrounds to elements. These are layered atop one another
    // with the first background you provide on top and the last background listed in the back. Only
    // the last background can include a background color.

    @Override
    public List<CssDeclaration> resolveShorthand(String shorthandExpression) {
        if (CssConstants.INITIAL.equals(shorthandExpression) || CssConstants.INHERIT.equals(shorthandExpression)) {
            return Arrays.asList(
                    new CssDeclaration(CssConstants.BACKGROUND_COLOR, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_IMAGE, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_POSITION, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_SIZE, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_REPEAT, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_ORIGIN, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_CLIP, shorthandExpression),
                    new CssDeclaration(CssConstants.BACKGROUND_ATTACHMENT, shorthandExpression)
            );
        }

        List<String> commaSeparatedExpressions = splitMultipleBackgrounds(shorthandExpression);

        // TODO ignore multiple backgrounds at the moment
        String backgroundExpression = commaSeparatedExpressions.get(0);
        String[] resolvedProps = new String[8];

        String[] props = backgroundExpression.split("\\s+");
        boolean slashEncountered = false;
        for (String value : props) {
            int slashCharInd = value.indexOf('/');
            if (slashCharInd > 0 && !value.contains("url(")) {
                slashEncountered = true;
                String value1 = value.substring(0, slashCharInd);
                String value2 = value.substring(slashCharInd + 1, value.length());
                putPropertyBasedOnType(resolvePropertyType(value1), value1, resolvedProps, false);
                putPropertyBasedOnType(resolvePropertyType(value2), value2, resolvedProps, true);
            } else {
                putPropertyBasedOnType(resolvePropertyType(value), value, resolvedProps, slashEncountered);
            }

        }

        for (int i = 0; i < resolvedProps.length; ++i) {
            if (resolvedProps[i] == null) {
                resolvedProps[i] = CssConstants.INITIAL;
            }
        }
        List<CssDeclaration> cssDeclarations = Arrays.asList(
                new CssDeclaration(CssConstants.BACKGROUND_COLOR, resolvedProps[BACKGROUND_COLOR_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_IMAGE, resolvedProps[BACKGROUND_IMAGE_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_POSITION, resolvedProps[BACKGROUND_POSITION_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_SIZE, resolvedProps[BACKGROUND_POSITION_OR_SIZE_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_REPEAT, resolvedProps[BACKGROUND_REPEAT_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_ORIGIN, resolvedProps[BACKGROUND_ORIGIN_OR_CLIP_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_CLIP, resolvedProps[BACKGROUND_CLIP_TYPE]),
                new CssDeclaration(CssConstants.BACKGROUND_ATTACHMENT, resolvedProps[BACKGROUND_ATTACHMENT_TYPE])
        );

        return cssDeclarations;
    }

    private int resolvePropertyType(String value) {
        if (value.contains("url(") || CssConstants.NONE.equals(value)) {
            return BACKGROUND_IMAGE_TYPE;
        } else if (CssConstants.BACKGROUND_REPEAT_VALUES.contains(value)) {
            return BACKGROUND_REPEAT_TYPE;
        } else if (CssConstants.BACKGROUND_ATTACHMENT_VALUES.contains(value)) {
            return BACKGROUND_ATTACHMENT_TYPE;
        } else if (CssConstants.BACKGROUND_POSITION_VALUES.contains(value)) {
            return BACKGROUND_POSITION_TYPE;
        } else if (CssUtils.isNumericValue(value) || CssUtils.isMetricValue(value) || CssUtils.isRelativeValue(value)) {
            return BACKGROUND_POSITION_OR_SIZE_TYPE;
        } else if (CssConstants.BACKGROUND_SIZE_VALUES.contains(value)) {
            return BACKGROUND_POSITION_OR_SIZE_TYPE;
        } else if(CssUtils.isColorProperty(value)) {
            return BACKGROUND_COLOR_TYPE;
        } else if (CssConstants.BACKGROUND_ORIGIN_OR_CLIP_VALUES.contains(value)) {
            return BACKGROUND_ORIGIN_OR_CLIP_TYPE;
        }
        return UNDEFINED_TYPE;
    }

    private void putPropertyBasedOnType(int type, String value, String[] resolvedProps, boolean slashEncountered) {
        if (type == UNDEFINED_TYPE) {
            Logger logger = LoggerFactory.getLogger(BackgroundShorthandResolver.class);
            logger.error(MessageFormat.format(LogMessageConstant.WAS_NOT_ABLE_TO_DEFINE_BACKGROUND_CSS_SHORTHAND_PROPERTIES, value));
            return;
        }
        if (type == BACKGROUND_POSITION_OR_SIZE_TYPE && !slashEncountered) {
            type = BACKGROUND_POSITION_TYPE;
        }

        if (type == BACKGROUND_ORIGIN_OR_CLIP_TYPE && resolvedProps[BACKGROUND_ORIGIN_OR_CLIP_TYPE] != null) {
            type = BACKGROUND_CLIP_TYPE;
        }

        if ((type == BACKGROUND_POSITION_OR_SIZE_TYPE || type == BACKGROUND_POSITION_TYPE) && resolvedProps[type] != null) {
            resolvedProps[type] += " " + value;
        } else {
            resolvedProps[type] = value;
        }
    }

    private List<String> splitMultipleBackgrounds(String shorthandExpression) {
        List<String> commaSeparatedExpressions = new ArrayList<>();
        boolean isInsideParentheses = false; // in order to avoid split inside rgb/rgba color definition
        int prevStart = 0;
        for (int i = 0; i < shorthandExpression.length(); ++i) {
            if (shorthandExpression.charAt(i) == ',' && !isInsideParentheses) {
                commaSeparatedExpressions.add(shorthandExpression.substring(prevStart, i));
                prevStart = i + 1;
            } else if (shorthandExpression.charAt(i) == '(') {
                isInsideParentheses = true;
            } else if (shorthandExpression.charAt(i) == ')') {
                isInsideParentheses = false;
            }
        }

        if (commaSeparatedExpressions.isEmpty()) {
            commaSeparatedExpressions.add(shorthandExpression);
        }
        return commaSeparatedExpressions;
    }
}
