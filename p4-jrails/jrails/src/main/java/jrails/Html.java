package jrails;

import java.util.*;

public class Html {

    private List<String> html = new LinkedList<>();

    public String toString() {
        String result = "";
        for (String tag : html) {
            result += tag;
        }
        return result;
    }

    public Html seq(Html h) {
        Html result = new Html();
        result.html = new LinkedList<>(html);
        result.html.add(h.toString());
        return result;
    }

    public Html br() {
        Html result = new Html();
        result.html = new LinkedList<>(html);
        result.html.add("<br/>");
        return result;
    }

    public Html t(Object o) {
        Html result = new Html();
        result.html = new LinkedList<>(html);
        result.html.add(o.toString());
        return result;
    }

    public Html p(Html child) {
        Html result = new Html();
        String tag = "<p>" + child.toString() + "</p>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html div(Html child) {
        Html result = new Html();
        String tag = "<div>" + child.toString() + "</div>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html strong(Html child) {
        Html result = new Html();
        String tag = "<strong>" + child.toString() + "</strong>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html h1(Html child) {
        Html result = new Html();
        String tag = "<h1>" + child.toString() + "</h1>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html tr(Html child) {
        Html result = new Html();
        String tag = "<tr>" + child.toString() + "</tr>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html th(Html child) {
        Html result = new Html();
        String tag = "<th>" + child.toString() + "</th>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html td(Html child) {
        Html result = new Html();
        String tag = "<td>" + child.toString() + "</td>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html table(Html child) {
        Html result = new Html();
        String tag = "<table>" + child.toString() + "</table>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html thead(Html child) {
        Html result = new Html();
        String tag = "<thead>" + child.toString() + "</thead>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html tbody(Html child) {
        Html result = new Html();
        String tag = "<tbody>" + child.toString() + "</tbody>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html textarea(String name, Html child) {
        Html result = new Html();
        String tag = "<textarea name=\"" + name + "\">" + child.toString() +  "</textarea>";
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html link_to(String text, String url) {
        Html result = new Html();
        String tag = "<a href=\"" + url + "\">" + text + "</a>"; 
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html form(String action, Html child) {
        Html result = new Html();
        String tag = "<form action=\"" + action + "\" accept-charset=\"UTF-8\" method=\"post\">" + child.toString() + "</form>"; 
        result.html = new LinkedList<>(html);
        result.html.add(tag);
        return result;
    }

    public Html submit(String value) {
        Html result = new Html();
        result.html = new LinkedList<>(html);
        result.html.add("<input type=\"submit\" value=\"" + value + "\"/>");
        return result;
    }
}