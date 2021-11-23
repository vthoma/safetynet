package com.openclassroom.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class FirestationDTO {
    int countChild;
    int countAdult;
    List<Data> data;

    public void setData(String firstname, String lastname) {
        if (Objects.nonNull(data)) {
            Data d = new Data(firstname, lastname);
            this.data.add(d);
        } else {
            this.data = new ArrayList<>();
            Data d = new Data(firstname, lastname);
            this.data.add(d);
        }
    }
}
