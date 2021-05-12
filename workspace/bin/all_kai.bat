cls
@echo off
set PATH=%~dp0;%PATH%
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Read
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Analyse input2.txt analysis.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.BuildTree analysis.xml tree.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Classify tree.xml classifiedtree.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Annotate classifiedtree.xml annotatedtree.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.AnnotateAction annotatedtree.xml annotatedtreeaction.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.Extract annotatedtreeaction.xml results.xml
java -Dfile.encoding=UFT-8 -classpath jar/nlp.jar sel.nlp.ExtractWord results.xml annotatedtreeaction.xml result2.xml