package jrails;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class ViewTest extends View {
    // @Test
    // public void empty() {
    //     assertThat(View.empty().toString(), isEmptyString());
    // }

    @Test
    public void test1() {
        Foo b = new Foo();
        b.title = "Programming Languages: Build, Prove, and Compare";
        b.author = "Norman Ramsey";
        b.num_copies = 999;
        System.out.println(show(b).toString());
    }

    public static Html show(Foo b) {
        return p(strong(t("Title:")).t(b.title)).
                p(strong(t("Author:")).t(b.author)).
                p(strong(t("Copies:")).t(b.num_copies)).
                t(link_to("Edit", "/edit?id=" + b.id())).t(" | ").
                t(link_to("Back", "/"));
    }

    public void test2() {
        Html h = p(t("Hello")).div(t("World"));
        System.out.println(h.toString());
    }
    
}