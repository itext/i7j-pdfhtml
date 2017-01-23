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
import com.itextpdf.io.LogMessageConstant;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.ExtendedITextTest;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTest.class)
public class HeightTest extends ExtendedITextTest {

    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/HeightTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/HeightTest/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.CLIP_ELEMENT, count = 2)
    })
    @Test
    public void heightTest01() throws IOException, InterruptedException {
        String testName = "heightTest01";
        String diffPrefix = "diff01_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    public void heightTest02() throws IOException, InterruptedException {
        String testName = "heightTest02";
        String diffPrefix = "diff02_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }
    
    @Test
    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.CLIP_ELEMENT, count = 2)
    })
    public void heightTest03() throws IOException, InterruptedException {
        String testName = "heightTest03";
        String diffPrefix = "diff03_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    public void heightTest04() throws IOException, InterruptedException {
        String testName = "heightTest04";
        String diffPrefix = "diff04_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    @Ignore("little fixed parent height of kid with lots of content results in empty page")
    public void heightTest05() throws IOException, InterruptedException {
        String testName = "heightTest05";
        String diffPrefix = "diff05_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    @Ignore("DEVSIX-1007")
    public void heightTest06() throws IOException, InterruptedException {
        String testName = "heightTest06";
        String diffPrefix = "diff06_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.CLIP_ELEMENT, count = 2)
    })
    public void heightWithCollapsingMarginsTest01() throws IOException, InterruptedException {
        String testName = "heightWithCollapsingMarginsTest01";
        String diffPrefix = "diffMargins01_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    public void heightWithCollapsingMarginsTest03() throws IOException, InterruptedException {
        String testName = "heightWithCollapsingMarginsTest03";
        String diffPrefix = "diffMargins03_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.CLIP_ELEMENT, count = 2)
    })
    public void heightWithCollapsingMarginsTest04() throws IOException, InterruptedException {
        String testName = "heightWithCollapsingMarginsTest04";
        String diffPrefix = "diffMargins04_";

        // second paragraph should not be drawn in pdf, as it doesn't fit with it's margins
        
        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

    @Test
    @LogMessages(messages = {
            @LogMessage(messageTemplate = LogMessageConstant.CLIP_ELEMENT, count = 1)
    })
    public void heightWithCollapsingMarginsTest05() throws IOException, InterruptedException {
        String testName = "heightWithCollapsingMarginsTest05";
        String diffPrefix = "diffMargins05_";

        HtmlConverter.convertToPdf(new File(sourceFolder + testName + ".html"), new File(destinationFolder + testName + ".pdf"));
        Assert.assertNull(new CompareTool().compareByContent(destinationFolder + testName + ".pdf", sourceFolder + "cmp_" + testName + ".pdf", destinationFolder, diffPrefix));
    }

}
