
![Logo](https://github.com/Drag0nQQ/L2-Projet-Java/blob/main/readme_assets/logoMD.png?raw=true)


# Meta-Digger.io

Ce projet est réalisé par Axel OLIVEIRA et Laurent LIN, étudiants en L2-I à Cergy-Paris Université. Ce projet met en application les connaissances acquises dans le UE "Programmation orientée Objet".

## Introduction
Ce projet vise à la création d'une application bureautique sur les fichiers OpenDocument. L'intégralité du code est en Java.
Cette application offre les options suivantes :
* Scan d'un répertoire et affichage des fichiers OpenDocument présents.
* Affichage des métadonnées d'un document.
* Sauvegarde et modification des métadonnées d'un document.
  * Cache un message dans une image (Crée une nouvelle image au format PNG si l'image d'entrée est dans un autre format)
  * Retrouve un message caché dans une image par l'application


## Requirement
> Pour utiliser nos programmes, il faut au minima être sous Java 11 ou supérieur. Pour installer Java, veuillez-vous rendre [ici](https://www.oracle.com/java/technologies/downloads/).
## Documentation

### Utilisation de la version en ligne de commande
La version en ligne de commande doit être utilisée dans le terminal. Pour l'appeler, il suffit de naviguer vers le répertoire où se situe le fichier `CLI.jar`.\
Une fois dans le bon répertoire, l'appel se fait avec la commande suivante :  `java -jar CLI.jar`

Usage: `java -jar CLI.jar <option> [path] [args] [text]`
* `<option>` :
    * `-h` : affiche ce message d'aide
    * `-f` : précise que ce `[path]` est un fichier
    * `-d` : précise que ce `[path]` est un répertoire
* `[path]` : devrait être le chemin absolu (ou relatif) vers le fichier ou répertoire
* `[args]` : **seulement** pour l'option `<-f>` : précise quel meta tag on modifie.
    * `--title` : set title meta tag to `[text]`
    * `--subject` : set subject meta tag to `[text]`
* `[text]`: replace the current text with this new text (expected one after each modifiers)

### Utilisation de la version graphique

La version graphique de l'application offre les mêmes possibilités, à l'exception de l'exploration d'un répertoire qui se fait via un menu n'affichant que les images. Pour utiliser la version graphique, il est nécessaire de naviguer vers le répertoire ou est situé le fichier `gui.jar`. L'appel de ce dernier se fait par la commande : `java -jar gui.jar`


## Authors

- [@Drag0nQQ aka Axel](https://github.com/Drag0nQQ)
- [@Raygunito aka Laurent](https://github.com/Raygunito)

