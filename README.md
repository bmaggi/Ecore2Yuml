# Ecore2Yuml
Prototype to provide an [Ecore] to [Yuml] transformation

:warning: transformation in image have an issue with big ecore model.

#Status
[![License](https://img.shields.io/badge/license-EPL-blue.svg)](https://www.eclipse.org/legal/epl-v10.html)
[![Build Status](https://travis-ci.org/bmaggi/Ecore2Yuml.svg?branch=master)](https://travis-ci.org/bmaggi/Ecore2Yuml)

:warning: This is a prototype on how to use the Yuml service to generate an image for very simple Meta-Model

##How to make a release

Check that the target platform

Check that you are using latest version
mvn versions:display-plugin-updates

Set the correct version (remove -SNAPSHOT)

```
mvn -Dtycho.mode=maven org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=*new-version*-SNAPSHOT
```

Copy the update site in ?

Prepare the new version (add - SNAPSHOT)

[Yuml]: https://yuml.me/
[Ecore]: https://wiki.eclipse.org/Ecore
