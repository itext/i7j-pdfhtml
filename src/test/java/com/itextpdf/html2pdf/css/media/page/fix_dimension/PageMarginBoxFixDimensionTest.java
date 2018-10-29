package com.itextpdf.html2pdf.css.media.page.fix_dimension;

import com.itextpdf.html2pdf.ExtendedHtmlConversionITextTest;
import com.itextpdf.test.annotations.type.IntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;

@Category(IntegrationTest.class)
public class PageMarginBoxFixDimensionTest extends ExtendedHtmlConversionITextTest {


    public static final String sourceFolder = "./src/test/resources/com/itextpdf/html2pdf/css/media/page/fix_dimension/";
    public static final String destinationFolder = "./target/test/com/itextpdf/html2pdf/css/media/page/fix_dimension/";

    @BeforeClass
    public static void beforeClass() {
        createOrClearDestinationFolder(destinationFolder);
    }

    // Top margin box tests
    @Test
    public void topOnlyLeftFixPxTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixPx", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixPtTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixPt", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixPercentTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixPercent", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixInTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixIn", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixCmTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixCm", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixMmTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixMm", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixPcTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixPc", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixEmTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixEm", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyLeftFixExTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyLeftFixEx", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyRightFixPercentTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyRightFixPercent", sourceFolder, destinationFolder);
    }

    @Test
    public void topOnlyCenterFixPercentTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topOnlyCenterFixPercent", sourceFolder, destinationFolder);
    }

    @Test
    public void topFixCenterAndRightTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topFixCenterAndRight", sourceFolder, destinationFolder);
    }

    @Test
    public void topFixLeftAndFixCenterTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("topFixLeftAndFixCenter", sourceFolder, destinationFolder);
    }

    @Test
    public void topFixLeftAndRight() throws IOException, InterruptedException {
        convertToPdfAndCompare("topFixLeftAndRight", sourceFolder, destinationFolder);
    }

    @Test
    public void topFixLeftAndFixCenterAndFixRight() throws IOException, InterruptedException {
        convertToPdfAndCompare("topFixLeftAndFixCenterAndFixRight", sourceFolder, destinationFolder);
    }

    //Left margin box tests
    @Test
    public void leftOnlyFixTopTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftOnlyFixTop", sourceFolder, destinationFolder);
    }

    @Test
    public void leftOnlyFixCenterTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftOnlyFixCenter", sourceFolder, destinationFolder);
    }

    @Test
    public void leftOnlyFixBottomTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftOnlyFixBottom", sourceFolder, destinationFolder);
    }

    @Test
    public void leftTopAndFixCenterTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftTopAndFixCenter", sourceFolder, destinationFolder);
    }

    @Test
    public void leftTopAndFixBottomTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftTopAndFixBottom", sourceFolder, destinationFolder);
    }

    @Test
    public void leftFixCenterAndBottomTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftFixCenterAndBottom", sourceFolder, destinationFolder);
    }

    @Test
    public void leftFixTopAndFixCenterAndBottomTest() throws IOException, InterruptedException {
        convertToPdfAndCompare("leftFixTopAndFixCenterAndBottom", sourceFolder, destinationFolder);
    }
}