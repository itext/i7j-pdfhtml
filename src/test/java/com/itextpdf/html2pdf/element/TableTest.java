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
import com.itextpdf.io.util.UrlUtil;
import com.itextpdf.kernel.utils.CompareTool;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import com.itextpdf.html2pdf.Html2PdfProductInfo;
import com.itextpdf.kernel.Version;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

@Category(IntegrationTest.class)
public class TableTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/element/TableTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/element/TableTest/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    @Test
    public void helloTableDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table");
    }

    @Test
    public void helloTableFixedDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed1");
    }

    @Test
    public void helloTableFixed2DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed2");
    }

    @Test
    public void helloTableFixed3DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed3");
    }

    @Test
    public void helloTableFixed4DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed4");
    }

    @Test
    public void helloTableFixed5DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed5");
    }

    @Test
    public void helloTableFixed6DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed6");
    }

    @Test
    public void helloTableFixed7DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed7");
    }

    @Test
    public void helloTableFixed8DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_fixed8");
    }

    @Test
    public void helloTableAutoDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto");
    }

    @Test
    public void helloTableAuto2DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto2");
    }

    @Test
    public void helloTableAuto3DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto3");
    }

    @Test
    public void helloTableAuto4DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto4");
    }

    @Test //TODO this test should be improved, incorrect widths
    public void helloTableAuto5DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto5");
    }

    @Test
    public void helloTableAuto6DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto6");
    }

    @Test
    public void helloTableAuto7DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto7");
    }

    @Test
    public void helloTableAuto8DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto8");
    }

    @Test
    public void helloTableAuto9DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto9");
    }

    @Test //TODO this test should be improved, incorrect widths. Each cell shall have its max width.
    public void helloTableAuto10DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto10");
    }

    @Test
    public void helloTableAuto11DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto11");
    }

    @Test
    public void helloTableAuto12DocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_auto12");
    }

    @Test
    public void helloTableHeaderFooterDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_header_footer");
    }

    @Test
    public void helloTableColspanDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_colspan");
    }

    @Test
    public void helloTableRowspanDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_rowspan");
    }

    @Test
    public void helloTableColspanRowspanDocumentTest() throws IOException, InterruptedException {
        runTest("hello_table_colspan_rowspan");
    }

    @Test
    public void tableCssPropsTest01() throws IOException, InterruptedException {
        runTest("tableCssPropsTest01");
    }

    @Test
    public void tableCssPropsTest02() throws IOException, InterruptedException {
        runTest("tableCssPropsTest02");
    }

    @Test
    public void tableCssPropsTest03() throws IOException, InterruptedException {
        runTest("tableCssPropsTest03");
    }

    @Test
    public void defaultTableTest() throws IOException, InterruptedException {
        runTest("defaultTable");
    }

    @Test
    public void textInTableAndRowTest() throws IOException, InterruptedException {
        runTest("textInTableAndRow");
    }

    @Test
    public void thTagTest() throws IOException, InterruptedException {
        runTest("thTag");
    }

    @Test
    public void brInTdTest() throws IOException, InterruptedException {
        runTest("brInTd");
    }

    @Test
    public void tableBorderAttributeTest01() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest01");
    }

    @Test
    public void tableBorderAttributeTest02() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest02");
    }

    @Test
    public void tableBorderAttributeTest03() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest03");
    }

    @Test
    public void tableBorderAttributeTest04() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest04");
    }

    @Test
    public void tableBorderAttributeTest05() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest05");
    }

    @Test
    public void tableBorderAttributeTest06() throws IOException, InterruptedException {
        runTest("tableBorderAttributeTest06");
    }

    @Test
    // TODO this test currently does not work like in browsers. Cell heights are treated in a very special way in browsers,
    // but they are considered when deciding whether to expand the table.
    // Due to the mechanism layout currently works, we do not pass heights from html to layout for cells because otherwise
    // the content would be clipped if it does not fit, whereas the cell height should be expanted in html in this case.
    // This is the reason why we do not know on layout level if a height was set to an html cell.
    // There is a possibility to work around this problem by extending from TableRenderer for case of thml tables.
    // but this problem seems really not that important and a very narrow use case for now.
    // For related ticket, see DEVSIX-1072
    public void tableCellHeightsExpansionTest01() throws IOException, InterruptedException {
        runTest("tableCellHeightsExpansion01");
    }

    @Test
    public void tableCellHeightsExpansionTest02() throws IOException, InterruptedException {
        runTest("tableCellHeightsExpansion02");
    }

    @Test
    public void tableMaxHeightTest01() throws IOException, InterruptedException {
        runTest("tableMaxHeight01");
    }

    @Test
    public void tableMaxHeightTest02() throws IOException, InterruptedException {
        runTest("tableMaxHeight02");
    }

    @Test
    public void colspanInHeaderFooterTest() throws IOException, InterruptedException {
        runTest("table_header_footer_colspan");
    }

    private void runTest(String testName) throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + testName + ".html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, "diff_" + testName));
    }

}
