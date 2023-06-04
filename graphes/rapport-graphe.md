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

Comme dit précédemment, si les 2 adolescents sont incompatible, alors le poids de l'arrête est +infini (Double.MAX_VALUE en Java), sinon, on affecte 1 à l'arrête et on retire 0.1 pour chaque passe-temps en commun.

Version 2
---

Sera évaluée à partir du tag git `Graphes-v2`

### Exemple minimal pour la gestion de l'historique

*Présenter un exemple minimal qui est pertinent pour tester l'historique. L'exemple contiendra:*
- *huit adolescents de deux pays différents tels que* 
  - *certains des adolescents expriment des préférences d'historique (critère HISTORY). Toutes les valeurs possibles pour ce critère doivent être présentes* 
  - *aucun des adolescents n'est allergique aux animaux en aucun n'a exprimé de passe-temps, ainsi pour l'instant on peut se concentrer uniquement sur la gestion de l'historique*
- *un historique, c'est à dire une collection de paires d'adolescents qui étaient correspondants l'année passée. Ces paires doivent permettre d'illustrer les différents cas de figure qui peuvent se présenter par rapport aux contraintes d'historique et les huit adolescents*

*Puis, donner un appariement optimal qui tient compte des données d'historique, et expliquer pourquoi il est optimal. L'explication ne doit pas parler des graphes, mais uniquement des adolescents et les critères exprimés.*

#### Jeu de données

| Prénom       	| Nom    	| History 	|
|-----------	|-----------	|---------	|
| Nicolas   	| Dagneaux  	| same    	|
| Paul      	| Degraëve 	| other   	|
| Alexandre 	| Martel    	| other   	|
| Maxime 	| Blot    	|    	|
| Kais      	| Granjon   	| same    	|
| Damand    	| Simplet   	|         	|
| Eric      	| Leprêtre 	|         	|
| Léa       	| Demory    	|         	|

#### Résultat

Ci-dessous l'appariement optimal que nous obtenons. En effet, Nicolas et Kais qui ont souhaité rester ensemble le sont toujours, au contraire d'Alexandre et Paul qui ne sont pas ensemble, comme voulu.

| Prénom hôte 	| Nom hôte 	| Prénom invité 	| Nom invité 	|
|--------------	|-----------	|---------------	|------------	|
| Nicolas      	| Dagneaux  	| Kais          	| Granjon    	|
| Alexandre    	| Martel    	| Eric          	| Leprêtre  	|
| Maxime       	| Blot      	| Léa           	| Demory     	|
| Paul         	| Degraëve 	| Damand        	| Simplet    	|


### Deuxième exemple pour la gestion d'historique

*Modifiez l'exemple précédent en ajoutant des préférences liées aux passe-temps. Donnez l'appariement que vous considérez optimal dans ce cas. En particulier, expliquez comment vous comptez combiner une éventuelle affinité liée à l'historique avec l'affinité liée aux passe-temps. Rappelons que l'historique peut compter comme une contrainte rédhibitoire ou comme une préférence, voir le sujet pour plus de précisions.*

*Donner l'appariement que vous considérez optimal dans ce deuxième exemple, toujours sans parler de graphes.*

### Modélisation pour les exemples

*Pour chacun des deux exemples précédents, donnez un graphe (donné par sa matrice d'adjacence) tel que l'affectation minimale dans ce graphe correspond à l'appariement optimal identifié plus haut. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*

### Modélisation pour l'historique de la Version 2

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1. Décrire également comment vous construisez le graphe modèle à partir des données en entrée.*

### Implémentation de l'historique de la Version 2

*Quelles fonctions de votre code avez-vous modifié pour prendre en compte le critère historique ? Donnez ici les noms des méthodes (et leur classe), à quoi elles servent, et quelles modifications vous avez apportées. Essayez d'être synthétique.*

### Test pour l'historique de la Version 2

*Créer la classe de TestAffectationVersion2 qui contiendra deux méthodes de test, une pour chacun des exemples. Chacune de ces méthodes doit avoir la même structure que pour TestAffectationVersion1, c'est à dire créer les données d'entrée (adolescents, historique), créer le graphe, calculer l'affectation, et tester que le résultat est comme attendu.*

### Prendre en compte les autres préférences

*Pour chacun des autres critères d'affinité que vous décidez de prendre en compte, décrire comment vous changez la fonction weight de la classe AffectationUtil.*

### L'incompatibilité en tant que malus

*Proposer une formule ou une description précise qui explique comment calculer le poids d'une arête en considérant les incompatibilités comme des malus et les critères satisfaits comme des bonus. Implémenter cette formule dans une seconde méthode appelée `weightAdvanced`, ceci pour éviter de casser votre code. Puis, écrire une méthode de test qui permet d'illustrer le calcul d'affectation basé sur `weightAdvanced`. Vous pouvez égalmente tester l'affectation en utilisant le fichier de données `incompatibilityVsBonus.csv`.*