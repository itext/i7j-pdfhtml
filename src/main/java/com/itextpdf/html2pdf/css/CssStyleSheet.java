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
import com.itextpdf.html2pdf.css.resolve.shorthand.IShorthandResolver;
import com.itextpdf.html2pdf.css.resolve.shorthand.ShorthandResolverFactory;
import com.itextpdf.html2pdf.html.node.IElementNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CssStyleSheet {

    private List<CssStatement> statements;

    public CssStyleSheet() {
        statements = new ArrayList<>();
    }

    public void addStatement(CssStatement statement) {
        statements.add(statement);
    }

    // TODO move this functionality to the parser (parse into)
    public void appendCssStyleSheet(CssStyleSheet anotherCssStyleSheet) {
        statements.addAll(anotherCssStyleSheet.statements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CssStatement statement : statements) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(statement.toString());
        }
        return sb.toString();
    }

    public List<CssStatement> getStatements() {
        return Collections.unmodifiableList(statements);
    }

    public List<CssDeclaration> getCssDeclarations(IElementNode element, MediaDeviceDescription deviceDescription) {
        List<CssRuleSet> ruleSets = getCssRuleSets(element, deviceDescription);
        Map<String, CssDeclaration> declarations = new LinkedHashMap<>();
        Collections.sort(ruleSets, new CssRuleSetComparator());
        for (CssRuleSet ruleSet : ruleSets) {
            populateDeclarationsMap(ruleSet.getNormalDeclarations(), declarations);
        }
        for (CssRuleSet ruleSet : ruleSets) {
            populateDeclarationsMap(ruleSet.getImportantDeclarations(), declarations);
        }
        return new ArrayList<>(declarations.values());
    }

    private static void populateDeclarationsMap(List<CssDeclaration> declarations, Map<String, CssDeclaration> map) {
        for (CssDeclaration declaration : declarations) {
            IShorthandResolver shorthandResolver = ShorthandResolverFactory.getShorthandResolver(declaration.getProperty());
            if (shorthandResolver == null) {
                map.put(declaration.getProperty(), declaration);
            } else {
                List<CssDeclaration> resolvedShorthandProps = shorthandResolver.resolveShorthand(declaration.getExpression());
                for (CssDeclaration resolvedProp : resolvedShorthandProps) {
                    map.put(resolvedProp.getProperty(), resolvedProp);
                }
            }
        }
    }

    private List<CssRuleSet> getCssRuleSets(IElementNode element, MediaDeviceDescription deviceDescription) {
        List<CssRuleSet> ruleSets = new ArrayList<>();
        for (CssStatement statement : statements) {
            ruleSets.addAll(statement.getCssRuleSets(element, deviceDescription));
        }
        return ruleSets;
    }

}
