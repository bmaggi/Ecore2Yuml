#How to make a release of X.Y.Z and setup X.Y.Z+1 working state

Check that the target platform is correctly configured

```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z
git add *
git commit -s -m "Set version to X.Y.Z"
git tag -a vX.Y.Z -m "Release X.Y.Z"
mvn clean install
```
copy/paste reposotory in gh branches
rename the directory to X.Y.Z
```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z+1-SNAPSHOT 
Check that you are using latest versions
mvn versions:display-plugin-updates
Manual modification for category.xml and target in main pom.xml
mvn clean install (Check that it's still working)
git add *
git commit -s -m "Set version to X.Y.Z+1-SNAPSHOT"
git push 
git push origin vX.Y.Z
```

push the repo in gh_pages
set up the update site