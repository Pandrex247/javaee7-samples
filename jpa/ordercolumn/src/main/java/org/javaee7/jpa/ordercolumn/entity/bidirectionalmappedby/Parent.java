package org.javaee7.jpa.ordercolumn.entity.bidirectionalmappedby;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;

@Entity(name="Parent2")
public class Parent {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    
    @SuppressWarnings("unused")
    private int dummy = 1;

    @OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "parent")
    @OrderColumn
    private List<Child> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

}
