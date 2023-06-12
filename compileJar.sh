#! /bin/bash

JARNAME="Affectator-SIMULATOR3000.jar"
MANIFESTPATH="META-INF/Manifest.mf"
CLASSPATH=$(find lib -name "*.jar" | tr '\n' ' ')
CLASSPATH="Class-Path: $CLASSPATH"

#edit Manifest file
grep -q -e "Class-Path:" $MANIFESTPATH && sed -i -s '/^Class-Path:/d' $MANIFESTPATH
echo "$CLASSPATH" 1>> $MANIFESTPATH


jar cvmf $MANIFESTPATH $JARNAME -C bin . dev/res/*[^.] lib/*.jar

echo "Contient : "
jar tf $JARNAME