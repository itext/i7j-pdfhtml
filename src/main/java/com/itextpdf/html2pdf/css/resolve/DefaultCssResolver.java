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
import com.itextpdf.html2pdf.css.CssDeclaration;
import com.itextpdf.html2pdf.css.CssStyleSheet;
import com.itextpdf.html2pdf.css.parse.CssRuleSetParser;
import com.itextpdf.html2pdf.css.parse.CssStyleSheetParser;
import com.itextpdf.html2pdf.css.resolve.shorthand.IShorthandResolver;
import com.itextpdf.html2pdf.css.resolve.shorthand.ShorthandResolverFactory;
import com.itextpdf.html2pdf.html.AttributeConstants;
import com.itextpdf.html2pdf.html.TagConstants;
import com.itextpdf.html2pdf.html.node.IDataNode;
import com.itextpdf.html2pdf.html.node.IElement;
import com.itextpdf.html2pdf.html.node.INode;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultCssResolver implements ICssResolver {

    private CssStyleSheet cssStyleSheet;

    public DefaultCssResolver(INode treeRoot) throws IOException {
        collectCSSDeclarations(treeRoot);
    }

    @Override
    public Map<String, String> resolveStyles(IElement element) {
        List<CssDeclaration> nodeCssDeclarations = HtmlStylesToCssConverter.convert(element);

        // TODO MediaDeviceDescription handling
        nodeCssDeclarations.addAll(cssStyleSheet.getCssDeclarations(element, null));
        String styleAttribute = element.getAttribute(AttributeConstants.STYLE);
        if (styleAttribute != null) {
            nodeCssDeclarations.addAll(CssRuleSetParser.parsePropertyDeclarations(styleAttribute));
        }

        Map<String, String> elementStyles = cssDeclarationsToMap(nodeCssDeclarations);

        if (element.parentNode() instanceof IElement) {
            Map<String, String> parentStyles = ((IElement) element.parentNode()).getStyles();

            if (parentStyles == null) {
                Logger logger = LoggerFactory.getLogger(DefaultCssResolver.class);
                logger.error("Element parent styles are not resolved. Styles for current element might be incorrect.");
            }

            if (parentStyles != null) {
                for (Map.Entry<String, String> entry : parentStyles.entrySet()) {
                    String cssProperty = entry.getKey();
                    String elementPropValue = elementStyles.get(cssProperty);
                    if ((elementPropValue == null && CssInheritance.isInheritable(cssProperty)) || CssConstants.INHERIT.equals(elementPropValue)) {
                        elementStyles.put(cssProperty, entry.getValue());
                    }
                }
            }
        }

        for (Map.Entry<String, String> entry : elementStyles.entrySet()) {
            if (CssConstants.INITIAL.equals(entry.getValue())
                    || CssConstants.INHERIT.equals(entry.getValue())) { // if "inherit" is not resolved till now, parents don't have it
                elementStyles.put(entry.getKey(), CssDefaults.getDefaultValue(entry.getKey()));
            }
        }

        return elementStyles;
    }

    private Map<String, String> cssDeclarationsToMap(List<CssDeclaration> nodeCssDeclarations) {
        Map<String, String> stylesMap = new HashMap<>();
        for (CssDeclaration cssDeclaration : nodeCssDeclarations) {
            IShorthandResolver shorthandResolver = ShorthandResolverFactory.getShorthandResolver(cssDeclaration.getProperty());
            if (shorthandResolver == null) {
                stylesMap.put(cssDeclaration.getProperty(), cssDeclaration.getExpression());
            } else {
                List<CssDeclaration> resolvedShorthandProps = shorthandResolver.resolveShorthand(cssDeclaration.getExpression());
                for (CssDeclaration resolvedProp : resolvedShorthandProps) {
                    stylesMap.put(resolvedProp.getProperty(), resolvedProp.getExpression());
                }
            }
        }
        return stylesMap;
    }

    private void collectCSSDeclarations(INode treeRoot) throws IOException {
        cssStyleSheet = new CssStyleSheet();

        INode headNode = findHeadNode(treeRoot);
        if (headNode == null) {
            return;
        }

        for (INode headChild : headNode.childNodes()) {
            if (headChild instanceof IElement) {
                IElement headChildElement = (IElement) headChild;
                if (headChildElement.name().equals(TagConstants.STYLE)) {
                    // && headChildElement.getAttribute(AttributeConstants.TYPE).equals("text/css")) { TODO most likely unnecessary and even is bad to use
                    if (headChild.childNodes().size() > 0 && headChild.childNodes().get(0) instanceof IDataNode) {
                        String styleData = ((IDataNode) headChild.childNodes().get(0)).getWholeData();
                        cssStyleSheet.appendCssStyleSheet(CssStyleSheetParser.parse(styleData));
                    }
                } else if (headChildElement.name().equals(TagConstants.LINK)
                        // TODO ensure this is right and must-have here; introduce constant? also: check " type="text/css" "
                        && "stylesheet".equals(headChildElement.getAttribute(AttributeConstants.REL))) {
                    String styleSheetUrl = headChildElement.getAttribute(AttributeConstants.HREF);

                    // TODO resolve url into stream properly
//                    InputStream fis = new FileInputStream(styleSheetUrl);
//                    cssStyleSheet.appendCssStyleSheet(CssStyleSheetParser.parse(fis));
                }
            }
        }
    }

    private INode findHeadNode(INode node) {
        Queue<INode> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            INode currentNode = q.poll();
            if (currentNode instanceof IElement && ((IElement) currentNode).name().equals(TagConstants.HEAD)) {
                return currentNode;
            }
            for (INode child : currentNode.childNodes()) {
                if (child instanceof IElement) {
                    q.add(child);
                }
            }
        }
        return null;
    }
}
