package jrails;

import java.util.*;

public class Bar extends Model {
    @Column public Boolean bool;
    @Column public String name;
    @Column public int age;

    // @Override
    // public String toString() {
    //     return bool.toString();
    // }
}