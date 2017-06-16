/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2017 iText Group NV
    Authors: Bruno Lowagie, Paulo Soares, et al.
    
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class containing possible CSS property keys and values, pseudo element keys,
 * units of measurement, and so on.
 */
public class CssConstants {

    // properties
	
    /** The Constant ALIGN. */
    public static final String ALIGN = "align";
    
    /** The Constant BACKGROUND. */
    public static final String BACKGROUND = "background";
    
    /** The Constant BACKGROUND_ATTACHMENT. */
    public static final String BACKGROUND_ATTACHMENT = "background-attachment";
    
    /** The Constant BACKGROUND_BLEND_MODE. */
    public static final String BACKGROUND_BLEND_MODE = "background-blend-mode";
    
    /** The Constant BACKGROUND_CLIP. */
    public static final String BACKGROUND_CLIP = "background-clip";
    
    /** The Constant BACKGROUND_COLOR. */
    public static final String BACKGROUND_COLOR = "background-color";
    
    /** The Constant BACKGROUND_IMAGE. */
    public static final String BACKGROUND_IMAGE = "background-image";
    
    /** The Constant BACKGROUND_ORIGIN. */
    public static final String BACKGROUND_ORIGIN = "background-origin";
    
    /** The Constant BACKGROUND_POSITION. */
    public static final String BACKGROUND_POSITION = "background-position";
    
    /** The Constant BACKGROUND_REPEAT. */
    public static final String BACKGROUND_REPEAT = "background-repeat";
    
    /** The Constant BACKGROUND_SIZE. */
    public static final String BACKGROUND_SIZE = "background-size";
    
    /** The Constant BLEED. */
    public static final String BLEED = "bleed";
    
    /** The Constant BORDER. */
    public static final String BORDER = "border";
    
    /** The Constant BORDER_BOTTOM. */
    public static final String BORDER_BOTTOM = "border-bottom";
    
    /** The Constant BORDER_BOTTOM_COLOR. */
    public static final String BORDER_BOTTOM_COLOR = "border-bottom-color";
    
    /** The Constant BORDER_BOTTOM_STYLE. */
    public static final String BORDER_BOTTOM_STYLE = "border-bottom-style";
    
    /** The Constant BORDER_BOTTOM_WIDTH. */
    public static final String BORDER_BOTTOM_WIDTH = "border-bottom-width";
    
    /** The Constant BORDER_COLLAPSE. */
    public static final String BORDER_COLLAPSE = "border-collapse";
    
    /** The Constant BORDER_COLOR. */
    public static final String BORDER_COLOR = "border-color";
    
    /** The Constant BORDER_IMAGE. */
    public static final String BORDER_IMAGE = "border-image";
    
    /** The Constant BORDER_LEFT. */
    public static final String BORDER_LEFT = "border-left";
    
    /** The Constant BORDER_LEFT_COLOR. */
    public static final String BORDER_LEFT_COLOR = "border-left-color";
    
    /** The Constant BORDER_LEFT_STYLE. */
    public static final String BORDER_LEFT_STYLE = "border-left-style";
    
    /** The Constant BORDER_LEFT_WIDTH. */
    public static final String BORDER_LEFT_WIDTH = "border-left-width";
    
    /** The Constant BORDER_RADIUS. */
    public static final String BORDER_RADIUS = "border-radius";
    
    /** The Constant BORDER_RIGHT. */
    public static final String BORDER_RIGHT = "border-right";
    
    /** The Constant BORDER_RIGHT_COLOR. */
    public static final String BORDER_RIGHT_COLOR = "border-right-color";
    
    /** The Constant BORDER_RIGHT_STYLE. */
    public static final String BORDER_RIGHT_STYLE = "border-right-style";
    
    /** The Constant BORDER_RIGHT_WIDTH. */
    public static final String BORDER_RIGHT_WIDTH = "border-right-width";
    
    /** The Constant BORDER_SPACING. */
    public static final String BORDER_SPACING = "border-spacing";
    
    /** The Constant BORDER_STYLE. */
    public static final String BORDER_STYLE = "border-style";
    
    /** The Constant BORDER_TOP. */
    public static final String BORDER_TOP = "border-top";
    
