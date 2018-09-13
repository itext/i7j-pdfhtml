/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2018 iText Group NV
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
package com.itextpdf.html2pdf;

import com.itextpdf.io.util.UrlUtil;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class Html2PdfTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/Html2PdfTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/Html2PdfTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    public void helloWorldParagraphTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_paragraph.html"), new File(destinationFolder + "hello_paragraph.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_paragraph.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_paragraph.pdf", sourceFolder + "cmp_hello_paragraph.pdf", destinationFolder, "diff01_"));
    }

    @Test
    // TODO DEVSIX-1124
    public void helloParagraphTableTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_paragraph_table.html"), new File(destinationFolder + "hello_paragraph_table.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_paragraph_table.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_paragraph_table.pdf", sourceFolder + "cmp_hello_paragraph_table.pdf", destinationFolder, "diff02_"));
    }

    @Test
    public void helloMalformedDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_malformed.html"), new File(destinationFolder + "hello_malformed.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_malformed.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_malformed.pdf", sourceFolder + "cmp_hello_malformed.pdf", destinationFolder, "diff03_"));
    }

    @Test
    public void helloParagraphJunkSpacesDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_paragraph_junk_spaces.html"), new File(destinationFolder + "hello_paragraph_junk_spaces.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_paragraph_junk_spaces.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_paragraph_junk_spaces.pdf", sourceFolder + "cmp_hello_paragraph_junk_spaces.pdf", destinationFolder, "diff03_"));
    }


    @Test
    // TODO DEVSIX-1124
    public void helloParagraphNestedInTableDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_paragraph_nested_in_table.html"), new File(destinationFolder + "hello_paragraph_nested_in_table.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_paragraph_nested_in_table.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_paragraph_nested_in_table.pdf", sourceFolder + "cmp_hello_paragraph_nested_in_table.pdf", destinationFolder, "diff03_"));
    }

    @Test
    public void helloParagraphWithSpansDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_paragraph_with_span.html"), new File(destinationFolder + "hello_paragraph_with_span.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_paragraph_with_span.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_paragraph_with_span.pdf", sourceFolder + "cmp_hello_paragraph_with_span.pdf", destinationFolder, "diff03_"));
    }

    @Test
    public void helloDivDocumentTest() throws IOException, InterruptedException {
        HtmlConverter.convertToPdf(new File(sourceFolder + "hello_div.html"), new File(destinationFolder + "hello_div.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "hello_div.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "hello_div.pdf", sourceFolder + "cmp_hello_div.pdf", destinationFolder, "diff03_"));
    }

    @Test
    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.WORKER_UNABLE_TO_PROCESS_OTHER_WORKER)
    })
    public void aBlockInPTagTest() throws IOException, InterruptedException {
        //TODO after DEVSIX-2002 fix change cmp_ file and remove expected LogMessage annotation
        HtmlConverter.convertToPdf(new File(sourceFolder + "aBlockInPTag.html"), new File(destinationFolder + "aBlockInPTag.pdf"));
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(sourceFolder + "aBlockInPTag.html").getPath() + "\n");
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + "aBlockInPTag.pdf", sourceFolder + "cmp_aBlockInPTag.pdf", destinationFolder, "diff03_"));
    }

}
