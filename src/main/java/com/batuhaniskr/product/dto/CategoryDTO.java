package com.batuhaniskr.product.dto;

import java.util.Set;

public class CategoryDTO {
    private Integer id;
    private String categoryName;
    private Set<ProductDTO> productSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<ProductDTO> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ProductDTO> productSet) {
        this.productSet = productSet;
    }
}
