package org.ajoberstar.gradle.bintray;

import org.gradle.model.Managed;

@Managed
public interface Bintray {
    String getOwner();
    void setOwner(String owner);

    String getRepo();
    void setRepo(String repo);

    String getPkg();
    void setPkg(String pkg);

    boolean getPublish();
    void setPublish(boolean publish);
}
