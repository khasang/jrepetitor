package io.khasang.jrepetitor.util;

public class MyClass {
    private int count = 11;

    public MyClass() {
    }

    public MyClass(int count) {
        this.count = count;
    }

    public int checkUniqParam() {
        if (isUniq(count)) {
            return count;
        } else {
            return 43;
        }
    }

    private boolean isUniq(int count) {
        return count > 1;
    }
}
