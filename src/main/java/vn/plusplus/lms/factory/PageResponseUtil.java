package vn.plusplus.lms.factory;

import org.springframework.data.domain.Page;

public class PageResponseUtil {
    public static PageResponse buildPageResponse(Page page){
        int currentPage = page.getNumber() + 1;
        if (currentPage <= page.getTotalPages()) {
            return new PageResponse(page.getTotalPages(), page.hasNext(), page.hasPrevious(), currentPage, page.getTotalElements());
        } else {
            return new PageResponse(page.getTotalPages(), null, null, null, page.getTotalElements());
        }
    }
}
