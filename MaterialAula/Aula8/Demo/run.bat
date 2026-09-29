javac -cp "lib/junit-platform-console-standalone-1.10.0.jar;lib/h2-2.2.224.jar" -d . src/*.java

java -cp ".;lib/junit-platform-console-standalone-1.10.0.jar;lib/h2-2.2.224.jar" org.junit.platform.console.ConsoleLauncher execute --scan-class-path --details verbose