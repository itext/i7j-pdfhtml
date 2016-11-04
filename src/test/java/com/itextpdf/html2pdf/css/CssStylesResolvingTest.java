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

import com.itextpdf.html2pdf.ResourceResolver;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.html2pdf.css.resolve.DefaultCssResolver;
import com.itextpdf.html2pdf.css.resolve.ICssResolver;
import com.itextpdf.html2pdf.html.IHtmlParser;
import com.itextpdf.html2pdf.html.impl.jsoup.JsoupHtmlParser;
import com.itextpdf.html2pdf.html.node.IDocument;
import com.itextpdf.html2pdf.html.node.IElement;
import com.itextpdf.html2pdf.html.node.INode;
import com.itextpdf.test.ITextTest;
import com.itextpdf.test.annotations.type.UnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.*;

@Category(UnitTest.class)
// TODO extend ExtendedITextTest
public class CssStylesResolvingTest extends ITextTest {
    private static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/CssElementStylesResolvingTest/";

    @Test
    public void collectStylesDeclarationsTest01() throws IOException {
        test("collectStylesDeclarationsTest01.html", "html body p",
                "color: red", "text-align: center", "font-size: 15px",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void collectStylesDeclarationsTest02() throws IOException {
        test("collectStylesDeclarationsTest02.html", "html body p",
                "color: blue", "text-align: center", "font-style: italic", "font-size: 15px",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void collectStylesDeclarationsTest03() throws IOException {
        test("collectStylesDeclarationsTest03.html", "html body p",
                "color: red", "text-align: right", "font-size: 10px",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void stylesInheritanceTest01() throws IOException {
        test("stylesInheritanceTest01.html", "html body p span",
                "color: blue", "text-align: center", "font-style: italic", "font-size: 15px");
    }

    @Test
    public void stylesInheritanceTest02() throws IOException {
        test("stylesInheritanceTest02.html", "html body p span",
                "color: black", "text-align: center", "font-style: italic", "font-size: 15px");
    }

    @Test
    public void stylesInheritanceTest03() throws IOException {
        test("stylesInheritanceTest03.html", "html body p span",
                "color: green", "font-size: 12pt");
    }

    @Test
    public void stylesInheritanceTest04() throws IOException {
        test("stylesInheritanceTest04.html", "html body p span",
                "color: blue", "font-size: 12pt");
    }

    @Test
    public void stylesInheritanceTest05() throws IOException {
        test("stylesInheritanceTest05.html", "html body p span",
                "color: black", "font-size: 12pt");
    }

    @Test
    public void stylesInheritanceTest06() throws IOException {
        test("stylesInheritanceTest06.html", "html body p span",
                "margin-left: 20px",
                "margin-right: 0",
                "background-color: yellow",
                "font-size: 12pt");
    }

    @Test
    public void stylesInheritanceTest07() throws IOException {
        test("stylesInheritanceTest07.html", "html body div p span",
                "margin-left: 0",
                "padding-top: 10px",
                "background-color: yellow",
                "font-size: 12pt");
    }

    @Test
    public void stylesShorthandsTest01() throws IOException {
        test("stylesShorthandsTest01.html", "html body p",
                "border-bottom-style: dashed",
                "border-bottom-width: 5px",
                "border-left-style: dashed",
                "border-left-width: 5px",
                "border-right-style: dashed",
                "border-right-width: 5px",
                "border-top-style: dashed",
                "border-top-width: 5px",
                "border-bottom-color: red",
                "border-left-color: red",
                "border-right-color: red",
                "border-top-color: red",
                "font-size: 12pt",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void htmlStylesConvertingTest01() throws IOException {
        test("htmlStylesConvertingTest01.html", "html body b p",
                "font-weight: bold",
                "font-size: 12pt",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void htmlStylesConvertingTest02() throws IOException {
        test("htmlStylesConvertingTest01.html", "html body b i p",
                "font-weight: bold", "font-style: italic",
                "font-size: 12pt",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void htmlStylesConvertingTest03() throws IOException {
        test("htmlStylesConvertingTest01.html", "html body i p",
                "font-style: italic",
                "font-size: 12pt",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void htmlStylesConvertingTest04() throws IOException {
        test("htmlStylesConvertingTest01.html", "html body i center p",
                "font-style: italic", "text-align: center",
                "font-size: 12pt",
                "margin-bottom: 1em",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 1em");
    }

    @Test
    public void htmlStylesConvertingTest05() throws IOException {
        test("htmlStylesConvertingTest05.html", "html body table",
                "border-bottom-style: solid", "border-left-style: solid", "border-right-style: solid", "border-top-style: solid",
                "border-bottom-width: 2px", "border-left-width: 2px", "border-right-width: 2px", "border-top-width: 2px",
                "border-bottom-color: black", "border-left-color: black", "border-right-color: black", "border-top-color: black",
                "font-size: 12pt",
                "margin-bottom: 0",
                "margin-left: 0",
                "margin-right: 0",
                "margin-top: 0",
                "text-indent: 0");
    }

    @Test
    public void htmlStylesConvertingTest06() throws IOException {
        test("htmlStylesConvertingTest05.html", "html body table tbody tr",
                "background-color: yellow",
                "font-size: 12pt",
                "text-indent: 0",
                "vertical-align: middle");
    }

    @Test
    public void htmlStylesConvertingTest07() throws IOException {
        test("htmlStylesConvertingTest07.html", "html body p font span",
                "font-size: large", "font-family: verdana", "color: blue");
    }

    @Test
    public void htmlStylesConvertingTest08() throws IOException {
        test("htmlStylesConvertingTest08.html", "html body p font span",
                "font-size: large", "font-family: verdana", "color: blue");
    }

    @Test
    public void htmlStylesConvertingTest09() throws IOException {
        test("htmlStylesConvertingTest08.html", "html body div center",
                "text-align: center",
                "display: block",
                "font-size: 12pt");
    }

    @Test
    public void htmlStylesConvertingTest10() throws IOException {
        test("htmlStylesConvertingTest10.html", "html body p font span",
                "font-size: 10px", "font-family: verdana", "color: blue");
    }

    @Test
    public void htmlStylesConvertingTest11() throws IOException {
        test("htmlStylesConvertingTest10.html", "html body",
                "background-color: yellow",
                "font-size: 12pt",
                "margin-bottom: 10%",
                "margin-left: 10%",
                "margin-right: 10%",
                "margin-top: 10%");
    }

    private void resolveStylesForTree(INode node, ICssResolver cssResolver) {
        if (node instanceof IElement) {
            ((IElement)node).setStyles(cssResolver.resolveStyles((IElement)node));
        }

        for (INode child : node.childNodes()) {
            resolveStylesForTree(child, cssResolver);
        }
    }

    private void test(String fileName, String elementPath, String... expectedStyles) throws IOException {
        String filePath = sourceFolder + fileName;
        IHtmlParser parser = new JsoupHtmlParser();
        IDocument document = parser.parse(new FileInputStream(filePath), "UTF-8");
        ICssResolver cssResolver = new DefaultCssResolver(document, MediaDeviceDescription.createDefault(), new ResourceResolver(""));
        resolveStylesForTree(document, cssResolver);

        IElement element = findElement(document, elementPath);
        if (element == null) {
            Assert.fail(MessageFormat.format("Element at path \"{0}\" was not found.", elementPath));
        }
        Map<String, String> elementStyles = element.getStyles();
        Set<String> expectedStylesSet = new HashSet<>(Arrays.asList(expectedStyles));
        Set<String> actualStylesSet = stylesMapToHashSet(elementStyles);
        Assert.assertTrue(getDifferencesMessage(expectedStylesSet, actualStylesSet), setsAreEqual(expectedStylesSet, actualStylesSet));
    }

    private IElement findElement(INode root, String ancestryPath) {
        INode currElement = root;
        String[] ancestors = ancestryPath.split(" ");
        int ancestorPathIndex = 0;

        boolean foundElement = false;
        while (ancestorPathIndex < ancestors.length) {
            String ancestor = ancestors[ancestorPathIndex];
            int ancestorIndex = 0;
            int dash = ancestor.indexOf('-');
            if (dash > 0) {
                ancestorIndex = Integer.parseInt(ancestor.substring(dash + 1, ancestor.length()));
                ancestor = ancestor.substring(0, dash);
            }

            int sameNameInd = 0;
            foundElement = false;
            for (INode nextKid : currElement.childNodes()) {
                if (nextKid instanceof IElement && ((IElement) nextKid).name().equals(ancestor) && sameNameInd++ == ancestorIndex) {
                    currElement = nextKid;
                    foundElement = true;
                    break;
                }
            }
            if (foundElement) {
                ++ancestorPathIndex;
            } else {
                break;
            }
        }

        return foundElement ? (IElement) currElement : null;
    }

    private Set<String> stylesMapToHashSet(Map<String, String> stylesMap) {
        Set<String> stylesSet = new HashSet<>();
        for (Map.Entry<String, String> entry : stylesMap.entrySet()) {
            stylesSet.add(entry.getKey() + ": " +entry.getValue());
        }
        return stylesSet;
    }

    private String getDifferencesMessage(Set<String> expectedStyles, Set<String> actualStyles) {
        StringBuilder sb = new StringBuilder("Resolved styles are different from expected!");
        Set<String> expCopy = new TreeSet<>(expectedStyles);
        Set<String> actCopy = new TreeSet<>(actualStyles);
        expCopy.removeAll(actualStyles);
        actCopy.removeAll(expectedStyles);
        if (!expCopy.isEmpty()) {
            sb.append("\nExpected but not found properties:\n");
            for (String expProp : expCopy) {
                sb.append(expProp).append('\n');
            }
        }
        if (!actCopy.isEmpty()) {
            sb.append("\nNot expected but found properties:\n");
            for (String actProp : actCopy) {
                sb.append(actProp).append('\n');
            }
        }
        return sb.toString();
    }

    private boolean setsAreEqual(Set<String> expectedStyles, Set<String> actualStyles) {
        return expectedStyles.size() == actualStyles.size() && expectedStyles.containsAll(actualStyles);
    }
}
