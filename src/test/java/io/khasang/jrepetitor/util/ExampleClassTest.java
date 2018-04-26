package io.khasang.jrepetitor.util;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExampleClassTest {

    @Test
    public void testCheckUniqParam() {
        MyClass myClass = mock(MyClass.class);

        when(myClass.checkUniqParam()).thenReturn(43);

        ExampleClass exampleClass = new ExampleClass();

        assertEquals(44, exampleClass.checkUniqParam(myClass.checkUniqParam()));
    }

    @Test
    public void testMoreThanOneReturnValue() {
        Iterator<String> num = mock(Iterator.class);

        when(num.next()).thenReturn("Barsik").thenReturn("hungry");
        String result = num.next() + " " + num.next();

        assertEquals("Barsik hungry", result);
    }

    @Test
    public void testReturnValueDependOnMethodparam() {
        Comparable<String> comparable = mock(Comparable.class);

        when(comparable.compareTo("Barsik")).thenReturn(1);
        when(comparable.compareTo("Murzik")).thenReturn(0);

        assertEquals(1, comparable.compareTo("Barsik"));
        assertEquals(0, comparable.compareTo("Murzik"));
    }

    @Test
    public void testReturnValueInDependOnMethodparam() {
        Comparable<Integer> comparable = mock(Comparable.class);

        when(comparable.compareTo(anyInt())).thenReturn(-1);

        assertEquals(-1, comparable.compareTo(123));
    }

    @Test
    public void testReturnValueInDependOnMethodparam2() {
        Comparable<MyClass> comparable = mock(Comparable.class);
        when(comparable.compareTo(isA(MyClass.class))).thenReturn(0);
        assertEquals(0, comparable.compareTo(new MyClass(12)));
    }
}
