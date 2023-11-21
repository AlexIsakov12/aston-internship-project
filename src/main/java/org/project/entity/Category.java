package org.project.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String description;
    private List<Task> taskList;

    public Category(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(description, category.description) && Objects.equals(taskList, category.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, taskList);
    }
}
