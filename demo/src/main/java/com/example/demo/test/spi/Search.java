package com.example.demo.test.spi;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public interface Search {
    public List<String> searchDoc(String keyword);

    public static void main(String[] args) {
        ServiceLoader<Search> loader = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = loader.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello world");
        }

    }
}