    /** The Constant BORDER_TOP_COLOR. */
    public static final String BORDER_TOP_COLOR = "border-top-color";
    
    /** The Constant BORDER_TOP_STYLE. */
    public static final String BORDER_TOP_STYLE = "border-top-style";
    
    /** The Constant BORDER_TOP_WIDTH. */
    public static final String BORDER_TOP_WIDTH = "border-top-width";
    
    /** The Constant BORDER_WIDTH. */
    public static final String BORDER_WIDTH = "border-width";
    
    /** The Constant BOTH. */
    public static final String BOTH = "both";
    
    /** The Constant BOX_SHADOW. */
    public static final String BOX_SHADOW = "box-shadow";
    
    /** The Constant CAPTION_SIDE. */
    public static final String CAPTION_SIDE = "caption-side";
    
    /** The Constant CLEAR. */
    public static final String CLEAR = "clear";
    
    /** The Constant COLOR. */
    public static final String COLOR = "color";
    
    /** The Constant CONTENT. */
    public static final String CONTENT = "content";
    
    /** The Constant COUNTER_INCREMENT. */
    public static final String COUNTER_INCREMENT = "counter-increment";
    
    /** The Constant COUNTER_RESET. */
    public static final String COUNTER_RESET = "counter-reset";
    
    /** The Constant DIRECTION. */
    public static final String DIRECTION = "direction";
    
    /** The Constant DISPLAY. */
    public static final String DISPLAY = "display";
    
    /** The Constant EMPTY_CELLS. */
    public static final String EMPTY_CELLS = "empty-cells";
    
    /** The Constant FLOAT. */
    public static final String FLOAT = "float";
    
    /** The Constant FONT. */
    public static final String FONT = "font";
    
    /** The Constant FONT_FAMILY. */
    public static final String FONT_FAMILY = "font-family";
    
    /** The Constant FONT_FEATURE_SETTINGS. */
    public static final String FONT_FEATURE_SETTINGS = "font-feature-settings";
    
    /** The Constant FONT_KERNING. */
    public static final String FONT_KERNING = "font-kerning";
    
    /** The Constant FONT_LANGUAGE_OVERRIDE. */
    public static final String FONT_LANGUAGE_OVERRIDE = "font-language-override";
    
    /** The Constant FONT_SIZE. */
    public static final String FONT_SIZE = "font-size";
    
    /** The Constant FONT_SIZE_ADJUST. */
    public static final String FONT_SIZE_ADJUST = "font-size-adjust";
    
    /** The Constant FONT_STRETCH. */
    public static final String FONT_STRETCH = "font-stretch";
    
    /** The Constant FONT_STYLE. */
    public static final String FONT_STYLE = "font-style";
    
    /** The Constant FONT_SYNTHESIS. */
    public static final String FONT_SYNTHESIS = "font-synthesis";
    
    /** The Constant FONT_VARIANT. */
    public static final String FONT_VARIANT = "font-variant";
    
    /** The Constant FONT_VARIANT_ALTERNATES. */
    public static final String FONT_VARIANT_ALTERNATES = "font-variant-alternates";
    
    /** The Constant FONT_VARIANT_CAPS. */
    public static final String FONT_VARIANT_CAPS = "font-variant-caps";
    
    /** The Constant FONT_VARIANT_EAST_ASIAN. */
    public static final String FONT_VARIANT_EAST_ASIAN = "font-variant-east-asian";
    
    /** The Constant FONT_VARIANT_LIGATURES. */
    public static final String FONT_VARIANT_LIGATURES = "font-variant-ligatures";
    
    /** The Constant FONT_VARIANT_NUMERIC. */
    public static final String FONT_VARIANT_NUMERIC = "font-variant-numeric";
    
    /** The Constant FONT_VARIANT_POSITION. */
    public static final String FONT_VARIANT_POSITION = "font-variant-position";
    
    /** The Constant FONT_WEIGHT. */
    public static final String FONT_WEIGHT = "font-weight";
    
    /** The Constant HANGING_PUNCTUATION. */
    public static final String HANGING_PUNCTUATION = "hanging-punctuation";
    
