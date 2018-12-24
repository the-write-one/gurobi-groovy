@echo off
set GROOVY_DIR=c:\Tools\groovy-2.4.7
set GUROBY_DIR=c:\VM\gurobi810\win64\

"%GROOVY_DIR%\bin\groovy.bat" -cp "%GUROBY_DIR%\lib\gurobi.jar" %*
