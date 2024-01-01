package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JRouterTest {

    private JRouter jRouter;

    @Before
    public void setUp() throws Exception {
        jRouter = new JRouter();
    }

    @Test
    public void addRoute() {
        jRouter.addRoute("GET", "/", String.class, "index");
        assertThat(jRouter.getRoute("GET", "/"), is("java.lang.String#index"));
    }

    // @Test
    public void invokeRoute() {
        jRouter.addRoute("GET", "/", Foo.class, "show");
        // Html html = jRouter.route("GET", "/", )
        assertThat(jRouter.getRoute("GET", "/"), is("java.lang.String#index"));
        // Html html = jRouter.route()
    }

    /*
    public void test1() {
        JRouter r = new JRouter();
        r.addRoute("GET", "/new", BookController.class, "new_book");

        String route = r.getRoute("GET", "/new");
    
        assert(route != null);
        assert(route.isEqual("BookController#new_book"));
    }
    */
}