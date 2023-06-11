SAE S2.02 -- Rapport pour la ressource Graphes
===

DAGNEAUX Nicolas - DEGRAEVE Paul -  MARTEL Alexandre

Groupe D
___

# Sommaire
1. [Version_1](#version-1)
    1. [Premier_exemple](#étude-dun-premier-exemple)
    2. [Modélisation_exemple](#modélisation-de-lexemple)
    3. [Modélisation_version_1](#modélisation-pour-la-version-1)
2. [Version_2](#version-2)
    1. [Exemple_minimal](#exemple-minimal-pour-la-gestion-de-lhistorique)
    2. [Deuxième_exemple](#deuxième-exemple-pour-la-gestion-dhistorique)
    3. [Modélisation_exemple](#modélisation-pour-les-exemples)
    4. [Modélisation_version_2](#modélisation-pour-lhistorique-de-la-version-2)
    5. [Implémentation](#implémentation-de-lhistorique-de-la-version-2)
    6. [Autres_préférences](#prendre-en-compte-les-autres-préférences)
    7. [Incompatiblité](#lincompatibilité-en-tant-que-malus)

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

Nous pouvons observer que les résultats sont un peu différents de tout à l'heure. En effet, malgrès que Damand souhaitait être avec Léa, il se retrouve avec Alexandre car il n'a aucun passe-temps en commun avec Léa. A contrario, il possède 2 passe-temps en commun avec Alexandre. Maxime et Mr. Leprêtre, quand à eux, partagent les mêmes passe-temps, ils se retrouvent donc ensembles. Il ne reste plus que Paul et Léa, bien qu'ils n'ont aucun passe-temps en commun. Nicolas et Kais sont toujours ensembles comme ils l'ont souhaité.

### Modélisation pour les exemples

#### Exemple 1

Pour cet exemple, nous considérerons que si 2 ados veulent être ensemble, alors l'arrête vaut -10. Si seul l'un des 2 veut de nouveau être avec l'autre, alors l'arrête vaut -5. Si l'un des 2 (ou les 2) ne veut plus être avec l'autre, alors l'arrête vaut 10. Sinon, l'arrête vaut 0.

|              | Nicolas | Paul | Maxime | Damand |
|--------------|---------|------|--------|--------|
| Kais         |   -10   |  0   |   0    |   0    |
| Alexandre    |    0    |  10  |   0    |   0    |
| Mr. Leprêtre |    0    |  0   |   0    |   0    |
| Léa          |    0    |  0   |   0    |  -5    |

#### Exemple 2

Pour cet exemple, si 2 ados veulent être ensemble, alors l'arrête vaut toujours -10. Si seul l'un des 2 veut de nouveau être avec l'autre, alors l'arrête vaut -1. Si l'un des 2 (ou les 2) ne veut plus être avec l'autre, alors l'arrête vaut 10. Sinon, l'arrête vaut 0.

Ensuite, pour chaque passe-temps en commun, on enlève 1 au poids de l'arrête.

|              | Nicolas | Paul | Maxime | Damand |
|--------------|---------|------|--------|--------|
| Kais         |   -12   |  0   |   0    |  -1    |
| Alexandre    |   -1    |  9   |  -1    |  -2    |
| Mr. Leprêtre |    0    | -1   |  -2    |  -1    |
| Léa          |   -1    |  0   |   0    |  -1    |

### Modélisation pour l'historique de la Version 2

Le poids d'une arrête par défaut est 1.

Si 2 adolescent veulent être ensemble, alors on enlève 10 au poids de l'arrête. Si seul l'un des 2 adolescents veut de nouveau être avec l'autre, alors on enlève 0.1 au poids de l'arrète. Si l'un des 2 (ou les 2) ne veut plus être avec l'autre, alors on ajoute Double.MAX_VALUE au poids de l'arrête. Sinon, on ne change pas son poids.

Pour construire le graphe, la fonction prend en paramètre une liste d'adolescents, un pays hôte, un pays invité et une liste de couple d'adolescents (l'historique). Puis les adolescents sont assignés à 2 listes différents en fonction de leur pays, une liste d'adolescents hôtes et une liste d'adolescents visiteurs. Ensuite, on construit le graphe `GrapheNonOrienteValue<Teenager>` du package de graphe fourni su Moodle.

### Implémentation de l'historique de la Version 2

#### AffectationUtil.weight()

Cette fonction calcule le poids d'une arrête entre 2 adolescents données.

Elle fait appel à la fonction `historyWeight()` pour calculer la modification apporter au poids de l'arrête.

#### AffectationUtil.historyWeight()

Cette fonction sert à calculer la modification du poids de l'arrête en fonction des préférences liées à l'historique.

Cette fonction retourne -10 si les 2 adolescents veulent être à 2, Double.MAX_VALUE si l'un des 2 ne souhaite pas se rertouver avec l'autre, -0.1 si seul l'un des 2 a exprimé le choix "same", 0 sinon.

### Prendre en compte les autres préférences

#### Gender

Pour le genre, si les 2 adolescents se "complète", on retire 0.5 au poids de l'arrête. Si un des 2 a émis un choix que l'autre rempli, on enlève 0.1 au poids de l'arrête. Sinon on ne touche pas au poids de l'arrête.

#### Age

Si les 2 adolescents ont moins d'un an et demie d'écart, on retire 0.1 au poids de l'arrête.

### L'incompatibilité en tant que malus

Pour implémenter l'incompatibilté en tant que malus, nous avons repris les différentes sous-méthodes de la fonction `compatibleWithGuest()`, mais au lieu de définir le poids de l'arrête à Double.MAX_VALUE comme dans la version 1, ici nous retirons simplement une valeur "forte" au poids de l'arrête.