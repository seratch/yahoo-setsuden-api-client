#!/bin/sh
mvn clean -DaltDeploymentRepository=release-repo::default::file:~/github/seratch.github.com/mvn-repo/snapshots clean deploy

