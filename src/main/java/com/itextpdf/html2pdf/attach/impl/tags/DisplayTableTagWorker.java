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
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.layout.IPropertyContainer;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.ILeafElement;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

import java.util.ArrayList;
import java.util.List;

public class DisplayTableTagWorker implements ITagWorker {

    private List<List<Cell>> columns = new ArrayList<>();
    private IPropertyContainer table;
    private WaitingInlineElementsHelper inlineHelper;

    public DisplayTableTagWorker(IElementNode element, ProcessorContext context) {
        columns.add(new ArrayList<Cell>());
        inlineHelper = new WaitingInlineElementsHelper(element.getStyles().get(CssConstants.WHITE_SPACE), element.getStyles().get(CssConstants.TEXT_TRANSFORM));
    }

    @Override
    public void processEnd(IElementNode element, ProcessorContext context) {
        flushInlineElements();
        int maxRowSize = 0;
        for (int i = 0; i < columns.size(); i++) {
            maxRowSize = Math.max(maxRowSize, columns.get(i).size());
        }
        if (maxRowSize == 0) {
            // Workaround because there are problems with empty table
            table = new Div();
        } else {
            float[] columnsWidths = new float[columns.size()];
            for (int i = 0; i < columnsWidths.length; i++) {
                columnsWidths[i] = -1;
            }
            table = new Table(UnitValue.createPointArray(columnsWidths));
            for (int i = 0; i < maxRowSize; i++) {
                for (int j = 0; j < columns.size(); j++) {
                    Cell cell = i < columns.get(j).size() ? columns.get(j).get(i) : null;
                    if (cell == null) {
                        cell = createWrapperCell();
                    }
                    ((Table)table).addCell(cell);
                }
            }
        }
    }

    @Override
    public boolean processContent(String content, ProcessorContext context) {
        inlineHelper.add(content);
        return true;
    }

    @Override
    public boolean processTagChild(ITagWorker childTagWorker, ProcessorContext context) {
        boolean displayTableCell = childTagWorker instanceof IDisplayAware && CssConstants.TABLE_CELL.equals(((IDisplayAware) childTagWorker).getDisplay());
        if (childTagWorker.getElementResult() instanceof IBlockElement) {
            IBlockElement childResult = (IBlockElement) childTagWorker.getElementResult();
            Cell curCell = childResult instanceof Cell ? (Cell)childResult : createWrapperCell().add(childResult);
            processCell(curCell, displayTableCell);
            return true;
        } else if (childTagWorker.getElementResult() instanceof ILeafElement) {
            inlineHelper.add((ILeafElement) childTagWorker.getElementResult());
            return true;
        } else if (childTagWorker instanceof SpanTagWorker) {
            if (displayTableCell) {
                flushInlineElements();
            }
            boolean allChildrenProcessed = true;
            for (IPropertyContainer propertyContainer : ((SpanTagWorker) childTagWorker).getAllElements()) {
                if (propertyContainer instanceof ILeafElement) {
                    inlineHelper.add((ILeafElement) propertyContainer);
                } else {
                    allChildrenProcessed = false;
                }
            }
            if (displayTableCell) {
                Cell cell = createWrapperCell();
                inlineHelper.flushHangingLeaves(cell);
                processCell(cell, displayTableCell);
            }
            return allChildrenProcessed;
        }
        return false;
    }

    @Override
    public IPropertyContainer getElementResult() {
        return table;
    }

    private void processCell(Cell cell, boolean displayTableCell) {
        flushInlineElements();
        if (displayTableCell) {
            List<Cell> curCol = new ArrayList<>();
            curCol.add(cell);
            if (columns.get(columns.size() - 1).size() == 0) {
                columns.remove(columns.size() - 1);
            }
            columns.add(curCol);
            columns.add(new ArrayList<Cell>());
        } else {
            columns.get(columns.size() - 1).add(cell);
        }
    }

    private void flushInlineElements() {
        if (inlineHelper.getSanitizedWaitingLeaves().size() > 0) {
            Cell waitingLavesCell = createWrapperCell();
            inlineHelper.flushHangingLeaves(waitingLavesCell);
            processCell(waitingLavesCell, false);
        }
    }

    private Cell createWrapperCell() {
        return new Cell().setBorder(Border.NO_BORDER).setPadding(0);
    }
}
