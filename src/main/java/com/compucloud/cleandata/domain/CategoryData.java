package com.compucloud.cleandata.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CategoryData.
 */
@Entity
@Table(name = "category_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategoryData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 256)
    @Column(name = "category", length = 256, nullable = false)
    private String category;

    @NotNull
    @Size(max = 256)
    @Column(name = "subcategory", length = 256, nullable = false)
    private String subcategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryData categoryData = (CategoryData) o;
        if(categoryData.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, categoryData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryData{" +
            "id=" + id +
            ", category='" + category + "'" +
            ", subcategory='" + subcategory + "'" +
            '}';
    }
}
