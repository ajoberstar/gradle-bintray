package org.ajoberstar.gradle.bintray;

import org.gradle.model.Managed;

@Managed
public interface Bintray {
    String getOwner();
    void setOwner(String owner);

    String getRepository();
    void setRepository(String repository);

    String getPackage();
    void setPackage(String package);

    boolean getPublish();
    void setPublish(boolean publish);
}
