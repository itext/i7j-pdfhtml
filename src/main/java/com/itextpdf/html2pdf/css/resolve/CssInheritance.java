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

import com.itextpdf.html2pdf.css.CssConstants;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CssInheritance {

    /**
     * In accordance with "http://www.w3schools.com/cssref/" and "https://developer.mozilla.org/en-US/docs/Web/CSS/Reference"
     */
    private static final Set<String> inheritableProperties = new HashSet<>(Arrays.asList(

            // Color Properties
            CssConstants.COLOR,

            // Basic Box Properties
            CssConstants.VISIBILITY,

            // Text Properties
            CssConstants.HANGING_PUNCTUATION,
            CssConstants.HYPHENS,
            CssConstants.LETTER_SPACING,
            CssConstants.LINE_HEIGHT,
            CssConstants.OVERFLOW_WRAP,
            CssConstants.TAB_SIZE,
            CssConstants.TEXT_ALIGN,
            CssConstants.TEXT_ALIGN_LAST,
            CssConstants.TEXT_INDENT,
            CssConstants.TEXT_JUSTIFY,
            CssConstants.TEXT_TRANSFORM,
            CssConstants.WHITE_SPACE,
            CssConstants.WORD_BREAK,
            CssConstants.WORD_SPACING,
            CssConstants.WORDWRAP,

            // Text Decoration Properties
            CssConstants.TEXT_SHADOW,
            CssConstants.TEXT_UNDERLINE_POSITION,

            // Font Properties
            CssConstants.FONT,
            CssConstants.FONT_FAMILY,
            CssConstants.FONT_FEATURE_SETTINGS,
            CssConstants.FONT_KERNING,
            CssConstants.FONT_LANGUAGE_OVERRIDE,
            CssConstants.FONT_SIZE,
            CssConstants.FONT_SIZE_ADJUST,
            CssConstants.FONT_STRETCH,
            CssConstants.FONT_STYLE,
            CssConstants.FONT_SYNTHESIS,
            CssConstants.FONT_VARIANT,
            CssConstants.FONT_VARIANT_ALTERNATES,
            CssConstants.FONT_VARIANT_CAPS,
            CssConstants.FONT_VARIANT_EAST_ASIAN,
            CssConstants.FONT_VARIANT_LIGATURES,
            CssConstants.FONT_VARIANT_NUMERIC,
            CssConstants.FONT_VARIANT_POSITION,
            CssConstants.FONT_WEIGHT,

            // Writing Modes Properties
            CssConstants.FONT_DIRECTION,
            CssConstants.TEXT_ORIENTATION,
            CssConstants.TEXT_COMBINE_UPRIGHT,
            CssConstants.UNICODE_BIDI,
            CssConstants.WRITING_MODE,

            // Table Properties
            CssConstants.BORDER_COLLAPSE,
            CssConstants.BORDER_SPACING,
            CssConstants.CAPTION_SIDE,
            CssConstants.EMPTY_CELLS,

            // Lists and Counters Properties
            CssConstants.LIST_STYLE,
            CssConstants.LIST_STYLE_IMAGE,
            CssConstants.LIST_STYLE_POSITION,
            CssConstants.LIST_STYLE_TYPE,

            // Generated Content for Paged Media
            CssConstants.QUOTES
    ));

    public static boolean isInheritable(String cssProperty) {
        return inheritableProperties.contains(cssProperty);
    }
}
