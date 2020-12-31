package info.narmontas.valuemover;

import info.narmontas.valuemover.annotation.IgnoreValue;

public class AnnotatedTestType {
    private Long id;
    private int level;
    private String description;
    @IgnoreValue
    private String ignoredField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIgnoredField() {
        return ignoredField;
    }

    public void setIgnoredField(String ignoredField) {
        this.ignoredField = ignoredField;
    }

    @Override
    public String toString() {
        return "AnnotatedTestType{" +
                "id=" + id +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", ignoredField='" + ignoredField + '\'' +
                '}';
    }
}
