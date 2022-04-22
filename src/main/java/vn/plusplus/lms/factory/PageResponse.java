package vn.plusplus.lms.factory;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PageResponse {

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("has_next")
    private Boolean hasNext;

    @JsonProperty("has_previous")
    private Boolean hasPrevious;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("total_elements")
    private Long totalElements;

    public PageResponse(Integer totalPages, Boolean hasNext, Boolean hasPrevious, Integer currentPage, Long totalElements) {
        this.totalPages = totalPages;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer pageNumber) {
        this.totalPages = pageNumber;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Boolean getHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(Boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

}
