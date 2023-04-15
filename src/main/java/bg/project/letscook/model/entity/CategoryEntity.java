package bg.project.letscook.model.entity;

import bg.project.letscook.model.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public CategoryEnum getCategory() {
        return category;
    }

    public CategoryEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
}
