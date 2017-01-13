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
package com.itextpdf.html2pdf.css.apply.util;

import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.css.util.CssUtils;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.Property;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import java.util.List;
import java.util.Map;

public class VerticalAlignmentApplierUtil {

    private static final double ASCENDER_COEFFICIENT = 0.8;
    private static final double DESCENDER_COEFFICIENT = 0.2;

    private VerticalAlignmentApplierUtil() {
    }

    public static void applyVerticalAlignmentForCells(Map<String, String> cssProps, ProcessorContext context, IPropertyContainer element) {
        String vAlignVal = cssProps.get(CssConstants.VERTICAL_ALIGN);
        if (vAlignVal != null) {
            // In layout, 'top' is the default behaviour for cells;
            // 'baseline' is not supported at the moment on layout level, so it defaults to value 'top';
            // all other possible values except 'middle' and 'bottom' do not apply to cells; 'baseline' is applied instead.

            if (CssConstants.MIDDLE.equals(vAlignVal)) {
                element.setProperty(Property.VERTICAL_ALIGNMENT, VerticalAlignment.MIDDLE);
            } else if (CssConstants.BOTTOM.equals(vAlignVal)) {
                element.setProperty(Property.VERTICAL_ALIGNMENT, VerticalAlignment.BOTTOM);
            }
        }
    }

    public static void applyVerticalAlignmentForInlines(Map<String, String> cssProps, ProcessorContext context, IElementNode elementNode, List<IPropertyContainer> childElements) {
        String vAlignVal = cssProps.get(CssConstants.VERTICAL_ALIGN);
        if (vAlignVal != null) {

            // TODO for inline images and tables (inline-blocks) v-align is not supported

            float textRise = 0;

            // TODO 'top' and 'bottom' values are not supported;
            // 'top' and 'bottom' require information of actual line height, therefore should be applied at layout level;
            // 'sub', 'super' calculations are based on the behaviour of the common browsers (+33% and -20% shift accordingly from the parent's font size);
            // 'middle', 'text-top', 'text-bottom' calculations are based on the approximate assumptions that x-height is 0.5 of the font size
            // and descender and ascender heights are 0.2 and 0.8 of the font size accordingly.

            if (CssConstants.SUB.equals(vAlignVal) || CssConstants.SUPER.equals(vAlignVal)) {
                textRise = calcTextRiseForSupSub(elementNode, vAlignVal);

            } else if (CssConstants.MIDDLE.equals(vAlignVal)) {
                textRise = calcTextRiseForMiddle(elementNode);

            } else if (CssConstants.TEXT_TOP.equals(vAlignVal)) {
                textRise = calcTextRiseForTextTop(elementNode, context.getCssContext().getRootFontSize());

            } else if (CssConstants.TEXT_BOTTOM.equals(vAlignVal)) {
                textRise = calcTextRiseForTextBottom(elementNode, context.getCssContext().getRootFontSize());

            } else if (CssUtils.isMetricValue(vAlignVal)) {
                textRise = CssUtils.parseAbsoluteLength(vAlignVal);

            } else if (vAlignVal.endsWith(CssConstants.PERCENTAGE)) {
                textRise = calcTextRiseForPercentageValue(elementNode, context.getCssContext().getRootFontSize(), vAlignVal);
            }
            if (textRise != 0) {
                for (IPropertyContainer element : childElements) {
                    if (element instanceof Text) {
                        Float effectiveTr = element.<Float>getProperty(Property.TEXT_RISE);
                        if (effectiveTr != null) {
                            effectiveTr += textRise;
                        } else {
                            effectiveTr = textRise;
                        }
                        element.setProperty(Property.TEXT_RISE, effectiveTr);
                    } else if (element instanceof IBlockElement) {
                        break;
                    }
                }
            }
        }
    }

    private static float calcTextRiseForSupSub(IElementNode elementNode, String vAlignVal) {
        float parentFontSize = getParentFontSize(elementNode);
        String superscriptPosition = "33%";
        String subscriptPosition = "-20%";
        String relativeValue = CssConstants.SUPER.equals(vAlignVal) ? superscriptPosition : subscriptPosition;
        return CssUtils.parseRelativeValue(relativeValue, parentFontSize);
    }

