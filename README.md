
![Logo](https://github.com/Drag0nQQ/L2-Projet-Java/blob/main/readme_assets/logoMD.png?raw=true)


# Meta-Digger.io

Ce projet est réalisé par Axel OLIVEIRA et Laurent LIN, étudiants en L2-I à Cergy-Paris Université. Ce projet met en application les connaissances acquises dans l'UE "Programmation orientée Objet & Java".

## Introduction
Ce projet vise à la création d'une application bureautique sur les fichiers OpenDocument. L'intégralité du code est en Java.
Cette application offre les options suivantes :
* Scan d'un répertoire et affichage des fichiers OpenDocument présents.
* Affichage des métadonnées d'un document.
* Sauvegarde et modification des métadonnées d'un document.
  * Le titre peut être modifié
  * Le sujet peut être modifié



## Requirement
> Pour utiliser nos programmes, il faut au minima être sous Java 11 ou supérieur. Pour installer Java, veuillez-vous rendre [ici](https://www.oracle.com/java/technologies/downloads/).
## Documentation

### Utilisation de la version en ligne de commande
La version en ligne de commande doit être utilisée dans le terminal. Pour l'appeler, il suffit de naviguer vers le répertoire où se situe le fichier `CLI.jar`.\
Une fois dans le bon répertoire, l'appel se fait avec la commande suivante :  `java -jar CLI.jar`

**Usage: `java -jar CLI.jar <option> [path] [args] [text]`**
* `<option>` :
    * `-h` : affiche ce message d'aide
    * `-f` : précise que ce `[path]` est un fichier
    * `-d` : précise que ce `[path]` est un répertoire
* `[path]` : devrait être le chemin absolu (ou relatif) vers le fichier ou répertoire
* `[args]` : **seulement** pour l'option `<-f>` : précise quel meta tag on modifie.
    * `--title` : défini le tag "title" à `[text]`
    * `--subject` : défini le tag "subject" à `[text]`
* `[text]`: remplace le texte actuel par ce nouveau texte (attendu après chaque modificateur)

### Utilisation de la version graphique

La version graphique de l'application offre les mêmes possibilités, offrant en plus la modification des mots clés, ainsi que l'exploration d'un répertoire qui se fait via une arborescence n'affichant que les documents odt.\
Pour utiliser la version graphique, il est nécessaire de naviguer vers le répertoire où est situé le fichier `GUI.jar`. L'appel de ce dernier se fait par la commande : `java -jar GUI.jar`


## Auteurs

- [@Drag0nQQ aka Axel](https://github.com/Drag0nQQ)
- [@Raygunito aka Laurent](https://github.com/Raygunito)

![badge](https://img.shields.io/badge/Projet%20Java-Too%20easy-success)
