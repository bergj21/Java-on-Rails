package jrails;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class ModelTest {

    // @Test
    public void fill() {
        Foo f1 = new Foo();
        f1.title = "Programming Languages: Build, Prove, and Compare";
        f1.author = "Norman Ramsey";
        f1.num_copies = 999;
        f1.save();
        Foo f2 = new Foo();
        f2.title = "Hello World";
        f2.author = "Jack";
        f2.num_copies = 21;
        f2.save(); 

        Bar b1 = new Bar();
        b1.bool = false;
        b1.save(); 
        Bar b2 = new Bar();
        b2.bool = true;
        b2.save();


        Foo f = Model.find(Foo.class, f1.id());
        assert(f.title.equals(f1.title));
        assert(f.author.equals(f1.author));
        assert(f.num_copies == f1.num_copies);
        assert(f.id() == f1.id());
        // System.out.println(f.toString());
        
        f = Model.find(Foo.class, f2.id());
        assert(f.title.equals(f2.title));
        assert(f.author.equals(f2.author));
        assert(f.num_copies == f2.num_copies);
        assert(f.id() == f2.id());
        // System.out.println(f.toString());

        List<Foo> foos = Model.all(Foo.class); 
        System.out.println(foos.size());
        assert(foos.size() == 5);

    }

    @Test
    public void newtest2() {
        Foo f1 = new Foo();
        f1.title = "Programming Languages: Build, Prove, and Compare";
        f1.author = "Norman Ramsey";
        f1.num_copies = 999;
        f1.bool = true;
        f1.save();

        Foo f = Model.find(Foo.class, f1.id());
        assert(f.title.equals(f1.title));
        assert(f.author.equals(f1.author));
        // assert(f.num_copies == f1.num_copies);
        System.out.println(f.bool);
        assert(f1.bool == f.bool);
        assert(f.id() == f1.id());

        // Foo f2 = new Foo();
        // f2.title = "Hello World";
        // f2.author = "Jack";
        // f2.num_copies = 21;
        // f2.bool = false;
        // f2.save(); 



    }

    // @Test
    public void newtest() {
        // System.out.println("HELLO!!");
        Bar b1 = new Bar();
        b1.bool = false;
        b1.name = "Jack";
        b1.age = 21;
        b1.save(); 

        Bar b2 = new Bar();
        b2.bool = true;
        b2.name = "Lilah";
        b2.age = 20;
        b2.save();

        Bar b = Model.find(Bar.class, b1.id());
        assert(b.bool == b1.bool);
        assert(b.name.equals(b1.name));
        assert(b.age == b1.age);
        assert(b.id() == b1.id());

        b.bool = true;
        b.save();

        b = Model.find(Bar.class, b.id());
        assert(b.bool == true);
        assert(b.name.equals(b1.name));
        assert(b.age == b1.age);
        assert(b.id() == b1.id());

        b = Model.find(Bar.class, b2.id());
        assert(b.bool == b2.bool);
        assert(b.name.equals(b2.name));
        assert(b.age == b2.age);
        assert(b.id() == b2.id());
    }

    // @Test
    public void test() {
        Foo f4 = new Foo();
        f4.title = "SR Latch";
        f4.author = "Sheldon";
        f4.num_copies = 1000;
        f4.save();

        Foo f = Model.find(Foo.class, f4.id());
        assert(f.title.equals(f4.title));
        assert(f.author.equals(f4.author));
        assert(f.num_copies == f4.num_copies);
        assert(f4.id() == f.id());

        f.title = "Hello";
        f.author = "Shark Meldon";
        f.num_copies = 12012;
        f.save();
        
        f4 = Model.find(Foo.class, f.id());
        assert(f.title.equals(f4.title));
        assert(f.author.equals(f4.author));
        assert(f.num_copies == f4.num_copies);

        List<Foo> foos = Model.all(Foo.class); 
        assert(foos.size() == 3);
    }
    
    // @Test
    public void test1() {
        
        Foo f = Model.find(Foo.class, 1);
        assert(f.title.equals("Programming Languages: Build, Prove, and Compare"));
        assert(f.author.equals("Norman Ramsey"));
        assert(f.num_copies == 999);

        // f = Model.find(Foo.class, f1.id());
        // assert(f.title.equals(f1.title));
        // assert(f.author.equals(f1.author));
        // assert(f.num_copies == f1.num_copies);

        Bar b = Model.find(Bar.class, 4);
        assert(b.bool == true);

        // b = Model.find(Bar.class, b2.id());
        // assert(b.bool == true);
    }

    
    // @Test
    public void test3() {
        Foo f3 = new Foo();
        f3.title = "Flip-Flop";
        f3.author = "IBM";
        f3.num_copies = 12;
        f3.save();
        assertThat(Model.find(Foo.class, f3.id()), notNullValue());
    }

    /*
    @Test
    public void test4() {
        Foo f4 = new Foo();
        f4.title = "SR Latch";
        f4.author = "Sheldon";
        f4.num_copies = 1000;
        f4.save();

        Foo f = Model.find(Foo.class, f4.id());


        assert(f.title.equals(f4.title));
        assert(f.author.equals(f4.author));
        assert(f.num_copies == f4.num_copies);
        assert(f.id() == f4.id());

        List<Foo> foos = Model.all(Foo.class); 
        assert(foos.size() == 3);
        System.out.println(foos.toString());

        List<Bar> bars = Model.all(Bar.class); 
        assert(bars.size() == 2);
        System.out.println(bars.toString());
    }

    @Test
    public void test5() {
        List<Foo> foos = Model.all(Foo.class); 
        assert(foos.size() == 2);

        List<Bar> bars = Model.all(Bar.class); 
        assert(bars.size() == 2);
    }

    @Test
    public void test6() {
        List<Foo> foos = Model.all(Foo.class); 
        assert(foos.size() == 2);

        List<Bar> bars = Model.all(Bar.class); 
        assert(bars.size() == 2);
    }

    @After
    public void reset() {
        Model.reset();
    }
    */
}