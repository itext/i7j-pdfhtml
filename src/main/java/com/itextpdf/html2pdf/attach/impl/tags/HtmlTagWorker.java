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
package com.itextpdf.html2pdf.attach.impl.tags;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.impl.layout.HtmlDocumentRenderer;
import com.itextpdf.html2pdf.attach.impl.layout.form.element.FormField;
import com.itextpdf.html2pdf.attach.util.WaitingInlineElementsHelper;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.css.resolve.ICssResolver;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.html2pdf.html.node.INode;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.Property;

public class HtmlTagWorker implements ITagWorker {

    private Document document;
    private WaitingInlineElementsHelper inlineHelper;

    public HtmlTagWorker(IElementNode element, ProcessorContext context) {
        boolean immediateFlush = !context.getCssContext().isPagesCounterPresent();
        PdfDocument pdfDocument = context.getPdfDocument();
        document = new Document(pdfDocument, pdfDocument.getDefaultPageSize(), immediateFlush);
        document.setRenderer(new HtmlDocumentRenderer(document, immediateFlush));
        
        document.setProperty(Property.COLLAPSING_MARGINS, true);
        document.setFontProvider(context.getFontProvider());
        String fontFamily = element.getStyles().get(CssConstants.FONT_FAMILY);
        document.setProperty(Property.FONT, fontFamily);
        inlineHelper = new WaitingInlineElementsHelper(element.getStyles().get(CssConstants.WHITE_SPACE), element.getStyles().get(CssConstants.TEXT_TRANSFORM));
    }

    @Override
    public void processEnd(IElementNode element, ProcessorContext context) {
        inlineHelper.flushHangingLeaves(document);
    }

    @Override
    public boolean processContent(String content, ProcessorContext context) {
        inlineHelper.add(content);
        return true;
    }

    @Override
    public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
        boolean processed;
        if (childTagWorker instanceof SpanTagWorker) {
            boolean allChildrenProcessed = true;
            for (IPropertyContainer propertyContainer : ((SpanTagWorker) childTagWorker).getAllElements()) {
                if (propertyContainer instanceof ILeafElement) {
                    inlineHelper.add((ILeafElement) propertyContainer);
                } else {
                    allChildrenProcessed = processBlockChild(propertyContainer) && allChildrenProcessed;
                }
            }
            processed = allChildrenProcessed;
        }  else if (childTagWorker.getElementResult() instanceof FormField) {
            inlineHelper.add((FormField) childTagWorker.getElementResult());
            return true;
        } else if (childTagWorker.getElementResult() instanceof AreaBreak) {
            postProcessInlineGroup();
            document.add((AreaBreak) childTagWorker.getElementResult());
            processed = true;
        } else {
            return processBlockChild(childTagWorker.getElementResult());
        }

        return processed;
    }

    @Override
    public IPropertyContainer getElementResult() {
        return document;
    }

    public void processPageRules(INode rootNode, ICssResolver cssResolver, ProcessorContext context) {
        ((HtmlDocumentRenderer)document.getRenderer()).processPageRules(rootNode, cssResolver, context);
    }
    
    private boolean processBlockChild(IPropertyContainer element) {
        postProcessInlineGroup();
        if (element instanceof IBlockElement) {
            document.add((IBlockElement) element);
            return true;
        } else if (element instanceof Image) {
            document.add((Image) element);
        }
        return true;
    }

    private void postProcessInlineGroup() {
        inlineHelper.flushHangingLeaves(document);
    }

}
