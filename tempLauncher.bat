@echo off
set javaPath="C:\Users\Deividas\.jdks\openjdk-24.0.2+12-54\bin"
set projectPath="C:\Users\Deividas\Desktop\UniS5\Piliakalnis\out\production\Piliakalnis"
echo OPLIA
%javaPath%\java.exe -cp %projectPath% Core.Main
pause