    /** The Constant HEIGHT. */
    public static final String HEIGHT = "height";
    
    /** The Constant HSPACE. */
    public static final String HSPACE = "hspace";
    
    /** The Constant HYPHENS. */
    public static final String HYPHENS = "hyphens";
    
    /** The Constant LETTER_SPACING. */
    public static final String LETTER_SPACING = "letter-spacing";
    
    /** The Constant LINE_HEIGHT. */
    public static final String LINE_HEIGHT = "line-height";
    
    /** The Constant LIST_STYLE. */
    public static final String LIST_STYLE = "list-style";
    
    /** The Constant LIST_STYLE_IMAGE. */
    public static final String LIST_STYLE_IMAGE = "list-style-image";
    
    /** The Constant LIST_STYLE_POSITION. */
    public static final String LIST_STYLE_POSITION = "list-style-position";
    
    /** The Constant LIST_STYLE_TYPE. */
    public static final String LIST_STYLE_TYPE = "list-style-type";
    
    /** The Constant MARKS. */
    public static final String MARKS = "marks";
    
    /** The Constant MARGIN. */
    public static final String MARGIN = "margin";
    
    /** The Constant MARGIN_BOTTOM. */
    public static final String MARGIN_BOTTOM = "margin-bottom";
    
    /** The Constant MARGIN_LEFT. */
    public static final String MARGIN_LEFT = "margin-left";
    
    /** The Constant MARGIN_RIGHT. */
    public static final String MARGIN_RIGHT = "margin-right";
    
    /** The Constant MARGIN_TOP. */
    public static final String MARGIN_TOP = "margin-top";
    
    /** The Constant MAX_HEIGHT. */
    public static final String MAX_HEIGHT = "max-height";
    
    /** The Constant MIN_HEIGHT. */
    public static final String MIN_HEIGHT = "min-height";
    
    /** The Constant OPACITY. */
    public static final String OPACITY = "opacity";
    
    /** The Constant OUTLINE. */
    public static final String OUTLINE = "outline";
    
    /** The Constant OUTLINE_COLOR. */
    public static final String OUTLINE_COLOR = "outline-color";
    
    /** The Constant OUTLINE_STYLE. */
    public static final String OUTLINE_STYLE = "outline-style";
    
    /** The Constant OUTLINE_WIDTH. */
    public static final String OUTLINE_WIDTH = "outline-width";
    
    /** The Constant OVERFLOW_WRAP. */
    public static final String OVERFLOW_WRAP = "overflow-wrap";
    
    /** The Constant PADDING. */
    public static final String PADDING = "padding";
    
    /** The Constant PADDING_BOTTOM. */
    public static final String PADDING_BOTTOM = "padding-bottom";
    
    /** The Constant PADDING_LEFT. */
    public static final String PADDING_LEFT = "padding-left";
    
    /** The Constant PADDING_RIGHT. */
    public static final String PADDING_RIGHT = "padding-right";
    
    /** The Constant PADDING_TOP. */
    public static final String PADDING_TOP = "padding-top";
    
    /** The Constant PAGE_BREAK_AFTER. */
    public static final String PAGE_BREAK_AFTER = "page-break-after";
    
    /** The Constant PAGE_BREAK_BEFORE. */
    public static final String PAGE_BREAK_BEFORE = "page-break-before";
    
    /** The Constant PAGE_BREAK_INSIDE. */
    public static final String PAGE_BREAK_INSIDE = "page-break-inside";
    
    /** The Constant POSITION. */
    public static final String POSITION = "position";
    
    /** The Constant QUOTES. */
    public static final String QUOTES = "quotes";
    
    /** The Constant SIZE. */
    public static final String SIZE = "size";
    
    /** The Constant STYLE. */
    public static final String STYLE = "style";
    
    /** The Constant TAB_SIZE. */
    public static final String TAB_SIZE = "tab-size";
    
    /** The Constant TABLE_CELL. */
    public static final String TABLE_CELL = "table-cell";
    
    /** The Constant TABLE_LAYOUT. */
    public static final String TABLE_LAYOUT = "table-layout";
    
    /** The Constant TEXT_ALIGN. */
    public static final String TEXT_ALIGN = "text-align";
    
