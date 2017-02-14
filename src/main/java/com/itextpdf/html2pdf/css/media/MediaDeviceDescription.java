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
package com.itextpdf.html2pdf.css.media;

public class MediaDeviceDescription {

    private String type;
    private int bitsPerComponent = 0;
    private int colorIndex = 0;
    // in points
    private float width;
    // in points
    private float height;
    private boolean isGrid;
    private String scan;
    private String orientation;
    private int monochrome;
    // in dpi // TODO change default units? If so, change CssUtils#parseResolution as well
    private float resolution;

    /**
     * See {@link MediaType} class constants for possible values.
     * @param type a type of the media to use.
     */
    public MediaDeviceDescription(String type) {
        this.type = type;
    }

    public MediaDeviceDescription(String type, float width, float height) {
        this(type);
        this.width = width;
        this.height = height;
    }

    public static MediaDeviceDescription createDefault() {
        return new MediaDeviceDescription(MediaType.ALL);
    }

    public String getType() {
        return type;
    }

    public int getBitsPerComponent() {
        return bitsPerComponent;
    }

    public MediaDeviceDescription setBitsPerComponent(int bitsPerComponent) {
        this.bitsPerComponent = bitsPerComponent;
        return this;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    public MediaDeviceDescription setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public MediaDeviceDescription setWidth(float width) {
        this.width = width;
        return this;
    }

    public float getHeight() {
        return height;
    }

    public MediaDeviceDescription setHeight(float height) {
        this.height = height;
        return this;
    }

    public boolean isGrid() {
        return isGrid;
    }

    public MediaDeviceDescription setGrid(boolean grid) {
        isGrid = grid;
        return this;
    }

    public String getScan() {
        return scan;
    }

    public MediaDeviceDescription setScan(String scan) {
        this.scan = scan;
        return this;
    }

    public String getOrientation() {
        return orientation;
    }

    public MediaDeviceDescription setOrientation(String orientation) {
        this.orientation = orientation;
        return this;
    }

    public int getMonochrome() {
        return monochrome;
    }

    public MediaDeviceDescription setMonochrome(int monochrome) {
        this.monochrome = monochrome;
        return this;
    }

    public float getResolution() {
        return resolution;
    }

    public MediaDeviceDescription setResolution(float resolution) {
        this.resolution = resolution;
        return this;
    }
}
