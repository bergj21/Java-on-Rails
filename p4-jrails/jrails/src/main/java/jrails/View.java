package jrails;

public class View {
    private static Html h = new Html(); 

    public static Html empty() {
        return h.t("");
    }

    public static Html br() {
        return h.br();
    }

    public static Html t(Object o) {
        return h.t(o);
    }

    public static Html p(Html child) {
        return h.p(child);
    }

    public static Html div(Html child) {
        return h.div(child);
    }

    public static Html strong(Html child) {
        return h.strong(child);
    }

    public static Html h1(Html child) {
        return h.h1(child);
    }

    public static Html tr(Html child) {
        return h.tr(child);
    }

    public static Html th(Html child) {
        return h.th(child);
    }

    public static Html td(Html child) {
        return h.td(child);
    }

    public static Html table(Html child) {
        return h.table(child);
    }

    public static Html thead(Html child) {
        return h.thead(child);
    }

    public static Html tbody(Html child) {
        return h.tbody(child);
    }

    public static Html textarea(String name, Html child) {
        return h.textarea(name, child);
    }

    public static Html link_to(String text, String url) {
        return h.link_to(text, url);
    }

    public static Html form(String action, Html child) {
        return h.form(action, child);
    }

    public static Html submit(String value) {
        return h.submit(value);
    }
}