    /** The Constant TEXT_ALIGN_LAST. */
    public static final String TEXT_ALIGN_LAST = "text-align-last";
    
    /** The Constant TEXT_COMBINE_UPRIGHT. */
    public static final String TEXT_COMBINE_UPRIGHT = "text-combine-upright";
    
    /** The Constant TEXT_DECORATION. */
    public static final String TEXT_DECORATION = "text-decoration";
    
    /** The Constant TEXT_INDENT. */
    public static final String TEXT_INDENT = "text-indent";
    
    /** The Constant TEXT_JUSTIFY. */
    public static final String TEXT_JUSTIFY = "text-justify";
    
    /** The Constant TEXT_ORIENTATION. */
    public static final String TEXT_ORIENTATION = "text-orientation";
    
    /** The Constant TEXT_SHADOW. */
    public static final String TEXT_SHADOW = "text-shadow";
    
    /** The Constant TEXT_TRANSFORM. */
    public static final String TEXT_TRANSFORM = "text-transform";
    
    /** The Constant TEXT_UNDERLINE_POSITION. */
    public static final String TEXT_UNDERLINE_POSITION = "text-underline-position";
    
    /** The Constant UNICODE_BIDI. */
    public static final String UNICODE_BIDI = "unicode-bidi";
    
    /** The Constant VERTICAL_ALIGN. */
    public static final String VERTICAL_ALIGN = "vertical-align";
    
    /** The Constant VISIBILITY. */
    public static final String VISIBILITY = "visibility";
    
    /** The Constant VSPACE. */
    public static final String VSPACE = "vspace";
    
    /** The Constant WHITE_SPACE. */
    public static final String WHITE_SPACE = "white-space";
    
    /** The Constant WIDTH. */
    public static final String WIDTH = "width";
    
    /** The Constant WORDWRAP. */
    public static final String WORDWRAP = "word-wrap";
    
    /** The Constant WORD_BREAK. */
    public static final String WORD_BREAK = "word-break";
    
    /** The Constant WORD_SPACING. */
    public static final String WORD_SPACING = "word-spacing";
    
    /** The Constant WRITING_MODE. */
    public static final String WRITING_MODE = "writing-mode";

    // property values
    
    /** The Constant ABSOLUTE. */
    public static final String ABSOLUTE = "absolute";
    
    /** The Constant ALWAYS. */
    public static final String ALWAYS = "always";
    
    /** The Constant ARMENIAN. */
    public static final String ARMENIAN = "armenian";
    
    /** The Constant AVOID. */
    public static final String AVOID = "avoid";
    
    /** The Constant AUTO. */
    public static final String AUTO = "auto";
    
    /** The Constant BLINK. */
    public static final String BLINK = "blink";
    
    /** The Constant BLOCK. */
    public static final String BLOCK = "block";
    
    /** The Constant BOLD. */
    public static final String BOLD = "bold";
    
    /** The Constant BOLDER. */
    public static final String BOLDER = "bolder";
    
    /** The Constant BORDER_BOX. */
    public static final String BORDER_BOX = "border-box";
    
    /** The Constant BOTTOM. */
    public static final String BOTTOM = "bottom";
    
    /** The Constant CAPITALIZE. */
    public static final String CAPITALIZE = "capitalize";
    
    /** The Constant CAPTION. */
    public static final String CAPTION = "caption";
    
    /** The Constant CENTER. */
    public static final String CENTER = "center";
    
    /** The Constant CIRCLE. */
    public static final String CIRCLE = "circle";
    
    /** The Constant CJK_IDEOGRAPHIC. */
    public static final String CJK_IDEOGRAPHIC = "cjk-ideographic";
    
    /** The Constant CLOSE_QUOTE. */
    public static final String CLOSE_QUOTE = "close-quote";
    
    /** The Constant CONTAIN. */
    public static final String CONTAIN = "contain";
    
    /** The Constant CONTENT_BOX. */
    public static final String CONTENT_BOX = "content-box";
    
    /** The Constant COVER. */
    public static final String COVER = "cover";
    
    /** The Constant CROP. */
    public static final String CROP = "crop";
    
