SAE S2.02 -- Rapport pour la ressource Graphes
===

DAGNEAUX Nicolas - DEGRAEVE Paul -  MARTEL Alexandre

Groupe D
___

Version 1
---

Sera évaluée à partir du tag git `Graphes-v1`

### Étude d'un premier exemple

En observant les données de la figure 2, nous pouvons remarquer plusieurs appariements possibles.

- Adonia-Xolag, Bellatrix-Zander, et Callista-Yak.
- Adonia-Yak, Bellatrix-Xolag, et Callista-Zander.
- Adonia-Zander, Bellatrix-Xolag, et Callista-Yak.
- Adonia-Yak, Bellatrix-Zander, et Callista-Xolag.

En effet, ces appariement permettent à Bellatrix (étant la seule à avoir des allergies) de se retrouver chez une famille ne possédant pas d'animaux.

Mais ces appariements ne prennent pas en compte les passe-temps. En effet, plus les adolescents ont de passe-temps en commun, plus il y a de chance qu'ils s'entendent bien. 

Si l'on regarde la figure 2, nous pouvons observer que Adonia et Yak ne partagent aucun passe-temps. Pareil pour Bellatrix et Zander, et Callista, Xolag et Zander.

Ce qui nous laisse donc un seul appariement optimal pour satistaire tout le monde.

- Adonia-Zander, Bellatrix-Xolag, et Callista-Yak.

### Modélisation de l'exemple

Voici la matrice d'adjacence du graphes que nous avons modélisé pour représenter le problème. Les lettres sont les noms des adolescents et les chiffres sont les poids des arrêtes.

----A----B----C\
X--0.9--0.9---1\
Y---1----#---0.8\
Z--0.9---1----1

Pour le poids des arrêtes, nous avons décidés :

- si 2 adolescents sont incompatibles, alors la valeur de l'arrête est +inf (représenté par le #).
- si ils sont compatibles, on affecte 1 à l'arrête est on retire 0.1 pour chaque passe-temps en commun.

### Modélisation pour la Version 1

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1.*

### Exemple de vérification de l'incompatibilité 

*Cet exemple va mettre au défi votre modèle vis à vis de la prise en compte de l'incompatibilité entre adolescents 

Récupérez sur Moodle le fichier de données `compatibilityVsHobbies.csv`. Expliquez quelle est sa particularité de cet exemple. Écrire la méthode de test qui test qui charge cet exemple, construit le graphe modèle, calcule l'affectation, et finalement vérifie qu'aucune paire d'adolescents non compatibles n'a été construite par l'algorithme.*