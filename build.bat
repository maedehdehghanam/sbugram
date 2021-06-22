
set PATH_TO_FX=".\javafx-sdk\lib"

del /s /q out
rmdir /s /q out
mkdir out

dir /s /B *.java > sources.txt 
javac -d out --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml @sources.txt


pause
cls

java -cp out sbu.server.Server

pause