    /** The Constant CROSS. */
    public static final String CROSS = "cross";
    
    /** The Constant DASHED. */
    public static final String DASHED = "dashed";
    
    /** The Constant DECIMAL. */
    public static final String DECIMAL = "decimal";
    
    /** The Constant DECIMAL_LEADING_ZERO. */
    public static final String DECIMAL_LEADING_ZERO = "decimal-leading-zero";
    
    /** The Constant DISC. */
    public static final String DISC = "disc";
    
    /** The Constant DOTTED. */
    public static final String DOTTED = "dotted";
    
    /** The Constant DOUBLE. */
    public static final String DOUBLE = "double";
    
    /** The Constant FIXED. */
    public static final String FIXED = "fixed";
    
    /** The Constant GEORGIAN. */
    public static final String GEORGIAN = "georgian";
    
    /** The Constant GROOVE. */
    public static final String GROOVE = "groove";
    
    /** The Constant HEBREW. */
    public static final String HEBREW = "hebrew";
    
    /** The Constant HIDDEN. */
    public static final String HIDDEN = "hidden";
    
    /** The Constant HIRAGANA. */
    public static final String HIRAGANA = "hiragana";
    
    /** The Constant HIRAGANA_IROHA. */
    public static final String HIRAGANA_IROHA = "hiragana-iroha";
    
    /** The Constant ICON. */
    public static final String ICON = "icon";
    
    /** The Constant INHERIT. */
    public static final String INHERIT = "inherit";
    
    /** The Constant INITIAL. */
    public static final String INITIAL = "initial";
    
    /** The Constant INLINE. */
    public static final String INLINE = "inline";
    
    /** The Constant INLINE_BLOCK. */
    public static final String INLINE_BLOCK = "inline-block";
    
    /** The Constant INLINE_TABLE. */
    public static final String INLINE_TABLE = "inline-table";
    
    /** The Constant INSET. */
    public static final String INSET = "inset";
    
    /** The Constant INSIDE. */
    public static final String INSIDE = "inside";
    
    /** The Constant INVERT. */
    public static final String INVERT = "invert";
    
    /** The Constant ITALIC. */
    public static final String ITALIC = "italic";
    
    /** The Constant JUSTIFY. */
    public static final String JUSTIFY = "justify";
    
    /** The Constant LANDSCAPE. */
    public static final String LANDSCAPE = "landscape";
    
    /** The Constant LARGE. */
    public static final String LARGE = "large";
    
    /** The Constant LARGER. */
    public static final String LARGER = "larger";
    
    /** The Constant LEFT. */
    public static final String LEFT = "left";
    
    /** The Constant LIGHTER. */
    public static final String LIGHTER = "lighter";
    
    /** The Constant LINE_THROUGH. */
    public static final String LINE_THROUGH = "line-through";
    
    /** The Constant LOCAL. */
    public static final String LOCAL = "local";
    
    /** The Constant LOWER_ALPHA. */
    public static final String LOWER_ALPHA = "lower-alpha";
    
    /** The Constant LOWER_GREEK. */
    public static final String LOWER_GREEK = "lower-greek";
    
    /** The Constant LOWER_LATIN. */
    public static final String LOWER_LATIN = "lower-latin";
    
    /** The Constant LOWER_ROMAN. */
    public static final String LOWER_ROMAN = "lower-roman";
    
    /** The Constant LOWERCASE. */
    public static final String LOWERCASE = "lowercase";
    
    /** The Constant LTR. */
    public static final String LTR = "ltr";
    
    /** The Constant MANUAL. */
    public static final String MANUAL = "manual";
    
    /** The Constant MEDIUM. */
    public static final String MEDIUM = "medium";
    
    /** The Constant MENU. */
    public static final String MENU = "menu";
    
    /** The Constant MESSAGE_BOX. */
    public static final String MESSAGE_BOX = "message-box";
    
    /** The Constant MIDDLE. */
    public static final String MIDDLE = "middle";
    
    /** The Constant NO_OPEN_QUOTE. */
    public static final String NO_OPEN_QUOTE = "no-open-quote";
    
    /** The Constant NO_CLOSE_QUOTE. */
    public static final String NO_CLOSE_QUOTE = "no-close-quote";
    
