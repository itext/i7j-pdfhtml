package com.itextpdf.html2pdf.css;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.ExtendedHtmlConversionITextTest;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.utils.CompareTool;
import com.itextpdf.test.annotations.LogMessage;
import com.itextpdf.test.annotations.LogMessages;
import com.itextpdf.test.annotations.type.IntegrationTest;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

@Category(IntegrationTest.class)
public class VisibilityTest extends ExtendedHtmlConversionITextTest {
    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/VisibilityTest/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/VisibilityTest/";

    @BeforeClass
    public static void beforeClass() {
        createDestinationFolder(destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyLastPageTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyLastPageTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyTableTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyTableTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertySvgTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertySvgTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyLinkTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyLinkTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyImagesTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyImagesTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyInFormsTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyInFormsTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyInFormFieldTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyInFormFieldTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyInFormRadioButtonTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyInFormRadioButtonTest", sourceFolder, destinationFolder);
    }

    @Test
    @LogMessages(messages = {@LogMessage
            (messageTemplate = com.itextpdf.html2pdf.LogMessageConstant.ACROFORM_NOT_SUPPORTED_FOR_SELECT)})
    //TODO update cmp-file after DEVSIX-2090 and DEVSIX-1901 done
    public void visiblePropertyInFormDropdownListTest() throws IOException, InterruptedException {
        String htmlFile = sourceFolder + "visiblePropertyInFormDropdownListTest.html";
        String outAcroPdf = destinationFolder + "visiblePropertyInFormDropdownListTest.pdf";

        ConverterProperties properties = new ConverterProperties();
        properties.setCreateAcroForm(true);
        HtmlConverter.convertToPdf(new File(htmlFile), new File(outAcroPdf), properties);
        Assert.assertNull(new CompareTool().compareByContent(outAcroPdf, sourceFolder +
                "cmp_visiblePropertyInFormDropdownListTest.pdf", destinationFolder, "diff_dropdown"));
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyInFormCheckBoxTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyInFormCheckBoxTest", sourceFolder, destinationFolder);
    }

    @Test
    //TODO update cmp-file after DEVSIX-2090 done
    public void visiblePropertyDivTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("visiblePropertyDivTest", sourceFolder, destinationFolder);
    }
}