# Contributing
Please read our [general contribution guide](https://github.com/jazz-community/welcome/blob/master/CONTRIBUTING.md). Its content applies to all repositories of the _jazz-community_ organization.

## Providing tests
No project has enough tests, and we would be very happy to cover one of our foundational projects with better test cases. If you want to get into hacking on jazz plugins, providing some tests to this project would be a very welcome contribution.

## Adding a feature
Adding features to the base service should only happen in rare cases. If there is functionality that you are missing, it is likely better to add a library or provide the missing functionlity by creating your own library. This project is meant to provide a layer that is as slim as possible for adding url routing functionality to plugins. 

If you still think the base service is missing features, please open an issue in this project providing as many details for your envisioned change as you can.

## Preparing a release
* Tag every new release
* Every new feature **must** have unit tests
* Any release should be tested exhaustively
* When introducing breaking changes, make sure to create a new major version and a corresponding branch
* Old releases must have maintenance branches before you push to master
* Use the tycho-versions-plugin to keep version numbers consistent
    `mvn org.eclipse.tycho:tycho-versions-plugin:set-version "-DnewVersion=1.0.0-SNAPSHOT"`
* After changing versions with the tycho-versions-plugin, make sure to change the MANIFEST.mf file in the test project to match the current release
* Generate (`mvn javadoc:javadoc`) and release javadoc in the `/docs` folder.

