@echo off

set file=application.properties
set file1=application.yaml

if exist %file% (
    call pro.bat
) else (
    if exist %file1% (
    		call yaml.bat
	) else (
    		echo No File
)
)