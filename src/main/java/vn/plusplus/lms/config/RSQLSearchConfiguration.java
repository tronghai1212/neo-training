package vn.plusplus.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

@Configuration
public class RSQLSearchConfiguration {
    public static final String DIRECTION_ASC = "ASC";

    @Value("${RSQL.size:20}")
    private int size;
    @Value("${RSQL.sort:id}")
    private String sort;
    @Value("${RSQL.direction:ASC}")
    private String direction;

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String sort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Sort.Direction direction() {
        return DIRECTION_ASC.equals(direction) ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
