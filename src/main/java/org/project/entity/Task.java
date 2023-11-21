package org.project.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String description;
    private User user;
    private Mandatory mandatory;
    private List<Category> categoryList;

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(description, task.description) && Objects.equals(user, task.user) && Objects.equals(categoryList, task.categoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, user, categoryList);
    }
}
