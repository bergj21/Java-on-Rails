package jrails;

import java.util.*;

public class Foo extends Model {
    @Column public String title;
    @Column public String author;
    @Column public int num_copies;
    @Column public boolean bool;

    // @Override
    // public String toString() {
    //     return "\nTitle: " + title + ", Author: " + author + ", Num Copies: " + num_copies;
    // }
}