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

import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.parse.CssStyleSheetParser;
import com.itextpdf.html2pdf.html.IHtmlParser;
import com.itextpdf.html2pdf.html.impl.jsoup.JsoupHtmlParser;
import com.itextpdf.html2pdf.html.impl.jsoup.node.JsoupDocument;
import com.itextpdf.html2pdf.html.impl.jsoup.node.JsoupElement;
import com.itextpdf.html2pdf.html.node.IDocument;
import com.itextpdf.html2pdf.html.node.IElement;
import com.itextpdf.test.ITextTest;
import com.itextpdf.test.annotations.type.UnitTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)
// TODO check logging by extending from ExtendedITextTest
public class CssMatchingTest extends ITextTest {

    private static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/CssMatchingTest/";

    @Test
    public void test01() throws IOException {
        String htmlFileName = sourceFolder + "html01.html";
        String cssFileName = sourceFolder + "css01.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(1, declarations.size());
        Assert.assertEquals("font-weight: bold", declarations.get(0).toString());
    }

    @Test
    public void test02() throws IOException {
        String htmlFileName = sourceFolder + "html02.html";
        String cssFileName = sourceFolder + "css02.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(2, declarations.size());
        Assert.assertEquals("font-weight: bold", declarations.get(1).toString());
        Assert.assertEquals("color: red", declarations.get(0).toString());
    }

    @Test
    public void test03() throws IOException {
        String htmlFileName = sourceFolder + "html03.html";
        String cssFileName = sourceFolder + "css03.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(2, declarations.size());
        Assert.assertEquals("font-weight: bold", declarations.get(0).toString());
        Assert.assertEquals("color: black", declarations.get(1).toString());
    }

    @Test
    public void test04() throws IOException {
        String htmlFileName = sourceFolder + "html04.html";
        String cssFileName = sourceFolder + "css04.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(1, declarations.size());
        Assert.assertEquals("font-size: 100px", declarations.get(0).toString());
    }

    @Test
    public void test05() throws IOException {
        String htmlFileName = sourceFolder + "html05.html";
        String cssFileName = sourceFolder + "css05.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(1, declarations.size());
        Assert.assertEquals("color: red", declarations.get(0).toString());
    }

    @Test
    public void test06() throws IOException {
        String htmlFileName = sourceFolder + "html06.html";
        String cssFileName = sourceFolder + "css06.css";
        IHtmlParser htmlParser = new JsoupHtmlParser();
        IDocument document = htmlParser.parse(new FileInputStream(htmlFileName), "UTF-8");
        CssStyleSheet css = CssStyleSheetParser.parse(new FileInputStream(cssFileName));
        MediaDeviceDescription deviceDescription = new MediaDeviceDescription("all");
        IElement element = new JsoupElement(((JsoupDocument)document).getDocument().getElementsByTag("p").first());
        List<CssDeclaration> declarations = css.getCssDeclarations(element, deviceDescription);
        Assert.assertEquals(1, declarations.size());
        Assert.assertEquals("color: blue", declarations.get(0).toString());
    }

}