    /** The Constant NO_REPEAT. */
    public static final String NO_REPEAT = "no-repeat";
    
    /** The Constant NONE. */
    public static final String NONE = "none";
    
    /** The Constant NORMAL. */
    public static final String NORMAL = "normal";
    
	/** The Constant NOWRAP. */
    public static final String NOWRAP = "nowrap";

    /** The Constant OBLIQUE. */
    public static final String OBLIQUE = "oblique";
    
    /** The Constant OPEN_QUOTE. */
    public static final String OPEN_QUOTE = "open-quote";
    
    /** The Constant OUTSIDE. */
    public static final String OUTSIDE = "outside";
    
    /** The Constant OUTSET. */
    public static final String OUTSET = "outset";
    
    /** The Constant OVERLINE. */
    public static final String OVERLINE = "overline";
    
    /** The Constant PADDING_BOX. */
    public static final String PADDING_BOX = "padding-box";
    
    /** The Constant PORTRAIT. */
    public static final String PORTRAIT = "portrait";
    
    /** The Constant PRE. */
    public static final String PRE = "pre";
    
    /** The Constant PRE_LINE. */
    public static final String PRE_LINE = "pre-line";
    
    /** The Constant PRE_WRAP. */
    public static final String PRE_WRAP = "pre-wrap";
    
    /** The Constant RELATIVE. */
    public static final String RELATIVE = "relative";
    
    /** The Constant REPEAT. */
    public static final String REPEAT = "repeat";
    
    /** The Constant REPEAT_X. */
    public static final String REPEAT_X = "repeat-x";
    
    /** The Constant REPEAT_Y. */
    public static final String REPEAT_Y = "repeat-y";
    
    /** The Constant RIDGE. */
    public static final String RIDGE = "ridge";
    
    /** The Constant RIGHT. */
    public static final String RIGHT = "right";
    
    /** The Constant RTL. */
    public static final String RTL = "rtl";
    
    /** The Constant SCROLL. */
    public static final String SCROLL = "scroll";
    
    /** The Constant SMALL. */
    public static final String SMALL = "small";
    
    /** The Constant SMALL_CAPS. */
    public static final String SMALL_CAPS = "small-caps";
    
    /** The Constant SMALL_CAPTION. */
    public static final String SMALL_CAPTION = "small-caption";
    
    /** The Constant SMALLER. */
    public static final String SMALLER = "smaller";
    
    /** The Constant SOLID. */
    public static final String SOLID = "solid";
    
    /** The Constant SQUARE. */
    public static final String SQUARE = "square";
    
    /** The Constant START. */
    public static final String START = "start";
    
    /** The Constant STATIC. */
    public static final String STATIC = "static";
    
    /** The Constant STATUS_BAR. */
    public static final String STATUS_BAR = "status-bar";
    
    /** The Constant SUB. */
    public static final String SUB = "sub";
    
    /** The Constant SUPER. */
    public static final String SUPER = "super";
    
    /** The Constant TABLE. */
    public static final String TABLE = "table";
    
    /** The Constant TEXT_BOTTOM. */
    public static final String TEXT_BOTTOM = "text-bottom";
    
    /** The Constant TEXT_TOP. */
    public static final String TEXT_TOP = "text-top";
    
    /** The Constant THICK. */
    public static final String THICK = "thick";
    
    /** The Constant THIN. */
    public static final String THIN = "thin";
    
    /** The Constant TOP. */
    public static final String TOP = "top";
    
    /** The Constant TRANSPARENT. */
    public static final String TRANSPARENT = "transparent";
    
    /** The Constant UNDERLINE. */
    public static final String UNDERLINE = "underline";
    
    /** The Constant UPPER_ALPHA. */
    public static final String UPPER_ALPHA = "upper-alpha";
    
    /** The Constant UPPER_LATIN. */
    public static final String UPPER_LATIN = "upper-latin";
    
    /** The Constant UPPER_ROMAN. */
    public static final String UPPER_ROMAN = "upper-roman";
    
    /** The Constant UPPERCASE. */
    public static final String UPPERCASE = "uppercase";
    
