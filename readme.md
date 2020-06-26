To get started:

1. Install JDK 8. (Newer versions will not work.)

    On OS X with Homebrew, run these commands:
    ```
    $ brew tap AdoptOpenJDK/openjdk
    $ brew cask install adoptopenjdk8
    ```
2. Create .envrc as follows:
    ```
    export ONEBUSAWAY_ENDPOINT=http://api.pugetsound.onebusaway.org
    export ONEBUSAWAY_KEY=your_OBA_key # Can use "test" for testing purposes
    export JAVA_HOME=<path to JDK 8, possibly /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home>
    export PATH=$JAVA_HOME/bin:$PATH
    ```
3. Try to build and run
    1. `$ ./gradlew assemble`
    2. `java -jar build/libs/weatherbus-bus-0.0.1-SNAPSHOT.jar`
    
    
IntelliJ setup:

(Note: this does not currently work unless you're running IntelliJ itself on an 
old version of Java. Gradle needs to be upgraded before newer versions will 
work.)

1. Launch IntelliJ from within the repo root: `idea .`
2. Import the gradle file:
    1. Import Project from the intellij splash screen
    2. Select build.gradle from the repo root
    3. Accept the defaults
    4. Set the project SDK:
        1. Select File -> Project Structure
        2. Select Project Settings/Project
        3. Set Project SDK to JDK 8 (probably in /Library/Java/JavaVirtualMachines).
3. Tell compiler that lombok is a thing
    1. Preferences -> Plugins -> Install
    2. Install lombok plugin
4. Enable annotation processing
    1. IntelliJ Idea -> Preferences
    2. Build, Execution, Deployment -> Compiler -> Annotation Processors
    3. Check "Enable annotation processing"
