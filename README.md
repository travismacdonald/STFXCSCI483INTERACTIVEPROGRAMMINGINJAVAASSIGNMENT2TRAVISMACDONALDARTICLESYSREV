# ArticleRevSys

An application for reviewing scientific articles. Provides two main functionality for users:

1. Review a publication.
2. Asses reviews from other reviewers.

# Design Decisions

This application contains some relatively arbitrary design decisions including:

| Field         | Character Limit (each) | Max Points | Min Points |
| ------------- |:---------------:|:----------:|:----------:|
| Title         | 100             | N/A        | N/A        |
| URL           | 100             | N/A        | N/A        |
| Summary       | 2000            | N/A        | N/A        |
| Positives     | 500             | 5          | 1          |
| Negatives     | 500             | 5          | 1          |
| Major Points  | 1000            | 5          | 1          |
| Minor Points  | 500             | 5          | 1          |
| Reviewer Name | 100             | N/A        | N/A        |


The reason for implemented this is to enforce *some* limit on the amount of information that a reviewer can upload. However, it should be noted that this application was designed so that these can be easily modified and/or removed (e.g. ArsConstants can be modified to change global behavior of the entire application).