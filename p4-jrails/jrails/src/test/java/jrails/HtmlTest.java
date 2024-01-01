package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html h = new Html();

    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }
    

   @Test
   public void test1() {
        
        // Html html = h.t("Hello World");
        Html html = h.p(h.strong(h.t("Hello")));

        Html html2 = h.div(h.strong(h.t("World")));

        Html result = html.seq(html2);
        System.out.println(result.toString());
   }

   @Test
   public void test2() {
        Html textarea = h.textarea("w3schools", h.t("Review of W3Schools"));
        Html anchor = h.link_to("Show", "/show?id=42");
        Html form = h.form("/create", h.p(h.strong(h.t("Hello"))));
        Html submit = h.submit("Save");
        System.out.println(textarea.toString());
        System.out.println(anchor.toString());
        System.out.println(form.toString());
        System.out.println(submit.toString());
   }

   @Test
   public void test3() {
        Html html = h.p(h.t("Hello"));


        Html result = html.p(h.t("World"));
        System.out.println(result.toString());
   }
}
