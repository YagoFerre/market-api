package yago.ferreira.marketapi.adapters.in.controller.dto.response;

import java.util.List;
import java.util.stream.DoubleStream;

public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalPages;
    private long totalElements;

    public PageResponse() {
    }

    public PageResponse(List<T> content, int page, int size, long totalPages, long totalElements) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public DoubleStream stream() {
        return null;
    }
}
