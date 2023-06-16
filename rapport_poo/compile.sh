#!/bin/bash

#Génération du rapport HTML
pandoc Rapport.md --syntax-definition=theme/java.xml --pdf-engine=prince --css=styles/style-pdf.css --toc --toc-depth=4 --section-divs -s -o Compte-rendu-POO.html

#Génération du rapport PDF en utilisant le moteur "prince"
pandoc Rapport.md --syntax-definition=theme/java.xml --pdf-engine=prince --css=styles/style-pdf.css --toc --toc-depth=4 --section-divs -s -o Compte-rendu-POO.pdf
