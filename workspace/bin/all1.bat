cls
@echo off
set PATH=%~dp0;%PATH%
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Analyse input.txt | ^
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.BuildTree | ^
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Classify | ^
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Annotate | ^
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.AnnotateAction | ^
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Extract > results.xml
