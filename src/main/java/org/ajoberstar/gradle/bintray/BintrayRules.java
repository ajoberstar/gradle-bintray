/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ajoberstar.gradle.bintray;

import java.util.Objects;
import org.gradle.api.publish.PublishingExtension;
import org.gradle.model.Defaults;
import org.gradle.model.Model;
import org.gradle.model.Mutate;
import org.gradle.model.RuleSource;
import org.gradle.model.Validate;

class BintrayRules extends RuleSource {
  @Model
  public void bintray(Bintray bintray) {
    // just create the model element
  }

  @Defaults
  public void bintrayDefaults(Bintray bintray) {
    bintray.setPublish(true);
  }

  @Validate
  public void bintrayValidate(Bintray bintray) {
    Objects.requireNonNull(bintray.getOwner(), "bintray.owner must be populated");
    Objects.requireNonNull(bintray.getRepo(), "bintray.repo must be populated");
    Objects.requireNonNull(bintray.getPkg(), "bintray.pkg must be populated");
  }

  @Mutate
  public void addBintrayRepository(PublishingExtension publishing, Bintray bintray) {
    String url =
        String.format(
            "https://api.bintray.com/maven/%s/%s/%s/;publish=%s",
            bintray.getOwner(),
            bintray.getRepo(),
            bintray.getPkg(),
            bintray.getPublish() ? "1" : "0");
    publishing.repositories(
        repositories -> {
          repositories.maven(
              repo -> {
                repo.setName("bintray");
                repo.setUrl(url);
                repo.credentials(
                    creds -> {
                      creds.setUsername(System.getenv("BINTRAY_USER"));
                      creds.setPassword(System.getenv("BINTRAY_KEY"));
                    });
              });
        });
  }
}
