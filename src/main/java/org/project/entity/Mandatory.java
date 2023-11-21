package org.project.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mandatory {
    private Long id;
    private String description;
    private List<Task> taskList;

    public Mandatory(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mandatory mandatory = (Mandatory) o;
        return Objects.equals(id, mandatory.id) && Objects.equals(description, mandatory.description) && Objects.equals(taskList, mandatory.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, taskList);
    }
}
