cd /d %~dp0
protoc --java_out=src\main\java proto\*.proto
pause