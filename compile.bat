@echo off
if not exist out mkdir out
javac -d out src\*.java
if %errorlevel% == 0 (
    echo Compile successful.
) else (
    echo Compile FAILED.
    pause
)
