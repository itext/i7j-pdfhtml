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
package com.itextpdf.html2pdf.element;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.ITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
// TODO extend from ExtendedITextTest and therefore check logging
public class TableTest extends ITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/element/TableTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/element/TableTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void helloTableDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_table.html"), new File(destinationFolder + "hello_table.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_table.pdf", sourceFolder + "cmp_hello_table.pdf", destinationFolder, "diff01_"));
    }

    @Test
    public void helloTableHeaderFooterDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_table_header_footer.html"), new File(destinationFolder + "hello_table_header_footer.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_table_header_footer.pdf", sourceFolder + "cmp_hello_table_header_footer.pdf", destinationFolder, "diff02_"));
    }

    @Test
    public void helloTableColspanDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_table_colspan.html"), new File(destinationFolder + "hello_table_colspan.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_table_colspan.pdf", sourceFolder + "cmp_hello_table_colspan.pdf", destinationFolder, "diff03_"));
    }

    @Test
    public void helloTableRowspanDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_table_rowspan.html"), new File(destinationFolder + "hello_table_rowspan.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_table_rowspan.pdf", sourceFolder + "cmp_hello_table_rowspan.pdf", destinationFolder, "diff04_"));
    }

    @Test
    public void helloTableColspanRowspanDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_table_colspan_rowspan.html"), new File(destinationFolder + "hello_table_colspan_rowspan.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_table_colspan_rowspan.pdf", sourceFolder + "cmp_hello_table_colspan_rowspan.pdf", destinationFolder, "diff05_"));
    }

}
