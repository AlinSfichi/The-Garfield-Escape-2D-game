# 🎮 The Garfield Escape

## Descriere
Acest proiect este un joc 2D de tip labirint, realizat în Java, în care jucătorul controlează pisoiul Garfield. 
Scopul jocului este să colecteze un anumit număr de peștișori și să ajungă la finalul labirintului înainte ca timpul să expire, evitând obstacolele și inamicii.

## Caracteristici
- 🚀 Dezvoltare în **Java** folosind OOP
- 🎨 Grafică 2D simplă cu Java Swing
- 🎮 Control prin taste direcționale (↑ ↓ ← →)
- 🔄 Implementarea unei bucle de joc și a mai multor nivele
- 💾 Sistem de salvare și încărcare progres
- 🏆 High Score și sistem de dificultate progresivă

## Reguli joc
- Jucătorul trebuie să adune un număr minim de peștișori înainte de a putea avansa la nivelul următor.
- Evitarea capcanelor și a inamicului (Odie, cățelul care urmărește Garfield).
- Dacă timpul expiră sau Garfield este prins, nivelul trebuie reluat.
- Peștișorii adunați oferă **+3 secunde** bonus la cronometru.
- Capcanele (pânze de păianjen) încetinesc mișcarea jucătorului.

## Implementare
- **Design Pattern-uri utilizate:**
  - 🔹 **Singleton** – gestionarea nivelelor și a bazei de date
  - 🔹 **Abstract Factory** – fabricarea diferitelor stări ale jocului
  - 🔹 **Composite** – managementul entităților și obiectelor de pe hartă
- **Bază de date SQLite** pentru salvarea scorurilor și progresului jucătorului

## Autor
👤 **Alin-Ionuț Sfichi**  
📧 alinalinsfichi@gmail.com  

---
🚀 *Proiect creat pentru a demonstra cunoștințe în programarea Java, dezvoltarea jocurilor 2D și utilizarea design pattern-urilor.*
