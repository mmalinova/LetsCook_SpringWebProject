package bg.project.letscook.model.entity;

import bg.project.letscook.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
