# gradle-bintray

[![Bintray](https://api.bintray.com/packages/ajoberstar/maven/gradle-bintray/images/download.svg)](https://bintray.com/ajoberstar/maven/gradle-bintray/_latestVersion)
[![Travis](https://img.shields.io/travis/ajoberstar/gradle-bintray.svg?style=flat-square)](https://travis-ci.org/ajoberstar/gradle-bintray)
[![Quality Gate](https://sonarqube.ajoberstar.com/api/badges/gate?key=org.ajoberstar:gradle-bintray)](https://sonarqube.ajoberstar.com/dashboard/index/org.ajoberstar:gradle-bintray)
[![GitHub license](https://img.shields.io/github/license/ajoberstar/gradle-bintray.svg?style=flat-square)](https://github.com/ajoberstar/gradle-bintray/blob/master/LICENSE)

## Why do you care?

The official [gradle-bintray-plugin](https://github.com/bintray/gradle-bintray-plugin) uses it's own custom tasks to publish rather than the Gradle `maven-publish` tasks. It also pushes a lot of logic into an `afterEvaluate` which can cause ordering problems with other plugins. Bintray supports direct maven publishing, so we can do this with a lot less hassle.

## What is it?

`gradle-bintray` is a [Gradle](http://gradle.org) plugin, `org.ajoberstar.bintray`, which adds a maven repository to the `publishing` container for your Bintray package. You can then publish using the normal publish tasks created by `maven-publish`.

See [maven-publish](https://docs.gradle.org/current/userguide/publishing_maven.html) docs for more details on usage.

## Usage

See the [Release Notes](https://github.com/ajoberstar/gradle-bintray/releases) for updates on
changes and compatibility with Java and Gradle versions.

### Applying the Plugin

**Plugins DSL**

```groovy
plugins {
    id 'org.ajoberstar.bintray' version '<version>'
}
```

**Classic**

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'org.ajoberstar:gradle-bintray:<version>'
    }
}

apply plugin: 'org.ajoberstar.bintray'
```

### Configuration

**NOTE:** In general, there are no default values here. The main exception is that the `repoUri` will be automatically set if you use the `org.ajoberstar.grgit` plugin to your project's origin repo URI.

```groovy
model {
    bintray {
        owner = 'my-user' // your bintray user or org
        repository = 'my-repo' // your bintray repo
        package = 'my-pkg' // your bintrary package
        publish = false // publish the files after upload to Bintray, defaults true
    }
}
```

Credentials are taken from `BINTRAY_USER` and `BINTRAY_KEY` environment variables by default.

### Tasks and Execution

The repository will be named `bintray`, so `maven-publish` will create tasks like this: `publishMainPublicationToBintrayRepository`.

## Questions, Bugs, and Features

Please use the repo's [issues](https://github.com/ajoberstar/gradle-bintray/issues)
for all questions, bug reports, and feature requests.

## Contributing

Contributions are very welcome and are accepted through pull requests.

Smaller changes can come directly as a PR, but larger or more complex
ones should be discussed in an issue first to flesh out the approach.

If you're interested in implementing a feature on the
[issues backlog](https://github.com/ajoberstar/gradle-bintray/issues), add a comment
to make sure it's not already in progress and for any needed discussion.

## Acknowledgements

Thanks to all of the [contributors](https://github.com/ajoberstar/gradle-bintray/graphs/contributors).
