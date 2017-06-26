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
package com.itextpdf.html2pdf.attach.impl.tags;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.attach.util.WaitingInlineElementsHelper;
import com.itextpdf.html2pdf.attach.wrapelement.SpanWrapper;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.ILeafElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TagWorker class for the <code>span</code> tag.
 */
public class SpanTagWorker implements ITagWorker, IDisplayAware {

    /** The span wrapper. */
    SpanWrapper spanWrapper;

    // TODO ideally, this should be refactored. For now, I don't see a beautiful way of passing this information to other workers.
    // Also, we probably should wait a bit until the display support is more or less stable
    private Map<IPropertyContainer, String> childrenDisplayMap = new HashMap<>();
    
    /** A list of elements belonging to the span. */
    private List<IPropertyContainer> elements;
    
    /** The own leaf elements. */
    private List<IPropertyContainer> ownLeafElements = new ArrayList<>();
    
    /** The helper object for waiting inline elements. */
    private WaitingInlineElementsHelper inlineHelper;
    
    /** The display value. */
    private String display;

    /**
     * Creates a new <code>SpanTagWorker</code> instance.
     *
     * @param element the element
     * @param context the processor context
     */
    public SpanTagWorker(IElementNode element, ProcessorContext context) {
        spanWrapper = new SpanWrapper();
        Map<String, String> styles = element.getStyles();
        inlineHelper = new WaitingInlineElementsHelper(styles == null ? null : styles.get(CssConstants.WHITE_SPACE), styles == null ? null : styles.get(CssConstants.TEXT_TRANSFORM));
        display = element.getStyles() != null ? element.getStyles().get(CssConstants.DISPLAY) : null;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.html2pdf.attach.ITagWorker#processEnd(com.itextpdf.html2pdf.html.node.IElementNode, com.itextpdf.html2pdf.attach.ProcessorContext)
     */
    @Override
    public void processEnd(IElementNode element, ProcessorContext context) {
        flushInlineHelper();
        elements = spanWrapper.getElements();
    }

    /* (non-Javadoc)
     * @see com.itextpdf.html2pdf.attach.ITagWorker#processContent(java.lang.String, com.itextpdf.html2pdf.attach.ProcessorContext)
     */
    @Override
    public boolean processContent(String content, ProcessorContext context) {
        inlineHelper.add(content);
        return true;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.html2pdf.attach.ITagWorker#processTagChild(com.itextpdf.html2pdf.attach.ITagWorker, com.itextpdf.html2pdf.attach.ProcessorContext)
     */
    @Override
    public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
        IPropertyContainer element = childTagWorker.getElementResult();
        if (element instanceof ILeafElement) {
            flushInlineHelper();
            spanWrapper.add((ILeafElement) element);
            ownLeafElements.add(element);
            return true;
        } else if (childTagWorker instanceof SpanTagWorker) {
            flushInlineHelper();
            spanWrapper.add(((SpanTagWorker) childTagWorker).spanWrapper);
            childrenDisplayMap.putAll(((SpanTagWorker) childTagWorker).childrenDisplayMap);
            return true;
        } else if (childTagWorker.getElementResult() instanceof IBlockElement) {
            if (childTagWorker instanceof IDisplayAware) {
                String display = ((IDisplayAware) childTagWorker).getDisplay();
                childrenDisplayMap.put(childTagWorker.getElementResult(), display);
            }
            flushInlineHelper();
            spanWrapper.add((IBlockElement) childTagWorker.getElementResult());
            return true;
        }

        return false;
    }

    /**
     * Gets all the elements in the span.
     *
     * @return a list of elements
     */
    public List<IPropertyContainer> getAllElements() {
        return elements;
    }

    /**
     * Gets the span's own leaf elements.
     *
     * @return the own leaf elements
     */
    public List<IPropertyContainer> getOwnLeafElements() {
        return ownLeafElements;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.html2pdf.attach.ITagWorker#getElementResult()
     */
    @Override
    public IPropertyContainer getElementResult() {
        return null;
    }

    /* (non-Javadoc)
     * @see com.itextpdf.html2pdf.attach.impl.tags.IDisplayAware#getDisplay()
     */
    @Override
    public String getDisplay() {
        return display;
    }

    /**
     * The child shall be one from {@link #getAllElements()} list.
     */
    String getElementDisplay(IPropertyContainer child) {
        return childrenDisplayMap.get(child);
    }

    /**
     * Flushes the waiting leaf elements.
     */
    private void flushInlineHelper() {
        spanWrapper.addAll(inlineHelper.getWaitingLeaves());
        ownLeafElements.addAll(inlineHelper.getWaitingLeaves());
        inlineHelper.clearWaitingLeaves();
    }

}
