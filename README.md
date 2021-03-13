# ArticleRevSys

An application for reviewing scientific articles. Provides two main functionality for users:

1. Review a publication.
2. Asses reviews from other reviewers.

# Design Decisions

This application contains some relatively arbitrary design decisions including:

1. 2000 character limit on the summary field.
2. A maximum of 5 points for each of major, minor, positive, and negative critiques.
3. A minimum of 1 point for each of major, minor, positive, and negative critiques.
4. A character limit of 1000 for each of major, minor, positive, and negative critiques.
5. A max length of 50 characters for the publication title.

The reason for implemented this is to enforce *some* limit on the amount of information that a reviewer can upload. However, it should be noted that this application was designed so that these can be easily modified and/or removed (e.g. ArsConstants can be modified to change global behavior of the entire application).