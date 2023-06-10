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

|   | A | B | C |
|---|---|---|---|
| X |0.9|0.9| 1 |
| Y | 1 | # |0.8|
| Z |0.9| 1 | 1 |

Pour le poids des arrêtes, nous avons décidés :

- si 2 adolescents sont incompatibles, alors la valeur de l'arrête est +inf (représenté par le #).
- si ils sont compatibles, on affecte 1 à l'arrête est on retire 0.1 pour chaque passe-temps en commun.

### Modélisation pour la Version 1

Comme dit précédemment, si les 2 adolescents sont incompatible, alors le poids de l'arrête est +infini (Double.MAX_VALUE en Java), sinon, on affecte 1 à l'arrête et on retire 0.1 pour chaque passe-temps en commun.

Version 2
---

Sera évaluée à partir du tag git `Graphes-v2`

### Exemple minimal pour la gestion de l'historique

#### Jeu de données

| Prénom       	| Nom    	    | Historique|
|-----------	|-----------	|---------	|
| Nicolas   	| Dagneaux  	|  same    	|
| Paul      	| Degraëve 	    |  other   	|
| Alexandre 	| Martel    	|  other   	|
| Maxime 	    | Blot    	    |    	    |
| Kais      	| Granjon   	|  same    	|
| Damand    	| Simplet   	|  same   	|
| Eric      	| Leprêtre 	    |         	|
| Léa       	| Demory    	|         	|

Nous pouvons voir que Nicolas et Kais souhaite rester ensemble alors que Paul et Alexandre ne veulent pas se retrouver à 2. Damand souhaite se retrouver avec la même personne, les autres n'ont pas omis d'opinion.

#### Historique

| Ado 1    | Ado 2        |
|--------- |--------      |
| Nicolas  | Kais         |
| Paul     | Alexandre    |
| Maxime   | Mr. Leprêtre |
| Damand   | Léa          |

#### Résultat

Ci-dessous l'appariement optimal que nous obtenons. En effet, Nicolas et Kais qui ont souhaité rester ensemble le sont toujours, au contraire d'Alexandre et Paul qui ne sont pas ensemble, comme voulu. Damand est lui aussi de nouveau avec Léa.

| Prénom hôte 	| Nom hôte 	    | Prénom invité 	| Nom invité 	|
|--------------	|-----------	|---------------	|------------	|
| Nicolas      	| Dagneaux  	| Kais          	| Granjon    	|
| Damand    	| Simplet   	| Léa          	    | Demory  	    |
| Maxime       	| Blot      	| Alexandre        	| Martel     	|
| Paul         	| Degraëve 	    | Eric     	        | Leprêtre      |


### Deuxième exemple pour la gestion d'historique

*Modifiez l'exemple précédent en ajoutant des préférences liées aux passe-temps. Donnez l'appariement que vous considérez optimal dans ce cas. En particulier, expliquez comment vous comptez combiner une éventuelle affinité liée à l'historique avec l'affinité liée aux passe-temps. Rappelons que l'historique peut compter comme une contrainte rédhibitoire ou comme une préférence, voir le sujet pour plus de précisions.*

*Donner l'appariement que vous considérez optimal dans ce deuxième exemple, toujours sans parler de graphes.*

#### Jeu de données

| Prénom       	| Nom    	    | Historique| Passe-temps
|-----------	|-----------    |-----------|-----------
| Nicolas   	| Dagneaux  	|  same    	| sport,culture
| Paul      	| Degraëve 	    |  other   	| reading,technology
| Alexandre 	| Martel    	|  other   	| technology,culture
| Maxime 	    | Blot    	    |    	    | science,technology
| Kais      	| Granjon   	|  same    	| sport,culture
| Damand    	| Simplet   	|  same   	| culture,reading,technology
| Eric      	| Leprêtre 	    |         	| technology,science
| Léa       	| Demory    	|         	| sport

#### Historique

| Ado 1    | Ado 2        |
|--------- |--------      |
| Nicolas  | Kais         |
| Paul     | Alexandre    |
| Maxime   | Mr. Leprêtre |
| Damand   | Léa          |

#### Résultat

| Prénom hôte 	| Nom hôte 	    | Prénom invité  | Nom invité   |
|--------------	|-----------	|--------------- |------------  |
| Nicolas      	| Dagneaux  	| Kais           | Granjon      |
| Damand    	| Simplet   	| Alexandre	     | Martel 	    |
| Maxime       	| Blot      	| Eric        	 | Leprêtre     |
| Paul         	| Degraëve 	    | Léa     	     | Demory       |

### Modélisation pour les exemples

*Pour chacun des deux exemples précédents, donnez un graphe (donné par sa matrice d'adjacence) tel que l'affectation minimale dans ce graphe correspond à l'appariement optimal identifié plus haut. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*

### Modélisation pour l'historique de la Version 2

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1. Décrire également comment vous construisez le graphe modèle à partir des données en entrée.*

### Implémentation de l'historique de la Version 2

#### AffectationUtil.weight()

Cette fonction calcule le poids d'une arrête entre 2 adolescents données.

Elle fait appel à la fonction `historyWeight()` pour calculer la modification apporter au poids de l'arrête.

#### AffectationUtil.historyWeight()

Cette fonction sert à calculer la modification du poids de l'arrête en fonction des préférences liées à l'historique.

Cette fonction retourne -10 si les 2 adolescents veulent être à 2, Double.MAX_VALUE si l'un des 2 ne souhaite pas se rertouver avec l'autre, -0.1 si l'un des 2 a exprimé le choix "same", 0 sinon.

### Prendre en compte les autres préférences

#### Gender

Pour le genre, si les 2 adolescents se "complète", on retire 0.5 au poids de l'arrête. Si un des 2 a émis un choix que l'autre rempli, on enlève 0.1 au poids de l'arrête. Sinon on ne touche pas au poids de l'arrête.

#### Age

Si les 2 adolescents ont moins d'un an et demie d'écart, on retire 0.1 au poids de l'arrête.

### L'incompatibilité en tant que malus

*Proposer une formule ou une description précise qui explique comment calculer le poids d'une arête en considérant les incompatibilités comme des malus et les critères satisfaits comme des bonus. Implémenter cette formule dans une seconde méthode appelée `weightAdvanced`, ceci pour éviter de casser votre code. Puis, écrire une méthode de test qui permet d'illustrer le calcul d'affectation basé sur `weightAdvanced`. Vous pouvez égalmente tester l'affectation en utilisant le fichier de données `incompatibilityVsBonus.csv`.*

