#! /bin/bash

JARNAME="Affectator-SIMULATOR3000.jar"
MANIFESTPATH="META-INF/Manifest.mf"
CLASSPATH=$(find lib -name "*.jar" | tr '\n' ' ')
CLASSPATH="Class-Path: $CLASSPATH"

jar cvmf $MANIFESTPATH $JARNAME -C bin . lib/jgrapht-core-1.5.1.jar lib/sae2_02.jar

echo "Contient : "
jar tf $JARNAME

rm ~/dev/SAE2-01-IHM-DAGNEAUX-DEGRAEVE-MARTEL/Affectator-SIMULATOR3000.jar
cp Affectator-SIMULATOR3000.jar ~/dev/SAE2-01-IHM-DAGNEAUX-DEGRAEVE-MARTEL/