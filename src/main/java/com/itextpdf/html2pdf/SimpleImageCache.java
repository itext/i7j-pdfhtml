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

import com.itextpdf.io.image.ImageData;

import java.util.LinkedHashMap;
import java.util.Map;

class SimpleImageCache {
    private Map<String, ImageData> cache = new LinkedHashMap<>();
    private Map<String, Integer> imagesFrequency = new LinkedHashMap<>();
    private int capacity;

    SimpleImageCache() {
        this.capacity = 100;
    }

    SimpleImageCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity");
        }
        this.capacity = capacity;
    }

    void putImage(String src, ImageData imageData) {
        if (cache.containsKey(src)) {
            return;
        }
        ensureCapacity();
        cache.put(src, imageData);
    }

    ImageData getImage(String src) {
        Integer frequency = imagesFrequency.get(src);
        if (frequency != null) {
            imagesFrequency.put(src, frequency + 1);
        } else {
            imagesFrequency.put(src, 1);
        }

        return cache.get(src);
    }

    int size() {
        return cache.size();
    }

    private void ensureCapacity() {
        if (cache.size() >= capacity) {
            String mostUnpopularImg = null;
            int minFrequency = Integer.MAX_VALUE;
            for (String imgSrc : cache.keySet()) { // TODO keySet preserves order of LinkedList? and in .net?
                Integer imgFrequency = imagesFrequency.get(imgSrc);
                if (imgFrequency == null || imgFrequency < minFrequency) {
                    mostUnpopularImg = imgSrc;
                    if (imgFrequency == null) {
                        break;
                    } else {
                        minFrequency = (int)imgFrequency;
                    }
                }
            }

            cache.remove(mostUnpopularImg);
        }
    }
}
