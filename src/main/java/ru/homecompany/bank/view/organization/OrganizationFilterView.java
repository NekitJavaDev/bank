package ru.homecompany.bank.view.organization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Output View for working with organization filter methods. Returns ROW DATA (JSON STRING) to user
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class OrganizationFilterView {

    public Integer id;

    public String name;

    public Boolean isActive;

    public OrganizationFilterView() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "OrganizationFilterView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
