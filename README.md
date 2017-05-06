# Jazz Plugin Base Service
Abstract base service implementation which serves as the base class for web services as plugins.

## Abstract
This is an abstract implementation of a jazz plugin which can be used to implement (restful) HTTP web services that can be deployed as an update-site. For details on how an actual service extending this base package looks like, see the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype). That page also demonstrates how an actual implementation can be integrated into your sytem.

This page concentrates on the functionalities of the base service and how to use them to build web services.

## Motivation
The plugin presented here is loosely based on [Ralph Schoon's RTC Extensions Workshop](https://rsjazz.wordpress.com/2017/03/17/updated-rtc-extensions-workshop-for-rtc-6-0-3/). The motivation behind creating jazz plugins that can be used to create HTTP services is to have light weight custom services that can use the jazz server API. They allow us to create custom HTTP interfaces on top of jazz that seemlessly integrate into our update site deployment and require very minimal configuration. Also, being small update sites, they can be development, maintained and deployed independently.

## Usage
This project is the base on which the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype) builds. If you want to develop you own service, the archetype is how you should bootstrap your environment. It gets you started with a completely deployable new service that extends this base service, and shows how this service can be called etc.

The Jazz Plugin Base Service provides a layer on top of `TeamRawService` and provides functionality for routing incoming HTTP requests to services. It is meant as a simple starting point for creating more elaborate service structures, and is therefore regarded as stable. Any extended functionality should usually be implemented in your final service implementation.

### Installation: Adding the base service to your maven repository
In order to inherit from the base service and handle depedencies on it, you will want to add it to your maven repository.

1. Clone this repository `git clone https://github.com/jazz-community/jazz-plugin-base-service.git`
2. Run `mvn install` from inside the checked out repository.

### Running the tests
The Base Service comes with it's own set of unit tests and is largely covered by them. The unit tests in this project are a good starting point to get an idea of how services can be tested using the provided mocks.

To run the tests, simply run `mvn integration-test` from the project root.

# Documentation
This documentation is meant to help developers that are basing their services on the Base Service get a grasp of the base service functionality, but also as a long term documentation for developers maintaining the base service implementation.

## Getting started
As mentioned, the best way to get started is having a look at the `ExampleService` in the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype) project. It shows how to use the `Router` to add new services and how to construct them using the `RestFactory`. Knowing about `Router`, `RestFactory` and the `AbstractRestService` class is enough to get started developing your own services. Also, the tests in this repository show how to instanciate and use these classes. As such, the tests are a good entry point as well.

## Javadoc
Exhaustive documentation can be found in the API documentation. `Clone`the repo and open `/docs/index.html`.

## Preparing a release
* Tag every new release
* Every new feature **must** have unit tests
* Any release should be tested exhaustively
* When introducing breaking changes, make sure to create a new major version and a corresponding branch
* Use the tycho-versions-plugin to keep version numbers consistent
    `mvn org.eclipse.tycho:tycho-versions-plugin:set-version "-DnewVersion=1.0.0"`
* Generate (`mvn javadoc:javadoc`) and release javadoc in the `/docs` folder.

## Contributing
Please use the [Issue Tracker](https://github.com/jazz-community/jazz-plugin-base-service/issues) of this repository to report issues or suggest enhancements.<br>
Pull requests are very welcome.

## Licensing
Copyright (c) Siemens AG. All rights reserved.<br>
Licensed under the [MIT](https://github.com/jazz-community/jazz-plugin-base-service/blob/master/LICENSE) License.

