
set PATH_TO_FX=".\javafx-sdk\lib"



java -cp out --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml  sbu.client.Main

pause
