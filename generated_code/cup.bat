@echo off
java -jar "java-cup-11a.jar" -parser Parser -symbols CrystalSymbol < crystalscript.cup
pause