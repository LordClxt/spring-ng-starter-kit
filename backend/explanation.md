# Architecture de Sécurité JWT

Ce document explique le fonctionnement de l'authentification JWT dans votre application Spring Boot.

## 🔐 Principe Général

Le système fonctionne sur le principe du **"Stateless"** (sans état). Au lieu de créer une session côté serveur (comme avec les cookies `JSESSIONID` classiques), le serveur génère un **Token JWT** (JSON Web Token) que le client (Angular) doit stocker et renvoyer à chaque requête dans le header `Authorization`.

## 1. Les Classes de Configuration

### `SecurityConfig.java`

C'est le cerveau de la sécurité. Elle configure :

- **`SecurityFilterChain`** : Définit les règles de sécurité HTTP.
  - Désactive CSRF (inutile pour les API stateless).
  - Définit les routes publiques (`/auth/**`, `/api/home`) et protégées.
  - Configure la gestion de session sur `STATELESS`.
  - Ajoute le filtre JWT (`JwtAuthenticationFilter`) **avant** le filtre standard.
- **Beans d'authentification** :
  - `AuthenticationManager` : Le chef d'orchestre.
  - `AuthenticationProvider` : La logique de vérification.
  - `PasswordEncoder` : Pour hacher les mots de passe (BCrypt).

### `ApplicationConfig.java`

Contient les beans utilitaires comme `UserDetailsService`.

## 2. Les Interfaces et Implémentations Clés

### `UserDetails` (Interface Spring Security)

- **C'est quoi ?** : L'abstraction d'un utilisateur pour Spring Security.
- **Dans votre code** : Votre classe `User` implémente cette interface.
- **Rôle** : Permet à Spring Security de manipuler vos utilisateurs sans connaître votre modèle spécifique.

### `UserDetailsService` (Interface Spring Security)

- **C'est quoi ?** : Un service qui charge un utilisateur à partir de son nom d'utilisateur.
- **Implémentation** :
  ```java
  return username -> repository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  ```
- **Rôle** : Fait le pont entre Spring Security et votre base de données (`UserRepository`).

### `DaoAuthenticationProvider`

- **C'est quoi ?** : Le composant qui vérifie le mot de passe.
- **Fonctionnement** :
  1.  Reçoit une demande d'authentification.
  2.  Utilise `UserDetailsService` pour récupérer l'utilisateur.
  3.  Utilise `PasswordEncoder` pour comparer le mot de passe.

## 3. Le Filtre JWT (`JwtAuthenticationFilter`)

C'est le gardien de porte qui s'exécute à **chaque requête**.

1.  **Interception** : Vérifie le header `Authorization: Bearer <token>`.
2.  **Extraction & Validation** : Extrait et valide le token via `JwtService`.
3.  **Authentification** : Si le token est valide, il charge l'utilisateur et le place dans le `SecurityContextHolder`.

## 🔄 Résumé du Flux

1.  **Login** : L'utilisateur envoie ses identifiants.
2.  **Vérification** : `AuthenticationManager` valide via `DaoAuthenticationProvider`.
3.  **Token** : Un JWT est généré et renvoyé.
4.  **Requêtes suivantes** : Le client envoie le JWT.
5.  **Accès** : `JwtAuthenticationFilter` valide le token et autorise l'accès.
