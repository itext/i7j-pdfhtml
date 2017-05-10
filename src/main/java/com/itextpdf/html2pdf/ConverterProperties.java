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
package com.itextpdf.html2pdf;

import com.itextpdf.html2pdf.attach.ITagWorkerFactory;
import com.itextpdf.html2pdf.attach.impl.OutlinesHandler;
import com.itextpdf.html2pdf.css.apply.ICssApplierFactory;
import com.itextpdf.html2pdf.css.media.MediaDeviceDescription;
import com.itextpdf.layout.font.FontProvider;

public class ConverterProperties {

    private MediaDeviceDescription mediaDeviceDescription;
    private FontProvider fontProvider;
    private ITagWorkerFactory tagWorkerFactory;
    private ICssApplierFactory cssApplierFactory;
    private OutlinesHandler outlinesHandler;
    private String baseUri;
    private boolean createAcroForm = false;

    public ConverterProperties() {
    }

    public ConverterProperties(ConverterProperties other) {
        this.mediaDeviceDescription = other.mediaDeviceDescription;
        this.fontProvider = other.fontProvider;
        this.tagWorkerFactory = other.tagWorkerFactory;
        this.cssApplierFactory = other.cssApplierFactory;
        this.baseUri = other.baseUri;
        this.createAcroForm = other.createAcroForm;
        this.outlinesHandler = other.outlinesHandler;
    }

    public MediaDeviceDescription getMediaDeviceDescription() {
        return mediaDeviceDescription;
    }

    public ConverterProperties setMediaDeviceDescription(MediaDeviceDescription mediaDeviceDescription) {
        this.mediaDeviceDescription = mediaDeviceDescription;
        return this;
    }

    public FontProvider getFontProvider() {
        return fontProvider;
    }

    public ConverterProperties setFontProvider(FontProvider fontProvider) {
        this.fontProvider = fontProvider;
        return this;
    }

    public ITagWorkerFactory getTagWorkerFactory() {
        return tagWorkerFactory;
    }

    public ConverterProperties setTagWorkerFactory(ITagWorkerFactory tagWorkerFactory) {
        this.tagWorkerFactory = tagWorkerFactory;
        return this;
    }

    public ICssApplierFactory getCssApplierFactory() {
        return cssApplierFactory;
    }

    public ConverterProperties setCssApplierFactory(ICssApplierFactory cssApplierFactory) {
        this.cssApplierFactory = cssApplierFactory;
        return this;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public ConverterProperties setBaseUri(String baseUri) {
        this.baseUri = baseUri;
        return this;
    }

    public boolean isCreateAcroForm() {
        return createAcroForm;
    }

    public ConverterProperties setCreateAcroForm(boolean createAcroForm) {
        this.createAcroForm = createAcroForm;
        return this;
    }

    public OutlinesHandler getOutlinesHandler() {
        return outlinesHandler;
    }

    public ConverterProperties setOutlinesHandler(OutlinesHandler outlinesHandler) {
        this.outlinesHandler = outlinesHandler;
        return this;
    }
}
