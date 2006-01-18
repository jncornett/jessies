package e.ptextarea;

import e.gui.*;
import e.util.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Links to web sites from written-out URLs.
 * 
 * Examples:
 *   http://www.google.com
 *   http://www.google.com/
 *   http://www.gnu.org/software/make/manual/html_mono/make.html
 *   http://cvs.gnome.org/viewcvs/gtk%2B/gtk/gtkstock.h?view=markup
 *   <a href="http://www.google.com">Google</a>
 */
class HyperlinkStyleApplicator extends RegularExpressionStyleApplicator {
    public HyperlinkStyleApplicator(PTextArea textArea) {
        super(textArea, "\\b(https?://[^ \t\"\n]+)", PStyle.HYPERLINK);
    }
    
    @Override
    public boolean canApplyStylingTo(PStyle style) {
        return (style == PStyle.NORMAL || style == PStyle.COMMENT);
    }
    
    @Override
    protected void configureSegment(PTextSegment segment, Matcher matcher) {
        String url = matcher.group(1);
        segment.setLinkAction(new WebLinkAction("Web Link", url));
    }
}
