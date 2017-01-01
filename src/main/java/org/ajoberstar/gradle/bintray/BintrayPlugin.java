package org.ajoberstar.gradle.bintray;

import org.gradle.api.Project;
import org.gradle.api.Plugin;

public class BintrayPlugin implements Plugin<Project> {
    public void apply(Project project) {
        project.getPluginManager().withPlugin("maven-publish", plugin -> {
            project.getPluginManager().apply(BintrayRules.class);
        });
    }
}
