package vn.plusplus.lms.factory;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralList<T> implements Serializable {
    private ResponseStatus status;
    private List<T> data;
}
