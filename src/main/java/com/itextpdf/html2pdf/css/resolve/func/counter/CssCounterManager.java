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
package com.itextpdf.html2pdf.css.resolve.func.counter;

import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.html.node.INode;
import com.itextpdf.kernel.numbering.ArmenianNumbering;
import com.itextpdf.kernel.numbering.EnglishAlphabetNumbering;
import com.itextpdf.kernel.numbering.GeorgianNumbering;
import com.itextpdf.kernel.numbering.GreekAlphabetNumbering;
import com.itextpdf.kernel.numbering.RomanNumbering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssCounterManager {

    private static final String DISC_SYMBOL = "\u2022";
    private static final String CIRCLE_SYMBOL = "\u25e6";
    private static final String SQUARE_SYMBOL = "\u25a0";

    private static final int DEFAULT_COUNTER_VALUE = 0;
    private static final int DEFAULT_INCREMENT_VALUE = 1;
    private static final int MAX_ROMAN_NUMBER = 3999;

    private Map<INode, Map<String, Integer> > counters = new HashMap<>();

    public CssCounterManager() {
    }

    public String resolveCounter(String counterName, String listSymbolType, INode scope) {
        Map<String, Integer> scopeCounters = findSuitableScopeMap(scope, counterName);
        Integer counterValue = scopeCounters != null ? scopeCounters.get(counterName) : null;
        if (counterValue == null) {
            return null; // TODO we do that to print a logger message. We might want to reconsider and silently reset to 0 in the future
        } else {
            if (listSymbolType == null) {
                return String.valueOf(counterValue);
            } else {
                if (CssConstants.NONE.equals(listSymbolType)) {
                    return "";
                } else if (CssConstants.DISC.equals(listSymbolType)) {
                    return DISC_SYMBOL;
                } else if (CssConstants.SQUARE.equals(listSymbolType)) {
                    return SQUARE_SYMBOL;
                } else if (CssConstants.CIRCLE.equals(listSymbolType)) {
                    return CIRCLE_SYMBOL;
                } else if (CssConstants.UPPER_ALPHA.equals(listSymbolType) || CssConstants.UPPER_LATIN.equals(listSymbolType)) {
                    return EnglishAlphabetNumbering.toLatinAlphabetNumberUpperCase(counterValue);
                } else if (CssConstants.LOWER_ALPHA.equals(listSymbolType) || CssConstants.LOWER_LATIN.equals(listSymbolType)) {
                    return EnglishAlphabetNumbering.toLatinAlphabetNumberLowerCase(counterValue);
                } else if (CssConstants.LOWER_ROMAN.equals(listSymbolType)) {
                    return counterValue <= MAX_ROMAN_NUMBER ? RomanNumbering.toRomanLowerCase(counterValue) : String.valueOf(counterValue);
                } else if (CssConstants.UPPER_ROMAN.equals(listSymbolType)) {
                    return counterValue <= MAX_ROMAN_NUMBER ? RomanNumbering.toRomanUpperCase(counterValue) : String.valueOf(counterValue);
                } else if (CssConstants.DECIMAL_LEADING_ZERO.equals(listSymbolType)) {
                    return (counterValue < 10 ? "0" : "") + String.valueOf(counterValue);
                } else if (CssConstants.LOWER_GREEK.equals(listSymbolType)) {
                    return GreekAlphabetNumbering.toGreekAlphabetNumberLowerCase(counterValue);
                } else if (CssConstants.GEORGIAN.equals(listSymbolType)) {
                    return GeorgianNumbering.toGeorgian(counterValue);
                } else if (CssConstants.ARMENIAN.equals(listSymbolType)) {
                    return ArmenianNumbering.toArmenian(counterValue);
                } else {
                    return String.valueOf(counterValue); //TODO
                }
            }
        }
    }

    public String resolveCounters(String counterName, String counterSeparatorStr, String listSymbolType, INode scope) {
        INode currentScope = scope;
        List<String> resolvedCounters = null;
        while (currentScope != null) {
            INode curCounterOwnerScope = findCounterOwner(currentScope, counterName);
            if (curCounterOwnerScope != null) {
                if (resolvedCounters == null) {
                    resolvedCounters = new ArrayList<>();
                }
                resolvedCounters.add(resolveCounter(counterName, listSymbolType, curCounterOwnerScope));
            }
            currentScope = curCounterOwnerScope == null ? null : curCounterOwnerScope.parentNode();
        }
        if (resolvedCounters == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = resolvedCounters.size() - 1; i >= 0; i--) {
                sb.append(resolvedCounters.get(i));
                if (i != 0) {
                    sb.append(counterSeparatorStr);
                }
            }
            return sb.toString();
        }
    }

    public void resetCounter(String counterName, INode scope) {
        resetCounter(counterName, DEFAULT_COUNTER_VALUE, scope);
    }

    public void resetCounter(String counterName, int value, INode scope) {
        getOrCreateScopeCounterMap(scope).put(counterName, value);
    }

    public void incrementCounter(String counterName, int incrementValue, INode scope) {
        Map<String, Integer> scopeCounters = findSuitableScopeMap(scope, counterName);
        Integer curValue = scopeCounters != null ? scopeCounters.get(counterName) : null;
        if (curValue == null) {
            // If 'counter-increment' or 'content' on an element or pseudo-element refers to a counter that is not in the scope of any 'counter-reset',
            // implementations should behave as though a 'counter-reset' had reset the counter to 0 on that element or pseudo-element.
            curValue = DEFAULT_COUNTER_VALUE;
            resetCounter(counterName, curValue, scope);
            scopeCounters = getOrCreateScopeCounterMap(scope);
        }
        scopeCounters.put(counterName, curValue + incrementValue);
    }

    public void incrementCounter(String counterName, INode scope) {
        incrementCounter(counterName, DEFAULT_INCREMENT_VALUE, scope);
    }

    private Map<String, Integer> getOrCreateScopeCounterMap(INode scope) {
        Map<String, Integer> scopeCounters = counters.get(scope);
        if (scopeCounters == null) {
            scopeCounters = new HashMap<>();
            counters.put(scope, scopeCounters);
        }
        return scopeCounters;
    }

    private Map<String, Integer> findSuitableScopeMap(INode scope, String counterName) {
        INode ownerScope = findCounterOwner(scope, counterName);
        return ownerScope == null ? null : counters.get(ownerScope);
    }

    private INode findCounterOwner(INode scope, String counterName) {
        while (scope != null && (!counters.containsKey(scope) || !counters.get(scope).containsKey(counterName))) {
            // First, search through previous siblings
            boolean foundSuitableSibling = false;
            if (scope.parentNode() != null) {
                List<INode> allSiblings = scope.parentNode().childNodes();
                int indexOfCurScope = allSiblings.indexOf(scope);
                for (int i = indexOfCurScope - 1; i >= 0; i--) {
                    INode siblingScope = allSiblings.get(i);
                    if (counters.containsKey(siblingScope) && counters.get(siblingScope).containsKey(counterName)) {
                        scope = siblingScope;
                        foundSuitableSibling = true;
                        break;
                    }
                }
            }
            // If a previous sibling with matching counter was not found, move to parent scope
            if (!foundSuitableSibling) {
                scope = scope.parentNode();
            }
        }
        return scope;
    }

}
