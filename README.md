# 🚀 Keycloak + Spring MVC: CRUD Product avec Authentification

Ce projet vise a mettre en place un module module maven.Comme suit.
Créer un projet maven nomée commandes : projet parent
Le projet parent est un projet spring boot
Créer les modules maven suivants : services, web et start
services sera un projet qui gere le metier de l'application
web va gerer les controller
start est le projet de démarrage

---

## 📋 Table des matières
- [Objectif](#-objectif)
- [Prérequis](#-prérequis)
- [Installation](#-installation)
- [Lancement du projet](#-lancement-du-projet)
- [Captures d'écran](#-captures-décran)

---

## 🎯 Objectif
L'objectif de ce projet est de créer un module maven avec Springboot.

---

## 📦 Prérequis
Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- **Java 17** ou version supérieure 
- **Maven** pour la gestion des dépendances

---

## 🛠 Installation

### 1. Cloner le projet
Commencez par cloner le dépôt GitHub sur votre machine locale :
- **git clone https://github.com/sombregn/keycloak-_product_spring_mvc.git**

### 2. Accéder au répertoire du projet
Accédez au répertoire du projet cloné :
- **cd keycloak-_product_spring_mvc**

### 3. Démarrer les services avec Docker
Lancez les services nécessaires (Keycloak et l'application) avec Docker Compose :
- **docker compose up -d**

🚀 Lancement du projet
### 1. Build de l'application
Compilez et construisez l'application avec Maven :
-**mvn clean install**

### 2. Lancer l'application
Démarrez l'application Spring Boot :
- **mvn spring-boot:run**
- **Vous pouvez également lancer l'application directement depuis votre IDE en appuyant sur le bouton Run.**

### 🌐 URLs d'accès
Une fois l'application lancée, vous pouvez accéder aux services via les URLs suivantes :
- **Application Spring MVC : http://localhost:8092**
- **Console d'administration Keycloak : http://localhost:8181**

### 📸 Captures d'écran
Pour visualiser des captures d'écran de l'application et de Keycloak, consultez le dossier captures dans le projet.

### 📝 Remarque
Assurez-vous que Docker est bien installé et en cours d'exécution avant de lancer les commandes docker compose up -d. Si vous rencontrez des problèmes, vérifiez les logs Docker pour plus d'informations.