    /** The Constant X_LARGE. */
    public static final String X_LARGE = "x-large";
    
    /** The Constant X_SMALL. */
    public static final String X_SMALL = "x-small";
    
    /** The Constant XX_LARGE. */
    public static final String XX_LARGE = "xx-large";
    
    /** The Constant XX_SMALL. */
    public static final String XX_SMALL = "xx-small";

    // properties possible values
    
    /** The Constant BACKGROUND_SIZE_VALUES. */
    public static final Set<String> BACKGROUND_SIZE_VALUES = new HashSet<>(
            Arrays.asList(AUTO, COVER, CONTAIN));
    
    /** The Constant BACKGROUND_ORIGIN_OR_CLIP_VALUES. */
    public static final Set<String> BACKGROUND_ORIGIN_OR_CLIP_VALUES = new HashSet<>(
            Arrays.asList(PADDING_BOX, BORDER_BOX, CONTENT_BOX));
    
    /** The Constant BACKGROUND_REPEAT_VALUES. */
    public static final Set<String> BACKGROUND_REPEAT_VALUES = new HashSet<>(
            Arrays.asList(REPEAT, NO_REPEAT, REPEAT_X, REPEAT_Y));
    
    /** The Constant BACKGROUND_ATTACHMENT_VALUES. */
    public static final Set<String> BACKGROUND_ATTACHMENT_VALUES = new HashSet<>(
            Arrays.asList(FIXED, SCROLL, LOCAL));
    
    /** The Constant BACKGROUND_POSITION_VALUES. */
    public static final Set<String> BACKGROUND_POSITION_VALUES = new HashSet<>(
            Arrays.asList(LEFT, CENTER, BOTTOM, TOP, RIGHT));
    
    /** The Constant BORDER_WIDTH_VALUES. */
    public static final Set<String> BORDER_WIDTH_VALUES = new HashSet<>(
            Arrays.asList(new String[] {THIN, MEDIUM, THICK}));
    
    /** The Constant BORDER_STYLE_VALUES. */
    public static final Set<String> BORDER_STYLE_VALUES = new HashSet<>(
            Arrays.asList(new String[] {NONE, HIDDEN, DOTTED, DASHED, SOLID, DOUBLE, GROOVE, RIDGE, INSET, OUTSET}));
    
    /** The Constant FONT_ABSOLUTE_SIZE_KEYWORDS. */
    public static final Set<String> FONT_ABSOLUTE_SIZE_KEYWORDS = new HashSet<>(Arrays.asList(
            CssConstants.MEDIUM, CssConstants.XX_SMALL, CssConstants.X_SMALL, CssConstants.SMALL, CssConstants.LARGE,
            CssConstants.X_LARGE, CssConstants.XX_LARGE
    ));

    // pseudo-elements
    
    /** The Constant AFTER. */
    public static final String AFTER = "after";
    
    /** The Constant BEFORE. */
    public static final String BEFORE = "before";

    // Functions
    
    /** The Constant COUNTER. */
    public static final String COUNTER = "counter";
    
    /** The Constant COUNTERS. */
    public static final String COUNTERS = "counters";

    // units of measurement
    
    /** The Constant CM. */
    public static final String CM = "cm";
    
    /** The Constant EM. */
    public static final String EM = "em";
    
    /** The Constant EX. */
    public static final String EX = "ex";
    
    /** The Constant IN. */
    public static final String IN = "in";
    
    /** The Constant MM. */
    public static final String MM = "mm";
    
    /** The Constant PC. */
    public static final String PC = "pc";
    
    /** The Constant PERCENTAGE. */
    public static final String PERCENTAGE = "%";
    
    /** The Constant PT. */
    public static final String PT = "pt";
    
    /** The Constant PX. */
    public static final String PX = "px";
    
    /** The Constant REM. */
    public static final String REM = "rem";
    
    /** The Constant Q. */
    public static final String Q = "q";

    // units of resolution
    
    /** The Constant DPI. */
    public static final String DPI = "dpi";
    
    /** The Constant DPCM. */
    public static final String DPCM = "dpcm";
    
    /** The Constant DPPX. */
    public static final String DPPX = "dppx";
}
