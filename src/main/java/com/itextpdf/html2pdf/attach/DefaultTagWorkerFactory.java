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

import com.itextpdf.html2pdf.DefaultTagMapping;
import com.itextpdf.html2pdf.exceptions.NoTagWorkerFoundException;
import com.itextpdf.html2pdf.html.node.IElementNode;

import java.lang.reflect.Constructor;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SamuelHuylebroeck on 11/30/2016.
 */
public class DefaultTagWorkerFactory implements ITagWorkerFactory {

    /**
     * Internal map to keep track of tags and associated tagworkers
     */
    private Map<String, String> map;

    public DefaultTagWorkerFactory() {
        this.map = new ConcurrentHashMap<String, String>();
        registerDefaultHtmlTagWorkers();
    }


    @Override
    public ITagWorker getTagWorkerInstance(IElementNode tag, ProcessorContext context) throws NoTagWorkerFoundException {
        //Get Tag Worker class name
        String tagWorkerClassName = map.get(tag.name());
        if (tagWorkerClassName == null) {
            //TODO:Log the fact that no instance could be found
            //throw new NoTagWorkerFoundException(NoTagWorkerFoundException.NoTagWorkerRegistered);
            return null;
        }
        //Use reflection to create an instance
        try {
            Class<?> c = Class.forName(tagWorkerClassName);
            Constructor ctor = c.getDeclaredConstructor(IElementNode.class, ProcessorContext.class);
            ctor.setAccessible(true);
            ITagWorker res = (ITagWorker) ctor.newInstance(tag, context);
            return res;
        } catch (ClassNotFoundException e) {
            throw new NoTagWorkerFoundException(NoTagWorkerFoundException.TagWorkerClassDoesNotExist);
        } catch (NoSuchMethodException e) {
            throw new NoTagWorkerFoundException(NoTagWorkerFoundException.REFLECTION_IN_TAG_WORKER_FACTORY_IMPLEMENTATION_FAILED);
        } catch (IllegalAccessException e) {
            throw new NoTagWorkerFoundException(NoTagWorkerFoundException.REFLECTION_IN_TAG_WORKER_FACTORY_IMPLEMENTATION_FAILED);
        } catch (InstantiationException e) {
            throw new NoTagWorkerFoundException(NoTagWorkerFoundException.REFLECTION_IN_TAG_WORKER_FACTORY_IMPLEMENTATION_FAILED);
        } catch (InvocationTargetException e) {
            throw new NoTagWorkerFoundException(NoTagWorkerFoundException.REFLECTION_IN_TAG_WORKER_FACTORY_IMPLEMENTATION_FAILED);
        }


    }

    @Override
    public void registerTagWorker(String tag, String nameSpace) {
        map.put(tag, nameSpace);
    }

    @Override
    public void removetagWorker(String tag) {
        map.remove(tag);
    }

    private void registerDefaultHtmlTagWorkers() {
        Map<String, String> defaultMapping = DefaultTagMapping.getDefaultTagWorkerMapping();
        for (Map.Entry<String, String> ent : defaultMapping.entrySet()) {
            map.put(ent.getKey(), ent.getValue());
        }
    }


}