    private static float calcTextRiseForMiddle(IElementNode elementNode) {
        String ownFontSizeStr = elementNode.getStyles().get(CssConstants.FONT_SIZE);
        float fontSize = CssUtils.parseAbsoluteLength(ownFontSizeStr);
        float parentFontSize = getParentFontSize(elementNode);

        double fontMiddleCoefficient = 0.3;
        float elementMidPoint = (float) (fontSize * fontMiddleCoefficient); // shift to element mid point from the baseline
        float xHeight = parentFontSize / 4;

        return xHeight - elementMidPoint;
    }

    private static float calcTextRiseForTextTop(IElementNode elementNode, float rootFontSize) {
        String ownFontSizeStr = elementNode.getStyles().get(CssConstants.FONT_SIZE);
        float fontSize = CssUtils.parseAbsoluteLength(ownFontSizeStr);
        String lineHeightStr = elementNode.getStyles().get(CssConstants.LINE_HEIGHT);
        float lineHeightActualValue = getLineHeightActualValue(fontSize, rootFontSize, lineHeightStr);
        float parentFontSize = getParentFontSize(elementNode);

        float elementTopEdge = (float) (fontSize * ASCENDER_COEFFICIENT + (lineHeightActualValue - fontSize) / 2);
        float parentTextTop = (float) (parentFontSize * ASCENDER_COEFFICIENT);

        return parentTextTop - elementTopEdge;
    }

    private static float calcTextRiseForTextBottom(IElementNode elementNode, float rootFontSize) {
        String ownFontSizeStr = elementNode.getStyles().get(CssConstants.FONT_SIZE);
        float fontSize = CssUtils.parseAbsoluteLength(ownFontSizeStr);
        String lineHeightStr = elementNode.getStyles().get(CssConstants.LINE_HEIGHT);
        float lineHeightActualValue = getLineHeightActualValue(fontSize, rootFontSize, lineHeightStr);
        float parentFontSize = getParentFontSize(elementNode);

        float elementBottomEdge = (float) (fontSize * DESCENDER_COEFFICIENT + (lineHeightActualValue - fontSize) / 2);
        float parentTextBottom = (float) (parentFontSize * DESCENDER_COEFFICIENT);

        return elementBottomEdge - parentTextBottom;
    }

    private static float calcTextRiseForPercentageValue(IElementNode elementNode, float rootFontSize, String vAlignVal) {
        String ownFontSizeStr = elementNode.getStyles().get(CssConstants.FONT_SIZE);
        float fontSize = CssUtils.parseAbsoluteLength(ownFontSizeStr);
        String lineHeightStr = elementNode.getStyles().get(CssConstants.LINE_HEIGHT);
        float lineHeightActualValue = getLineHeightActualValue(fontSize, rootFontSize, lineHeightStr);

        return CssUtils.parseRelativeValue(vAlignVal, lineHeightActualValue);
    }


    private static float getLineHeightActualValue(float fontSize, float rootFontSize, String lineHeightStr) {
        float lineHeightActualValue;
        if (lineHeightStr != null) {
            UnitValue lineHeightValue = CssUtils.parseLengthValueToPt(lineHeightStr, fontSize, rootFontSize);
            if (CssUtils.isNumericValue(lineHeightStr)) {
                lineHeightActualValue = fontSize * lineHeightValue.getValue();
            } else if (lineHeightValue.isPointValue()) {
                lineHeightActualValue = lineHeightValue.getValue();
            } else {
                lineHeightActualValue = fontSize * lineHeightValue.getValue() / 100;
            }
        } else {
            lineHeightActualValue = (float) (fontSize * 1.2);
        }
        return lineHeightActualValue;
    }

    private static float getParentFontSize(IElementNode elementNode) {
        float parentFontSize;
        if (elementNode.parentNode() instanceof IElementNode) {
            String parentFontSizeStr = ((IElementNode) elementNode.parentNode()).getStyles().get(CssConstants.FONT_SIZE);
            parentFontSize = CssUtils.parseAbsoluteLength(parentFontSizeStr);
        } else {
            // let's take own font size for this unlikely case
            String ownFontSizeStr = elementNode.getStyles().get(CssConstants.FONT_SIZE);
            parentFontSize = CssUtils.parseAbsoluteLength(ownFontSizeStr);
        }
        return parentFontSize;
    }
}
