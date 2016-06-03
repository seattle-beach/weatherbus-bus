To get started:

1. Set up the environment:
        * Download bus_envrc from the Google Drive folder and save it as .envrc
        * Launch IntelliJ from within the repo root: `idea .`
2. Import the gradle file:
        * Import Project from the intellij splash screen
        * Select build.gradle from the repo root
        * Accept the defaults
3. Tell compiler that lombok is a thing
        * Preferences -> Plugins -> Install
        * Install lombok plugin
4. Enable annotation processing
        * IntelliJ Idea -> Preferences
        * Build, Execution, Deployment -> Compiler -> Annotation Processors
        * Check "Enable annotation processing"
