package com.rickandmorty.infrastructure.adapter.shared;

public class PageAsk {
    private final Integer page;
    private final Integer size;

    private PageAsk(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public static PageAsk create(Integer page, Integer size) {
        return new PageAsk(page, size);
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
