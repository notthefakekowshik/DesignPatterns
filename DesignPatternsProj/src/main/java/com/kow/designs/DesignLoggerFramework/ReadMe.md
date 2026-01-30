Logging framework which is used to log messages.

Requirements :
1. Developers should be able to set log levels.
   a. If log level is set to X, then all logs of level X and above should be logged.
2. Developers can add appenders based on their choice
   a. Console appender
   b. File appender -> Users should be able to set their file path
      Q : what's the file size?
      Q : What if file path doesn't present? create a new file or ski silently?
3. Should be thread safe

Class/Interfaces/Enums involved :
Classes :
1. MyLogger
2. ConsoleAppender
3. FileAppender
4. LogMessage

Interfaces :
1. Appender Strategy


Enum :
1. LogLevel


Design patterns Involved :
1. MyLogger should be singleton
2. MyLogger shou