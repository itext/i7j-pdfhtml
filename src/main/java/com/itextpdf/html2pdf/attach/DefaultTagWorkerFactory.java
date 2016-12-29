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
package com.itextpdf.html2pdf.attach;

import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.exception.TagWorkerInitializationException;
import com.itextpdf.html2pdf.html.node.IElementNode;
import com.itextpdf.html2pdf.util.TagProcessorMapping;

import java.lang.reflect.Constructor;

public class DefaultTagWorkerFactory implements ITagWorkerFactory {

    private TagProcessorMapping defaultMapping;
    private TagProcessorMapping userMapping;

    public DefaultTagWorkerFactory() {
        defaultMapping = DefaultTagWorkerMapping.getDefaultTagWorkerMapping();
        userMapping = new TagProcessorMapping();
    }

    @Override
    public ITagWorker getTagWorkerInstance(IElementNode tag, ProcessorContext context) throws TagWorkerInitializationException {
        Class<?> tagWorkerClass = getTagWorkerClass(userMapping, tag);
        if (tagWorkerClass == null) {
            tagWorkerClass = getTagWorkerClass(defaultMapping, tag);
        }
        if (tagWorkerClass == null) {
            return null;
        }
        // Use reflection to create an instance
        try {
            Constructor ctor = tagWorkerClass.getDeclaredConstructor(new Class<?>[]{IElementNode.class, ProcessorContext.class});
            ITagWorker res = (ITagWorker) ctor.newInstance(new Object[]{tag, context});
            return res;
        } catch (Exception e) {
            throw new TagWorkerInitializationException(TagWorkerInitializationException.REFLECTION_IN_TAG_WORKER_FACTORY_IMPLEMENTATION_FAILED, tagWorkerClass.getName(), tag.name());
        }
    }

    @Override
    public void registerTagWorker(String tag, Class<?> tagWorkerClass) {
        userMapping.putMapping(tag, tagWorkerClass);
    }

    @Override
    public void registerTagWorker(String tag, String display, Class<?> tagWorkerClass) {
        userMapping.putMapping(tag, display, tagWorkerClass);
    }

    private static Class<?> getTagWorkerClass(TagProcessorMapping mapping, IElementNode tag) {
        Class<?> tagWorkerClass = null;
        String display = tag.getStyles() != null ? tag.getStyles().get(CssConstants.DISPLAY) : null;
        if (display != null) {
            tagWorkerClass = mapping.getMapping(tag.name(), display);
        }
        if (tagWorkerClass == null) {
            tagWorkerClass = mapping.getMapping(tag.name());
        }
        return tagWorkerClass;
    }

}
