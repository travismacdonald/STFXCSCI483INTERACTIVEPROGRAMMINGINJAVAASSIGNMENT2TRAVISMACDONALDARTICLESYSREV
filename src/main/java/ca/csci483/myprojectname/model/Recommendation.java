package ca.csci483.myprojectname.model;

public enum Recommendation {
    ACCEPT("Accept"),
    REJECT("Reject"),
    MINOR_REV("Minor Revisions"),
    MAJOR_REV("Major Revisions");

    private final String name;

    private Recommendation(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

}
