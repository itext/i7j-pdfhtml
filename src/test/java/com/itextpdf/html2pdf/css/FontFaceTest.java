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

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.LogMessageConstant;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.media.MediaType;
import com.itextpdf.html2pdf.exception.Html2PdfException;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.util.UrlUtil;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class FontFaceTest extends ExtendedITextTest {
    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/FontFaceTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/FontFaceTest/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    @Test
    public void droidSerifWebFontTest() throws IOException, InterruptedException {
        runTest("droidSerifWebFontTest");
    }

    @Test
    public void droidSerifLocalFontTest() throws IOException, InterruptedException {
        runTest("droidSerifLocalFontTest");
    }

    @Test
    public void droidSerifLocalLocalFontTest() throws IOException, InterruptedException {
        runTest("droidSerifLocalLocalFontTest");
    }

    @Test
    public void droidSerifLocalWithMediaFontTest() throws IOException, InterruptedException {
        runTest("droidSerifLocalWithMediaFontTest");
    }

    @Test
    public void droidSerifLocalWithMediaRuleFontTest() throws IOException, InterruptedException {
        runTest("droidSerifLocalWithMediaRuleFontTest");
    }

    @Test
    public void droidSerifLocalWithMediaRuleFontTest2() throws IOException, InterruptedException {
        runTest("droidSerifLocalWithMediaRuleFontTest2");
    }

    @Test
    @LogMessages(messages = {@LogMessage(messageTemplate = LogMessageConstant.UNABLE_TO_RETRIEVE_STREAM_WITH_GIVEN_BASE_URI)})
    public void fontFaceGrammarTest() throws IOException, InterruptedException {
        runTest("fontFaceGrammarTest");
    }

    @Test
    public void droidSerifLocalWithMediaRuleFontTest3() throws IOException, InterruptedException {
        String name = "droidSerifLocalWithMediaRuleFontTest";
        String htmlPath = sourceFolder + name + ".html";
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(htmlPath).getPath() + "\n");

        ConverterProperties converterProperties = new ConverterProperties()
                .setMediaDeviceDescription(new MediaDeviceDescription(MediaType.PRINT))
                .setFontProvider(new FontProvider());
        String exception = null;
        try {
            HtmlConverter.convertToPdf(htmlPath, new ByteArrayOutputStream(), converterProperties);
        } catch (Exception e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Font Provider with zero fonts shall fail",
                Html2PdfException.FontProviderContainsZeroFonts, exception);
    }

    @Test
    public void fontFaceWoffTest01() throws IOException, InterruptedException {
        runTest("fontFaceWoffTest01");
    }

    @Test
    @Ignore("DEVSIX-1368: font-face alias is not recognized correctly by font selector if it is specified in quotes")
    public void fontFaceWoffTest02() throws IOException, InterruptedException {
        runTest("fontFaceWoffTest02");
    }

    private void runTest(String name) throws IOException, InterruptedException {
        String htmlPath = sourceFolder + name + ".html";
        String pdfPath = destinationFolder + name + ".pdf";
        String cmpPdfPath = sourceFolder + "cmp_" + name + ".pdf";
        String diffPrefix = "diff_" + name + "_";
        System.out.println("html: file:///" + UrlUtil.toNormalizedURI(htmlPath).getPath() + "\n");

        ConverterProperties converterProperties = new ConverterProperties()
                .setMediaDeviceDescription(new MediaDeviceDescription(MediaType.PRINT))
                .setFontProvider(new DefaultFontProvider());
        HtmlConverter.convertToPdf(new File(htmlPath), new File(pdfPath), converterProperties);
        Assert.assertFalse("Temporary font was found.", converterProperties.getFontProvider().getFontSet().contains("droid serif"));
        Assert.assertNull(new CompareTool().compareByContent(pdfPath, cmpPdfPath, destinationFolder, diffPrefix));
    }
}
