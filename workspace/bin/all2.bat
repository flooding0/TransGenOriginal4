cls
@echo off
set PATH=%~dp0;%PATH%
analyse input.txt | buildTree | classify | annotate | annotateAction | extract > results.xml
