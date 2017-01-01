package org.ajoberstar.gradle.bintray;

import org.gradle.model.RuleSource;
import org.gradle.model.Defaults;
import org.gradle.model.Model;
import org.gradle.model.Mutate;
import org.gradle.model.Validate;
import org.gradle.api.publish.PublishingExtension;

class BintrayRules extends RuleSource {
    @Model
    public void bintray(Bintray bintray) {}

    @Defaults
    public void bintrayDefaults(Bintray bintray) {
        bintray.setPublish(true);
    }

    @Validate
    public void bintrayValidate(Bintray bintray) {
        Objects.requireNonNull(bintray.getOwner(), "bintray.owner must be populated");
        Objects.requireNonNull(bintray.getRepository(), "bintray.repository must be populated");
        Objects.requireNonNull(bintray.getOwner(), "bintray.package must be populated");
    }

    @Mutate
    public void addBintrayRepository(PublishingExtension publishing, Bintray bintray) {
        String url = String.format("https://api.bintray.com/maven/%s/%s/%s/;publish=%s", bintray.getOwner(), bintray.getRepository(), bintray.getPackage(), bintray.getPublish() ? "1" : "0");
        publishing.repositories(repositories -> {
            repositories.maven(repo -> {
                maven.setName("bintray");
                maven.setUrl(url);
                maven.credentials(creds -> {
                    creds.setUsername(System.getenv("BINTRAY_USER"));
                    creds.setPassword(System.getenv("BINTRAY_KEY"));
                });
            });
        });
    }
}
