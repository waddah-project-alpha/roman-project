# Integer to Roman Converter
## Overview
This project is an assignment as part of the process for an application to a Senior Software Engineer position at Adobe. It's a Spring Boot application which exposes 
an endpoint that allows a user to send in an integer and returns the Roman notation representation of that number. The notation
used in this app is referenced in [Wikipedia](https://en.wikipedia.org/wiki/Roman_numerals), and in I support both the
[standard](https://en.wikipedia.org/wiki/Roman_numerals#Standard_form) and [additive](https://en.wikipedia.org/wiki/Roman_numerals#Other_additive_forms)
forms. Throughout the application code these are referenced as the short and long notations respectively, to make it easier to
relate to the actual result (the additive form is more verbose and therefore longer for certain inputs). The app does not 
support the value 0 and accepts all integers up to 2,200,000,000.

## Running the app
Being a Spring Boot app, this application has an internal server and can be either opened and run in an IDE or from the 
command line. You'll need to either clone or download the application from the Github repo on your machine before running it.
### Run in IDE
To run the application in an IDE, simply open the application in the IDE of your choice (instructions can vary depending on IDE),
and allow Gradle to load the references. Note that this app uses JDK 17, so you'll need to have it available for it to run.
Unit tests can also be run to validate the application logic.

### Run via command line
To run via command line, navigate to the `executable` folder in the app, and run `java -jar integer-to-roman.jar` in a command
line. The application should start and you'll see log trace in the window.

## Testing
Once the app is running, you can test it by sending a request via browser or your favorite API building app (Postman, Insomnia...)
to this URL:
```
http://localhost:8080/romannumeral?query={integer}[&useShortNotation=true|false]
```
If `useShortNotation` is ommitted or set to true, the result will be in the standard format as discussed above, otherwise
it'll use the additive format.
Once you've made some requests, you can open `http://localhost:8080/actuator/prometheus` to see requests data. Look for 
`roman_to_integer_controller_hit_total` counter and `roman_to_integer_controller_duration` histogram, which are registerd
by the app.

## Methodology
### Functionality
In addition to providing the conversion service for an integer to a Roman literal, the application performs below functions:
* Validation by guarding against invalid inputs
* Logging for core methods
* Monitoring for controller request
* Error handling
* Unit testing

A more detailed explanation for engineering design and methodology is in below sections
### Algorithm
The algorithm used to perform the actual conversion is pretty simple and relies on building the return string from left to
right by recursively finding the smallest value in a map and deducting it from the input. You can see more details in the
javadoc of the `BaseConversionService#convertToRoman` method.
### Engineering
The application uses the Controller/Service/Repository pattern to process requests. Though it's a small application, I wanted
each layer to handle its own role, with the controller sanitizing input and delegating to the service layer, which executes
the main functionality by using (static) data from the repository. Additionally, the app applies the following concepts:
* Separation of concern: This is most clearly seen by using the Aspect Oriented Programming methodlogy for logging and 
monitoring (check `ApplicationMonitoringAspect.java` and `ApplicationLoggingAspect.java`). This removes boilerplate and
repetetive code from implementing methods, and centralizes configuration and logic in one class.
* Centralization of Error Handling: Although this app only has one controller, I extracted the error handling logic into its
own ControllerAdvice. This will make it easier to handle exceptions as the application grows larger, and clears up the code
* DRY: Throughout the app, I tried to reuse functionality whenever possible to minimize code (ex: reusing validation code in
`IntegerToRomanConversionValidatorService` and implementing polymorphism in `BaseConversionService`)
* Depedency Injection: Used injectable components instead of static classes to make them more interchangeable and
make the code more unit testable

Note that for logging, I am simply using the console output, though this can be easily changed to log to a file or other
media instead.
Additionally, I'm using Prometheus library to create a counter and histogram for telemetry. The data can be then fed to a
Prometheus agent and Graphana instance with some custom dashboards for visualization, which is beyond the scope of this app.

## Packaging Layout
Please find below the layout of the core folders under the src folder, along with a short description about the role for each:
* src/main/java/com/adobe/integertoroman
  * aop: this contains AspectJ classes, for logging and monitoring. This "hijacks" executions on target methods, allowing
for adding logging and invoking counters.
  * config: Contains the web security config file. This is more of a POC as we don't really require permissions for requests
  * controlleradvice: [Controller advice](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc) class which handles
exceptions thrown by the controller and returns a user friendly message
  * converter: This encapsulates the functionality of the integer to roman conversion. I have found that organizing project
packages by functionality rather than by role (i.e. controller, service, dao...) makes it easier to find and manage code
and makes for a more modular code, though at the expense of a deeper hierarchy, and some cross-functional concerns that have
to be dealt with
    * controller: As the name implies, contains the main controller class for our app
    * repository: Contains our data repo, which is the Integer to Roman notation strings maps
    * service: Contains the conversion and validation services. It also has an `internal` subpackage
  * exceptions: Contains our custom exception class
  * metrics: Defines the custom counter and histogram which are used in the app 

The testing package mainly focuses on the core functionality (i.e. the converter), and mimicks the folder structure laid above.

## Potential Improvements
This is a sample app which is by no means perfect and has a lot of potential for improvements. Some of those are:
* Better method targeting in AOP, ex. by allowing methods to opt out of auto logging using tags
* More and better metric definition and targeting
* Default app wide error message (ex. for invalid links)
* Anti scraping/DDOS prevention

There's certainly a lot more than this, but I feel like these would be good starting points

## Final Thoughts
If you've read this far, I salute you! I hope you'll have as much fun reading through this app as I had coding it!
