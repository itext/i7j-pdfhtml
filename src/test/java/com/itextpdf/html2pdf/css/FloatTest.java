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
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.media.MediaType;
import com.itextpdf.html2pdf.css.util.CssUtils;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class FloatTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/FloatTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/FloatTest/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    @Test
    public void float01Test() throws IOException, InterruptedException {
        runTest("float01Test", "diff01_");
    }

    @Test
    public void float02Test() throws IOException, InterruptedException {
        runTest("float02Test", "diff02_");
    }

    @Test
    public void float03Test() throws IOException, InterruptedException {
        runTest("float03Test", "diff03_");
    }

    @Test
    public void float04Test() throws IOException, InterruptedException {
        runTest("float04Test", "diff04_");
    }

    @Test
    public void float05Test() throws IOException, InterruptedException {
        runTest("float05Test", "diff05_");
    }

    @Test
    public void float06Test() throws IOException, InterruptedException {
        runTest("float06Test", "diff07_");
    }

    @Test
    public void float07Test() throws IOException, InterruptedException {
        runTest("float07Test", "diff08_");
    }

    @Test
    public void float08Test() throws IOException, InterruptedException {
        runTest("float08Test", "diff27_");
    }

    @Test
    public void float09Test() throws IOException, InterruptedException {
        runTest("float09Test", "diff09_");
    }

    @Test
    public void float10Test() throws IOException, InterruptedException {
        runTest("float10Test", "diff10_");
    }

    @Test
    public void float11Test() throws IOException, InterruptedException {
        runTest("float11Test", "diff11_");
    }

    @Test
    public void float12Test() throws IOException, InterruptedException {
        runTest("float12Test", "diff12_");
    }

    @Test
    public void float13Test() throws IOException, InterruptedException {
        runTest("float13Test", "diff13_");
    }

    @Test
    @Ignore("In this test css property overflow: hidden is ignored by iText. This leads to invalid results. Perhaps, one day it will be fixed")
    public void float14Test() throws IOException, InterruptedException {
        runTest("float14Test", "diff14_");
    }

    @Test
    public void float15Test() throws IOException, InterruptedException {
        runTest("float15Test", "diff15_");
    }

    @Test
    public void float16Test() throws IOException, InterruptedException {
        // TODO at the moment we always wrap inline text in paragraphs, thus when we process next floating element it's always on next line
        // see also float50Test and float51Test

        // TODO as a possible solution in future we might consider adding floats blocks as inlines-blocks in inline helper
        runTest("float16Test", "diff16_");
    }

    @Test
    public void float17Test() throws IOException, InterruptedException {
        runTest("float17Test", "diff17_");
    }

    @Test
    public void float19Test() throws IOException, InterruptedException {
        runTest("float19Test", "diff19_");
    }

    @Test
    public void float20Test() throws IOException, InterruptedException {
        runTest("float20Test", "diff20_");
    }

    @Test
    public void float21Test() throws IOException, InterruptedException {
        runTest("float21Test", "diff21_");
    }

    @Test
    public void float22Test() throws IOException, InterruptedException {
        runTest("float22Test", "diff22_");
    }

    @Test
    public void float23Test() throws IOException, InterruptedException {
        runTest("float23Test", "diff23_");
    }

    @Test
    public void float24Test() throws IOException, InterruptedException {
        runTest("float24Test", "diff24_");
    }

    @Test
    public void float25Test() throws IOException, InterruptedException {
        // TODO at the moment we always wrap inline text in paragraphs, thus when we process next floating element it's always on next line
        // see also float50Test and float51Test
        runTest("float25Test", "diff25_");
    }

    @Test
    public void float26Test() throws IOException, InterruptedException {
        runTest("float26Test", "diff26_");
    }

    @Test
    public void float27Test() throws IOException, InterruptedException {
        runTest("float27Test", "diff27_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float28Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("float28Test", "diff28_");
    }

    @Test
    public void float29Test() throws IOException, InterruptedException {
        runTest("float29Test", "diff29_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float30Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269 and DEVSIX-1270
        runTest("float30Test", "diff30_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float31Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269 and DEVSIX-1270
        runTest("float31Test", "diff31_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float32Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("float32Test", "diff32_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float33Test() throws IOException, InterruptedException {
        runTest("float33Test", "diff33_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float34Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("float34Test", "diff34_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float35Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("float35Test", "diff35_");
    }

    @Test
    public void float36Test() throws IOException, InterruptedException {
        // TODO at the moment we always wrap inline text in paragraphs, thus when we process next floating element it's always on next line
        // see also float50Test and float51Test
        runTest("float36Test", "diff36_");
    }

    @Test
    public void float37Test() throws IOException, InterruptedException {
        runTest("float37Test", "diff37_");
    }

    @Test
    public void float38Test() throws IOException, InterruptedException {
        runTest("float38Test", "diff38_");
    }

    @Test
    public void float39Test() throws IOException, InterruptedException {
        runTest("float39Test", "diff39_");
    }

    @Test
    @Ignore("DEVSIX-1269")
    public void float40Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("float40Test", "diff40_");
    }

    @Test
    public void float41Test() throws IOException, InterruptedException {
        runTest("float41Test", "diff41_");
    }

    @Test
    public void float42Test() throws IOException, InterruptedException {
        runTest("float42Test", "diff42_");
    }

    @Test
    public void float43Test() throws IOException, InterruptedException {
        runTest("float43Test", "diff43_");
    }

    @Test
    @Ignore("DEVSIX-1268")
    public void float44Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1268
        runTest("float44Test", "diff44_");
    }

    @Test
    @Ignore("DEVSIX-1268")
    public void float45Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1268
        runTest("float45Test", "diff45_");
    }

    @Test
    public void float46Test() throws IOException, InterruptedException {
        runTest("float46Test", "diff46_");
    }

    @Test
    public void float47Test() throws IOException, InterruptedException {
        runTest("float47Test", "diff47_");
    }

    @Test
    public void float48Test() throws IOException, InterruptedException {
        runTest("float48Test", "diff48_");
    }

    @Test
    public void float49Test() throws IOException, InterruptedException {
        runTest("float49Test", "diff49_");
    }

    @Test
    public void float50Test() throws IOException, InterruptedException {
        // TODO at the moment we always wrap inline text in paragraphs, thus we process this test exactly like in float51Test
        runTest("float50Test", "diff50_");
    }

    @Test
    public void float51Test() throws IOException, InterruptedException {
        runTest("float51Test", "diff51_");
    }

    @Test
    public void float54Test() throws IOException, InterruptedException {
        runTest("float54Test", "diff54_");
    }

    @Test
    public void floatAndTables01Test() throws IOException, InterruptedException {
        runTest("floatAndTables01Test", "diffTables01_");
    }

    @Test
    public void floatAndTables02Test() throws IOException, InterruptedException {
        runTest("floatAndTables02Test", "diffTables02_");
    }

    @Test
    public void floatAndTables03Test() throws IOException, InterruptedException {
        runTest("floatAndTables03Test", "diffTables03_");
    }

    @Test
    public void floatAndTables04Test() throws IOException, InterruptedException {
        runTest("floatAndTables04Test", "diffTables04_");
    }

    @Test
    public void floatAndTables05Test() throws IOException, InterruptedException {
        runTest("floatAndTables05Test", "diffTables05_");
    }

    @Test
    public void floatAndTables06Test() throws IOException, InterruptedException {
        runTest("floatAndTables06Test", "diffTables06_");
    }

    @Test
    public void floatAndTables07Test() throws IOException, InterruptedException {
        runTest("floatAndTables07Test", "diffTables07_");
    }

    @Test
    public void floatAndTables08Test() throws IOException, InterruptedException {
        runTest("floatAndTables08Test", "diffTables08_");
    }

    @Test
    public void floatAndTables09Test() throws IOException, InterruptedException {
        runTest("floatAndTables09Test", "diffTables09_");
    }

    @Test
    public void floatAndTables10Test() throws IOException, InterruptedException {
        runTest("floatAndTables10Test", "diffTables10_");
    }

    @Test
    public void floatImage01Test() throws IOException, InterruptedException {
        runTest("floatImage01Test", "diffImages01_");
    }

    @Test
    public void floatImage02Test() throws IOException, InterruptedException {
        runTest("floatImage02Test", "diffImages02_");
    }

    @Test
    public void floatImage03Test() throws IOException, InterruptedException {
        runTest("floatImage03Test", "diffImages03_");
    }

    @Test
    public void floatImage04Test() throws IOException, InterruptedException {
        // TODO word splitting logic working not entirely correctly
        runTest("floatImage04Test", "diffImages04_");
    }

    @Test
    public void floatImage05Test() throws IOException, InterruptedException {
        runTest("floatImage05Test", "diffImages05_");
    }

    @Test
    public void floatImage06Test() throws IOException, InterruptedException {
        runTest("floatImage06Test", "diffImages06_");
    }

    @Test
    public void floatImage07Test() throws IOException, InterruptedException {
        // TODO word splitting logic working not entirely correctly
        runTest("floatImage07Test", "diffImages07_");
    }

    @Test
    public void floatImage08Test() throws IOException, InterruptedException {
        runTest("floatImage08Test", "diffImages08_");
    }

    @Test
    public void floatImage09Test() throws IOException, InterruptedException {
        runTest("floatImage09Test", "diffImages09_");
    }

    @Test
    public void floatImage10Test() throws IOException, InterruptedException {
        // TODO we don't apply leading on floats, this somewhat noticeable when huge line-height in html is used
        runTest("floatImage10Test", "diffImages10_");
    }

    @Test
    public void floatImage11Test() throws IOException, InterruptedException {
        runTest("floatImage11Test", "diffImages11_");
    }

    @Test
    public void floatImage12Test() throws IOException, InterruptedException {
        // TODO we don't apply leading on floats, this somewhat noticeable when huge line-height in html is used
        runTest("floatImage12Test", "diffImages12_");
    }

    @Test
    public void floatImage13Test() throws IOException, InterruptedException {
        // TODO we don't apply leading on floats, this somewhat noticeable when huge line-height in html is used
        runTest("floatImage13Test", "diffImages13_");
    }

    @Test
    public void floatInline01Test() throws IOException, InterruptedException {
        runTest("floatInline01Test", "diffImages01_");
    }

    @Test
    public void floatInline02Test() throws IOException, InterruptedException {
        runTest("floatInline02Test", "diffImages02_");
    }

    @Test
    public void floatInline03Test() throws IOException, InterruptedException {
        runTest("floatInline03Test", "diffImages03_");
    }

    @Test
    public void floatInline04Test() throws IOException, InterruptedException {
        runTest("floatInline04Test", "diffImages04_");
    }

    @Test
    public void floatInline05Test() throws IOException, InterruptedException {
        runTest("floatInline05Test", "diffImages05_");
    }

    @Test
    public void floatInline06Test() throws IOException, InterruptedException {
        runTest("floatInline06Test", "diffImages06_");
    }

    @Test
    public void floatInline07Test() throws IOException, InterruptedException {
        runTest("floatInline07Test", "diffImages07_");
    }

    @Test
    public void floatInline08Test() throws IOException, InterruptedException {
        runTest("floatInline08Test", "diffImages08_");
    }

    @Test
    public void floatInline09Test() throws IOException, InterruptedException {
        // TODO DEVSIX-1269
        runTest("floatInline09Test", "diffImages09_");
    }

    @Test
    public void floatInline10Test() throws IOException, InterruptedException {
        runTest("floatInline10Test", "diffImages10_");
    }

    @Test
    public void floatInline11Test() throws IOException, InterruptedException {
        runTest("floatInline11Test", "diffImages11_");
    }

    @Test
    public void floatInline12Test() throws IOException, InterruptedException {
        runTest("floatInline12Test", "diffImages12_");
    }

    @Test
    public void floatInline13Test() throws IOException, InterruptedException {
        runTest("floatInline13Test", "diffImages13_");
    }

    @Test
    public void floatInline14Test() throws IOException, InterruptedException {
        runTest("floatInline14Test", "diffImages14_");
    }

    @Test
    public void floatInline15Test() throws IOException, InterruptedException {
        // TODO another difference concerning nested spans processing
        runTest("floatInline15Test", "diffImages15_");
    }

    @Test
    public void floatInline16Test() throws IOException, InterruptedException {
        runTest("floatInline16Test", "diffImages16_");
    }

    @Test
    public void floatInline17Test() throws IOException, InterruptedException {
        runTest("floatInline17Test", "diffImages17_");
    }

    private void runTest(String testName, String diff) throws IOException, InterruptedException {
        String htmlName = sourceFolder + testName + ".html";
        String outFileName = destinationFolder + testName + ".pdf";
        String cmpFileName = sourceFolder + "cmp_" + testName + ".pdf";
        HtmlConverter.convertToPdf(new File(htmlName), new File(outFileName));
        Assert.assertNull(new CompareTool().compareByContent(outFileName, cmpFileName, destinationFolder, diff));
    }

    @Test
    public void responsiveIText() throws IOException, InterruptedException {
        PageSize[] pageSizes = {
                null,
                new PageSize(PageSize.A3.getHeight(), PageSize.A4.getHeight()),
                new PageSize(760,PageSize.A4.getHeight()),
                new PageSize(PageSize.A5.getWidth(), PageSize.A4.getHeight())
        };

        String htmlSource = sourceFolder + "responsiveIText.html";

        for (PageSize pageSize : pageSizes) {
            Float pxWidth = pageSize != null ? CssUtils.parseAbsoluteLength(String.valueOf(pageSize.getWidth())) : null;
            String outName = "responsiveIText" + (pxWidth != null ? "_" + pxWidth : "") + ".pdf";
            PdfWriter writer = new PdfWriter(destinationFolder + outName);
            PdfDocument pdfDoc = new PdfDocument(writer);
            ConverterProperties converterProperties = new ConverterProperties();
            if (pageSize != null) {
                pdfDoc.setDefaultPageSize(pageSize);
                MediaDeviceDescription mediaDescription = new MediaDeviceDescription(MediaType.SCREEN);
                mediaDescription.setWidth(pxWidth);
                converterProperties.setMediaDeviceDescription(mediaDescription);
            }
            HtmlConverter.convertToPdf(new FileInputStream(htmlSource), pdfDoc, converterProperties);
            pdfDoc.close();
        }

        for (PageSize pageSize : pageSizes) {
            Float pxWidth = pageSize != null ? CssUtils.parseAbsoluteLength(String.valueOf(pageSize.getWidth())) : null;
            String outName = "responsiveIText" + (pxWidth != null ? "_" + pxWidth : "") + ".pdf";
            String cmpName = "cmp_" + outName;

            Assert.assertNull(new CompareTool().compareByContent(destinationFolder + outName, sourceFolder + cmpName, destinationFolder, "diffResponsive_"));
        }
    }
}
