package vn.plusplus.lms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DaysModel {
    @JsonProperty(value = "Monday")
    private List<String> monday;

    @JsonProperty(value = "Tuesday")
    private List<String> tuesday;

    @JsonProperty(value = "Wednesday")
    private List<String> wednesday;

    @JsonProperty(value = "Thursday")
    private List<String> thursday;

    @JsonProperty(value = "Friday")
    private List<String> friday;

    @JsonProperty(value = "Saturday")
    private List<String> saturday;

    @JsonProperty(value = "Sunday")
    private List<String> sunday;
}
