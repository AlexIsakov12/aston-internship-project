package org.project.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String email;
    private List<Task> taskList;

    public User(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nickname, user.nickname) && Objects.equals(email, user.email) && Objects.equals(taskList, user.taskList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, email, taskList);
    }
}
