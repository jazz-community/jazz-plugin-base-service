# Jazz Plugin Base Service
template which serves as the base class for plugin-based web services.

## Description
This is an abstract (as in abstract class in Java) implementation of a jazz plugin that can be used to implement (restful) HTTP web services. Being a jazz plugin, services implemented in this manner can be deployed as an update-site. For an example of a service basing on this package, see the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype), which comes with an exhaustive example. That page also demonstrates how an implementation can be integrated into your system.

This page concentrates on the functionalities of the base service and how to use them to build web services.

## Motivation
The plugin presented here is loosely based on the topics in [Ralph Schoon's RTC Extensions Workshop](https://rsjazz.wordpress.com/2017/03/17/updated-rtc-extensions-workshop-for-rtc-6-0-3/). We wanted to have the ability to create light weight HTTP services that have access to the jazz server API. They allow us to create custom HTTP interfaces on top of jazz that require minimal configuration and seemlessly integrate into our update site deployment. Being small update-sites, they can be developed, maintained and deployed independently.

## Usage
This project is the base on which the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype) builds. If you want to develop your own service, the archetype is how you should bootstrap your environment. It gets you started with a complete, deployable, new service that extends this base service, and demonstrats how to interact with this service.

The Jazz Plugin Base Service provides a layer on top of `TeamRawService` and provides functionality for routing incoming HTTP requests to service implementations. It is meant as a simple starting point for creating more elaborate service structures, and is therefore regarded as stable. Any extended functionality should usually be implemented in your final service implementation.

### Installation: Adding the base service to your maven repository
In order to inherit from the base service and have projects depend on it, you will want to add it to your maven repository.

1. Clone this repository `git clone https://github.com/jazz-community/jazz-plugin-base-service.git`
2. Run `mvn install` from inside the checked out repository.

### Running the tests
The Base Service comes with its own set of unit tests and is largely covered by them. The unit tests in this project are a good starting point to get an idea of how services can be tested using the provided mocks.

To run the tests, simply run `mvn integration-test` from the project root.

# Documentation
This documentation is meant to help developers that are basing their services on the Base Service get a grasp of the base service functionality, but also as a long term documentation for developers maintaining the base service implementation.

## Getting started
As mentioned, the best way to get started is having a look at the `ExampleService` in the [Jazz Plugin Maven Archetype](https://github.com/jazz-community/jazz-plugin-maven-archetype) project. It shows how to use the `Router` to add new services and how to construct them using the `RestFactory`. Knowing about `Router`, `RestFactory` and the `AbstractRestService` class is enough to get started developing your own services. Also, the tests in this repository show how to instanciate and use these classes. As such, the tests are a good entry point as well.

## Javadoc
Exhaustive documentation can be found in the API documentation. `Clone` the repo and open `/docs/index.html`.

## Contributing
Please use the [Issue Tracker](https://github.com/jazz-community/jazz-plugin-base-service/issues) of this repository to report issues or suggest enhancements.

For general contribution guidelines, please refer to [CONTRIBUTING.md](https://github.com/jazz-community/jazz-plugin-base-service/blob/master/CONTRIBUTING.md)

## Licensing
Copyright (c) Siemens AG. All rights reserved.<br>
Licensed under the [MIT](https://github.com/jazz-community/jazz-plugin-base-service/blob/master/LICENSE) License.

