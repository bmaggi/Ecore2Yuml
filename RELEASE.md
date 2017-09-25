#How to make a release of X.Y.Z and setup X.Y.Z+1 working state

Check that the target platform is correctly configured

```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z
git add *
git commit -s -m "Set version to 0.1.0"
git tag -a v0.1.0 -m "Release 0.1.0"
mvn clean install
```
copy/paste reposotory in gh branches
rename the directory to X.Y.Z
```
mvn org.eclipse.tycho:tycho-versions-plugin:set-version -DnewVersion=X.Y.Z+1-SNAPSHOT 
Check that you are using latest version
mvn versions:display-plugin-updates
mvn clean install (Check that it's still working)
git add *
git commit -s -m "Set version to 0.2.0-SNAPSHOT"
git push 
git push origin vX.Y.Z
```

push the repo in gh_pages
set up the update site