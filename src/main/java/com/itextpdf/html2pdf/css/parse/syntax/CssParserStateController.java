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
package com.itextpdf.html2pdf.css.parse.syntax;

import com.itextpdf.html2pdf.css.CssNestedAtRule;
import com.itextpdf.html2pdf.css.CssRuleSet;
import com.itextpdf.html2pdf.css.CssSemicolonAtRule;
import com.itextpdf.html2pdf.css.CssStyleSheet;
import com.itextpdf.html2pdf.css.parse.CssRuleSetParser;
import java.util.List;
import java.util.Stack;

public final class CssParserStateController {

    private IParserState currentState;
    // Non-comment
    private IParserState previousActiveState;
    private StringBuilder buffer = new StringBuilder();

    private String currentSelector;
    private CssStyleSheet styleSheet;

    private Stack<CssNestedAtRule> nestedAtRules;

    private final IParserState commentStartState;
    private final IParserState commendEndState;
    private final IParserState commendInnerState;
    private final IParserState unknownState;
    private final IParserState ruleState;
    private final IParserState propertiesState;
    private final IParserState atRuleBlockState;

    public CssParserStateController() {
        styleSheet = new CssStyleSheet();
        nestedAtRules = new Stack<>();

        commentStartState = new CommentStartState(this);
        commendEndState = new CommentEndState(this);
        commendInnerState = new CommentInnerState(this);
        unknownState = new UnknownState(this);
        ruleState = new RuleState(this);
        propertiesState = new PropertiesState(this);
        atRuleBlockState = new AtRuleBlockState(this);

        currentState = unknownState;
    }

    public void process(char ch) {
        currentState.process(ch);
    }

    public CssStyleSheet getParsingResult() {
        return styleSheet;
    }

    void appendToBuffer(char ch) {
        buffer.append(ch);
    }

    void enterPreviousActiveState() {
        setState(previousActiveState);
    }

    void enterCommentStartState() {
        saveActiveState();
        setState(commentStartState);
    }

    void enterCommentEndState() {
        setState(commendEndState);
    }

    void enterCommentInnerState() {
        setState(commendInnerState);
    }

    void enterRuleState() {
        setState(ruleState);
    }

    void enterUnknownStateIfNestedBlocksFinished() {
        if (nestedAtRules.empty()) {
            setState(unknownState);
        } else {
            setState(atRuleBlockState);
        }
    }

    void enterUnknownState() {
        setState(unknownState);
    }

    void enterAtRuleBlockState() {
        setState(atRuleBlockState);
    }

    void enterPropertiesState() {
        setState(propertiesState);
    }

    void storeCurrentSelector() {
        currentSelector = buffer.toString();
        buffer.setLength(0);
    }

    void storeCurrentProperties() {
        processProperties(currentSelector, buffer.toString());
        currentSelector = null;
        buffer.setLength(0);
    }

    void storeSemicolonAtRule() {
        processSemicolonAtRule(buffer.toString());
        buffer.setLength(0);
    }

    void finishAtRuleBlock() {
        CssNestedAtRule atRule = nestedAtRules.pop();
        processFinishedAtRuleBlock(atRule);
        buffer.setLength(0);
    }

    void pushBlockPrecedingAtRule() {
        nestedAtRules.add(new CssNestedAtRule(buffer.toString()));
        buffer.setLength(0);
    }

    private void saveActiveState() {
        previousActiveState = currentState;
    }

    private void setState(IParserState state) {
        currentState = state;
    }

    private void processProperties(String selector, String properties) {
        List<CssRuleSet> ruleSets = CssRuleSetParser.parseRuleSet(selector, properties);
        for (CssRuleSet ruleSet : ruleSets) {
            if (nestedAtRules.empty()) {
                styleSheet.addStatement(ruleSet);
            } else {
                nestedAtRules.peek().addStatementToBody(ruleSet);
            }
        }
    }

    private void processSemicolonAtRule(String ruleStr) {
        CssSemicolonAtRule atRule = new CssSemicolonAtRule(ruleStr);
        styleSheet.addStatement(atRule);
    }

    private void processFinishedAtRuleBlock(CssNestedAtRule atRule) {
        if (!nestedAtRules.empty()) {
            nestedAtRules.peek().addStatementToBody(atRule);
        } else {
            styleSheet.addStatement(atRule);
        }
    }
}
