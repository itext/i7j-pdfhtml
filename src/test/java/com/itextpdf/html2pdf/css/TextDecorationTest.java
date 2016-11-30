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
package com.itextpdf.html2pdf.css;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class TextDecorationTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/TextDecorationTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/TextDecorationTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void textDecoration01Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest01.html"), new File(destinationFolder + "textDecorationTest01.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest01.pdf", sourceFolder + "cmp_textDecorationTest01.pdf", destinationFolder, "diff01_"));
    }

    @Test
    public void textDecoration02Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest02.html"), new File(destinationFolder + "textDecorationTest02.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest02.pdf", sourceFolder + "cmp_textDecorationTest02.pdf", destinationFolder, "diff02_"));
    }

    @Test
    public void textDecoration03Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest03.html"), new File(destinationFolder + "textDecorationTest03.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest03.pdf", sourceFolder + "cmp_textDecorationTest03.pdf", destinationFolder, "diff03_"));
    }

    //Text decoration property is in defaults.css for a[href], should be replaced by css.
    @Test
    public void textDecoration04Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest04.html"), new File(destinationFolder + "textDecorationTest04.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest04.pdf", sourceFolder + "cmp_textDecorationTest04.pdf", destinationFolder, "diff04_"));
    }

    //Text decoration children with none (values should be merged, none should be ignored)
    @Test
    public void textDecoration05Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest05.html"), new File(destinationFolder + "textDecorationTest05.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest05.pdf", sourceFolder + "cmp_textDecorationTest05.pdf", destinationFolder, "diff05_"));
    }

    //Text decoration with display:inline-block spans (values should be replaced)
    @Test
    public void textDecoration06Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest06.html"), new File(destinationFolder + "textDecorationTest06.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest06.pdf", sourceFolder + "cmp_textDecorationTest06.pdf", destinationFolder, "diff06_"));
    }

    //Text decoration property should be replaced by node's style attribute.
    @Test
    public void textDecoration07Test() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "textDecorationTest07.html"), new File(destinationFolder + "textDecorationTest07.pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "textDecorationTest07.pdf", sourceFolder + "cmp_textDecorationTest07.pdf", destinationFolder, "diff07_"));
    }
}
